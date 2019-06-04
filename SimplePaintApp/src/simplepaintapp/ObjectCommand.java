package simplepaintapp;

public abstract class ObjectCommand implements Command {
    

    @Override
    public void execute() {
    }

    public abstract void undo();

    public abstract void redo();
}
