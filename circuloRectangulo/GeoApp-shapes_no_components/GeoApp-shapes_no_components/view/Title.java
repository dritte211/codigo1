package view;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;

public class Title extends JPanel
{
    private JLabel title;
    public Title(String text) {
        setPreferredSize(new Dimension(0, 40));
        setBackground(Color.WHITE);
        title = new JLabel(text);
        add(title);
    }
    
    public void setTitle (String text) {
        title.setText(text);
    }
}