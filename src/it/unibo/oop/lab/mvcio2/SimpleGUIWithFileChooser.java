package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;
import it.unibo.oop.lab.mvcio.Controller.FileNotSettedException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame();
    private final Controller controller;
    /**
     * builds a new {@link SimpleGUI}.
     * @param c Controller object
     */
    public SimpleGUIWithFileChooser(final Controller c) {
        this.controller = c;
        JTextArea textArea = new JTextArea();
        JButton saveButton = new JButton("Save");
        JPanel canvas = new JPanel(new BorderLayout());
        frame.setContentPane(canvas);
        canvas.add(textArea, BorderLayout.CENTER);
        canvas.add(saveButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * handlers
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    controller.write(textArea.getText());
                } catch (IOException | FileNotSettedException e) {
                    e.printStackTrace();
                }
            }
        });
        //INIZIO NUOVO ES SimpleGUIWithFileChooser
        JTextField field1 = new JTextField();
        JButton browse = new JButton("Browse..");
        JPanel panel1 = new JPanel(new BorderLayout());
        canvas.add(panel1, BorderLayout.NORTH);
        panel1.add(field1, BorderLayout.CENTER);
        panel1.add(browse, BorderLayout.LINE_END);
        field1.setEnabled(false);
        try {
            field1.setText(this.controller.getFilePath());
        } catch (FileNotSettedException e) {
            field1.setText("file not selected yet");
        }
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JFileChooser filechooser = new JFileChooser();
                if (filechooser.showSaveDialog(browse) == JFileChooser.APPROVE_OPTION) {
                    try {
                        SimpleGUIWithFileChooser.this.controller.setFile(filechooser.getSelectedFile().getAbsolutePath());
                        field1.setText(controller.getFilePath());
                    } catch (FileNotFoundException | FileNotSettedException e1) {
                        new JOptionPane();
                        JOptionPane.showMessageDialog(browse, "Error occured");
                    }

                } 
            }
        });
    }
    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String...strings) {
        new SimpleGUIWithFileChooser(new Controller("/home/lux/lorenzo.txt")).display();
    }
}
