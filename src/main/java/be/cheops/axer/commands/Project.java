package be.cheops.axer.commands;

import be.cheops.axer.commands.options.NameOption;
import org.crsh.cli.Command;
import org.crsh.cli.Named;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;

import java.util.UUID;

@Named("project")
public class Project extends BaseCommand {

    @Usage("Create project")
    @Command
    @Named("create")
    public Object create(@NameOption String name) {
        final ProjectModel model = new ProjectModel(name);
        return model;
    }

    private static class ProjectModel {
        private final UUID id = UUID.randomUUID();
        private final String name;

        public ProjectModel(String name) {
            this.name = name;
        }

        public String identifier() {
            return id.toString();
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Project{");
            sb.append("name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
