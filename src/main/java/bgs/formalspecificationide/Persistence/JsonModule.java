package bgs.formalspecificationide.Persistence;

import bgs.formalspecificationide.Factories.IModelFactory;
import bgs.formalspecificationide.Model.Project;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.inject.Inject;

import java.io.IOException;

class JsonModule extends SimpleModule {

    private final IModelFactory modelFactory;

    private class ProjectDeserializer extends JsonDeserializer<Project> {

        private final ObjectMapper objectMapper;

        public ProjectDeserializer(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public Project deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            var project = objectMapper.readValue(jsonParser, Project.class);

            if (project != null) {
                modelFactory.registerProject(project);
            }

            return project;
        }
    }

    @Inject
    public JsonModule(IModelFactory modelFactory, ObjectMapper objectMapper) {
        this.modelFactory = modelFactory;

        addDeserializer(Project.class, new ProjectDeserializer(objectMapper));
    }

}
