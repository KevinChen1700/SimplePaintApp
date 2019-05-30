/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

/**
 *
 * @author Sjimmie
 */
public class ChangeActionCommand implements Command {
    private String action;
    private Canvas canvas;
    public ChangeActionCommand(String action, Canvas canvas){
    this.action=action;
    this.canvas = canvas;
    }
    public void execute(){ canvas.setAction(action);}
    
}
