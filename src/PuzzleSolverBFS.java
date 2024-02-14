import java.util.*;

public class PuzzleSolverBFS {
	
	private int[][] initState;
	
	public PuzzleSolverBFS(int[][] initState) {
		this.initState = initState;
	}
	
	public void solve(){
		System.out.println("Running BFS: ");
		PuzzleState startState = new PuzzleState(initState, null, "Start" ,0);
		Queue<PuzzleState> queue = new LinkedList<>();
		Set<PuzzleState> visited = new HashSet<>();
		
		queue.add(startState);
		visited.add(startState);
		
		while(!queue.isEmpty()) {
			PuzzleState curr = queue.poll();
			PuzzleState.numberOfNodes++;

			
			if(curr.isAnswer()) {
				System.out.println("Goal reached");
				System.out.println("Number of nodes generated: " + PuzzleState.numberOfNodes);
				PuzzleState.numberOfNodes = 0;
				curr.printPath();
				return;
			}
			
			for(PuzzleState next: curr.generateNextState()) {
				if(!visited.contains(next)) {
					queue.add(next);
					visited.add(next);
				}
			}
		}
		System.out.println("solotion not found");
		PuzzleState.numberOfNodes = 0;
	}
}
