/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Graphics;


/**
 *
 * @author Kevin
 */
public class DrawShapeCommand implements Command {
    private Shape shape;
    private Graphics g;
    public DrawShapeCommand(Shape shape, Graphics g){
        this.shape = shape;
        this.g = g;
    }
    
    public void execute()
    {
      shape.draw(g);
    }
}
