import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
public class BaccaratFrame extends JFrame {

    private JPanel gamePanel;
    private JTextField betField;
    private JButton houseBet;
    private JPanel baccPanel;
    private JButton playerBet;
    private JButton tieBet;
    private JLabel lastCard;
    private JLabel currentBet;
    private JLabel playerHand;
    private JPanel handPanel;
    private JLabel dealerHandValue;
    private JPanel lastCardPanel;
    private JPanel playerHandPanel;
    private JPanel dealerHandPanel;
    private JLabel playerHandValue;
    private JLabel dealerHand;
    private JPanel betPanel;
    private JLabel betType;
    private JPanel actionPanel;
    private JLabel actionLabel;
    private JLabel winnerLabel;
    private JPanel winnerPanel;
    private final Deck gameDeck;
    private int betAmount;

    private final String LAST_CARD_INITIAL_VALUE = lastCard.getText();
    private final String CURRENT_BET_INITIAL_VALUE = currentBet.getText();
    private final String PLAYER_HAND_INITIAL_VALUE = playerHand.getText();
    private final String DEALER_HAND_INITIAL_VALUE = dealerHand.getText();
    private final String PLAYER_HAND_VALUE_INITIAL_VALUE = playerHandValue.getText();
    private final String DEALER_HAND_VALUE_INITIAL_VALUE = dealerHandValue.getText();
    private final String BET_TYPE_INITIAL_VALUE = betType.getText();
    private final String ACTION_LABEL_INITIAL_VALUE = actionLabel.getText();

    public BaccaratFrame(){
        gameDeck = new Deck(6);
        setIconImage(MasterFrame.ICON_IMAGE);
        setTitle("Punto Banco Baccarat");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(gamePanel);

        betField.addActionListener(_ -> {
            try{
                if(BigDecimal.valueOf(Long.parseLong(betField.getText())).compareTo(MasterFrame.gambler.getMoney()) > 0) {
                    JOptionPane.showMessageDialog(null, STR."That's too big of a bet. You can't bet more money than you have! \n Your current balance is $\{MasterFrame.gambler.getMoney()}");
                    betField.setText("");
                    return;
                }
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, STR."That's not a number! Make sure to input one. \n Your current balance is $\{MasterFrame.gambler.getMoney()}");
                betField.setText("");
                return;
            }
            betAmount = Integer.parseInt(betField.getText());
            MasterFrame.gambler.setMoney(MasterFrame.gambler.getMoney().subtract(BigDecimal.valueOf(betAmount)));
            JOptionPane.showMessageDialog(null, STR."You made a bet! \n Your current balance is $\{MasterFrame.gambler.getMoney()}");
            betField.setText("");
            betField.setEnabled(false);
            betField.setToolTipText("You've already made a bet.");
            houseBet.setEnabled(true);
            playerBet.setEnabled(true);
            tieBet.setEnabled(true);
            currentBet.setText(STR."\{CURRENT_BET_INITIAL_VALUE} $\{betAmount}");
        });

