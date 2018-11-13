package myMath;

import java.util.Comparator;
/**
 * this class reprasents a comperator that helps us sort the monoms in the polynoms
 * by their power, the biggest power will be first.
 * @author Dolev Saliman
 *
 */

public class Monom_Comperator implements Comparator<Monom> {
	// ******** add your code below *********
	public int compare(Monom a, Monom b) {
		if (a.get_power() == b.get_power())
			return 0;
		if (a.get_power() > b.get_power())
			return -1;
		
		else return 1;
	}

}
