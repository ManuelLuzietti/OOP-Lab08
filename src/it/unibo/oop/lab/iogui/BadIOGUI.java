package it.unibo.oop.lab.iogui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.nio.file.Files;

/**
 * This class is a simple application that writes a random number on a file.
 * 
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class BadIOGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final String PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + BadIOGUI.class.getSimpleName() + ".txt";
    private static final int PROPORTION = 5;
    private final Random rng = new Random();
    private final JFrame frame = new JFrame(TITLE);
    private final File f1 = new File(PATH);
    /**
     * 
     */
    //    public BadIOGUI() {
    //        final JPanel canvas = new JPanel();
    //        canvas.setLayout(new BorderLayout());
    //        final JButton write = new JButton("Write on file");
    //        canvas.add(write, BorderLayout.CENTER);
    //        frame.setContentPane(canvas);
    //        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //        /*
    //         * Handlers
    //         */
    //        write.addActionListener(new ActionListener() {
    //            @Override
    //            public void actionPerformed(final ActionEvent e) {
    //                /*
    //                 * This would be VERY BAD in a real application.
    //                 * 
    //                 * This makes the Event Dispatch Thread (EDT) work on an I/O
    //                 * operation. I/O operations may take a long time, during which
    //                 * your UI becomes completely unresponsive.
    //                 */
    //                try (PrintStream ps = new PrintStream(PATH)) {
    //                    ps.print(rng.nextInt());
    //                } catch (FileNotFoundException e1) {
    //                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
    //                    e1.printStackTrace();
    //                }
    //            }
    //        });
    //    }
    public BadIOGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        BoxLayout box = new BoxLayout(panel1, BoxLayout.LINE_AXIS);
        panel1.setLayout(box);
        canvas.add(panel1, BorderLayout.CENTER);
        JButton button1 = new JButton("Write");
        panel1.add(button1);
        JTextField text = new JTextField();
        canvas.add(text, BorderLayout.NORTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button2 = new JButton("Read");
        panel1.add(button2);
        /*
         * setto stesso file di lettura scrittura di read write
         */
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        /*
         * Handlers:
         */

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String output =  rng.nextInt() + " ";
                text.setText(output);
                try (PrintStream s = new PrintStream(f1)) {
                    s.append(output);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } 
//                DataOutputStream str ;
//                try {
//                    str = new DataOutputStream(new FileOutputStream(f1));
//                    str.writeChar(81);
//                } catch (IOException el) {
//                    el.getStackTrace();
//                } 
            }
        });
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                String s;
//                //                try {
//                //                    s = Files.readString(f1.toPath());
//                //                    System.out.println(s);
//                //                } catch (IOException e) {
//                //                    e.printStackTrace();
//                //                }
                DataInputStream se;
                try {
                    se = new DataInputStream(new FileInputStream(f1));
                    System.out.println(se.readLine());
                    se.close();
                } catch(IOException e) {
                    e.getStackTrace();
                } 
            }
        });
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
        new BadIOGUI().display();
    }
}
