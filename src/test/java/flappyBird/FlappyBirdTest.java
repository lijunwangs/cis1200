package flappyBird;

import org.junit.jupiter.api.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class FlappyBirdTest {
    @Test
    public void createBird() {
        Bird bird = new Bird(200, 200);

        // bird should start at (0, 0)
        assertEquals(0, bird.getPx());
        assertEquals(0, bird.getPy());

        // bird should not be moving at the start
        assertEquals(0, bird.getVx());
        assertEquals(0, bird.getVy());
    }

    @Test
    public void birdVelocityUpdatesPosition() {
        Bird bird = new Bird(200, 200);

        bird.setVx(10);
        bird.setVy(20);
        assertEquals(10, bird.getVx());
        assertEquals(20, bird.getVy());

        assertEquals(0, bird.getPx());
        assertEquals(0, bird.getPy());

        bird.move();

        assertEquals(10, bird.getPx());
        assertEquals(20, bird.getPy());
    }

    @Test
    public void moveBirdOffScreen() {
        Bird bird = new Bird(200, 200);
        bird.setVx(-10);
        bird.move();
        assertEquals(0, bird.getPx());
    }

    @Test
    public void moveObstacle() {
        Obstacle obstacle = new Obstacle(200, 200, 50, 100, 0, 1);
        obstacle.move();
        assertEquals(996, obstacle.getPx());
    }

    @Test
    public void obstacleOutOfBounds() {
        Obstacle obstacle = new Obstacle(200, 200, 50, 100, 0, 1);
        obstacle.setPx(-60);
        assertTrue(obstacle.isOutOfBounds());
    }

    @Test
    public void twoObjectIntersection() {
        Bird bird = new Bird(200, 200);
        Obstacle obstacle = new Obstacle(200, 200, 50, 100, 0, 1);
        assertFalse(bird.intersects(obstacle));
        obstacle.setPx(0);
        obstacle.setPy(200);
        assertTrue(bird.intersects(obstacle));
    }

    @Test
    public void generateRandomObstacles() {
        GameDisplay court = new GameDisplay();
        court.generateRandomObstacle();
        assertEquals(2, court.getObstacles().size());
    }

    @Test
    public void removeObstacles() {
        GameDisplay court = new GameDisplay();
        court.generateRandomObstacle();
        for (Obstacle obstacle : court.getObstacles()) {
            obstacle.setPx(-300);
        }
        assertEquals(0, court.getObstacles().size());
    }

    @Test
    public void scoreUpdate() {
        GameDisplay court = new GameDisplay();
        int score = court.getScore();
        Bird bird = new Bird(200, 200);
        bird.setVx(10);
        Obstacle obstacle = new Obstacle(200, 200, 50, 100, 0, 1);
        Obstacle obstacle2 = new Obstacle(200, 200, 50, 100, 300, 1);
        assertEquals(0, score);
        obstacle.setPx(0);
        obstacle2.setPx(0);
        bird.setPx(100);
        assertEquals(100, score);
    }
}
