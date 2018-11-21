
package myMath;

import java.util.StringTokenizer;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */

public class Monom implements function
{
	//------------------------Fields--------------------------------
	private double _coefficient;  
	private int _power; 
	//----------------------Constructors----------------------------
	/**
	 * A constructor that accepts values
	 * @param a represents a coefficient
	 * @param b represents a power
	 */
	public Monom(double a, int b)
	{
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * A copy constructor 
	 * @param ot represents Monom object that we want to copy
	 */
	public Monom(Monom ot) 
	{
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * A constructor that accepts values type String
	 * @param str represents string Monom
	 */
	public Monom(String str) 
	{
		double coefficint=0;
		int power=0;
		StringTokenizer st = new StringTokenizer(str,"+,x^");
		if (st.hasMoreTokens())
		{
			 coefficint = (new Double(st.nextToken()).doubleValue());
		}
		if (st.hasMoreTokens())
		{
			 power =(new Integer(st.nextToken()).intValue());	
		}
		this.set_coefficient(coefficint);
		this.set_power(power);
	}
	//-------------------Get and Set fields------------------------
	/**
	 * returns the value of coefficient
	 * @return
	 */
	public double get_coefficient()
	{
		return this._coefficient;
	}
	/**
	 * returns the value of power
	 * @return
	 */
	public int get_power() 
	{
		return this._power ;
	}
	/**
	 * set the value of coefficient
	 * @param a
	 */
	private void set_coefficient(double a)
	{
		this._coefficient = a;
	}

	/**
	 * set the value of power
	 * @param p 
	 */
	private void set_power(int p) 
	{
		this._power = p;
	}
	//---------------------Functions---------------------------
	/** 
	 * Calculates the function f(x)
	 * @param x represents the value of the variable X
	 */
	public double f(double x)
	{
		return this._coefficient*(Math.pow(x,this._power));
	}

	/**
	 * Derivative of the current monom
	 * @return Derivative monom
	 */
	public Monom derivative() 
	{
		int power = this.get_power();
		double coefficient = this.get_coefficient ();
		return new Monom(power*coefficient,power-1);
	}
	/**
	 * Monom addition
	 * @param m1 represents the Monom that we would like to add
	 * @return the result of addition between two Monoms
	 */
	public void add (Monom m1)
	{
		if(m1.get_power()!=this.get_power()) 
		{
			throw new RuntimeException("Error: can not add two monoms with different coefficients");
		}
		this._coefficient=this._coefficient+m1._coefficient;
	}
	/**
	 * Multiply between the current Monom and another Monom
	 * @param m1
	 * @return the current Monom after multiplication.
	 */
	public void multiply (Monom m1)
	{
		this._power= this._power+m1._power;
		this._coefficient= this._coefficient*m1._coefficient;	
	}
	//----------------------------check---------------------------------
	public boolean isLegal() 
	{
		if(this.get_coefficient()==0) 
		{
			return false;
		}
	    if(this.get_power()<0) 
		{
			return false;
		}
		 return true;
	}
	//---------------------------Print-------------------------------
	/**
	 * print Monom
	 * @return String that represents a Monom
	 */
	public String toString()
	{
		int temp_c;
		String str_c="";
		String str_monom="";

		if(this._coefficient%1 == 0)
		{
			temp_c = (int)this._coefficient;
			if(this._coefficient >= 0)
			{
				str_c = "+"+temp_c;
			}
			else
			{
				str_c = ""+temp_c;
			}
		}
		else
		{
			if(this._coefficient > 0)
			{
				str_c = "+"+this._coefficient;
			}
			else
			{
				str_c = ""+this._coefficient;
			}
		}
		if(this.get_power() == 0)
		{
			str_monom =str_c;
			return str_monom;
		}
		else if(this.get_power() == 1)
		{
			str_monom =str_c+"X";
			return str_monom;
		}
		else
		{
			str_monom =str_c+"X^"+this.get_power();
			return str_monom;
		}
	}
}
