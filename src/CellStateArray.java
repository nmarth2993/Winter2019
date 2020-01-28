import java.util.Random;

public class CellStateArray {
	public final static int ALIVE = 1;
	public final static int DEAD = 0;

	private int[][] cellStates;

	public CellStateArray(boolean random) {
		cellStates = new int[100][100];
		if (random) {
			Random r = new Random();

			for (int i = 0; i < cellStates.length; i++) {
				for (int j = 0; j < cellStates.length; j++) {
					cellStates[i][j] = r.nextInt(2);
				}
			}
		}
	}

	public CellStateArray(int[][] array) {
		cellStates = new int[100][100];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				cellStates[i][j] = array[i][j];
			}
		}
	}

	public int[][] getCellStates() {
		return cellStates;
	}

	public void setCellStates(int[][] states) {
		cellStates = states;
	}

	/*
	 * public static void main(String[] args) { CellStateArray a = new
	 * CellStateArray(true);
	 * 
	 * for (int i = 0; i < a.getCellStates().length; i++) { for (int j = 0; j <
	 * a.getCellStates()[0].length; j++) { System.out.print(a.getCellStates()[i][j]
	 * + " "); } System.out.println(); } System.out.println("\n\n");
	 * GenerationCalculator gc = new GenerationCalculator(a);
	 * System.out.println(gc.checkNeighbors(1, 0)); CellStateArray b =
	 * gc.getNextGen(); for (int i = 0; i < b.getCellStates().length; i++) { for
	 * (int j = 0; j < b.getCellStates()[0].length; j++) {
	 * System.out.print(b.getCellStates()[i][j] + " "); } System.out.println(); } }
	 */
}
