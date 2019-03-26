/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplepaintapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GUI extends JFrame {

    public static String action;
    public static GUI frame;  
    private JPanel contentPane;
    public static ArrayList<Paint> paint = new ArrayList<Paint>();
    
    public static void main(String[] args) {
        frame = new GUI();
    	frame.setVisible(true);
    }
    public GUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SimplePaintApp");
		setSize(800, 500);
		setMinimumSize(getSize());
                
                contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
                
                JButton btnRect = new JButton("Rectangle");
		btnRect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				action = "Rectangle";
			}
		});
                panel.add(btnRect);
                
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				action = "move";
			}
		});
		panel.add(btnMove);
		
		contentPane.add(new Canvas(), BorderLayout.CENTER);
                
               
    
    
    
    }
    
}
