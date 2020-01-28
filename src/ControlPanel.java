import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ControlPanel {

    JFrame frame;
    JPanel panel;

    JLabel title;

    JButton mandButton;
    JButton circlesButton;
    JButton lifeButton;

    public ControlPanel() {
        frame = new JFrame();
        panel = new CPanel();

        frame.setContentPane(panel);

        title = new JLabel("Winter 2019 Collection");
        title.setFont(new Font("Arial", Font.BOLD, 36));

        panel.setLayout(new GridLayout(2, 3, 15, 15));

        panel.add(new JLabel());
        panel.add(title);
        panel.add(new JLabel());

        mandButton = new JButton("mandelbrot");
        circlesButton = new JButton("circles");
        lifeButton = new JButton("life");

        try {
            Image img = ImageIO.read(new File("img/mand.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            mandButton.setIcon(new ImageIcon(img));
            Image img2 = ImageIO.read(new File("img/circles.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            circlesButton.setIcon(new ImageIcon(img2));
            Image img3 = ImageIO.read(new File("img/life.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            lifeButton.setIcon(new ImageIcon(img3));
            
        } catch (Exception e) {
            e.printStackTrace();
        }


        JButton[] a = {mandButton, circlesButton, lifeButton};
        ButtonListener buttonListener = new ButtonListener();
        for (int i = 0; i < a.length; i++) {
            a[i].addActionListener(buttonListener);
        }

        for (int i = 0; i < a.length; i++) {
            panel.add(a[i]);
        }

        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.pack();
    }

    class CPanel extends JPanel {
        
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("mandelbrot")) {
                SwingUtilities.invokeLater(() -> {
                    JFrame.setDefaultLookAndFeelDecorated(false);
                    new MandelbrotGrapher();
                });
            }
            else if (e.getActionCommand().equals("circles")) {
                SwingUtilities.invokeLater(() -> {
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    //setting above true makes fullscreen bigger
                    new Circles();
                });
            }
            else {
                SwingUtilities.invokeLater(() -> {
                    JFrame.setDefaultLookAndFeelDecorated(false);
                    //(it's a static variable, have to reset)
                    new Life();
                });
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ControlPanel();
        });
    }
}