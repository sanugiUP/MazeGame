package edu.curtin.app;
import java.util.*;

public class HorizontalWall extends WallDecorator
{
	private String boxCh;
	protected List<int[]> neighbours;
	
	public HorizontalWall(String inType, String row, String column)
	{
		super(inType, row, column);
		boxCh = "\u2500";
		neighbours = new ArrayList<>();
	}
	
	@Override
	public String getPrintData(boolean rowU, boolean rowD, boolean colR, boolean colL) 
	{		
		if(rowD == false && rowU == false && colL == true && colR == true) {
			boxCh = "\u2500";
		}else if(rowD == true && rowU == false && colL == false && colR == true){
			boxCh = "\u250c"; 
		}else if(rowD == true && rowU == false && colL == true && colR == false){
			boxCh = "\u2510"; 
		}else if(rowD == false && rowU == true && colL == false && colR == true){
			boxCh = "\u2514"; 
		}else if(rowD == false && rowU == true && colL == true && colR == false) {
			boxCh = "\u2518"; 
		}else if(rowD == true && rowU == true && colL == false && colR == true) {
			boxCh = "\u251c"; 
		}else if(rowD == true && rowU == true && colL == true && colR == false) {
			boxCh = "\u2524"; 
		}else if(rowD == true && rowU == false && colL == true && colR == true) {
			boxCh = "\u252c"; 
		}else if(rowD == false && rowU == true && colL == true && colR == true) {
			boxCh = "\u2534"; 
		}else if(rowD == true && rowU == true && colL == true && colR == true) {
			boxCh = "\u253c"; 
		}else if(rowD == false && rowU == false && colL == true && colR == false) {
			boxCh = "\u2500"; 
		}else if(rowD == false && rowU == false && colL == false && colR == false) {
			boxCh = "\u2500";
		}
		return boxCh;
	}

	@Override
	public List<int[]> getNeighborCorrdinates(int r, int c)
	{	
		int[] cd = new int[2];
		int[] cd1 = new int[2];
		int[] cd2 = new int[2];
		int[] cd3 = new int[2];	
		cd[0] = r;
		cd[1] = c + 1;
		cd1[0] = r;
		cd1[1] = c + 2;
		cd2[0] = r;
		cd2[1] = c + 3;
		cd3[0] = r;
		cd3[1] = c + 4;
		neighbours.add(cd);
		neighbours.add(cd1);
		neighbours.add(cd2);
		neighbours.add(cd3);
		return neighbours;
	}
}
