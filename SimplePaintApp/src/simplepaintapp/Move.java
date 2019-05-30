/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

/**
 *
 * @author Kevin
 */
public class Move implements Visitable {
    //code to get the values for move function
    
    //accept the visitor
    public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
