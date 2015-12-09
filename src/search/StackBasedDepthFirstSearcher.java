package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
		
		ArrayList<T> list = new ArrayList<T>();
		
		list.add(searchProblem.getInitialState());
		
		Stack<Holder<T>> stack = new Stack<Holder<T>>();
		
		stack.push(new Holder<T>(searchProblem.getInitialState(), list));
		
		while (!stack.isEmpty()) {
			Holder<T> tempPath = stack.pop();
			
			if (searchProblem.isGoal(tempPath.getData())) {
				return tempPath.getList();
			} else {
			
				List<T> successors = searchProblem.getSuccessors(tempPath.getData());
				
				for (int i = 0; i < successors.size(); i++) {
					if (!visited.contains(successors.get(i))) {
						List<T> list2 = new ArrayList<T>(tempPath.getList());
						list2.add(successors.get(i));
						stack.push(new Holder<T>(successors.get(i), list2));
						
						visited.add(successors.get(i));
					}
				}
			}
		}
		
		return new ArrayList<T>();
	}
}
