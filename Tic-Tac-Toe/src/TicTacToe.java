import com.sun.tools.jconsole.JConsolePlugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TicTacToe implements ActionListener {


    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean xPlayerTurn;
    boolean endGame = false;

    TicTacToe() throws InterruptedException {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(true);
            buttons[i].addActionListener(this);
        }
        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!endGame) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    if (xPlayerTurn) {
                        if (buttons[i].getText().equals("")) {
                            buttons[i].setText("X");
                            buttons[i].setForeground(Color.green);
                            xPlayerTurn = false;
                            textField.setText("O Turn");
                            check();
                        }
                    } else {
                        if (buttons[i].getText().equals("")) {
                            buttons[i].setText("O");
                            buttons[i].setForeground(Color.red);
                            xPlayerTurn = true;
                            textField.setText("X Turn");
                            check();
                        }
                    }
                }
            }
        }
    }

    public void firstTurn() throws InterruptedException {
        Thread.sleep(2000);
        if (random.nextInt(2) == 0) {
            xPlayerTurn = true;
            textField.setText("X turn");
        } else {
            xPlayerTurn = false;
            textField.setText("O turn");
        }
    }

    public void check() {
        String symbolX = "X";
        String symbolO = "O";
        boolean xWin = haveAWinner(symbolX);
        boolean oWin = haveAWinner(symbolO);
        if (xWin) {
            xWins();
        } else if (oWin) {
            oWins();
        }
    }

    public void xWins() {
          textField.setText("X player WIN!");
          endGame = true;
    }

    public void oWins() {
         textField.setText("O player WIN!");
         endGame = true;
    }

 public boolean haveAWinner(String symbol){
     return (buttons[0].getText().equals(symbol) && buttons[1].getText().equals(symbol) && buttons[2].getText().equals(symbol))
             || (buttons[3].getText().equals(symbol) && buttons[4].getText().equals(symbol) && buttons[5].getText().equals(symbol))
             || (buttons[6].getText().equals(symbol) && buttons[7].getText().equals(symbol) && buttons[8].getText().equals(symbol))
             || (buttons[0].getText().equals(symbol) && buttons[4].getText().equals(symbol) && buttons[8].getText().equals(symbol))
             || (buttons[0].getText().equals(symbol) && buttons[3].getText().equals(symbol) && buttons[6].getText().equals(symbol))
             || (buttons[1].getText().equals(symbol) && buttons[4].getText().equals(symbol) && buttons[7].getText().equals(symbol))
             || (buttons[2].getText().equals(symbol) && buttons[5].getText().equals(symbol) && buttons[8].getText().equals(symbol));
 }
}
