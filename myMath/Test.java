package myMath;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * test the polynom
 * input A string representing the polynomial
 * output testing the polynom functionality
 * @author maayan
 *
 */
public class Test 
{
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		String str;
		Polynom p=null;
		double x=1;
		System.out.println("Enter a Polynom in the following form ax^b ");
		try
		{
			str = in.nextLine();
			p= new Polynom(str);
			
			System.out.println("f(x)="+p);
			System.out.println("f("+x+")="+p.f(x));
			System.out.println("f'(x)="+p.derivative());
			
			in.close();
		}
		catch (Exception e) 
		{
			System.err.println("** unknown error !! **"+e);
			System.exit(-2); // exit the java program!
		}
	}
}
