package simplepaintapp;

import java.util.Stack;

//invoker class with execute, undo and redo functionality
public class Invoker {

    public static Stack<Command> undoStack = new Stack();
    public static Stack<Command> redoStack = new Stack();

    public void execute(Command action) {
        action.execute();

        //only push into undoStack if the command is an extension of ObjectCommand
        if (action instanceof ObjectCommand) {
            undoStack.push(action);
        }
    }

    public void undo() {
        if (!(undoStack.isEmpty())) {
            Command command = undoStack.pop();
            ((ObjectCommand) command).undo();
            redoStack.push(command);  //push the popped command from undostack into redostack for redo functionality
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            ((ObjectCommand) command).redo();
            undoStack.push(command);   //push the popped command from redostack into undostack for undo functionality
        }
    }
}
