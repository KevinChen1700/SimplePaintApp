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
public interface Decorator {
    public void draw(Graphics g);
    public void move(int x, int y);
}
