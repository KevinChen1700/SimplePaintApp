/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Color;
import java.awt.Graphics2D;

//command for drawing the selected objects
public class DrawSelectedCommand implements Command {

    private DrawAbleShape obj;
    private Graphics2D g;

    public DrawSelectedCommand(DrawAbleShape obj, Graphics2D g) {
        this.obj = obj;
        this.g = g;
    }

    public void execute() {
        g.setColor(Color.RED);
        obj.draw(g);
    }
}
