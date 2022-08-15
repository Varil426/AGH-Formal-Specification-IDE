package bgs.formalspecificationide.model;

import java.io.File;
import java.util.UUID;

public class Image {

    private final UUID id;

    private File imageFile;

    public Image(UUID id, File imageFile) {
        this.id = id;
        this.imageFile = imageFile;
    }

    public UUID getId() {
        return id;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

}
