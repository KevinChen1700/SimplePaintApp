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
public class RectangleDelegate implements Strategy{
    private static RectangleDelegate instance;
    private RectangleDelegate(){}
    public static RectangleDelegate getInstance(){
        if(instance == null){
            instance  = new RectangleDelegate();
        }
        return instance;
    }

    @Override
    public void draw(Graphics g, int x, int y, int w, int h) {
        g.drawRect(x, y, w, h);
    }
    
    @Override
    public String toString(){
        return "Rectangle";
    }
    
}
