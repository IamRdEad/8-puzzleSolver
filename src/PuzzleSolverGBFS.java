import java.util.*;

public class PuzzleSolverGBFS {
	private int[][] board;
	
	public PuzzleSolverGBFS(int[][] board) {
		this.board = board;	
	}
	
	public void solve() {
		System.out.println("Running GBFS");
		PuzzleState initState = new PuzzleState(board, null, "Start", 0);
		PriorityQueue<PuzzleState> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.manDistance));
		
		pq.add(initState);
		
		while(!pq.isEmpty()) {
			PuzzleState curr = pq.poll();
			PuzzleState.numberOfNodes++;
			
			if(curr.isAnswer()) {
				System.out.println("Goal reached");
				System.out.println("Number of nodes generated: " + PuzzleState.numberOfNodes);
				PuzzleState.numberOfNodes = 0;
				curr.printPath();
				return;
			}
			
			for(PuzzleState next: curr.generateNextState()) {
				pq.add(next);
			}
		
		}
		System.out.println("solotion not found");
		PuzzleState.numberOfNodes = 0;
	}
}
