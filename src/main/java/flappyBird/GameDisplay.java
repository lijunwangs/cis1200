package flappyBird;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class GameDisplay extends JPanel {
    // the state of the game logic
    private Bird bird = new Bird(1000, 400); // the Black Square, keyboard control
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private boolean playing = true; // whether the game is running
    private int score = 0;
    private final JLabel scoreBoard = new JLabel();

    // Game constants
    public static final int COURT_WIDTH = 1000;
    public static final int COURT_HEIGHT = 400;
    public static final int BIRD_VELOCITY_X = 10;
    public static final int BIRD_VELOCITY_Y = 5;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 20;

    public GameDisplay() {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the
        // given INTERVAL. We register an ActionListener with this timer, whose
        // actionPerformed() method is called each time the timer triggers. We
        // define a helper method called tick() that actually does everything
        // that should be done in a single time step.
        Timer timer = new Timer(INTERVAL, e -> tick());
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key
        // is pressed, by changing the square's velocity accordingly. (The tick
        // method below actually moves the square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    bird.setVx(-BIRD_VELOCITY_X);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    bird.setVx(BIRD_VELOCITY_X);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    bird.setVy(BIRD_VELOCITY_Y);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    bird.setVy(-BIRD_VELOCITY_Y);
                }
            }

            public void keyReleased(KeyEvent e) {
                bird.setVx(0);
                bird.setVy(0);
            }
        });
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void generateRandomObstacle() {
        int maxW = 500;
        int minW = 200;
        int width = (int) (Math.random()*(maxW-minW) + 1) + minW;
        int maxG = 100;
        int minG = 80;
        int gap = (int) (Math.random()*(maxG-minG) + 1) + minG;
        int maxP = -750;
        int minP = -400;
        int posY = (int) (Math.random()*(maxP-minP) + 1) + minP;
        int velY = -1;
        if (posY + 800 < 200) {
            velY = 1;
        }
        obstacles.add(new Obstacle(2000, COURT_HEIGHT, width, 800, posY, velY));
        obstacles.add(new Obstacle(2000, COURT_HEIGHT, width, 800, posY + 800 + gap, velY));

    }
    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    int tickCounter = 0;
    void tick() {
        if (playing) {
            // advance the square and snitch in their current direction.
            bird.move();
            //obstacle.move();
            if (tickCounter % 275 == 0) {
                generateRandomObstacle();
            }

            if (tickCounter % 2 == 0) {
                for (Obstacle obstacle : obstacles) {
                    obstacle.move();
                }
            }

            for (Obstacle obstacle : obstacles) {
                if (bird.intersects(obstacle)) {
                    playing = false;
                    break;
                }
                /*if (obstacle.getPx() < bird.getPx()) {
                    score += 50;
                    scoreBoard.setText("Score: " + score);
                } */
                if (obstacle.isOutOfBounds()) {
                    obstacles.remove(obstacle);
                    score += 50;
                    scoreBoard.setText("Score: " + score);
                }
            }
            repaint();
            requestFocusInWindow();
        }
        tickCounter++;
    }

    public void reset() {
        bird = new Bird(COURT_WIDTH, COURT_HEIGHT);
        obstacles = new ArrayList<Obstacle>();
        playing = true;
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    public void pause() {
        playing = !playing;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bird.draw(g);
        //obstacle.draw(g);
        obstacles.forEach(obstacle -> obstacle.draw(g));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
