package flappyBird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RunFlappyBird implements Runnable {

    public static void applyFontToComponents(Font font, JComponent... components) {
        for (JComponent component : components) {
            component.setFont(font);
        }
    }

    public void run() {

        Color bgColor = new Color(226, 231, 224);
        Color sideColor = new Color(150, 168, 157);
        Font customFont = new Font("Arial", Font.PLAIN, 20);

        //font
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("files/retrograde.otf"));
            customFont = customFont.deriveFont(15f); // Set font size
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        catch (FontFormatException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }


        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("TOP LEVEL FRAME");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        status_panel.setBackground(sideColor);
        final JLabel scoreBoard = new JLabel("Score: ");
        status_panel.add(scoreBoard);

        // Main playing area
        final GameDisplay court = new GameDisplay(scoreBoard);
        frame.add(court, BorderLayout.CENTER);
        court.setBackground(bgColor);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        control_panel.setBackground(sideColor);
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> court.reset());
        control_panel.add(reset);

        // Pause button
        final JButton pause = new JButton("Pause");
        pause.addActionListener(e -> court.pause(pause));
        control_panel.add(pause);

        applyFontToComponents(customFont, scoreBoard, reset, pause);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
