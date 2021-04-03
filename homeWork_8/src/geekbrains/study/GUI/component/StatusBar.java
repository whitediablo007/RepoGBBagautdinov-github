package geekbrains.study.GUI.component;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JLabel {

    public StatusBar(){
        setPreferredSize(new Dimension(100, 50));
    }
    public void setMessage(String message){
        setText(" " + message);
    }
}
