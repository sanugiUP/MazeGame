package edu.curtin.app;
import java.util.*;

public class VerticalDoor extends DoorDecorator
{
	private String boxCh;
	private int colour;
	protected List<int[]> neighbours;
	
	public VerticalDoor(String inType, String row, String column, String colour) 
	{
		super(inType, row, column, colour);
		this.colour = Integer.parseInt(colour);
		boxCh = "\u2592";
		neighbours = new ArrayList<>();
	}
	
	@Override
	public void setPrintData(String input) 
	{
		boxCh = input;	
	}
	
	@Override
	public String getPrintData(boolean rowU, boolean rowD, boolean colL, boolean colR) 
	{	
		return getColour(this.colour) + boxCh + "\033[m";
	}
	
	@Override
	public int[] getCoordinates(int rw, int cl) 
	{
		int[] coordinates = new int[2];
		if(rw != 0)
		{
			rw = (rw * 2) + 1;
		}
		
		if(rw == 0)
		{
			rw = rw + 1;
		}
		cl = (cl * 4);
		
		coordinates[0] = rw;
		coordinates[1] = cl;
		return coordinates;
	}

	
}
