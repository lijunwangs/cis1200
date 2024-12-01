package flappyBird;

import javax.swing.*;
import java.awt.*;

public class RunFlappyBird implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for
        // local variables.

        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("TOP LEVEL FRAME");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);

        // Main playing area
        final GameDisplay court = new GameDisplay();
        frame.add(court, BorderLayout.CENTER);

        final JLabel score = new JLabel("Score: " + court.getScore());
        status_panel.add(score);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> court.reset());
        control_panel.add(reset);

        // Pause button
        final JButton pause = new JButton("Pause");
        pause.addActionListener(e -> court.pause());
        control_panel.add(pause);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
