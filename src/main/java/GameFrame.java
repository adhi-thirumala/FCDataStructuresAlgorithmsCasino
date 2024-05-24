import javax.swing.*;
import java.awt.*;

class GameFrame extends JFrame {
    JPanel panel;
    JLabel label;
    GameFrame(String title )
    {
        super( title );                      // invoke the JFrame constructor
        setSize( 150, 100 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        setLayout( new FlowLayout() );       // set the layout manager
        label = new JLabel("Hello Swing!");  // construct a JLabel
        add( label );                        // add the label to the JFrame
    }

}
