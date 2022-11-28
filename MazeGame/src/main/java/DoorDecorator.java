package edu.curtin.app;
public abstract class DoorDecorator extends Door
{
	public DoorDecorator(String inType, String row, String column, String colour) 
	{
		super(inType, row, column, colour);
	}	
}
