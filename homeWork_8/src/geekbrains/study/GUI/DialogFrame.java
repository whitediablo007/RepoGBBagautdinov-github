package geekbrains.study.GUI;

import geekbrains.study.enums.DotType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DialogFrame extends JDialog {

    public DotType playerType = DotType.X;
    public int mapSize = 3;

    public DialogFrame(JFrame parentFrame) {
        super(parentFrame, "Конфигурация игры", true);
        setBounds(300, 300, 400, 200);

        JTextField mapSizeTextField = new JTextField(String.valueOf(mapSize));

        JPanel panel = getPanel(mapSizeTextField);

        JButton applyBtn = applyButton(mapSizeTextField);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(getLogo(), BorderLayout.NORTH);
        add(applyBtn, BorderLayout.SOUTH);



        setVisible(true);
    }

    private JPanel getPanel(JTextField mapSizeTextField) {
        int gridSize = 2;

        JPanel panel = new JPanel(new GridLayout(gridSize, gridSize));

        panel.add(new JLabel("Выберите тип фишки: "));
        panel.add(createRadioButton());
        panel.add(new JLabel("Выберите размер игрового поля: "));
        panel.add(mapSizeTextField);

        return panel;
    }

    private JButton applyButton(JTextField mapSizeTextField){
        JButton applyButton = new JButton("Поехали!");

        WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapSize = Integer.parseInt(mapSizeTextField.getText());
                dispatchEvent(closeEvent);
            }
        });

        return applyButton;
    }

    private JPanel createRadioButton() {
        JRadioButton xButton = getRadioButton("X", DotType.X, true);
        JRadioButton oButton = getRadioButton("O", DotType.O, false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(xButton);
        buttonPanel.add(oButton);

        ButtonGroup group = new ButtonGroup();
        group.add(xButton);
        group.add(oButton);


        return buttonPanel;

    }

    private JRadioButton getRadioButton(String symbol, DotType dotType, boolean selected) {
        JRadioButton radioButton = new JRadioButton(symbol, selected);
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerType = dotType;
            }
        });
        return radioButton;
    }

    private Component getLogo(){
        ImageIcon logo = new ImageIcon("images/xo.png");
        return new JLabel("", logo, JLabel.CENTER);
    }

    public DotType getPlayerType(){
        return playerType;
    }

    public int getMapSize(){
        return mapSize;
    }

}
