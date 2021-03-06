/**
 * 
 */
package it.unibo.oop.lab.simplegui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class is a simple application that writes a random number on a file.
 * 
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class MiniGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final int PROPORTION = 5;
    private final Random rng = new Random();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * 
     */
    public MiniGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        //es1
        JPanel panel1 = new JPanel();
        LayoutManager box = new BoxLayout(panel1, BoxLayout.LINE_AXIS);
        panel1.setLayout(box);
        canvas.add(panel1, BorderLayout.CENTER);
        JButton button1 = new JButton("Button1");
        panel1.add(button1);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField result = new JTextField();
        canvas.add(result, BorderLayout.NORTH);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int output = rng.nextInt();
                System.out.println(output);
                result.setText(output + "");
            }
        });
        //final JButton write = new JButton("Print a random number on standard output");
        //        canvas.add(write, BorderLayout.CENTER);
        //        frame.setContentPane(canvas);
        //        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //        /*
        //         * Handlers
        //         */
        //        write.addActionListener(new ActionListener() {
        //            @Override
        //            public void actionPerformed(final ActionEvent e) {
        //                System.out.println(rng.nextInt());
        //            }
//        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new MiniGUI().display();
//       JPanel panel2 = new JPanel();
//       BoxLayout lay = new BoxLayout(panel2, BoxLayout.X_AXIS);
//       panel2.setLayout(lay);
//       JPanel panel1 = new JPanel();
//       BorderLayout e = new BorderLayout();
//       panel1.setLayout(e);
//       e.addLayoutComponent(panel1, BorderLayout.CENTER);
//       JButton button1 = new JButton("button1");
//       panel1.add(button1);
//       JFrame frame = new JFrame();
//       final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//       int sw = (int) screen.getWidth();
//       int sh = (int) screen.getHeight();
//       frame.setSize(sw / PROPORTION, sh / PROPORTION);
//       panel2.add(panel1);
//       JButton button2 = new JButton("hey");
//       panel2.add(button2);
//       frame.getContentPane().add(panel2);
//       //frame.getContentPane().add(panel1);
//       frame.setVisible(true);
    }

}
