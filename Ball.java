import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

class Ball {
    private static final int DIAMETER = 40;
    private int x, y, xa = 6, ya = 8;
    private PingPong game;

    public Ball(PingPong game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    void move() {
        boolean changeDirection = true;
        if (x + xa < 0) {
            game.increaseScore(2);
            x = game.getWidth()/2;
            xa = -xa;
        }
        else if (x + xa > game.getWidth() - DIAMETER) {
            game.increaseScore(1);
            x = game.getWidth()/2;
            xa = -xa;
        }
        else if (y + ya < 0 || y + ya > game.getHeight() - DIAMETER) {
            ya = -ya;
        }
        else if (game.getPlayer1().getBounds().intersects(getBounds()) || game.getPlayer2().getBounds().intersects(getBounds())) {
            xa = -xa;
        }
        else {
            changeDirection = false;
        }

        x = x + (changeDirection ? 2*xa : xa);
        y = y + ya;
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public int getY() {
        return y;
    }
}

class Racket {
    private static final int WIDTH = 10, HEIGHT = 50;
    private int x, y = 200, ya = 100;
    private PingPong game;
    private int up, down;

    public Racket(PingPong game, int up, int down, int x) {
        this.game = game;
        this.up = up;
        this.down = down;
        this.x = x;
    }

    public void move(int ballY) {
        int mid = this.y + HEIGHT / 2;  // Calculate the middle position of the racket
        if (ballY > mid) ya = 6;  // Move down if the ball is below the middle of the racket
        else if (ballY < mid) ya = -6;  // Move up if the ball is above the middle of the racket

        if (this.y + ya > 0 && this.y + ya < game.getHeight() - HEIGHT)
            this.y += ya;
    } 

    public void move() {
        if (this.y + ya > 0 && this.y + ya < game.getHeight() - HEIGHT)
            this.y += ya;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == up || e.getKeyCode() == down)
            ya = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == up) ya = -8;
        if (e.getKeyCode() == down) ya = 8;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
