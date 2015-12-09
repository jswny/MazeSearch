package mazes;

/**
 * A class representing an (x,y) position (or cell) in a grid-based maze.
 * @author liberato
 *
 */
public class Cell implements Comparable<Cell>{
	public final int x;
	public final int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Cell o) {
		int c = Integer.compare(x, o.x);
		if (c != 0)  {
			return c;
		}
		return Integer.compare(y, o.y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + "]";
	}
}
