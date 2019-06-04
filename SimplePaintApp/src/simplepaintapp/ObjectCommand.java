package simplepaintapp;

//give execute, undo and redo functions for the object
public abstract class ObjectCommand implements Command {
    
    @Override
    public void execute() {}

    public abstract void undo();
    
    public abstract void redo();
}
