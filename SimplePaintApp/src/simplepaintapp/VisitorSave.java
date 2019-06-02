/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class VisitorSave implements Visitable {
    private ArrayList<Shape> shapes;
    
    public VisitorSave(ArrayList<Shape> shapes){
        this.shapes = shapes;
    }
        //accept the visitor
    public void accept(Visitor visitor)
    {
        visitor.visitSave(this);
    }
    public ArrayList<Shape> getShapes(){
        return shapes;
    }
}
