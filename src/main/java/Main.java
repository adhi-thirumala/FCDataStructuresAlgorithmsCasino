import javax.swing.*;
import java.awt.*;


public class Main extends JFrame{
    public Main(){
        ImageIcon img = new ImageIcon("src/main/resources/icon.jpg");
        setIconImage(img.getImage());
        StartingScreen ss = new StartingScreen();
        setContentPane(ss);
        setTitle("Java Jackpot Fox Den Casino");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }


    public static void main(String[] args){
        new Main();
    }
}
