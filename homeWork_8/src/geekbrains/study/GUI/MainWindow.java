package geekbrains.study.GUI;

import geekbrains.study.GUI.component.StatusBar;
import geekbrains.study.core.GameService;
import geekbrains.study.core.domain.MatrixCoordinate;
import geekbrains.study.core.impl.GameServiceImpl;
import geekbrains.study.enums.DotType;
import geekbrains.study.factory.GameFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    public static final String DOT_EMPTY = ".";

    private JButton[][] buttons;

    private GameService gameService;
    private DotType playerType;

    public MainWindow() {
        setTitle("Крестики нолики 1.1");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);

        StatusBar statusBar = new StatusBar();
        statusBar.setMessage("Ожидание хода игрока");

        HelloFrame helloFrame = new HelloFrame(this);
        DialogFrame dialogFrame= new DialogFrame(this);


        int mapSize = dialogFrame.getMapSize();
        playerType = dialogFrame.getPlayerType();

        gameService = new GameServiceImpl(mapSize,playerType);

        JPanel gridPanel = createGridButtons(mapSize);

        setLayout(new BorderLayout());
        add(gridPanel);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createGridButtons(int mapSize) {
        JPanel gridPanel = new JPanel(new GridLayout(mapSize, mapSize));

        buttons = new JButton[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                JButton btn = new JButton(DOT_EMPTY);

                Font font = new Font(btn.getFont().getName(), btn.getFont().getStyle(), 46);

                btn.setFont(font);

                btn.putClientProperty("INDEX_ROW", i);
                btn.putClientProperty("INDEX_COLUMN", j);

                btn.addActionListener(getButtonListener());

                buttons[i][j] = btn;
                gridPanel.add(btn);

            }

        }
        return gridPanel;
    }

    private ActionListener getButtonListener() {
        return e -> {
            doHumanTurn((JButton) e.getSource());

            if (isGameContinue(playerType)) {
                doAiTurn();
            } else {
                disableAllButtons();
            }
        };
    }

    private boolean isGameContinue(DotType dotType){
        return !gameService.checkWin(dotType) && !gameService.isMapFull();
    }

    public void disableAllButtons() {
        for (JButton[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                button[j].setEnabled(false);

            }
        }
    }

    private void doAiTurn() {
        MatrixCoordinate matrixCoordinate = gameService.aiTurn();
        JButton aiSelectedButton = buttons[matrixCoordinate.getRowIndex()][matrixCoordinate.getColumnIndex()];

        disableButtonWithMark(aiSelectedButton, DotType.getEnemyType(playerType));
        if (!isGameContinue(DotType.getEnemyType(playerType))){
            disableAllButtons();
        }
    }

    private void doHumanTurn(JButton selectedButton) {
        int indexRow = (int) selectedButton.getClientProperty("INDEX_ROW");
        int indexColumn = (int) selectedButton.getClientProperty("INDEX_COLUMN");

        gameService.humanTurn(indexRow, indexColumn);
        disableButtonWithMark(selectedButton, playerType);
    }

    private void disableButtonWithMark(JButton button, DotType dotType) {
        button.setEnabled(false);
        button.setText(dotType.toString());
    }
}
