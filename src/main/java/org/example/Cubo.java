package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Cubo extends JLabel {
    private int width = 24;
    private int height = 24;
    private int currentX = 0;
    private int currentY = 0;
    private int velocityX = 0;
    private int velocityY = 0;
    private boolean isGoingLeft;
    private boolean isGoingRight;
    private boolean isInAir;
    private Image CuboSprites = null;
    private String jumpAudio = "C://Users//HP//IdeaProjects//CuboTheCubePlayableDemo//src//main//resources//SFX//jumpSFX.mp3";

    public boolean isGoingLeft() {
        return isGoingLeft;
    }

    public void setGoingLeft(boolean goingLeft) {
        isGoingLeft = goingLeft;
    }

    public boolean isGoingRight() {
        return isGoingRight;
    }

    public void setGoingRight(boolean goingRight) {
        isGoingRight = goingRight;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public Cubo() {
        try {
            CuboSprites = ImageIO.read(new File("C:/Users/HP/IdeaProjects/CuboTheCubePlayableDemo/src/main/resources/Images/cuboSprites.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new idleAnim().start();
        new idlePros().start();
        setSize(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(CuboSprites,currentX,currentY,this);
    }

    public void setPosition(int x, int y) {
        setBounds(x, y,width, height);
    }

    public void moveLeft(){
        new walkLeftPros().start();
        new walkLeftAnim().start();
    }

    public void moveRight(){
        new walkRightPros().start();
        new walkRightAnim().start();
    }

    public void jump(){
        if (!isInAir) {
            new audioPlayer().start();
            new jumpPros().start();
            new jumpAnim().start();
        }
    }

    class audioPlayer extends Thread{
        @Override
        public void run() {
            MP3Player mp3Player = new MP3Player();
            mp3Player.playMP3(jumpAudio);
        }
    }

    class idleAnim extends Thread{
        int count = 0;
        @Override
        public void run() {
            while (!isGoingRight&&!isGoingLeft&&!isInAir) {
                while (count < 6) {
                    count++;
                    if (count == 6) {
                        count = 0;
                        currentX = 0;
                    } else {
                        currentX -= width;
                        repaint();
                        try {
                            sleep(1000 / 6);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    class idlePros extends Thread{
        @Override
        public void run() {
            while (!isGoingRight&&!isGoingLeft&&!isInAir){
                if (velocityX<0){
                    velocityX+=5;
                    setPosition(getX()+velocityX,getY());
                } else if (velocityX>0) {
                    velocityX-=5;
                    setPosition(getX()-velocityX,getY());
                }
            }
        }
    }

    class walkLeftPros extends Thread{
        @Override
        public void run() {
            while (isGoingLeft){
                if (velocityX>-20){
                    velocityX-=5;
                }
                setPosition(getX()-velocityX,getY());
            }
        }
    }

    class walkRightPros extends Thread{
        @Override
        public void run() {
            while (isGoingRight){
                if (velocityX<20){
                    velocityX+=5;
                }
                setPosition(getX()+velocityX,getY());
            }
        }
    }

    class walkLeftAnim extends Thread{
        int count = 0;

        @Override
        public void run() {
            currentY = height*3;
            while (count<4){
                count++;
                if (count==4){
                    count = 0;
                    currentX = 0;
                }else {

                    currentX-=width;
                    repaint();
                    try {
                        sleep(1000/4);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }



    class walkRightAnim extends Thread{
        int count = 0;

        @Override
        public void run() {
            currentY = height * 2;
            while (count<4){
                count++;
                if (count==4){
                    count = 0;
                    currentX = 0;
                }else {
                    currentX-=width;
                    repaint();
                    try {
                        sleep(1000/4);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    class jumpPros extends Thread{
        boolean goingDown;
        @Override
        public void run() {
            if (velocityY>-10&&!goingDown){
                velocityY-=2;
                setPosition(getX(),getY()-velocityY);
            }else if (velocityY<0){
                goingDown = true;
                velocityY+=2;
                setPosition(getX(),getY()+velocityY);
            }
        }
    }

    class jumpAnim extends Thread{
        int count = 0;

        @Override
        public void run() {
            currentY = height*2;
            while (count<6){
                count++;
                if (count==6){
                    count = 0;
                    currentX = 0;
                }else {
                    currentX-=width;
                    repaint();
                    try {
                        sleep(1000/6);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
