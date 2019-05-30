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

    private static GUI frame;
    private JPanel contentPane;
    private Canvas canvas;
    Invoker invoker = new Invoker();
    VisitorOperations visitor = new VisitorOperations();

    //main function that starts the program
    public static void main(String[] args) {
        frame = new GUI();
        frame.setVisible(true);
    }

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SimplePaintApp");
        setSize(1200, 500);
        setMinimumSize(getSize());
        
        canvas = new Canvas(invoker);

        //panel that holds both the buttons and the canvas
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        //panel for the buttons
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        //buttons 
        JButton btnRect = new JButton("Rectangle");
        btnRect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                canvas.setAction("Rectangle");
            }
        });
        panel.add(btnRect);

        JButton btnEllipse = new JButton("Ellipse");
        btnEllipse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                canvas.setAction("Ellipse");
            }
        });
        panel.add(btnEllipse);
        
        JButton btnEmptySpace = new JButton("");
        panel.add(btnEmptySpace);

        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                canvas.setAction("select");
            }
        });
        panel.add(btnSelect);

        JButton btnMove = new JButton("Move");
        btnMove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                canvas.setAction("move");
            }
        });
        panel.add(btnMove);

        JButton btnResize = new JButton("Resize");
        btnResize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                canvas.setAction("resize");
            }
        });
        panel.add(btnResize);
        
        JButton btnEmptySpace2 = new JButton("");
        panel.add(btnEmptySpace2);
        
        JButton btnUndo = new JButton("Undo");
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                invoker.undo();
                canvas.repaint();
            }
        });
        panel.add(btnUndo);
        
        JButton btnRedo = new JButton("Redo");
        btnRedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                invoker.redo();
                canvas.repaint();
            }
        });
        panel.add(btnRedo);
        
        JButton btnEmptySpace3 = new JButton("");
        panel.add(btnEmptySpace3);
        
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                VisitorSave saveFile = new VisitorSave(canvas.getShapes());
                visitor.visitSave(saveFile);
                
            }
        });
        panel.add(btnSave);
        
        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                LoadCommand loadcommand = new LoadCommand(canvas);
                invoker.execute(loadcommand);
            }
        });
        panel.add(btnLoad);

        //adds canvas below the buttons
        contentPane.add(canvas, BorderLayout.CENTER);

    }

}
