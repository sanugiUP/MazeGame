package edu.curtin.app;
import java.util.*;

public abstract class MazeSquare implements MazeGrid
{	
	@Override public abstract String getPrintData(boolean rowU, boolean rowD, boolean colL, boolean colR);
	@Override public abstract void setPrintData(String input);
	@Override public abstract List<String> getData();
	@Override public abstract List<int[]> getNeighborCorrdinates(int r, int c);
	@Override public abstract int[] getCoordinates(int rw, int cl);
	@Override public abstract boolean requiresWallCheck();
	@Override public abstract boolean requiresDoorCheck();
	@Override public abstract void setCoordinates(int row, int col);
	
	//Below two methods should only apply to MazeBlock who also extends MazeGrid. Since they are part of functionality for MazeGrid they also need to be implemented in MazeSquare. However, sub-classess extending MazeSquare doesn't need these methods, hence they are abstract.
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract") 
	public List<Map<Integer, String>> manageKeys(int k, String function)
	{
		return null;
	}
	
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract") 
	public List<String> manageMessages(String m, String function)
	{
		return null;
	}
}
