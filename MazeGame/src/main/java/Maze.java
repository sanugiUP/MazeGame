package edu.curtin.app;
import java.util.*;

public class Maze 
{
	public MazeGrid[][] maze;
	public int rows;
	public int columns;
	
	public Maze(int rows, int columns)
	{
		maze = new MazeGrid[(rows * 2) + 1][(columns * 3) + (columns + 1)];
		this.rows = (rows * 2) + 1;
		this.columns = (columns * 3) + (columns + 1);
	}
	
	public void setBorders()
	{
		maze[0][0] = new Wall("WB", "0", "0");
		maze[rows-1][columns-1] = new Wall("WB", String.valueOf(rows-1), String.valueOf(columns-1));
		maze[rows-1][0] = new Wall("WB", String.valueOf(rows-1), String.valueOf(0));
		maze[0][columns-1] = new Wall("WB", String.valueOf(0), String.valueOf(columns-1));

		maze[0][0].setPrintData("\u250c");
		maze[rows-1][columns-1].setPrintData("\u2518");
		maze[rows-1][0].setPrintData("\u2514");
		maze[0][columns-1].setPrintData("\u2510");

		for(int i = 1; i < rows-1; i++)
		{
			maze[i][0] = new VerticalWall("WB", String.valueOf(i), "0");
			maze[i][0].setPrintData("\u2502");
			maze[i][columns-1] = new VerticalWall("WB", String.valueOf(i), String.valueOf(columns-1));
			maze[i][columns-1].setPrintData("\u2502");
		}

		for(int j = 1; j < columns-1; j++)
		{
			maze[0][j] = new HorizontalWall("WB", "0", String.valueOf(j));
			maze[0][j].setPrintData("\u2500");
			maze[rows-1][j] = new HorizontalWall("WB", String.valueOf(rows-1), String.valueOf(j));
			maze[rows-1][j].setPrintData("\u2500");	
		}	
	}
	
	
	public void setMaze(MazeGrid m, int r, int c)
	{
		int[] xy = m.getCoordinates(r, c);
		maze[xy[0]][xy[1]] = m;
		
		List<int[]> coordinates = m.getNeighborCorrdinates(xy[0], xy[1]);
		if(coordinates != null)
		{
			for(int[] i : coordinates)
			{
				int[] cor = i;
				
				if(m.getData().get(0).equals("DV"))
				{
					maze[cor[0]][cor[1]] = new VerticalWall("WV", String.valueOf(cor[0]), String.valueOf(cor[1]));
				}
				else
				{
					maze[cor[0]][cor[1]] = m;	
				}
			}	
		}
	}
	
	
	public boolean[] determinePrintCondition(int r, int c)
	{
		boolean[] determinants = new boolean[4];
		boolean rowU = false;
		boolean rowD = false;
		boolean colR = false;
		boolean colL = false;
		
		int ru = r - 1;
		int rd = r + 1;
		int cl = c - 1;
		int cr = c + 1;

		if(ru <= -1){
			ru = r;
		}
		if(cl <= -1){
			cl = c;
		}
		if(rd > rows-1) {
			rd = r;
		}
		if(cr > columns-1) {
			cr = c;
		}

		
		if((maze[r][cr] != null && maze[r][cr].requiresWallCheck() == true) || (maze[r][cr] != null && maze[r][cr].requiresDoorCheck() == true))
		{
			colR = true;
		}
		if((maze[r][cl] != null && maze[r][cl].requiresWallCheck() == true) || (maze[r][cl] != null && maze[r][cl].requiresDoorCheck() == true))
		{
			colL = true;
		}
		if((maze[rd][c] != null && maze[rd][c].requiresWallCheck() == true) || (maze[rd][c] != null && maze[rd][c].requiresDoorCheck() == true))
		{
			rowD = true;
		}
		if((maze[ru][c] != null && maze[ru][c].requiresWallCheck() == true)  || (maze[ru][c] != null && maze[ru][c].requiresDoorCheck() == true))
		{
			rowU = true;
		}
		
		if(r == 0)
		{
			rowU = false;
		}
		
		if(r >= rows-1)
		{
			rowD = false;
		}
		
		if(c == 0)
		{
			colL = false;
		}

		if(c >= columns-1)
		{
			colR = false;
		}
		
		determinants[0] = rowU;
		determinants[1] = rowD;
		determinants[2] = colR;
		determinants[3] = colL;
		
		return determinants;	
	}
	
	
	public void print()
	{	
		for(int i = 0; i < rows; i++) 
		{
			for(int j = 0; j < columns; j++) 
			{ 
				if(maze[i][j] == null)
				{
					System.out.print(" ");
				}
				else
				{
					boolean[] determinants = determinePrintCondition(i, j);
					System.out.print(maze[i][j].getPrintData(determinants[0], determinants[1], determinants[2], determinants[3]));	
				}
			}
			System.out.println();
		}
	}
	
	public MazeGrid getGrid(int r, int c, String t)
	{
		MazeGrid mg = null;
		if(t.equals("S") || t.equals("E"))
		{
			MazeBlock mb = new Player(t, r, c);
			int[] xy = mb.getCoordinates(r, c);
			mg = maze[xy[0]][xy[1]];
		}
		else if(t.equals("M"))
		{
			MazeBlock mb = new Message(t, r, c);
			int[] xy = mb.getCoordinates(r, c);
			mg = maze[xy[0]][xy[1]];
		}
		else if(t.equals("K"))
		{
			MazeBlock mb = new Key(r, c, 1);
			int[] xy = mb.getCoordinates(r, c);
			mg = maze[xy[0]][xy[1]];
		}
		return mg;
	}
}
