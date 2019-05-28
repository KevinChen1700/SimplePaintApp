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

    public static String action; //holds last pressed button
    public static GUI frame;
    private JPanel contentPane;
    public static ArrayList<DrawAbleShape> shapes = new ArrayList<DrawAbleShape>(); //ArrayList of all rectangles and ellipses made

    //main function that starts the program
    public static void main(String[] args) {
        frame = new GUI();
        frame.setVisible(true);
    }

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SimplePaintApp");
        setSize(800, 500);
        setMinimumSize(getSize());

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
                action = "Rectangle";
            }
        });
        panel.add(btnRect);

        JButton btnEllipse = new JButton("Ellipse");
        btnEllipse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                action = "Ellipse";
            }
        });
        panel.add(btnEllipse);

        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                action = "select";
            }
        });
        panel.add(btnSelect);

        JButton btnMove = new JButton("Move");
        btnMove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                action = "move";
            }
        });
        panel.add(btnMove);

        JButton btnResize = new JButton("Resize");
        btnResize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                action = "resize";
            }
        });
        panel.add(btnResize);

        //adds canvas below the buttons
        contentPane.add(new Canvas(), BorderLayout.CENTER);

    }

}
