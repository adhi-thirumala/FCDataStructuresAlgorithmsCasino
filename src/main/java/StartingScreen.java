import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartingScreen extends JPanel{
    private JPanel homePanel;

    private JLabel title;
    private JLabel question;
    private JTextField nameInput;
    private JButton startGameButton;
    private String name;


    public StartingScreen(){
        setVisible(true);
        nameInput.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * Saves the name input to a string.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(nameInput.getText(), ""))
                    name = "Adhi";
                else
                    name = nameInput.getText();
                startGameButton.setEnabled(true);
            }
        });
    }

    public JButton getStartGameButton() {
        return startGameButton;
    }

    public JPanel getHomePanel() {
        return homePanel;
    }

    @Override
    public String getName() {
        return name;
    }
}
