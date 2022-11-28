package edu.curtin.app;

/*REFERENCE : https://www.javatpoint.com/custom-exception*/
public class MazeException extends Exception
{
    public MazeException(String msg)
    {
        super(msg);
    }
 
    public MazeException(String msg, Throwable cause)
    {
        super(msg, cause);
    } 
}
