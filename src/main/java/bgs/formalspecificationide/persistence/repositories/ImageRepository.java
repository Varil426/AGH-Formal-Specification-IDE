package bgs.formalspecificationide.persistence.repositories;


import bgs.formalspecificationide.model.Image;
import bgs.formalspecificationide.persistence.IPersistenceHelper;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

class ImageRepository implements IImageRepository {

    private final Set<Image> images;
    private final IPersistenceHelper persistenceHelper;

    @Inject
    public ImageRepository(IPersistenceHelper persistenceHelper) {
        this.persistenceHelper = persistenceHelper;
        images = new HashSet<>();
    }

    @Override
    public void add(@NotNull Image item) {
        if (images.stream().anyMatch(x -> x.getId() == item.getId()))
            return;
        var newFile = persistenceHelper.saveImage(item.getImageFile(), item.getId());
        newFile.ifPresent(item::setImageFile);

        images.add(item);
    }

    @Override
    public List<Image> getAll() {
        var imageFiles = persistenceHelper.getAllImageFiles().stream().filter(x -> {
            // Checks whether image is already loaded
            var fileName = IPersistenceHelper.getFileNameWithoutExtension(x);
            var id = UUID.fromString(fileName);
            return images.stream().noneMatch(z -> z.getId() == id);
        }).toList();

        for (var imageFile : imageFiles) {
            var id = UUID.fromString(IPersistenceHelper.getFileNameWithoutExtension(imageFile));
            var newImage = new Image(id, imageFile);
            images.add(newImage);
        }

        return images.stream().toList();
    }

    @Override
    public List<Image> get(Predicate<Image> predicate) {
        return getAll().stream().filter(predicate).toList();
    }

    @Override
    public void remove(@NotNull Image item) {
        persistenceHelper.removeFile(item.getImageFile());
        images.remove(item);
    }

    @Override
    public void saveAll() {
        // Ignore
    }

    @Override
    public void save(@NotNull Image item) {
        // Ignore
    }
}
