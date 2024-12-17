package de.cargame.old.gui;

import javax.swing.*;

public class SimpleGUI extends JFrame {

    public SimpleGUI(){
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        setVisible(true);
    }

}
