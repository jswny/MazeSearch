package search;

import java.util.List;

/**
 * A solver for generic search problems.
 * 
 * The solver provides solutions based upon three search implementations.
 * 
 * @author liberato
 *
 * @param <T>
 *            the type of each state in the search problem
 */
public class Solver<T> {
	private final SearchProblem<T> problem;

	public Solver(SearchProblem<T> problem) {
		this.problem = problem;
	}

	/**
	 * Finds and returns a valid solution to the problem using recursive
	 * depth-first search.
	 * 
	 * The solution will either be a list of states from the initial state to a
	 * goal state, or the empty list (if no solution was found).
	 * 
	 * @return a solution to the problem
	 */
	public List<T> solveWithRecursiveDFS() {
		Searcher<T> s = new RecursiveDepthFirstSearcher<T>(problem);
		return s.findSolution();
	}

	/**
	 * Finds and returns a valid solution to the problem using iterative
	 * depth-first search.
	 * 
	 * The solution will either be a list of states from the initial state to a
	 * goal state, or the empty list (if no solution was found).
	 * 
	 * @return a solution to the problem
	 */
	public List<T> solveWithIterativeDFS() {
		Searcher<T> s = new StackBasedDepthFirstSearcher<T>(problem);
		return s.findSolution();
	}

	/**
	 * Finds and returns a valid solution to the problem using breadth-first
	 * search.
	 * 
	 * The solution will either be a list of states from the initial state to a
	 * goal state, or the empty list (if no solution was found).
	 * 
	 * @return a solution to the problem
	 */
	public List<T> solveWithBFS() {
		Searcher<T> s = new QueueBasedBreadthFirstSearcher<T>(problem);
		return s.findSolution();
	}
}
