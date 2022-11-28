package edu.curtin.app;
import java.util.*;

public abstract class MazeBlock implements MazeGrid
{
	protected List<int[]> neighbours;
	protected List<String> messages;
	protected List<Map<Integer, String>> keys;
	
	@Override public abstract String getPrintData(boolean rowU, boolean rowD, boolean colL, boolean colR);
	@Override public abstract void setPrintData(String input);
	@Override public abstract List<String> getData();
	@Override public abstract int[] getCoordinates(int rw, int cl);
	@Override public abstract void setCoordinates(int row, int col);
	
	@Override
	public boolean requiresDoorCheck()
	{
		return false;
	}
	
	@Override 
	public List<Map<Integer, String>> manageKeys(int k, String function)
	{
		if(keys == null)
		{
			keys = new ArrayList<>();
		}
		
		if(function.equals("add"))
		{
			Map<Integer, String> key = new HashMap<>();
			key.put(k, getColour(k));
			keys.add(key);
		}
		return keys;
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
	
	
	@Override
	public List<String> manageMessages(String m, String function)
	{
		if(messages == null)
		{
			messages = new ArrayList<>();
		}
		
		if(function.equals("add"))
		{
			if(!messages.contains(m))
			{
				messages.add(m);	
			}
		}	
		
		return messages;
	}
		
	@Override
	public List<int[]> getNeighborCorrdinates(int r, int c) 
	{
		neighbours = new ArrayList<>();
		return neighbours;		
	}
	
	@Override 
	public boolean requiresWallCheck()
	{
		return false;
	}
	
	@Override
	public String getConsoleOutput()
	{
		String consoleOutput = consoleOutputKeys() + "\n" + consoleOutputMessages();
		return consoleOutput;
	}
	
	public String consoleOutputKeys()
	{
		StringBuilder sb = new StringBuilder();
		if(keys == null)
		{
			sb.append(" ");
		}
		else
		{
			sb.append("Keys : ");
			for(Map<Integer, String> i : keys)
			{
				if(i.values().toString().length() != 2)
				{
					String output = i.values().toString().substring(1, 6);
					sb.append(output + "\u2555" + "\033[m" + "  ");
				}
			}
		}
		return sb.toString();
	}
	
	public String consoleOutputMessages()
	{
		StringBuilder sb = new StringBuilder();
		if(messages == null)
		{
			sb.append(" ");
		}
		else
		{
			sb.append("Messages : ");
			for(String i : messages)
			{
				sb.append(i + "  ");
			}
		}
		return sb.toString();
	}
}
