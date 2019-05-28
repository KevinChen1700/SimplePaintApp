/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import static simplepaintapp.Canvas.pt;
import static simplepaintapp.Canvas.g;


/**
 *
 * @author Kevin
 */
public class drawShapesCommand implements command {
    
    public void execute()
    {
      pt.draw(g);
    }
}
