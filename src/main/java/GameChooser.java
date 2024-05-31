import javax.swing.*;

public class GameChooser extends JPanel{
    private JPanel playPanel;
    private JButton crapsButton;
    private JButton rouletteButton;
    private JButton baccaratButton;
    private JButton scoreboardButton;
    private JLabel currentMoney;
    private JButton blackjackButton;
    private JPanel moneyAndExit;
    private JButton endGame;

    public GameChooser(){
        setVisible(false);
    }


    public JLabel getCurrentMoney() {
        return currentMoney;
    }

    public JPanel getPanel() {
        return playPanel;
    }

    public JButton getEndGame() {
        return endGame;
    }

    public JButton getScoreboardButton() {
        return scoreboardButton;
    }

    public JButton getCrapsButton() {
        return crapsButton;
    }


    public JButton getRouletteButton() {
        return rouletteButton;
    }


    public JButton getBaccaratButton() {
        return baccaratButton;
    }


    public JButton getBlackjackButton() {
        return blackjackButton;
    }


    public JPanel getMoneyAndExit() {
        return moneyAndExit;
    }

}



