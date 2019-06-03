/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class AddDecorator extends ObjectCommand {
    ArrayList<Decorator> decorators;
    Decorator decorator;
    public AddDecorator(ArrayList<Decorator> decorators, Decorator decorator  )
    {
     this.decorators = decorators;
     this.decorator = decorator;
    }
    
    public void execute()
    {
        decorators.add(decorator);
    }
    
    public void undo(){decorators.remove(decorator);}
    public void redo(){decorators.add(decorator);}
}
