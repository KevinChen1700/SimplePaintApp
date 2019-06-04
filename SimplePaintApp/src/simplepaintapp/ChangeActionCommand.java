package simplepaintapp;

//Class to change the action string with the corresponding text ("Rectangle" and "Ellipse")
public class ChangeActionCommand implements Command {
    private String action;
    private Canvas canvas;
    
    public ChangeActionCommand(String action, Canvas canvas){
    this.action=action;
    this.canvas = canvas;
    }
    
    public void execute()
    { 
        canvas.setAction(action) ;
    }
    
}
