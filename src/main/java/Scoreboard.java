import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Scoreboard extends JFrame {

    private JPanel panel1;
    private JTable scoreTable;
    private JPanel tablePanel;

    public Scoreboard(){
        setVisible(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("All-Time Casino Scoreboards");
        setContentPane(panel1);
        setIconImage(MasterFrame.ICON_IMAGE);
        panel1.add(tablePanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void refresh(){
        //ChatGPT Source - Prompt:
        //write me code that takes input of a csv file in java and outputs the contents of the csv file to a JTable object. the csv is 3 columns, make the code in Swing
        String filePath = "src/main/resources/scoreboard.csv";
        String[] columnNames = {"Place", "Name", "Final Money"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == columnNames.length) {
                    model.addRow(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JTable table = new JTable(model);
        table.setFont(new FontUIResource("Segoe UI", 0, 14));
        table.setBackground(new Color(36, 107, 97));
        table.setForeground(new Color(212,207, 106));
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panel1.setBackground(new Color(36, 107, 97));
        panel1.setForeground(new Color(181,91, 129));
    }
}
