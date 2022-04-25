package bgs.formalspecificationide.Persistence;

import bgs.formalspecificationide.Factories.IModelFactory;
import bgs.formalspecificationide.Model.ModelAggregate;
import bgs.formalspecificationide.Model.ModelBase;
import bgs.formalspecificationide.Model.Project;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.inject.Inject;

import java.io.IOException;

class JsonModule extends SimpleModule {

    private final IModelFactory modelFactory;

    private abstract static class BaseDeserializer<T extends ModelBase> extends JsonDeserializer<T> {

        private final ObjectMapper objectMapper;

        protected BaseDeserializer(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        protected ObjectMapper getObjectMapper() {
            return objectMapper;
        }

        protected void observeChildren(ModelBase modelBase) {
            if (modelBase instanceof ModelAggregate modelAggregate) {
                for (var child : modelAggregate.getChildren()) {
                    child.subscribe(modelAggregate);

                    observeChildren(child);
                }
            }
        }

    }

    private class ProjectDeserializer extends BaseDeserializer<Project> {

        public ProjectDeserializer(ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override
        public Project deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            var project = getObjectMapper().readValue(jsonParser, Project.class);

            if (project != null) {
                modelFactory.registerProject(project);
                observeChildren(project);
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
