/* 
* A class for calculating the heuristic value using Linear conflicts
* The linear conflicts is the sum of the Manhattan distance and the linear conflicts
* Each linear conflict adds 2 to the sum
* Since its expansion to Manhattan distance but still give optimize solution 
* by looking at a relaxed rules of the game its heuristic will be both consistent and admissible 
*/
public class HeuristicCalculator {

	public static int getHeuristic(int[][] board) {
		
		int heurstic = 0;
		heurstic = manhattanDistance(board);
		//System.out.println("Manhatan: " + heurstic);
		heurstic += calculateLinearConflicts(board)*2;
		//System.out.println("Conflicts: " + calculateLinearConflicts(board)*2);
		return heurstic;
		
	}
	
	private static int manhattanDistance(int[][] board) {
		int distance = 0; 
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				if(board[i][j]!=0){ //calculating for all non space tiles 
					int tile = board[i][j]; 
					int expectedRow = (tile)/board.length; //the row which the tile should be in
					int expectedCol = (tile)%board.length; //the col which the tile should be in
					distance += Math.abs(i-expectedRow) + Math.abs(j-expectedCol);
				}
			}
		}
		return distance;
	}
	
	/*
	 * Linear conflict detention: 
	 * When Two tiles tj and tk are in a linear conflict
	 * if tj and tk are in the same line, 
	 * the goal positions of tj and tk are both in that line,
	 * tj is to the right of tk and goal position of tj is to the left of the goal position of tk.
	 */
	
    private static int calculateLinearConflicts(int[][] board) {
        int conflicts = 0;
        for (int i = 0; i < board.length; i++) {
            conflicts += countConflicts(board, i, true); // Check rows
            conflicts += countConflicts(board, i, false); // Check columns
        }
        return conflicts;
    }
	
	 private static int countConflicts(int[][] board, int index, boolean isRow) {
	        int[] line = new int[board.length];
	        for (int i = 0; i < board.length; i++) {
	            line[i] = isRow ? board[index][i] : board[i][index];
	        }

	        int conflictCount = 0;
	        for (int i = 0; i < board.length; i++) {
	            if (line[i] == 0) continue; // Skip the empty space
	            for (int j = i + 1; j < board.length; j++) {
	                if (line[j] == 0) continue; // Skip the empty space
	                if (isInCorrectLine(line[i], index, isRow) && isInCorrectLine(line[j], index, isRow)) {
	                    if (isInConflict(line[i], line[j], index, isRow)) {
	                        conflictCount++;
	                    }
	                }
	            }
	        }
	        return conflictCount;
	    }
	 
	    private static boolean isInCorrectLine(int value, int index, boolean isRow) {
	        int correctIndex = value / 3;
	        return isRow ? (correctIndex == index) : (correctIndex % 3 == index);
	    }

	    private static boolean isInConflict(int value1, int value2, int index, boolean isRow) {
	        int correctPosition1 = isRow ? (value1 % 3) : (value1 / 3);
	        int correctPosition2 = isRow ? (value2 % 3) : (value2 / 3);
	        return correctPosition1 > correctPosition2;
	    }
	
}
