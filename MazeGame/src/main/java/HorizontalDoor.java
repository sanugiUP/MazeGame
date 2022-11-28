package edu.curtin.app;
import java.util.*;

public class HorizontalDoor extends DoorDecorator
{
	private String boxCh;
	private int colour;
	protected List<int[]> neighbours;
	
	public HorizontalDoor(String inType, String row, String column, String colour)
	{
		super(inType, row, column, colour);
		this.colour = Integer.parseInt(colour);
		boxCh = "\u2592";
		neighbours = new ArrayList<>();
	}

	@Override
	public String getPrintData(boolean rowU, boolean rowD, boolean colL, boolean colR) 
	{	
		return getColour(this.colour) + boxCh + "\033[m";
	}

	@Override
	public void setPrintData(String input) 
	{
		boxCh = input;	
	}

	@Override
	public List<int[]> getNeighborCorrdinates(int r, int c) 
	{
		int[] cd = new int[2];
		int[] cd1 = new int[2];
		cd[0] = r;
		cd[1] = c - 1;
		cd1[0] = r;
		cd1[1] = c + 1;
		neighbours.add(cd);
		neighbours.add(cd1);
		
		return neighbours;
	}
	
	@Override
	public int[] getCoordinates(int rw, int cl) 
	{
		int[] coordinates = new int[2];
		if(rw != 0)
		{
			rw = (rw * 2);
		}
		cl = (cl * 4) + 2;
		
		coordinates[0] = rw;
		coordinates[1] = cl;
		return coordinates;
	}
}
