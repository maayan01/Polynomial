package testing;

import static org.junit.Assert.*;
import myMath.Monom;
import myMath.Polynom;

import org.junit.Test;

public class PolynomTest 
{
	/**
	 * Test method isZero
	 */
	@Test
	public void test_isZero()
	{
		Polynom p1 = new Polynom();
		boolean actual = p1.isZero();
		boolean expected= true;
		assertTrue(expected == actual);
	}
	/**
	 * Test method toString Polynom
	 */
	@Test
	public void test_toString() 
	{
		Monom m1 = new Monom(-2,1);
		Monom m2 = new Monom(-2,2);
		Monom m3 = new Monom(7,6);
		Polynom p1 = new Polynom();
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		String actual = p1.toString();
		//System.out.println(actual);
		String expected="+7X^6-2X^2-2X";
		assertTrue(actual.equals(expected));
	}
	/**
	 * Test method f(x)
	 */
	@Test
	public void test_f() 
	{
		double x=1;
		Monom m1 = new Monom(-2,1);
		Monom m2 = new Monom(-2,2);
		Monom m3 = new Monom(7,6);
		Polynom p1 = new Polynom();
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		double actual = p1.f(x);
		double expected=3;
		assertTrue(expected == actual);
	}
	/**
	 * Test method equals
	 */
	@Test
	public void test_equals() 
	{
		Monom m1 = new Monom(-2,1);
		Monom m2 = new Monom(-2,2);
		Monom m3 = new Monom(7,6);
		Polynom p1 = new Polynom();
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		Monom m01 = new Monom(-2,1);
		Monom m02 = new Monom(-2,2);
		Monom m03 = new Monom(7,6);
		Polynom p2 = new Polynom();
		p2.add(m1);
		p2.add(m2);
		p2.add(m3);
		
		boolean actual = p2.equals(p1);
		boolean expected= true;
		assertTrue(expected == actual);
	}
	
}
