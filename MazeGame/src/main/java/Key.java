package edu.curtin.app;
import java.util.*;

public class Key extends MazeBlock
{
	private int row;
	private int column;
	private int colour;
	private boolean det;
	
	public Key(int row, int column, int cl)
	{
		this.row = row;
		this.column = column;
		this.colour = cl;
		det = true;
		keys = new ArrayList<>();
		Map<Integer, String> key = new HashMap<>();
		key.put(cl, getColour(cl));
		keys.add(key);
	}
	
	@Override
	public int[] getCoordinates(int rw, int cl) //Going to be same for every block
	{	
		int[] coordinates = new int[2];
		coordinates[0] = (rw * 2) + 1;
		coordinates[1] = (cl * 4) + 2 ;
		return coordinates;		
	}

	@Override
	public String getPrintData(boolean rowU, boolean rowD, boolean colL, boolean colR) 
	{
		String ret;
		if(det == true)
		{
			ret = getColour(colour) + "\u2555" + "\033[m";
		}
		else
		{
			ret = "";
		}
		return ret;
	}
	
	@Override
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
	
	@Override
	public void setPrintData(String input) // equals setBlockType
	{
		det = false;
	}
	
	@Override
	public List<String> getData() 
	{
		List<String> keyData = new ArrayList<>();
		keyData.add("K");
		keyData.add(String.valueOf(this.row));
		keyData.add(String.valueOf(this.column));
		keyData.add(String.valueOf(this.colour));
		return keyData;
	}
	
	@Override
	public void setCoordinates(int row, int col)
	{
		this.row = row;
		this.column = col;
	}
}
