package edu.curtin.app;
import java.util.*;

public class Player extends MazeBlock
{
	private String type;
	private int row;
	private int column;
		
	public Player(String type, int row, int column) 
	{
		this.type = type;
		this.row = row;
		this.column = column;
	}
	
	@Override 
	public boolean requiresWallCheck()
	{
		return false;
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
		String printData = " ";		
		if(type.equals("S"))
		{
			printData = "P";
		}
		if(type.equals("E"))
		{
			printData = "E";
		}
		return printData;
	}
	
	@Override
	public void setPrintData(String input) //when you want only the P to print in one maze square
	{
		type = input;
	}

	
	@Override
	public List<String> getData() 
	{
		List<String> data = new ArrayList<>();
		data.add(this.type);
		data.add(String.valueOf(this.row));
		data.add(String.valueOf(this.column));
		return data;
	}
	
	@Override
	public void setCoordinates(int row, int col)
	{
		this.row = row;
		this.column = col;
	}
}

