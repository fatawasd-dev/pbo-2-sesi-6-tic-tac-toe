import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class TicTacToe extends JFrame {
    private JButton[][] buttons;
    private JLabel statusBar;
    private boolean xTurn = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(250, 250, 250));

        setLocationRelativeTo(null);

        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        gamePanel.setBackground(new Color(200, 200, 200)); 
        gamePanel.setBorder(new LineBorder(Color.BLACK, 2)); 
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].addActionListener(new ButtonClickListener());
                buttons[i][j].setBackground(new Color(240, 240, 240)); 
                gamePanel.add(buttons[i][j]);
            }
        }

        statusBar = new JLabel("Player X's turn");
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18)); 

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetBoard());
        resetButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16)); 
        resetButton.setBackground(new Color(150, 150, 150)); 
        resetButton.setForeground(Color.RED); 
        resetButton.setFocusPainted(false); 

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(new Color(250, 250, 250)); 
        controlPanel.add(resetButton);

        add(gamePanel, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void resetBoard() {
        xTurn = true;
        statusBar.setText("Player X's turn");
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText("");
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            if (clickedButton.getText().equals("") && !checkForWin()) {
                if (xTurn) {
                    clickedButton.setText("X");
                    statusBar.setText("Player O's turn");
                } else {
                    clickedButton.setText("O");
                    statusBar.setText("Player X's turn");
                }
                xTurn = !xTurn;
                if (checkForWin()) {
                    String winner = xTurn ? "O" : "X";
                    JOptionPane.showMessageDialog(TicTacToe.this, "Player " + winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    resetBoard();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(TicTacToe.this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    resetBoard();
                }
            }
        }
    }

    private boolean checkForWin() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}
