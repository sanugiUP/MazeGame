package edu.curtin.app;
import java.util.*;

public interface MazeGrid
{
	String getPrintData(boolean rowU, boolean rowD, boolean colL, boolean colR);
	void setPrintData(String input);
	List<String> getData();
	List<int[]> getNeighborCorrdinates(int r, int c);
	int[] getCoordinates(int r, int c); 
	String getConsoleOutput();
	boolean requiresWallCheck();
	boolean requiresDoorCheck();
	List<Map<Integer, String>> manageKeys(int k, String function);
	List<String> manageMessages(String m, String function);
	void setCoordinates(int row, int col);
	
}
