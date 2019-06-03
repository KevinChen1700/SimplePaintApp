/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Color;
import static simplepaintapp.Canvas.endDrag;
import static simplepaintapp.Canvas.g;
import static simplepaintapp.Canvas.ptemp;
import static simplepaintapp.Canvas.startDrag;

/**
 *
 * @author Kevin
 */
public class DrawResizeCommand implements Command {
    
    public void execute()
    {
       g.setColor(Color.RED);
       int w = endDrag.x - startDrag.x;
       int h = endDrag.y - startDrag.y;
       ptemp.drawExpand(g, w, h);
    }
}