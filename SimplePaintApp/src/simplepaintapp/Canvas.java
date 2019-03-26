/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

/**
 *
 * @author Sjimmie
 */
public class Canvas extends JComponent{
    public Point startDrag, endDrag;
    public Paint ptemp;
    public BasicStroke bstroke;
    
    public Canvas(){
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                if(GUI.action =="move"){
                    for(int i =GUI.paint.size()-1; i>=0;i=i-1)
                    {
                        Paint pt = GUI.paint.get(i);
                        if(pt.contains(startDrag))
                        {
                            ptemp=pt;
                            GUI.paint.remove(pt);
                        }
                    }
                    
                
                }
            
            }
            public void mouseReleased(MouseEvent e)
            {
                Point p = new Point(e.getX(), e.getY());
                if(GUI.action == "Rectangle"){
	        		MyRectangle obj = new MyRectangle();
	        		obj.makeObject(startDrag, p);
	    			GUI.paint.add(obj);
	        	}
                        startDrag = null;
	        	endDrag = null;
	        	repaint();
            }
        
        
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
	        public void mouseDragged(MouseEvent e) {
	        	endDrag = new Point(e.getX(), e.getY());
	        	repaint();
	        }
	      });
    
    }
        public void paint(Graphics g) {
		GraphicAdapter g2 = new GraphicAdapter() {
		};
		g2.setGraphicAdapter(g);
		//bstroke = new BasicStroke(40);
		//g2.getGraphicAdapter().setStroke(bstroke);
		
		for (Paint pt : GUI.paint){
			pt.draw(g2);
		}
		if (startDrag != null && endDrag != null) {
			if(GUI.action == "Rectangle"){
				MyRectangle obj = new MyRectangle();
				obj.makeObject(startDrag, endDrag);
				obj.draw(g2);
			
			
			}
		}
        }

               
    
}