        playerBet.addActionListener(_ -> {
            playerBet.setEnabled(false);
            houseBet.setEnabled(false);
            tieBet.setEnabled(false);
            betType.setText(STR."\{BET_TYPE_INITIAL_VALUE} Player");
            try {
                int result = play();
                if(result == 1) {
                    winnerLabel.setText("Player Hand Wins.");
                    MasterFrame.gambler.setMoney(MasterFrame.gambler.getMoney().add(BigDecimal.valueOf(betAmount).multiply(BigDecimal.valueOf(2))));
                    JOptionPane.showMessageDialog(null, STR."""
You Win! Money increased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
                else if(result == 0) {
                    winnerLabel.setText("Hands Tie.");
                    JOptionPane.showMessageDialog(null, STR."""
You Lose! Money decreased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
                else{
                    winnerLabel.setText("Dealer Hand Wins.");
                    JOptionPane.showMessageDialog(null, STR."""
You Lose! Money decreased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            refresh();
        });
        houseBet.addActionListener(_ -> {
            playerBet.setEnabled(false);
            houseBet.setEnabled(false);
            tieBet.setEnabled(false);
            betType.setText(STR."\{BET_TYPE_INITIAL_VALUE} House");
            try {
                int result = play();
                if(result == -1) {
                    winnerLabel.setText("Dealer Hand Wins.");
                    MasterFrame.gambler.setMoney(MasterFrame.gambler.getMoney().add(BigDecimal.valueOf(betAmount).multiply(BigDecimal.valueOf(1.95))));
                    JOptionPane.showMessageDialog(null, STR."""
You Win! Money increased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
                else if(result == 0) {
                    winnerLabel.setText("Hands Tie.");
                    JOptionPane.showMessageDialog(null, STR."""
You Lose! Money decreased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
                else{
                    winnerLabel.setText("Player Hand Wins.");
                    JOptionPane.showMessageDialog(null, STR."""
You Lose! Money decreased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            refresh();
        });
        tieBet.addActionListener(_ -> {
            playerBet.setEnabled(false);
            houseBet.setEnabled(false);
            tieBet.setEnabled(false);

            betType.setText(STR."\{BET_TYPE_INITIAL_VALUE} Tie");
            try {
                int result = play();
                if(result == 1) {
                    winnerLabel.setText("Player Hand Wins.");
                    MasterFrame.gambler.setMoney(MasterFrame.gambler.getMoney().add(BigDecimal.valueOf(betAmount).multiply(BigDecimal.valueOf(8))));
                    JOptionPane.showMessageDialog(null, STR."""
You Win! Money increased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
                else if(result == 0) {
                    winnerLabel.setText("Hands Tie.");
                    JOptionPane.showMessageDialog(null, STR."""
You Lose! Money decreased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
                else{
                    winnerLabel.setText("Dealer Hand Wins.");
                    JOptionPane.showMessageDialog(null, STR."""
You Lose! Money decreased by $\{betAmount}
Current money: $\{MasterFrame.gambler.getMoney()}""");
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            refresh();
        });
    }
    /**
     * Returns -1 if the dealer wins, 0 if tie, and 1 if the player wins.
     */
    @SuppressWarnings("DuplicatedCode")
    private int play() throws InterruptedException {
        Card[] playerHandCards = new Card[3];
        Card[] dealerHandCards = new Card[3];
        lastCard.setText(STR."\{LAST_CARD_INITIAL_VALUE} \{gameDeck.draw()}");
        actionLabel.setText(STR."\{ACTION_LABEL_INITIAL_VALUE} Burning Card");
        JOptionPane.showMessageDialog(null, "Click here for next step.");
        for(int i = 0; i < 2; i ++){
            actionLabel.setText(STR."\{ACTION_LABEL_INITIAL_VALUE} Drawing Player Card");
            playerHandCards[i] = gameDeck.draw();
            lastCard.setText(STR."\{LAST_CARD_INITIAL_VALUE} \{playerHandCards[i]}");
            playerHand.setText(playerHand.getText() + playerHandCards[i]);
            playerHandValue.setText(STR."\{PLAYER_HAND_VALUE_INITIAL_VALUE}\{handToValue(playerHandCards)}");
            JOptionPane.showMessageDialog(null, "Click here for next step.");
            actionLabel.setText(STR."\{ACTION_LABEL_INITIAL_VALUE} Drawing Dealer Card");
            dealerHandCards[i] = gameDeck.draw();
            lastCard.setText(STR."\{LAST_CARD_INITIAL_VALUE} \{dealerHandCards[i]}");
            dealerHand.setText(STR."\{dealerHand.getText()} \{dealerHandCards[i]}");
            dealerHandValue.setText(STR."\{DEALER_HAND_VALUE_INITIAL_VALUE}\{handToValue(dealerHandCards)}");
            JOptionPane.showMessageDialog(null, "Click here for next step.");
        }
        byte playerHandScore = handToValue(playerHandCards);
        byte dealerHandScore = handToValue(dealerHandCards);
        if((playerHandScore == 9 && dealerHandScore < 9) || (playerHandScore == 8 && dealerHandScore < 8))
            return 1;
        if((dealerHandScore == 9 && playerHandScore < 9) || (dealerHandScore == 8 && playerHandScore < 8))
            return -1;
        if((playerHandScore == 9 && dealerHandScore == 9) || (playerHandScore == 8 && dealerHandScore == 8))
            return 0;
        if(playerHandScore <= 5){
            actionLabel.setText(STR."\{ACTION_LABEL_INITIAL_VALUE} Drawing Third Player Card");
            playerHandCards[2] = gameDeck.draw();
            lastCard.setText(STR."\{LAST_CARD_INITIAL_VALUE} \{playerHandCards[2]}");
            playerHand.setText(playerHand.getText() + playerHandCards[2]);
            playerHandScore = handToValue(playerHandCards);
            playerHandValue.setText(STR."\{PLAYER_HAND_VALUE_INITIAL_VALUE} \{playerHandScore}");
            JOptionPane.showMessageDialog(null, "Click here for next step.");
            if((dealerHandScore <= 2) ||
                    ((dealerHandScore == 3) &&
                            (playerHandCards[2].getNumber() != 8)) ||
                    ((dealerHandScore == 4) &&
                            ((playerHandCards[2].getNumber() == 2) || (playerHandCards[2].getNumber() == 3) || (playerHandCards[2].getNumber() == 4) || (playerHandCards[2].getNumber() == 5) || (playerHandCards[2].getNumber() == 6) || (playerHandCards[2].getNumber() == 7)))
                    || (dealerHandScore == 5 &&
                        ((playerHandCards[2].getNumber() == 4) || (playerHandCards[2].getNumber() == 5) || (playerHandCards[2].getNumber() == 6) || (playerHandCards[2].getNumber() == 7)))
                    || ((dealerHandScore == 6) &&
                        ((playerHandCards[2].getNumber() == 6) || (playerHandCards[2].getNumber() == 7)))){
                actionLabel.setText(STR."\{ACTION_LABEL_INITIAL_VALUE} Drawing Third Dealer Card");
                dealerHandCards[2] = gameDeck.draw();
                lastCard.setText(STR."\{LAST_CARD_INITIAL_VALUE} \{dealerHandCards[2]}");
                dealerHandValue.setText(STR."\{dealerHand.getText()} \{dealerHandCards[2]}");
                dealerHandScore = handToValue(dealerHandCards);
                dealerHandValue.setText(STR."\{DEALER_HAND_VALUE_INITIAL_VALUE}\{dealerHandScore}");
                JOptionPane.showMessageDialog(null, "Click here for next step.");
            }
        }
        else if(dealerHandScore <= 5){
            actionLabel.setText("Player score above 5. \n Player Stands.");
            JOptionPane.showMessageDialog(null, "Click here for next step.");
            actionLabel.setText(STR."\{ACTION_LABEL_INITIAL_VALUE} Drawing Third Dealer Card");
            dealerHandCards[2] = gameDeck.draw();
            lastCard.setText(STR."\{LAST_CARD_INITIAL_VALUE} \{dealerHandCards[2]}");
            dealerHand.setText(dealerHand.getText() + dealerHandCards[2]);
            dealerHandScore = handToValue(dealerHandCards);
            dealerHandValue.setText(STR."\{DEALER_HAND_VALUE_INITIAL_VALUE}\{dealerHandScore}");
            JOptionPane.showMessageDialog(null, "Click here for next step.");
        }
        else {
            actionLabel.setText("Player score above 5. \n Player Stands.");
            JOptionPane.showMessageDialog(null, "Click here for next step.");
            actionLabel.setText("Dealer score above 5. \n Dealer Stands.");
            JOptionPane.showMessageDialog(null, "Click here for next step.");
        }
        if(playerHandScore > dealerHandScore)
            return 1;
        else if(dealerHandScore > playerHandScore)
            return -1;
        return 0;


    }

    private static byte handToValue(Card[] hand){
        int result = 0;
        for(Card c : hand){
            if(c == null)
                return (byte) (result % 10);
            if(c.getNumber() > 9)
                result += 10;
            else
                result += c.getNumber();
        }
        return (byte) (result % 10);
    }



    private void refresh(){
        playerHand.setText(PLAYER_HAND_INITIAL_VALUE);
        playerHandValue.setText(PLAYER_HAND_VALUE_INITIAL_VALUE);
        betType.setText(BET_TYPE_INITIAL_VALUE);
        winnerLabel.setText("");
        actionLabel.setText(ACTION_LABEL_INITIAL_VALUE);
        dealerHand.setText(DEALER_HAND_INITIAL_VALUE);
        dealerHandValue.setText(DEALER_HAND_VALUE_INITIAL_VALUE);
        lastCard.setText(LAST_CARD_INITIAL_VALUE);
        betField.setEnabled(true);
    }
}
