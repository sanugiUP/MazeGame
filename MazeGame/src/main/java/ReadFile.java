package edu.curtin.app;
import java.util.*;
import java.io.*;
import java.util.logging.Logger;

@SuppressWarnings("PMD.GuardLogStatement")  //to avoid unnessassary if statements
public class ReadFile
{
	private static final Logger LOG = Logger.getLogger(ReadFile.class.getName());
	
	public Maze readFile(String fileName) throws MazeException, IOException
	{
		List<String> fileData = new ArrayList<>();
		@SuppressWarnings("PMD.CloseResource")  //the resource is closed below
		FileReader fr = new FileReader(fileName); 
		@SuppressWarnings("PMD.CloseResource")  //the resource is closed below
		BufferedReader br = new BufferedReader(fr);
		
		String l = br.readLine();
		if(l == null)
		{
			 throw new MazeException("Cannot read from file. The file is empty.");
		}
		
		String[] size = l.split(" ");
		
		int rows = Integer.parseInt(size[0]);
		int columns = Integer.parseInt(size[1]);
		if(rows == 0 || columns == 0)
		{
			throw new MazeException("Size cannot be 0");
		}
		LOG.fine(() -> "Rows read from file = " + rows + "Columns read from file = " + columns);
		
		Maze maze = new Maze(rows, columns);
		maze.setBorders();
		
		String line = br.readLine();			
		int r = 0;
		int c = 0;
		int playerCount = 0;
		
		while(line != null)
		{
			String[] info = line.split(" ");
			String cor = info[1] + info[2];
			boolean same = false;
			
			
			for(String i : fileData)
			{
				String[] content = i.split("/");
				if(content[1].equals(cor)) //when coordinates repeat
				{
					if(content[0].equals("S") && info[0].equals("S") || content[0].equals("E") && info[0].equals("E")) //check if the data is of same type at same location
					{
						System.out.println("Cannot have more than one player on the same coordinate!!");	
						same = true;
					}
					else if(content[0].equals("S") && info[0].equals("M") || content[0].equals("E") && info[0].equals("M")) //different type -> player and message 
					{
						MazeGrid mg = maze.getGrid(Integer.parseInt(info[1]), Integer.parseInt(info[2]), content[0]);
						StringBuilder sb = new StringBuilder();
						for(int j = 3; j < info.length; j++)
						{
							sb.append(info[j] + " ");
						}
						mg.manageMessages(sb.toString(), "add");
						same = true;
					}
					else if(content[0].equals("M") && info[0].equals("M"))
					{
						MazeGrid mg = maze.getGrid(Integer.parseInt(info[1]), Integer.parseInt(info[2]), content[0]);
						if(mg != null) // only when of type message, could be player or key
						{
							StringBuilder sb = new StringBuilder();
							for(int j = 3; j < info.length; j++)
							{
								sb.append(info[j] + " ");
							}
							mg.manageMessages(sb.toString(), "add");
							same = true;	
						}
					}
					else if(content[0].equals("K") && info[0].equals("K"))
					{
						MazeGrid mg = maze.getGrid(Integer.parseInt(info[1]), Integer.parseInt(info[2]), content[0]);
						mg.manageKeys(Integer.parseInt(info[3]), "add");
						same = true;
					} 
					else if(content[0].equals("M") && info[0].equals("K"))
					{
						MazeGrid mg = maze.getGrid(Integer.parseInt(info[1]), Integer.parseInt(info[2]), content[0]);
						mg.manageKeys(Integer.parseInt(info[3]), "add");
						same = true;
					}
					else if(content[0].equals("K") && info[0].equals("M"))
					{
						MazeGrid mg = maze.getGrid(Integer.parseInt(info[1]), Integer.parseInt(info[2]), content[0]);
						StringBuilder sb = new StringBuilder();
						for(int j = 3; j < info.length; j++)
						{
							sb.append(info[j] + " ");
						}
						mg.manageMessages(sb.toString(), "add");
						same = true;
					}
					else if(content[0].equals("S") && info[0].equals("K") || content[0].equals("E") && info[0].equals("K"))
					{
						MazeGrid mg = maze.getGrid(Integer.parseInt(info[1]), Integer.parseInt(info[2]), content[0]);
						mg.manageKeys(Integer.parseInt(info[3]), "add");
						same = true;
					}
					else if(content[0].equals("DV") && info[0].equals("DV") || content[0].equals("DH") && info[0].equals("DH"))
					{
						System.out.println("DOOR ALREADY EXIST!! DOOR " + info[1] + " " + info[2] + " HAS BEEN DISCARDED");
						same = true;
					}
					else if(content[0].equals("WV") && info[0].equals("WV") || content[0].equals("WH") && info[0].equals("WH"))
					{
						System.out.println("WALL ALREADY EXIST!! WALL " + info[1] + " " + info[2] + " HAS BEEN DISCARDED");
						same = true;
					}
				}
			}
			if(same == false)
			{
				if(info[0].equals("WV")) {
				MazeSquare wv = new VerticalWall("WV", info[1], info[2]);
				maze.setMaze(wv, Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				}
				else if(info[0].equals("WH")) {			
					MazeSquare wh = new HorizontalWall("WH", info[1], info[2]);
					maze.setMaze(wh, Integer.parseInt(info[1]), Integer.parseInt(info[2]));	
				}
				else if(info[0].equals("DV")) {
					MazeSquare dv = new VerticalDoor("DV", info[1], info[2], info[3]);
					maze.setMaze(dv, Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				}
				else if(info[0].equals("DH")) {
					MazeSquare dh = new HorizontalDoor("DH", info[1], info[2], info[3]);
					maze.setMaze(dh, Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				}
				else if(info[0].equals("M")) {
					StringBuilder sb = new StringBuilder();
					for(int i = 3; i < info.length; i++)
					{
						sb.append(info[i] + " ");
					}
					MazeBlock m = new Message(sb.toString(), r, c);
					maze.setMaze(m, Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				}
				else if(info[0].equals("K")) {
					MazeBlock k = new Key(r, c, Integer.parseInt(info[3]));
					maze.setMaze(k, Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				}
				else if(info[0].equals("E")) {
					MazeBlock e = new Player("E", Integer.parseInt(info[1]), Integer.parseInt(info[2]));
					maze.setMaze(e, Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				}
				else if(info[0].equals("S")) {
					MazeBlock p = new Player("S", Integer.parseInt(info[1]), Integer.parseInt(info[2]));
					maze.setMaze(p, Integer.parseInt(info[1]), Integer.parseInt(info[2]));
					playerCount++;
				}
			}
				
			fileData.add(info[0] + "/" + info[1] + info[2]);
			line = br.readLine();
		}
		br.close();
		fr.close();
		LOG.fine(() -> "fileData = " + fileData);
		if(playerCount < 1) //when there are no players
		{
			throw new MazeException("Player cannot be found!");
		}
		else if(playerCount > 1) //when there are no players
		{
			throw new MazeException("More than 1 player Identified.");
		}
		return maze;
	}
}
