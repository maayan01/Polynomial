package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> 
{
	/**
	 * Overriding the compare method to sort the Monom 
	 * @param m0 represents Monom 
	 * @param m1 represents another Monom 
	 */
	public int compare(Monom m0, Monom m1)
	{
		int res = m1.get_power() - m0.get_power();
		return res;
	} 
}
