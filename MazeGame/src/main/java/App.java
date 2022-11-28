package edu.curtin.app;
import java.util.*;
import java.io.*;
import java.util.logging.Logger;

@SuppressWarnings("PMD.GuardLogStatement")  //to avoid unnessassary if statements
public class App
{	
	private static final Logger LOG = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args)
	{
		String fileName = " ";
		try
	  	{
	  		@SuppressWarnings("PMD.CloseResource")  //the resource is closed below
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter File Name : ");
			fileName = sc.nextLine();
	  		ReadFile rf = new ReadFile();
			Maze maze = rf.readFile(fileName);
			GameMechanics gm = new GameMechanics();
			
			String output = gm.locatePlayer(maze).getConsoleOutput();
			LOG.info("Initial Output = " + output);
			
			maze.print();	
			System.out.println(output);

			while(gm.chWin)
			{
				
				System.out.println("Input : ");
				String input = sc.nextLine();
				
				if(input.equals("S") || input.equals("s"))
				{
					output = gm.moveDown(maze);
					LOG.info("Output at Down = " + output);	
				}
				else if(input.equals("N") || input.equals("n"))
				{
					output = gm.moveUp(maze);
					LOG.info("Output at Up = " + output);
				}
				else if(input.equals("W") || input.equals("w"))
				{
					output = gm.moveLeft(maze);
					LOG.info("Output at Left = " + output);
				}
				else if(input.equals("E") || input.equals("e"))
				{
					output = gm.moveRight(maze);
					LOG.info("Output at Right = " + output);
				}
				else
				{
					output = "Incorrect Input. Please try again with (w, e, n, s)";
				}
				
				LOG.info("Win con = " + gm.chWin);
				System.out.println("\033[2J" + "\033[H");
				maze.print();	
				System.out.println(output);	
			}
			sc.close();
	  	}
	  	catch(MazeException e)
	  	{
	  		System.out.println("Exception Occured : " + e);
	  	}
	  	catch(FileNotFoundException e)
		{
			System.out.println("Cannot find file " + fileName);
		}
		catch(NumberFormatException e)
		{
			System.out.println("File contains non-numeric data as coordinates");
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong" + e. getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Exception has occured : " + e. getMessage());
		}
						
	}
}
