package myMath;

import java.util.*;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able
{
	//------------------------Fields--------------------------------
	private ArrayList<Monom> polynomial ;
	private static final Monom_Comperator COMP = new Monom_Comperator();

	//----------------------Constructors----------------------------
	/**
	 * empty constructor
	 */
	public Polynom()
	{
		this.polynomial = new ArrayList<Monom>();
	}
	/**
	 * constructor that accepts a string of polynom
	 * @param str represents polynom
	 * @throws PolynomException
	 */
	public Polynom(String str) throws RuntimeException
	{
		this.polynomial = new ArrayList<Monom>();
		if(str == null)
		{
			throw new RuntimeException("Polynom canot be null!");
		}
		if(str.contains("--")|| str.contains("++")||str.contains("+-")||str.contains("-+"))
		{
			throw new RuntimeException("Polynom canot be include signs like -- or ++ or -+ or +_");
		}
		String[] first = str.split("(?=-)");
		Monom tempM=null;
		for(int i=0;i<first.length;i++)
		{
			String[] second = first[i].split("(?=[+])");
			for(int j=0;j<second.length;j++)
			{
				tempM = new Monom(second[j]);
				polynomial.add(tempM);
			}
			polynomial.sort(COMP);
		}
	}
	//--------------------------initialization & copy----------------------------------------
	/**
	 * create a deep copy of this Polynom
	 * @return new polynom copy 
	 */
	public Polynom_able copy()
	{
		Polynom res = new Polynom();
		Monom temp=null;
		while (this.iteretor().hasNext()) 
		{
			temp =this.iteretor().next();
			res.add(temp);
		}
		return res;
	}

	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 * @return
	 */
	public Iterator<Monom> iteretor() 
	{
		return this.polynomial.iterator();
	}
	//----------------------------manage arraylist-----------------------------------
	
	/**
	 * Adds another polynom to the current polynom
	 * @param p1 the new polynom we want to add
	 */
	public void add(Polynom_able p1)
	{
		Iterator<Monom> iter = p1.iteretor();
		while (iter.hasNext())
		{
			polynomial.add(iter.next());
		}
	}
	
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	public void add(Monom m1)
	{
		if(m1.isLegal()) 
		{
			boolean samePower = false;
			Iterator<Monom> iter = this.iteretor();
			while(!samePower && iter.hasNext()) 
			{
				Monom temp_m = iter.next();
				if(temp_m.get_power()==m1.get_power()) 
				{
					temp_m.add(m1);
					samePower = true;
					if(temp_m.get_coefficient()==0) 
					{ 
						iter.remove();
					}
				}
			}
			if(!samePower) 
			{
				polynomial.add(m1);
			}
		}
	}

	/**
	 * Subtract p1 from this Polynom
	 * @param p1
	 */
	public void substract(Polynom_able p1)
	{
		int index=0;
		Monom temp;
		while (p1.iteretor().hasNext())
		{
			temp=p1.iteretor().next();
			index =this.polynomial.indexOf(temp);
			this.polynomial.remove(index);
		}
	}
	/**
	 * Multiply this Polynom by p1
	 * @param p1
	 */
	public void multiply(Polynom_able p1)
	{
		Monom m0,m1;
		while (this.iteretor().hasNext()) 
		{
			m0 =this.iteretor().next();
			while (p1.iteretor().hasNext()) 
			{
				m1=p1.iteretor().next();
				m0.multiply(m1);
			}
			this.iteretor();
		}	
	}
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1
	 * @return true if this polynom represents the same function as p1
	 */
	public boolean equals (Polynom_able p1)
	{
		Monom_Comperator comp=null;
		Monom temp1=null;
		Monom temp2=null;
		while (this.iteretor().hasNext()) 
		{
			temp1 =this.iteretor().next();
			while (p1.iteretor().hasNext()) 
			{
				temp2 =p1.iteretor().next();
				if(comp.compare(temp2, temp1)== 0)
				{

				}
				if((temp1.get_power() != temp2.get_power() )&&(temp1.get_coefficient() != temp2.get_coefficient()))
				{
					return false;
				}
			}
		}
		return true;
	}
	//------------------------check--------------------------
	/**
	 * Test if this is the Zero Polynom
	 * @return true if it is a Zero Polynom
	 */
	public boolean isZero()
	{
		if(this.polynomial.size() ==0) 
		{
			return true;
		}
		return false;
	}
	//--------------------------------------------------
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x2<=x2 && (ii) f(x2)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return
	 */
	public double root(double x0, double x1, double eps)
	{
		double y0 = this.f(x0);
		double y1 = this.f(x1);
		if(y0*y1>0) {
			throw new RuntimeException("Error: f(x0) and f(x1) should be on oposite sides of the X asix");
		}
		//double delta_x = Math.abs(x0-x1);
		double delta_y = Math.abs(y0-y1);
		//	if(debug) {
		//		System.out.println("f("+x0+") = "+y0+"    f("+x1+") = "+y1+"   dx = "+delta_x);
		//	}
		//	if (delta_x>eps || delta_y>eps) {
		if (delta_y>eps) {
			double x_mid = (x0+x1)/2;
			double y_mid = this.f(x_mid);
			double dir = y0*y_mid;
			if(dir<0) {
				return root(x0,x_mid, eps);
			}
			else {
				return root(x_mid, x1, eps);
			}
		}
		return x0;		
	}

	public double area(double x0,double x1, double eps)
	{
		/**
		 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
		 * see: https://en.wikipedia.org/wiki/Riemann_integral
		 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
		 */
		
		
		if(x0>x1) 
		{
			double flg=x0;
			x0=x1;
			x1=flg;
		}
		double length=Math.abs(x1-x0);
		double count=0;
		while(length>0) 
		{
			if(f(x1)>0) count+=eps*f(x0);
			x0=x0+eps;
			length=length-eps;
		}
		return count;
	
	}

	//---------------------Functions---------------------------
	/**Calculates the value of the polynom
	 * @param x represents the value of the variable X
	 * @return res is the value of the polynom
	 */
	public double f(double x) 
	{
		Iterator<Monom> iter = this.iteretor();
		double res = 0;
		Monom m;
		while(iter.hasNext()) 
		{
			m = iter.next();
			res =res+ m.f(x);
		}
		return res;
	}
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return
	 */
	public Polynom_able derivative()
	{
		Iterator<Monom> iter = this.iteretor();
		Monom temp=null;
		Polynom res = new Polynom();
		if(!this.isZero())
		{
			while (iter.hasNext()) 
			{
				temp =iter.next();
				res.add(temp.derivative());	
			}
		}
		res.polynomial.sort(COMP);
		return res;
	}	
	//---------------------------Print-------------------------------
	/** string that represents the Polynom
	 *@return 
	 */
	public String toString()
	{
		polynomial.sort(COMP);
		String str="";
		Monom temp;
		Iterator<Monom> iter = this.iteretor();
		if(this.isZero())
		{
			return "0";
		}
		while (iter.hasNext()) 
		{
			temp = iter.next();
			str=str+temp.toString();
		}
		return str;
	}
}
