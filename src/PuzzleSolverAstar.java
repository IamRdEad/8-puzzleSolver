import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PuzzleSolverAstar {
	private int[][] board;
	
	public PuzzleSolverAstar(int[][] board) {
		this.board = board;
	}
	
	public void solve() {
		System.out.println("Running A*");
		PriorityQueue<PuzzleState> pq = new PriorityQueue<>(Comparator.comparingInt(PuzzleState::f));
		Set<PuzzleState> visited = new HashSet<>();
		
		PuzzleState initState = new PuzzleState(board, null, "start", 0);
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
			
			visited.add(curr);
			
			for(PuzzleState next: curr.generateNextState()) {
				if(!visited.contains(next)) {
					pq.add(next);
				}
			}
		}
		System.out.println("Solution not found");
		PuzzleState.numberOfNodes = 0;
	}
}
