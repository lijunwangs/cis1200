package flappyBird;

import org.junit.jupiter.api.*;
import java.awt.*;
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

        // update velocity to non-zero value
        bird.setVx(10);
        bird.setVy(20);
        assertEquals(10, bird.getVx());
        assertEquals(20, bird.getVy());

        // position should not have updated yet since we didn't call move()
        assertEquals(0, bird.getPx());
        assertEquals(0, bird.getPy());

        // move!
        bird.move();

        // square should've moved
        assertEquals(10, bird.getPx());
        assertEquals(20, bird.getPy());
    }

    @Test
    public void createObstacle() {
        Obstacle obstacle = new Obstacle(200, 200, 50, 100);
        assertEquals(0, obstacle.getPx());
    }

    @Test
    public void twoObjectIntersection() {
        // square should spawn at (0, 0)
        Square square = new Square(200, 200, Color.white);
        assertEquals(0, square.getPx());
        assertEquals(0, square.getPy());

        // mushroom should spawn at (130, 130)
        Poison mushroom = new Poison(200, 200);
        assertEquals(130, mushroom.getPx());
        assertEquals(130, mushroom.getPy());

        // they're very far apart, so they should not be intersecting
        assertFalse(square.intersects(mushroom));

        // move square on top of mushroom
        square.setPx(130);
        square.setPy(130);

        assertEquals(130, square.getPx());
        assertEquals(130, square.getPy());

        // now, they're on top of one another! they should intersect
        assertTrue(square.intersects(mushroom));
    }
}
