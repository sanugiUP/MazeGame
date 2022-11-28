package edu.curtin.app;
import java.util.*;
import java.util.logging.Logger;

@SuppressWarnings("PMD.GuardLogStatement")  //to avoid unnessassary if statements
public class GameMechanics
{
	private static final Logger LOG = Logger.getLogger(GameMechanics.class.getName());
	public boolean chWin;
	
	public GameMechanics()
	{
		this.chWin = true;
	}
	
	public String moveUp(Maze m)
	{
		MazeGrid p = locatePlayer(m);
		int row = Integer.parseInt(p.getData().get(1)); //coordinates from file
		int column = Integer.parseInt(p.getData().get(2)); // coordinates from file
		int r = row - 1;
		int c = column;
		int[] currentCor = p.getCoordinates(row, column); // coordinates in the maze 
		int[] destCor = p.getCoordinates(row-1, column); // coordinate of the block on top
		int[] spaceCor = {currentCor[0] - 1, currentCor[1]}; //coordinates of the space in between the blocks
		return move(m, p, currentCor, destCor, spaceCor, r, c);
	}
	
	
	public String moveDown(Maze m)
	{
		MazeGrid p = locatePlayer(m);
		int row = Integer.parseInt(p.getData().get(1)); //coordinates from file
		int column = Integer.parseInt(p.getData().get(2)); // coordinates from file
		int r = row + 1;
		int c = column;
		int[] currentCor = p.getCoordinates(row, column); // coordinates in the maze
		int[] destCor = p.getCoordinates(row+1, column); // coordinate of the block down
		int[] spaceCor = {currentCor[0] + 1, currentCor[1]}; //coordinates of the space in between the blocks
		return move(m, p, currentCor, destCor, spaceCor, r, c);	
	}
	
	
	public String moveLeft(Maze m)
	{
		MazeGrid p = locatePlayer(m);
		int row = Integer.parseInt(p.getData().get(1)); //coordinates from file
		int column = Integer.parseInt(p.getData().get(2)); // coordinates from file
		int r = row;
		int c = column - 1;
		int[] currentCor = p.getCoordinates(row, column); // coordinates in the maze 
		int[] destCor = p.getCoordinates(row, column - 1); // coordinate of the block down
		int[] spaceCor = {currentCor[0], currentCor[1] - 2}; //coordinates of the space in between the blocks
		return move(m, p, currentCor, destCor, spaceCor, r, c);	
	}
	
	
	public String moveRight(Maze m)
	{
		MazeGrid p = locatePlayer(m);
		int row = Integer.parseInt(p.getData().get(1)); //coordinates from file
		int column = Integer.parseInt(p.getData().get(2)); // coordinates from file
		int r = row;
		int c = column + 1;
		int[] currentCor = p.getCoordinates(row, column); // coordinates in the maze 
		int[] destCor = p.getCoordinates(row, column + 1); // coordinate of the block down
		int[] spaceCor = {currentCor[0], currentCor[1] + 2}; //coordinates of the space in between the blocks
		return move(m, p, currentCor, destCor, spaceCor, r, c);	
	}
	
	
	public String move(Maze m, MazeGrid p, int[] currentCor, int[] destCor, int[] spaceCor, int r, int c)
	{
		String output = "";
		LOG.info("PLAYER CURRENT LOCATION DATA : ");
		LOG.info("coordinates in maze = [" + currentCor[0] + ", " + currentCor[1] + "]");
		LOG.info("coordinate of the block on top = [" + destCor[0] + ", " + destCor[1] + "]");
		LOG.info("coordinates of the space in between the blocks = [" + spaceCor[0] + ", " + spaceCor[1] + "]");
		
		if(m.maze[spaceCor[0]][spaceCor[1]] != null) // theres a wall or a door
		{
			if(m.maze[spaceCor[0]][spaceCor[1]].requiresDoorCheck() == true) //when there's a door
			{
				boolean move = blockEqualsDoor(m, p, spaceCor, currentCor, r, c, destCor);
				if(move == true)
				{
					output = blockWithoutObstacles(m, p, currentCor, destCor, r, c);	
				}
				else if(move == false)
				{
					output = "You cannot move through the door";
				}
			}
			else if(m.maze[spaceCor[0]][spaceCor[1]].requiresWallCheck() == true) //when there's a wall
			{
				output = "You cannot move through the wall";
			}
		}
		else //there is no wall so the player can move one block up
		{
			output = blockWithoutObstacles(m, p, currentCor, destCor, r, c);
		}
		LOG.info("Console Output : " + output);
		return output;
	}
	
	
	public void updatePlayer(Maze m, MazeGrid oldPl, MazeGrid newPl, int[] currentCor, int r, int c)
	{
		List<Map<Integer, String>> ekeys = oldPl.manageKeys(0, " ");
		for(Map<Integer, String> i : ekeys)
		{
			if(!i.toString().equals("{}")) //no empty condition
			{
				newPl.manageKeys(Integer.parseInt(i.toString().substring(1, 2)), "add");
			}
		}
		
		//restore the current block which might contain messages to type Message MazeGrid.
		if(m.maze[currentCor[0]][currentCor[1]] != null)
		{
			List<String> pm = m.maze[currentCor[0]][currentCor[1]].manageMessages(null, "");
			m.maze[currentCor[0]][currentCor[1]] = null;
			if(pm != null && pm.size() > 0)
			{
				m.maze[currentCor[0]][currentCor[1]] = new Message(pm.get(0), r, c);
				for(int i = 1; i < pm.size(); i++)
				{
					m.maze[currentCor[0]][currentCor[1]].manageMessages(pm.get(i), "add");
				}
			}	
		}
	
	}
	
	
	public String blockWithoutObstacles(Maze m, MazeGrid p, int[] currentCor, int[] destCor, int r, int c)
	{
		String output = "";
		MazeGrid mg = m.maze[destCor[0]][destCor[1]];
		if(mg == null) //when destination block is a empty block
		{
			MazeBlock np = new Player("S", r, c); //create new player to replace the old player
			updatePlayer(m, p, np, currentCor, r, c);
			np.setCoordinates(r, c);
			m.setMaze(np, r, c);
			output = np.getConsoleOutput();
		}
		else
		{
			if(mg.manageKeys(0, " ").size() > 0 || mg.manageMessages(null, " ").size() > 0) //when there are keys or messages in the destination block
			{
				output = blockEqualsContent(m, p, currentCor, destCor, r, c);
			
			}
			if(mg.getData().get(0).equals("E"))
			{
				output = blockEqualsPlayer(m, p, currentCor, r, c, destCor);
			}
		}
		return output;
	}
	
	
	public String blockEqualsContent(Maze m, MazeGrid p, int[] currentCor, int[] destCor, int r, int c)
	{
		MazeBlock np = new Player("S", r, c);
		List<Map<Integer, String>> keys = m.maze[destCor[0]][destCor[1]].manageKeys(0, " ");
		List<String> messages = m.maze[destCor[0]][destCor[1]].manageMessages(null, "");
		updatePlayer(m, p, np, currentCor, r, c);

		for(Map<Integer, String> i : keys)
		{
			if(!i.toString().equals("{}"))
			{
				np.manageKeys(Integer.parseInt(i.toString().substring(1, 2)), "add");
			}
		}
		
		if(messages != null)
		{
			for(String i : messages)
			{
				np.manageMessages(i, "add");
			}
		}
		
		
		np.setCoordinates(r, c);
		m.setMaze(np, r, c);
		String output = np.getConsoleOutput();
		return output;
	}
	
	
	public String blockEqualsPlayer(Maze m, MazeGrid p, int[] currentCor, int r, int c, int[] destCor)
	{
		MazeBlock np = new Player("S", r, c);
		updatePlayer(m, p, np, currentCor, r, c);		
		np.setCoordinates(r, c);
		m.setMaze(np, r, c);
		String output = np.getConsoleOutput() + "\nGame Over" + "\nYou Won";
		chWin = false;
		return output;
	}
	
	
	@SuppressWarnings("PMD.UnusedAssignment") // the variable changed here is used in move() to determine a player condition
	public boolean blockEqualsDoor(Maze m, MazeGrid p, int[] doorCor, int[] currentCor, int r, int c, int[] destCor)
	{
		boolean move = false;
		String dColour = m.maze[doorCor[0]][doorCor[1]].getData().get(4); //get door colour
		List<Map<Integer, String>> keys = m.maze[currentCor[0]][currentCor[1]].manageKeys(0, " ");
		
		if(keys != null)
		{
			for(Map<Integer, String> i : keys)
			{
				if(i.containsKey(Integer.parseInt(dColour))) //when player has a key matching door color
				{
					move = true;
				}
			}	
		}
		return move;
	}
	
	
	public MazeGrid locatePlayer(Maze m)
	{
		MazeGrid p = null;
		for(int i = 0; i < m.rows; i++)
		{
			for(int j = 0; j < m.columns; j++)
			{
				if(m.maze[i][j] != null)
				{
					if(m.maze[i][j].getData().get(0).equals("S"))
					{
						p = m.maze[i][j];
					}
				}
			}
		}
		return p;
	}
}
