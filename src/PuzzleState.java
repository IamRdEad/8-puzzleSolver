import java.util.*;

public class PuzzleState {
	private int[][] board; 
	private final int SIZE = 3; 
	private PuzzleState parent;
	private String move; 
	private int emptyRow, emptyCol;
	static int numberOfNodes = 0;
	int manDistance; 
	int g; //cost to reach the state 
	
	
	public PuzzleState(int[][] board, PuzzleState parent, String move, int g) {
		this.board = deepCopy(board);
		this.parent = parent;
		this.move = move; 
		this.g = g;
		this.manDistance = HeuristicCalculator.getHeuristic(board);
		findEmptySlot();
	}
	
	//function for deep copy the array to prevent aliasing
	private int[][] deepCopy(int[][] boardToCopy){
		int[][] newBoard = new int[SIZE][SIZE];
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				newBoard[i][j] = boardToCopy[i][j];
			}
		}
		return newBoard;
	}
	
	private void findEmptySlot() {
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				if(this.board[i][j] == 0) {
					this.emptyRow = i; 
					this.emptyCol = j;
					return; //there is only 1 empty slot can exit the function since found solution 
				}
			}
		}
	}
	
	//compares the current state to the wanted state and return if its the answer or not
	public boolean isAnswer() {
		int[][] answer = {{0,1,2},
						  {3,4,5},
						  {6,7,8}};
		return Arrays.deepEquals(this.board, answer);
	}
	
	public List<PuzzleState> generateNextState(){
		List<PuzzleState> nextLayer = new ArrayList<>();
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; //all 4 possible direction to move 
		
		for(int i = 0; i<4; i++) {
			try {//in case we go out of array bounds in trying to switch the empty space
				int[][] newBoard = deepCopy(this.board);
				newBoard[this.emptyRow][this.emptyCol] = newBoard[this.emptyRow+dir[i][0]][this.emptyCol+dir[i][1]];
				newBoard[this.emptyRow+dir[i][0]][this.emptyCol+dir[i][1]] = 0;
				String move = "(" + (this.emptyRow + dir[i][0]) + "," + (this.emptyCol+dir[i][1]) + ")";
				nextLayer.add(new PuzzleState(newBoard, this, move, this.g+1));
			}catch (Exception e) {}//moving out of borders
		}
		
		return nextLayer;
	}
	
	public void printPath() {
		if(parent!= null) {
			parent.printPath();
		}
		System.out.println("Move: " + this.move);
		this.printBoard();
	}
	
	public void printBoard() {
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				System.out.print(this.board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int[][] getBoard(){
		return deepCopy(this.board);
	}
	
	public int f() {
		return this.g+this.manDistance;
	}
	
	//implementing hash for efficiency (preventing searching the same state twice) 
	
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(this.board);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {return true;}
		if(obj == null || this.getClass() != obj.getClass()) {return false;}
		PuzzleState puz = (PuzzleState) obj; 
		return Arrays.deepEquals(this.board, puz.board);
	}
	
}
