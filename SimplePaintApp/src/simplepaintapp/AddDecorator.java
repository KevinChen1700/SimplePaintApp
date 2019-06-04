package simplepaintapp;

import java.util.ArrayList;

//addDecorator class for making grouped texts for an object
public class AddDecorator extends ObjectCommand {
    ArrayList<Decorator> decorators;    //arraylist for grouped texts
    Decorator decorator;
    public AddDecorator(ArrayList<Decorator> decorators, Decorator decorator  )
    {
     this.decorators = decorators;
     this.decorator = decorator;
    }
    
    public void execute()
    {
        decorators.add(decorator);     //add the single text to the grouped text
    }
    
    
    //undo redo function for the texts
    public void undo(){decorators.remove(decorator);}
    public void redo(){decorators.add(decorator);}
}
