/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumptosurvive;

import javax.swing.JFrame;

/**
 *
 * @author ASUS
 */
public class JumpToSurvive extends JFrame {

    public JumpToSurvive() {
        initUI();
    }

    private void initUI() {
        add(new Tablero());
        //800 w y 500 h
        setSize(800, 500);
        setTitle("Jump To Survive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        JumpToSurvive jtp = new JumpToSurvive();
    }

}
