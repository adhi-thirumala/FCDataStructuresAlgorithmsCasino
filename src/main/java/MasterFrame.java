import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;


public class MasterFrame extends JFrame{
    public static final Player gambler = new Player();
    public static Image ICON_IMAGE = new ImageIcon("src/main/resources/icon.jpg").getImage();
    public MasterFrame(){
        setIconImage(ICON_IMAGE);
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

        gc.getEndGame().addActionListener(_ -> {
            try {
                addPlayerToCSV();
            }
            catch (IOException | CsvException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        });

        gc.getScoreboardButton().addActionListener(_ -> {
            Scoreboard sc = new Scoreboard();
            sc.refresh();
            setButtonsEnabled(gc.getPanel(), false);
            sc.addWindowListener(new WindowAdapter() {
                /**
                 * Invoked when the user attempts to close the window
                 * from the window's system menu.
                 *Re-enables the buttons on the
                 * @param e the event to be processed
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    setButtonsEnabled(gc.getPanel(), true);
                }
            });
        });

        gc.getBaccaratButton().addActionListener(_ -> {
            setVisible(false);
            BaccaratFrame bp = new BaccaratFrame();
            bp.addWindowListener(new WindowAdapter() {
                /**
                 * Invoked when a window is in the process of being closed.
                 * The close operation can be overridden at this point.
                 *
                 * @param e
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(true);
                    bp.dispose();
                }
            });
        });
    }

    public void addPlayerToCSV() throws IOException, CsvException {
        long lineCount;
        String PATH_OF_SCOREBOARD = "src/main/resources/scoreboard.csv";
        try (Stream<String> stream = Files.lines(Path.of(PATH_OF_SCOREBOARD), StandardCharsets.UTF_8)) {
            lineCount = stream.count();
        }
        FileReader fr = new FileReader(PATH_OF_SCOREBOARD);
        CSVReader cr = new CSVReader(fr);
        List<String[]> lines = cr.readAll();
        cr.close();
        fr.close();
        lines.add(new String[]{String.valueOf(lineCount + 1), gambler.getName(), String.valueOf(gambler.getMoney())});
        lines.sort((o1, o2) -> {
            BigDecimal temp = new BigDecimal(o2[2]).subtract(new BigDecimal(o1[2]));
            if(temp.compareTo(BigDecimal.ZERO) > 0)
                return 1;
            else if(temp.compareTo(BigDecimal.ZERO) == 0)
                return 0;
            else
                return -1;
        });
        for(int i = 0; i < lines.size(); i++) {
            String temp;
            //code for putting medals into the scoreboard
           /* if(i == 0)
                temp = "🥇";
            else if (i == 1)
                temp = "\uD83E\uDD48"; //silver second place medal
            else if (i == 2)
                temp =  "\uD83E\uDD48"; //bronze third place medal
            else */
                temp = String.valueOf(i + 1);
            lines.get(i)[0] = temp;
        }
        FileWriter fw = new FileWriter(PATH_OF_SCOREBOARD);
        CSVWriter cw = new CSVWriter(fw);
        cw.writeAll(lines, false);
        cw.close();
        fw.close();
    }
    public static void setButtonsEnabled(JPanel panel, boolean isEnabled){
        /*
         *    Title: Function to recursively set the enabled state of all buttons on a JPanel
         *    Author: <a href = https://stackoverflow.com/users/1632232/kesavamoorthi> Kesavamoorthi Subramanian </a>
         *    Date: September 9, 2015
         *    Availability: https://stackoverflow.com/questions/19324918/how-to-disable-all-components-in-a-jpanel
         *
        */
        panel.setEnabled(isEnabled);
        Component[] comps = panel.getComponents();
        for(Component comp : comps){
            if(comp instanceof JPanel)
                setButtonsEnabled((JPanel) comp, isEnabled);
            if(comp instanceof JButton)
                comp.setEnabled(isEnabled);
        }
    }
}
