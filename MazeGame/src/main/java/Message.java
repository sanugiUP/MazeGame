package edu.curtin.app;
import java.util.*;

public class Message extends MazeBlock
{
	private String message;
	private int row;
	private int column;
	private boolean det;
	
	public Message(String message, int row, int column)
	{
		this.message = message;
		this.row = row;
		this.column = column;
		det = true;
		messages = new ArrayList<>();
		messages.add(this.message);
	}

	@Override
	public void setPrintData(String input)
	{
		det = false;
	}
	
	@Override 
	public List<String> getData()
	{
		List<String> data = new ArrayList<>();
		data.add("M");
		data.add(String.valueOf(this.row));
		data.add(String.valueOf(this.column));
		data.add(message);
		return data;
	}
	
	@Override
	public void setCoordinates(int row, int col)
	{
		this.row = row;
		this.column = col;
	}
	
	@Override
	public String getPrintData(boolean rowU, boolean rowD, boolean colL, boolean colR) //message will not display anything on the maze
	{
		String printData = " ";
		if(det == true)
		{
			printData = "M";
		}
		return printData;
	}
	

	@Override
	public int[] getCoordinates(int rw, int cl) 
	{
		int[] coordinates = new int[2];
		coordinates[0] = (rw * 2) + 1;
		coordinates[1] = (cl * 4) + 2 ;
		return coordinates;
	}
}
