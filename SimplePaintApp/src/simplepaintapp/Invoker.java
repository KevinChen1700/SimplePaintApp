/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class Invoker {
      private List<command> actionList = new ArrayList<command>(); 
      
      public void takeAction(command action){
      actionList.add(action);	
      action.execute();
   }

   public void placeAction(command action){
   
      //for (command action : actionList) {
         action.execute();
      //}
      //actionList.clear();
   }
   
   public void undo()
   {
       //laatste command in actionList hier verwijderen
   }
   
   public void redo()
   {
      //laatste command in actionList hier plakken

   }
}
