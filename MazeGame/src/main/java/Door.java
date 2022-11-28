package edu.curtin.app;
import java.util.*;

public class Door extends MazeSquare
{
	private int row;
	private int column;
	private String type;
	protected int colour;
	protected String boxCh;
	protected List<int[]> neighbours;
	
	public Door(String inType, String row, String column, String colour)
	{
		neighbours = new ArrayList<>();
		this.type = inType;
		this.row = Integer.parseInt(row);
		this.column = Integer.parseInt(column);
		boxCh = " ";
		this.colour = Integer.parseInt(colour);
	}
	
	@Override
	public void setCoordinates(int row, int col)
	{
		this.row = row;
		this.column = col;	
	}
	
	@Override 
	public boolean requiresWallCheck()
	{
		return false;
	}
	
	@Override 
	public boolean requiresDoorCheck()
	{
		return true;
	}

	@Override
	public String getConsoleOutput() //deduct a key
	{
		return "";
	}

	@Override
	public String getPrintData(boolean rowU, boolean rowD, boolean colL, boolean colR)
	{
		return boxCh;
	}

	@Override
	public void setPrintData(String input) 
	{
		boxCh = input;
	}

	@Override
	public List<String> getData() 
	{
		List<String> doorData = new ArrayList<>();
		doorData.add(this.type);
		doorData.add(String.valueOf(this.row));
		doorData.add(String.valueOf(this.column));
		doorData.add(3, boxCh);
		doorData.add(String.valueOf(this.colour));
		return doorData;
	}

	@Override
	public List<int[]> getNeighborCorrdinates(int r, int c) 
	{
		return neighbours;
	}

	@Override
	@SuppressWarnings("PMD.UnusedAssignment") //suppressing this warning as both variables rw and cl are needed by getCoordinates() method implemented by other classes which extends MazeSquare
	public int[] getCoordinates(int rw, int cl) 
	{
		int[] coordinates = new int[2];
		rw = row;
		cl = column;
		return coordinates;
	}

	public String getColour(int input)
	{
		Map<Integer, String> doorColour = new HashMap<>();
		doorColour.put(1, "\033[31m");
		doorColour.put(2, "\033[32m");
		doorColour.put(3, "\033[33m");
		doorColour.put(4, "\033[34m");
		doorColour.put(5, "\033[35m");
		doorColour.put(6, "\033[36m");		
		String doorC = doorColour.get(input);
		return doorC;
	}
}
