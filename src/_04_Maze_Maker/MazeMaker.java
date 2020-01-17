package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		randGen = new Random();
		int x = randGen.nextInt(width);
		int y = randGen.nextInt(height);
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(x, y));
		boolean across=randGen.nextBoolean();
		if(across==true) {
			int a=randGen.nextInt(width);
			int b=randGen.nextInt(width);
			maze.getCell(a, 0).setNorthWall(false);
			maze.getCell(b, height-1).setSouthWall(false);
		}else {
			int c=randGen.nextInt(height);
			int d=randGen.nextInt(height);
			maze.getCell(0, c).setWestWall(false);
			maze.getCell(width-1, d).setEastWall(false);
		}
		

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. Get an ArrayList of unvisited neighbors using the current cell and the
		// method below
		ArrayList<Cell> cell = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (cell.size() > 0) {
			int c = randGen.nextInt(cell.size());
			Cell randomCell = cell.get(c);
			uncheckedCells.push(randomCell);
			removeWalls(currentCell, randomCell);
			currentCell = randomCell;
			selectNextPath(currentCell);

		}
		// C1. select one at random.
		// done
		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// C5. call the selectNextPath method with the current cell

		// D. if all neighbors are visited
		if (cell.size() == 0) {
			if (!(uncheckedCells.size() == 0)) {
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
		// D1. if the stack is not empty
		// done
		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getY() == c2.getY() && c1.getX() < c2.getX()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		else if (c1.getY() == c2.getY() && c1.getX() > c2.getX()) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		}
		else if (c1.getX() == c2.getX() && c1.getY() < c2.getY()) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
		else if (c1.getX() == c2.getX() && c1.getY() > c2.getY()) {
			c2.setSouthWall(false);
			c1.setNorthWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cell = new ArrayList<Cell>();
		if (c.getX() > 0) {
			Cell a = maze.getCell(c.getX() - 1, c.getY());
			if (!a.hasBeenVisited()) {
				cell.add(a);
			}
		}
		if (c.getY() < maze.getHeight()-1) {
			Cell b = maze.getCell(c.getX(), c.getY() + 1);
			if (!b.hasBeenVisited()) {
				cell.add(b);
			}
		}
		if (c.getX() < maze.getWidth()-1) {
			Cell d = maze.getCell(c.getX() + 1, c.getY());
			if (!d.hasBeenVisited()) {
				cell.add(d);
			}
		}
		if (c.getY() > 0) {
			Cell e = maze.getCell(c.getX(), c.getY() - 1);
			if (!e.hasBeenVisited()) {
                 cell.add(e);
			}
		}

		return cell;
	}
}
