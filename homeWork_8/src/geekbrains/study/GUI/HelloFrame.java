package geekbrains.study.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class HelloFrame extends JDialog {
    JPanel panel = new JPanel();

    ImageIcon tick = new ImageIcon("images/tick.png");
    ImageIcon duke = new ImageIcon("images/duke.gif");

    Container container = getContentPane();

    JLabel lbl3 = new JLabel("Привет Андрей! меня зовут Duke, Поиграем?", duke, JLabel.CENTER);

    JButton btn = new JButton("Lets go!", tick);

    public HelloFrame(JFrame parentFrame) {
        super(parentFrame, "Приветствие", true);
        setBounds(300, 300, 400, 200);
        add(panel);

        panel.add(btn);
        container.add("South", panel);
        container.add("Center", lbl3);
        applyButton();
        setVisible(true);

    }

    private void applyButton() {
        WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchEvent(closeEvent);
            }
        });
    }
}
