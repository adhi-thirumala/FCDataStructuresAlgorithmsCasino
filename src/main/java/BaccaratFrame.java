import javax.swing.*;

public class BaccaratFrame extends JFrame {
    private JPanel panel1;

    public BaccaratFrame(){
        setIconImage(MasterFrame.ICON_IMAGE);
        setTitle("Punto Banco Baccarat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
