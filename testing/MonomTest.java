package testing;

import static org.junit.Assert.*;
import myMath.Monom;

import org.junit.Test;

public class MonomTest 
{
	/**
	 * Test method f(x)
	 */
	@Test
	public void test_f() 
	{
		Monom m1 = new Monom(4,2);
		double actual = m1.f(2);
		double expected =16;
		assertEquals(expected, actual,0);
	}
	/**
	 * Test method derivative
	 */
	@Test
	public void test_derivative() 
	{
		Monom m1 = new Monom(4,1);
		Monom actual = m1.derivative();
		Monom expected = new Monom (4,0);
		assertEquals(expected.get_coefficient(), actual.get_coefficient(),0);
		assertEquals(expected.get_power(),actual.get_power(),0);
	}
	/**
	 * Test method addition
	 */
	@Test
	public void test_add() 
	{
		Monom m1 = new Monom(4,1);
		Monom m2 = new Monom(4,1);
		m1.add(m2);
		Monom actual = m1;
		Monom expected = new Monom (8,1);
		assertEquals(expected.get_coefficient(), actual.get_coefficient(),0);
	}
	/**
	 * Test method multiplication
	 */
	@Test
	public void test_multiply() 
	{
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(4,1);
		m1.multiply(m2);
		Monom actual = m1;
		Monom expected = new Monom (8,3);
		assertEquals(expected.get_coefficient(), actual.get_coefficient(),0);
		assertEquals(expected.get_power(),actual.get_power(),0);
	}
	/**
	 * Test method isLegal
	 */
	@Test
	public void test_isLegal() 
	{
		Monom m1 = new Monom(1,-1);
		boolean actual = m1.isLegal();
		boolean expected= false;
		assertTrue(expected == actual);
	}
	
	/**
	 * Test method toString Monom
	 */
	@Test
	public void test_toString() 
	{
		Monom m1 = new Monom(-2,1);
		String actual = m1.toString();
	//	System.out.println(actual);
		String expected="-2X";
		assertTrue(actual.equals(expected));
	}

}
