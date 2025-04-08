package Main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize =16;
    final int scale =3;//o sa fie 48 de pixeli un caracter

   public  final int tileSize= originalTileSize *scale;//48x48
    final int screenCol=16;
    final int screenRow=12;
    final int screenWidth=tileSize*screenCol;//768 pixeli
    final int screenHeight=tileSize*screenRow;//576 pixeli
    //o sa fie 768x576

    //FPS
    int FPS=60;

    //pt WASD
    KeyHandler keyH=new KeyHandler();

    //game clock
    Thread gameThread;
    Player player= new Player(this,keyH);

    int playerX=100;
    int playerY=100;
    int playerSpeed=4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));//seteaza marimea clasei
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);//aduce o performanta mai buna rendering ului
        this.addKeyListener(keyH);
        this.setFocusable(true);//se focuseaza pentru a primi key input
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval=1000000000/FPS;//pentru intervalul in care se va da update la desen
        //se comporta ca un sleep (0.016666 seconds)

        double nextDrawTime=System.nanoTime() +drawInterval;

        while(gameThread !=null)
        {



            //dam update la diferite informatii cum ar fi harta sau carcaterul
            //la player ne miscam pe axele x si y cand apasam pe o anumita tasta
            update();
            repaint();


            //il facem sa doarma pe timpul ramas dintre urmatorul desen si cel curent

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime=remainingTime/1000000;//il convertim din nanosecunde in milisecunde din cauza lui thread.sleep care accepta miliseconds

                if(remainingTime<0)
                {
                    remainingTime=0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }


    }
    public void update(){
        player.update();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2=(Graphics2D)g; //schimbam grafica pentru ca grafica 2d are mai multe functii

       player.draw(g2);

        g2.dispose();//e bun pt niste salvare a memoriei
    }


}
