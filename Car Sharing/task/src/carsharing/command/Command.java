package carsharing.command;


public class Command {
    private final String name;
    private final CommandRunnable runnable;

    public Command(String name, CommandRunnable runnable) {
        this.name = name;
        this.runnable = runnable;
    }

    public String getName() {
        return name;
    }

    public void run() {
        runnable.run();
    }
}
