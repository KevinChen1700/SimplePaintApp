package com.ssaurel.swingpaint;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.*;

public class SwingPaint {

    JButton ellipseBtn, rectBtn; 
    DrawArea drawArea;
    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ellipseBtn) {
                drawArea.ellipse();
            } else if (e.getSource() == rectBtn) {
                drawArea.rect();
            }
        }
    };

    public static void main(String[] args) {
        new SwingPaint().show();
    }

    public void show() {
        // create main frame
        JFrame frame = new JFrame("Swing Paint");
        Container content = frame.getContentPane();
        // set layout on content pane
        content.setLayout(new BorderLayout());
        // create draw area
        drawArea = new DrawArea();

        // add to content pane
        content.add(drawArea, BorderLayout.CENTER);

        // create controls to apply colors and call clear feature
        JPanel controls = new JPanel();

        rectBtn = new JButton("Rectangle");
        rectBtn.addActionListener(actionListener);
        ellipseBtn = new JButton("Ellipse");
        ellipseBtn.addActionListener(actionListener);

        rectBtn.setSelected(true);



        // add to panel
        controls.add(rectBtn);
        controls.add(ellipseBtn);


        // add to content pane
        content.add(controls, BorderLayout.NORTH);

        frame.setSize(800, 600);
        // can close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // show the swing paint result
        frame.setVisible(true);

        // Now you can try our Swing Paint !!! Enjoy <img draggable="false" class="emoji" alt="😀" src="https://s.w.org/images/core/emoji/11/svg/1f600.svg">
    }

}
