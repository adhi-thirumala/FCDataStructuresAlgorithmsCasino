import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MasterFrame extends JFrame{
    private Player gambler;
    public MasterFrame(){
        gambler = new Player();
        ImageIcon img = new ImageIcon("src/main/resources/icon.jpg");
        setIconImage(img.getImage());
        StartingScreen ss = new StartingScreen();
        GameChooser gc = new GameChooser();
        setContentPane(ss.getHomePanel());
        setTitle("Java Jackpot Fox Den Casino");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        ss.getStartGameButton().addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                gambler.setName(ss.getName());
                gc.getCurrentMoney().setText(STR."Current money: $\{gambler.getMoney()}");
                ss.getHomePanel().setVisible(false);
                setContentPane(gc.getPanel());
            }
        });
    }




}
