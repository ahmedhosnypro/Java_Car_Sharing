package carsharing.command;

public abstract class CliCommand {
    String name;

    public abstract void run();

    public String getName() {
        return name;
    }
}
