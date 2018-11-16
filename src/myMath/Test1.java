package myMath;

import static org.junit.Assert.*;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;
import org.junit.Test;

public class Test1 {

	@Test
	public void testInitString() {
		String s = "4.0X^6-3.0X^8";
		Polynom p = new Polynom();
		p.Init(s);
		if(p.monoms.isEmpty())
			fail("the Init is not working for this strings");
		if(p.monoms.size()==1)
			fail ("one of the monom in the string didnt entered the poliynom");
		else {
			System.out.println(p.toString());
		}
	}


	@Test
	public void testAddPolynom_able() {
		String s = "6.0X^4+5.0X^2";
		Polynom a = new Polynom();
		a.Init(s);
		String t = new String("3.0X^4+2.0X^1");
		Polynom b = new Polynom();
		b.Init(t);
		a.add(b);
		if (a.monoms.get(0).get_coefficient()!= 9 || a.monoms.get(0).get_power()!=4 
				|| a.monoms.get(1).get_coefficient() == 6 || a.monoms.get(0).get_power()!= 4)
			fail("Not yet implemented");
	}

	@Test
	public void testAddMonom() {
		String s = "6.0X^4+5.0X^2";
		Polynom a = new Polynom();
		a.Init(s);
		Monom m = new Monom(6.0 ,2);
		a.add(m);
		if (a.monoms.get(0).get_coefficient() != 6.0 || a.monoms.get(0).get_power()!= 4 || 
				a.monoms.get(1).get_coefficient() != 11.0 ||a.monoms.get(1).get_power() != 2){
			fail("There is a problem in the adding fuction of polynom with monom");	
		}
	}

	@Test
	public void testSubstract() {
		String s = "6.0X^4+5.0X^2";
		Polynom a = new Polynom();
		a.Init(s);
		String t = new String("3.0X^4+2.0X^1");
		Polynom b = new Polynom();
		b.Init(t);
		a.substract(b);
		System.out.println(a.toString());
		if (a.monoms.get(0).get_coefficient() != 3.0 || a.monoms.get(0).get_power()!= 4 || 
				a.monoms.get(1).get_coefficient() != 5.0 ||a.monoms.get(1).get_power() != 2 ||
				a.monoms.get(2).get_coefficient() != -2.0 ||a.monoms.get(2).get_power() != 1){
			fail("Not yet implemented");
		}
	}

	@Test
	public void testMultiply() {
		String s = "6.0X^4+5.0X^2";
		Polynom a = new Polynom();
		a.Init(s);
		String t = new String("3.0X^4+2.0X^1");
		Polynom b = new Polynom();
		b.Init(t);
		a.multiply(b);
		if (a.monoms.size() != 4) 
			fail("there is a problem with the multiply function");
	}

	@Test
	public void testEqualsPolynom_able() {
		String s = "6.0X^4+5.0X^2";
		Polynom a = new Polynom();
		a.Init(s);
		String t = "6.0X^4+5.0X^2";
		Polynom b = new Polynom();
		b.Init(t);
		if (!a.equals(b))
			fail("The fuctiom of checking if the polynoms are equal have a problem");
	}

	@Test// this fuction is running in Init and in substract
	public void testIsZero() {
	}

	@Test
	public void testRoot() {
		Polynom b = new Polynom();
		Monom m2 = new Monom(1, 3);
		b.add(m2);
		Monom m3 = new Monom(3, 1);
		b.add(m3);
		Monom h = new Monom(-5, 0);
		b.add(h);
		if (b.root(1, 2, 0.01) != 1.15234375)
			fail ( "The root is wrong");

	}

	// The funtion copy is implemented in substract fuction
	@Test
	public void testCopy() {
	}

	@Test
	public void testDerivative() {
		Polynom b = new Polynom();
		Monom m2 = new Monom(1, 3);
		b.add(m2);
		Monom m3 = new Monom(3, 1);
		b.add(m3);
		Monom h = new Monom(5, 0);
		b.add(h);
		Polynom _temp  = (Polynom) b.derivative();
		if (_temp.monoms.size() != 2 )
			fail("Not yet implemented");
	}

	@Test
	public void testArea() {
		Polynom b = new Polynom();
		Monom m2 = new Monom(1, 3);
		b.add(m2);
		Monom m3 = new Monom(3, 1);
		b.add(m3);
		Monom h = new Monom(5, 0);
		b.add(h);
		if (b.area(0, 1, 20) != 6.850625)
			fail("Not yet implemented");
	}

	@Test // this fuction works as iterator in every fuction
	public void testIteretor() {
	}

	@Test
	public void testF() {
		String s = "6.0X^4+5.0X^2";
		Polynom a = new Polynom();
		a.Init(s);
		double ans = a.f(2);
		if (ans != 116)
			fail("Not yet implemented");
	}

	@Test // this fuction implements in Init test
	public void testToString() {
	}
	@Test
	public void testgraphPlot() {
		String s = "0.2X^4-1.5X^3+3.0X^2-1.0X^1-5";
		Polynom a = new Polynom();
		a.Init(s);
		double x0 = -2;
		double x1 =6;
		a.graphPlot(x0,x1);
		 if (a.isZero() || a.monoms.isEmpty()) {
			 fail("problem with the polynom");
		 }
	}
	@Test // this test checks the area under and above of the function
	public void testareaUnder() {
		String s = "0.2X^4-1.5X^3+3.0X^2-1.0X^1-5";
		Polynom a = new Polynom();
		a.Init(s);
		double x0 = -2;
		double x1 =6;
		a.graphPlot(x0,x1);
		double eps = 0.01;
		double area = a.areaUnder(x0, x1, eps);
		if (area !=25.183633821940276) {
			fail ("the area function is not working right");
		}
	}
}
