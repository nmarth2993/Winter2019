import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	CellStateArray generation;
	GenerationCalculator gc;
	
	final static int TIME_INTERVAL = 500;
	public final static int WIDTH = 4;
	public final static int HEIGHT = 4;

	private int cellCount;
	private int generationCount;
	private boolean playing;
	private boolean mouseDown;

	public GamePanel(CellStateArray gen) {
		playing = true;
		cellCount = 0;
		generationCount = 0;
		setGeneration(gen);
		setGenerationCalculator(gen);
	}
	
	public Rectangle getPlayRectangle() {
		return new Rectangle((int) (getPreferredSize().getWidth() - 22), 30, 12, 10);
	}
	
	public void setPlaying(boolean play) {
		playing = play;
	}

	private void setGenerationCalculator(CellStateArray gen) {
		gc = new GenerationCalculator(gen);
	}

	public void setGeneration(CellStateArray gen) {
		this.generation = gen;
	}

	public CellStateArray getGeneration() {
		return generation;
	}

	public void setMouseDown(boolean m) {
		mouseDown = m;
	}

	public void startUpdateThread() {
		new Thread(() -> {
			generationCount = 0;
			for (;;) {
				try {
					Thread.sleep(mouseDown ? 20 : TIME_INTERVAL);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (playing) {
				generation = gc.calculateNextGen();
				setGenerationCalculator(generation);
				generationCount++;
				}
				for (int i = 0; i < generation.getCellStates().length; i++) {
					for (int j = 0; j < generation.getCellStates()[0].length; j++) {
						if (generation.getCellStates()[i][j] == CellStateArray.ALIVE) {
							cellCount++;
						}
					}
				}
				repaint();
			}
		}).start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		FontMetrics fm;
		
		g2d.setColor(Color.BLACK);
		
		g2d.fillPolygon(new int[] {getWidth() - 20, getWidth() - 20, getWidth() - 10}, new int[] {30, 40, 35}, 3);
		g2d.fillRect(getWidth() - 40, 30, 3, 10);
		g2d.fillRect(getWidth() - 35, 30, 3, 10);
		
		g2d.draw(getPlayRectangle());
	
		g2d.setFont(new Font("Arial", Font.BOLD, 12));
		fm = g2d.getFontMetrics();
		String state;
		if (playing) {
			state = "Playing";
			if (mouseDown) {
				state += " (25x)";
			}
			
		}
		else {
			state = "Paused";
		}

		g2d.drawString(state, getWidth() - 20 -	fm.stringWidth(state), 15);
		
		g2d.setFont(new Font("Arial", Font.BOLD, 16));
		fm = g2d.getFontMetrics();
		g2d.drawString("Generation: " + generationCount, 10, 20);
		g2d.drawString("Cells: " + cellCount, 20 + fm.stringWidth("Generation: " + generationCount), 20);
		for (int i = 0; i < generation.getCellStates().length; i++) {
			for (int j = 0; j < generation.getCellStates()[0].length; j++) {
				if (generation.getCellStates()[i][j] == 1) {
					g2d.setColor(Color.BLACK);
				} else {
					g2d.setColor(Color.WHITE);
				}
				g2d.fillRect(i * 4, 50 + j * 4, WIDTH, HEIGHT);
			}
		}
		cellCount = 0;
	}
}
