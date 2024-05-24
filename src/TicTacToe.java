
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textJLabel = new JLabel();
    JPanel textJPanel = new JPanel();
    JPanel boardJPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String CurrentPlayer = playerX;

    Boolean gameOver = false;
    int turn = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textJLabel.setBackground(Color.darkGray);
        textJLabel.setForeground(Color.white);
        textJLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textJLabel.setHorizontalAlignment(JLabel.CENTER);
        textJLabel.setText("Tic-Tac-Toe");
        textJLabel.setOpaque(true);

        textJPanel.setLayout(new BorderLayout());
        textJPanel.add(textJLabel);
        frame.add(textJPanel, BorderLayout.NORTH);

        boardJPanel.setLayout(new GridLayout(3, 3));
        boardJPanel.setBackground(Color.darkGray);
        frame.add(boardJPanel);

        for (int r= 0; r < 3; r++) {
            for (int c= 0; c < 3; c++) {
                JButton tileButton = new JButton();
                board[r][c] = tileButton;
                boardJPanel.add(tileButton);

                tileButton.setBackground(Color.darkGray);
                tileButton.setForeground(Color.white);
                tileButton.setFont(new Font("Arial", Font.BOLD, 120));
                tileButton.setFocusable(false);

                tileButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tileButton = (JButton) e.getSource();
                        if (tileButton.getText() == "") {
                            tileButton.setText(CurrentPlayer);
                            turn++;
                            checkWinner();
                            if (!gameOver) {
                                CurrentPlayer = CurrentPlayer == playerX ? playerO : playerX;
                                textJLabel.setText(CurrentPlayer + "'s turn.");
                            }
                        }

                    }
                });
            }
        }
    }

    void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;
            if (board[r][0].getText() == board[r][1].getText() && 
            board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;
            if (board[0][c].getText() == board[1][c].getText() && 
            board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        if (board[0][0].getText() == board[1][1].getText() && 
        board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        if (board[0][2].getText() == board[1][1].getText() && 
        board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turn == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++)  {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
            return;
        }
    }

    void setWinner(JButton tileButton) {
        tileButton.setForeground(Color.green);
        tileButton.setBackground(Color.gray);
        textJLabel.setText(CurrentPlayer + "is the winner");
    }

    void setTie(JButton tileButton) {
        tileButton.setForeground(Color.orange);
        tileButton.setBackground(Color.gray);
        textJLabel.setText("Tie!");
    }
}
