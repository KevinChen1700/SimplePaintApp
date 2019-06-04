package simplepaintapp;

import java.util.Stack;

public class Invoker {
   public static Stack<Command> undoStack = new Stack(); 
   public static Stack<Command> redoStack = new Stack(); 

    private Stack<Command> undoStack = new Stack();
    private Stack<Command> redoStack = new Stack();

    public void execute(Command action) {
        action.execute();
        if (action instanceof ObjectCommand) {
            undoStack.push(action);
        }
    }

    public void undo() {
        if (!(undoStack.isEmpty())) {
            Command command = undoStack.pop();
            ((ObjectCommand) command).undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            ((ObjectCommand) command).redo();
            undoStack.push(command);
        }
    }
}
