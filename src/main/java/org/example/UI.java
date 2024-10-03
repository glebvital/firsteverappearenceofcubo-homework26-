package org.example;

import org.example.Cubo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UI extends JFrame implements KeyListener {
    private Cubo cubo = new Cubo();
    public UI() {
        setLayout(null);


        cubo.setBounds(400, 200, cubo.getWidth(), cubo.getHeight());
        add(cubo);

        addKeyListener(this);
        setSize(800, 800);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                cubo.moveRight();
                cubo.setGoingRight(true);
                break;
            case KeyEvent.VK_LEFT:
                cubo.moveLeft();
                cubo.setGoingLeft(true);
                break;

            case KeyEvent.VK_SPACE:
                if (cubo.getVelocityY()==0){
                    cubo.jump();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                cubo.setGoingRight(false);
                break;
            case KeyEvent.VK_LEFT:
                cubo.setGoingLeft(false);
                break;
        }
    }
}