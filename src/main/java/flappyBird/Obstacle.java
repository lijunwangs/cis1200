package flappyBird;

import java.awt.*;

public class Obstacle extends GameObj {
    // an obstacle is composed of 2 separate rectangles with a space between them.
    // the space between them is randomly generated from a specific bounds
    // y velocity is either positive, negative, or 0.

    public static int posX = 1001;
    public static final int VEL_X = -5;
    private final Color color;

    public Obstacle(
            int courtWidth, int courtHeight, int width, int height, int posY, int velY, Color color
    ) {
        super(VEL_X, velY, posX, posY, width, height, courtWidth, courtHeight);
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRoundRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), 15, 15);
        /*
         * g.fillRect(this.getPx(), this.getPy() + this.getHeight() + gap,
         * this.getWidth(),
         * this.getCourtHeight()-this.getHeight() - gap);
         */
    }

    @Override
    public void move() {
        this.setPx(this.getPx() + this.getVx());
        this.setPy(this.getPy() + this.getVy());
        // this.setHeight(this.getHeight() + this.getVy());
    }

    @Override
    public void clip() {
    }
}
