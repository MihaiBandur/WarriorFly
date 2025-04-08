package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        JFrame window =new JFrame ();//cream fereastra
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//inchidem fereastra pe x
        window.setResizable(true);// nu putem da resize la fereastra
        window.setTitle("Povestea lui Dil Doe !");

        GamePanel gamePanel= new GamePanel();
        window.add(gamePanel);


        window.pack();//ajusteaza la marimea preferata

        window.setLocationRelativeTo(null);//pune fereastra in mijlocul ecranului
        window.setVisible(true);//sa vedem fereastra



        gamePanel.startGameThread();

    }
}
