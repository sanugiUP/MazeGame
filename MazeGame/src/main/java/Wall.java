package edu.curtin.app;
import java.util.ArrayList;
import java.util.List;

public class Wall extends MazeSquare
{
	private int row;
	private int column;
	private String type;
	protected String boxCh;
	protected List<int[]> neighbours;
	
	public Wall(String inType, String row, String column)
	{
		neighbours = new ArrayList<>();
		this.type = inType;
		this.row = Integer.valueOf(row);
		this.column = Integer.valueOf(column);
		boxCh = " ";
	}
	
	@Override
	public void setCoordinates(int row, int col)
	{
		this.row = row;
		this.column = column;	
	}
	
	@Override 
	public boolean requiresWallCheck()
	{
		return true;
	}
	
	@Override 
	public boolean requiresDoorCheck()
	{
		return false;
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
		List<String> wallData = new ArrayList<>(); //wall[0] = wall type | wall[1] = wall row | wall[2] = wall column | wall[3] = box character
		wallData.add(this.type);
		wallData.add(String.valueOf(this.row));
		wallData.add(String.valueOf(this.column));
		wallData.add(3, boxCh);
		return wallData;
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
		int r = rw;
		int c = cl;
		if(rw != 0)
		{
			r = rw * 2;
		}
		c = (cl * 4);

		coordinates[0] = r;
		coordinates[1] = c;
		return coordinates;
	}

	@Override
	public String getConsoleOutput() 
	{
		return "";
	}
}
