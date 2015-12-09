package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import sun.misc.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {
	
	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {

		ArrayList<T> list = new ArrayList<T>();
		
		list.add(searchProblem.getInitialState());
		
		Queue<Holder<T>> q = new Queue<Holder<T>>();
		
		q.enqueue(new Holder<T>(searchProblem.getInitialState(), list));
		
		while (!q.isEmpty()) {
			Holder<T> tempPath = null;
			try {
				tempPath = q.dequeue();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (searchProblem.isGoal(tempPath.getData())) {
				return tempPath.getList();
			} else {
			
				List<T> successors = searchProblem.getSuccessors(tempPath.getData());
				
				for (int i = 0; i < successors.size(); i++) {
					if (!visited.contains(successors.get(i))) {
						List<T> list2 = new ArrayList<T>(tempPath.getList());
						list2.add(successors.get(i));
						q.enqueue(new Holder<T>(successors.get(i), list2));
						
						visited.add(successors.get(i));
					}
				}
			}
		}
		
		return new ArrayList<T>();
	}
}
