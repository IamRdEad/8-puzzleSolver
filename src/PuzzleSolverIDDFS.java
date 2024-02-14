import java.util.HashSet;

public class PuzzleSolverIDDFS {
	private int maxDepth; 
	private int[][] initState;
	
	public PuzzleSolverIDDFS(int[][] initState, int maxDepth) {
		this.maxDepth = maxDepth;
		this.initState = initState;
	}
	
	public void solve() {
		System.out.println("Running IDDFS with Max depth of: " + this.maxDepth);
		PuzzleState startState = new PuzzleState(initState, null, "Start", 0);
		
		for(int i = 0; i<this.maxDepth; i++) {
			if(DLS(startState, i)) {
				return;
			}
		}
		System.out.println("Solution not found");
		PuzzleState.numberOfNodes = 0;
	}

	private boolean DLS(PuzzleState state, int limit) {
		if(state.isAnswer()) {
			System.out.println("Number of nodes generated: " + PuzzleState.numberOfNodes);
			PuzzleState.numberOfNodes = 0;
			state.printPath();
			return true;
		}
		if(limit <= 0) {
			return false;
		}
		for(PuzzleState nextState: state.generateNextState()) {
			PuzzleState.numberOfNodes++;
			if(DLS(nextState, limit-1)) {
				return true;
			}
		}
		
		return false;
	}
}
