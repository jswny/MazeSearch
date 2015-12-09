package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {
	
	List<Integer> list;
	List<Integer> start;
	
	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	public EightPuzzle(List<Integer> startingValues) {
		if (startingValues.isEmpty())
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(0))
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(1))
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(2))
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(3))
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(4))
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(5))
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(6))
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(7))
			throw new IllegalArgumentException();
		
		if (!startingValues.contains(8))
			throw new IllegalArgumentException();
		
		if (startingValues.size() > 9)
			throw new IllegalArgumentException();
		
		list = startingValues;
		
		start = new ArrayList<Integer>(startingValues);
	}

	@Override
	public List<Integer> getInitialState() {
		return start;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		
		int index = currentState.indexOf(0);
		
		List<Integer> switchables = new ArrayList<Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		if (index == 0) {
			switchables.add(1);
			switchables.add(3);
		}
		
		if (index == 1) {
			switchables.add(0);
			switchables.add(2);
			switchables.add(4);
		}
		
		if (index == 2) {
			switchables.add(1);
			switchables.add(5);
		}
		
		if (index == 3) {
			switchables.add(0);
			switchables.add(4);
			switchables.add(6);
		}
		
		if (index == 4) {
			switchables.add(1);
			switchables.add(3);
			switchables.add(5);
			switchables.add(7);
		}
		
		if (index == 5) {
			switchables.add(2);
			switchables.add(4);
			switchables.add(8);
		}
		
		if (index == 6) {
			switchables.add(3);
			switchables.add(7);
		}
		
		if (index == 7) {
			switchables.add(4);
			switchables.add(6);
			switchables.add(8);
		}
		
		if (index == 8) {
			switchables.add(5);
			switchables.add(7);
		}
		
		for (int j = 0; j < switchables.size(); j++) {
			ArrayList<Integer> tList = new ArrayList<Integer>(currentState);
			
			int temp = tList.get(index);
			
			tList.set(index, tList.get(switchables.get(j)));
			
			tList.set(switchables.get(j), temp);
			
			result.add(tList);
		}
		
		return result;
	}

	@Override
	public boolean isGoal(List<Integer> state) {
		for (int i = 0; i < 8; i++) {
			if (state.get(i) != i + 1)
				return false;
		}
		
		if (state.get(8) != 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}
