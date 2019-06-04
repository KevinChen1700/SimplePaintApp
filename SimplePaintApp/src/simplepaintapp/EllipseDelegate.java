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
public class EllipseDelegate implements Strategy {

    private static EllipseDelegate instance;

    private EllipseDelegate() {
    }

    public static EllipseDelegate getInstance() {
        if (instance == null) {
            instance = new EllipseDelegate();
        }
        return instance;
    }

    @Override
    public void draw(Graphics g, int x, int y, int w, int h) {
        g.drawOval(x, y, w, h);
    }

    @Override
    public String toString() {
        return "Ellipse";
    }
}
