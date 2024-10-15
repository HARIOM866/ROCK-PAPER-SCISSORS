import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorGUI extends JFrame implements ActionListener {
    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel, scoreLabel;
    private int playerScore = 0, computerScore = 0;

    public RockPaperScissorGUI() {
        setTitle("Rock-Paper-Scissors Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        resultLabel = new JLabel("Make your move!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Serif", Font.BOLD, 20));
        scoreLabel = new JLabel("Score: Player 0 - 0 Computer", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));

        add(resultLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerMove = e.getActionCommand();
        String computerMove = getComputerMove();
        String result = determineWinner(playerMove, computerMove);

        resultLabel.setText("Computer chose: " + computerMove + ". " + result);
        scoreLabel.setText("Score: Player " + playerScore + " - " + computerScore + " Computer");
    }

    private String getComputerMove() {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int index = random.nextInt(moves.length);
        return moves[index];
    }

    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "It's a tie!";
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            playerScore++;
            return "You win!";
        } else {
            computerScore++;
            return "You lose!";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorGUI game = new RockPaperScissorGUI();
            game.setVisible(true);
        });
    }
}
