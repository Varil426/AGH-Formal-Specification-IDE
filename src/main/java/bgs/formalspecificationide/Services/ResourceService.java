package bgs.formalspecificationide.Services;

import bgs.formalspecificationide.Exceptions.KeyNotFoundException;
import bgs.formalspecificationide.Exceptions.ResourceNotFoundException;
import com.google.inject.Inject;
import org.apache.logging.log4j.core.util.IOUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.Hashtable;

public class ResourceService implements IResourceService {

    @SuppressWarnings("FieldCanBeLocal")
    private final String textsFile = "/bgs/formalspecificationide/Texts/texts.json";

    private final Dictionary<String, String> textsDictionary = new Hashtable<>();
    private final LoggerService loggerService;

    @Inject
    ResourceService(LoggerService loggerService) throws ResourceNotFoundException {
        this.loggerService = loggerService;
        loadTexts();
    }

    @Override
    public String getText(String name) throws KeyNotFoundException {
        var value = textsDictionary.get(name);
        if (value == null) throw new KeyNotFoundException();
        return value;
    }

    private void loadTexts() throws ResourceNotFoundException {
        var textsStream = getClass().getResourceAsStream(textsFile);
        if (textsStream == null) throw new ResourceNotFoundException(textsFile);

        loggerService.logDebug("Loaded resource \"Texts\"");

        var stringBuilder = new StringBuilder();
        try (var reader = new BufferedReader(new InputStreamReader(textsStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            loggerService.logDebug("Couldn't read resource \"Texts\"");
        }

        var jsonObject = new JSONObject(stringBuilder.toString());
        jsonObject.keys().forEachRemaining(key -> textsDictionary.put(key, jsonObject.getString(key)));
    }
}
