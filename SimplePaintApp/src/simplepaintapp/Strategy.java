/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.Graphics;

/**
 *
 * @author Sjimmie
 */
public interface Strategy {
    public void draw(Graphics g, int x, int y, int w, int h);
    public String toString();
    
}
