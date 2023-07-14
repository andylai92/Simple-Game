import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PingPong extends JPanel {
    private Ball ball;
    private Racket player1, player2;
    private int score1, score2;

    public PingPong() {
        ball = new Ball(this, 300, 200);
        player1 = new Racket(this, KeyEvent.VK_UP, KeyEvent.VK_DOWN, 10);
        player2 = new Racket(this, KeyEvent.VK_W, KeyEvent.VK_S, 970);

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) { player1.keyReleased(e); player2.keyReleased(e); }
            @Override
            public void keyPressed(KeyEvent e) { player1.keyPressed(e); player2.keyPressed(e); }
        });
    }

    public void increaseScore(int playerNo) {
        if (playerNo == 1) {
            score1++;
        } else {
            score2++;
        }
    }

    public Racket getPlayer1() {
        return player1;
    }

    public Racket getPlayer2() {
        return player2;
    }

    private void update() {
        ball.move();
        player1.move();
        player2.move(ball.getY());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        ball.paint(g2d);
        g2d.setColor(Color.BLUE);
        player1.paint(g2d);
        player2.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(score1), 10, 30);
        g2d.drawString(String.valueOf(score2), getWidth()-40, 30);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Ping Pong Game");
        PingPong game = new PingPong();
        frame.add(game);
        frame.setSize(1000, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.update();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
