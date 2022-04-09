package bgs.formalspecificationide.Services;

import bgs.formalspecificationide.Exceptions.KeyNotFoundException;
import bgs.formalspecificationide.Exceptions.ResourceNotFoundException;
import com.google.inject.Inject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.Hashtable;

public class ResourceService implements IResourceService {

    final String textsFile = "/bgs/formalspecificationide/Texts/texts.json";

    final Dictionary<String, String> textsDictionary = new Hashtable<>();

    @Inject
    ResourceService() throws ResourceNotFoundException {
        LoadTexts();
    }

    @Override
    public String getText(String name) throws KeyNotFoundException {
        var value = textsDictionary.get(name);
        if (value == null) throw new KeyNotFoundException();
        return value;
    }

    private void LoadTexts() throws ResourceNotFoundException {
        var textsStream = getClass().getResourceAsStream(textsFile);
        if (textsStream == null) throw new ResourceNotFoundException(textsFile);

        var stringBuilder = new StringBuilder();
        try (var reader = new BufferedReader(new InputStreamReader(textsStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        var jsonObject = new JSONObject(stringBuilder.toString());
        jsonObject.keys().forEachRemaining(key -> textsDictionary.put(key, jsonObject.getString(key)));
    }
}
