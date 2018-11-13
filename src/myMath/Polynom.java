package myMath;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Dolev
 *
 */
public class Polynom implements Polynom_able{
	// ********** add your code below ***********
	public ArrayList<Monom> monoms = new ArrayList<Monom>();

	public void Init(){
		monoms = new ArrayList<Monom>();
	}
	public void Init(String s){
		String _temp = s;
		String [] _arraytemp = _temp.split("(?=\\+|\\-)");
		int i = 1;
		String []b = _arraytemp[0].split("X\\^");
		double coeff = Double.parseDouble(b[0]);
		int power = Integer.parseInt(b[1]);
		Monom m = new Monom(coeff,power);
		this.monoms.add(m);
		while (i<_arraytemp.length){
			b = _arraytemp[i].split("X\\^");
			if (b.length==2) {
				coeff = Double.parseDouble(b[0]);
				power = Integer.parseInt(b[1]);
			}
			else {
				coeff = Double.parseDouble(b[0]);
				power = 0;
			}
			m = new Monom(coeff,power);
			this.monoms.add(m);
			i++;		
		}
		this.isZero();
		Collections.sort(this.monoms , new Monom_Comperator());
	}

	/**
	 * Add p1 to this Polynom
	 * @param p1
	 */
	public void add(Polynom_able p1){
		Iterator<Monom> it = p1.iteretor();
		Monom m = it.next();
		while (it.hasNext()){
			this.add(m);
			m = it.next();
		}
		this.add(m);
	}
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	public void add(Monom m1){
		Iterator<Monom> t = iteretor();
		Monom_Comperator a = new Monom_Comperator();
		int index = 0;
		if (this.monoms.isEmpty())
			this.monoms.add(m1);
		else if (this.monoms.size() == 1){
			Monom monom = new Monom(this.monoms.get(0));
			int i = a.compare(monom,m1);
			if (i == 0){
				monom.Add(m1);
				this.monoms.set(index, monom);

			}
			if (i == 1){
				this.monoms.add(m1);
			}
			if (i==-1)
				this.monoms.add(m1);
		}
		else while(t.hasNext()){
			Monom monom = new Monom(t.next());
			int i = a.compare(monom,m1);
			if (i == 0){
				monom.Add(m1);
				this.monoms.set(index, monom);
				break;
			}
			if (i == 1){
				this.monoms.add(m1);
				break;
			}
			if (!t.hasNext() && i == -1 ){
				this.monoms.add(m1);
				break;
			}
			index++;
		}
		Collections.sort(this.monoms , new Monom_Comperator());
	}
	/**
	 * Subtract p1 from this Polynom
	 * @param p1
	 */
	public void substract(Polynom_able p1){
		Polynom temp = new Polynom();
		temp = (Polynom) p1.copy();
		Iterator<Monom> it1 = temp.iteretor();
		it1.next();
		int i = 0;
		while ( it1.hasNext() ){// makes the polynom negetive
			double temp_coef = temp.monoms.get(i).get_coefficient();
			temp.monoms.get(i).set_coefficient(temp_coef*-1);
			it1.next(); i++;
		}
		double temp_coef = temp.monoms.get(i).get_coefficient();
		temp.monoms.get(i).set_coefficient(temp_coef*-1);

		this.add(p1);// doing a to the negetive polynom with the non negetive one.
		if (this.isZero()){// checks if after the adding the polynom become zero polynom 
			System.out.println("the polynom is a zero polynom");
		}
	}
	/**
	 * Multiply this Polynom by p1
	 * @param p1
	 */
	public void multiply(Polynom_able p1){
		Iterator<Monom> it = p1.iteretor();
		Monom m = it.next();
		Polynom temp = new Polynom();

		while (it.hasNext()){
			int i = 0;
			while (i<this.monoms.size()){
				Monom monom = new Monom(this.monoms.get(i));
				monom.Multiply(m);
				temp.monoms.add(monom);
				i++;
			}
			m = it.next();
		}
		int i = 0;
		while (i<this.monoms.size()){
			Monom monom = new Monom(this.monoms.get(i));
			monom.Multiply(m);
			temp.monoms.add(monom);
			i++;
		}
		this.monoms = temp.monoms;
	}


	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1
	 * @return true iff this pulynom represents the same function ans p1
	 */
	public boolean equals (Polynom_able p1){
		boolean a = true;
		Polynom p2 = (Polynom) p1;
		Iterator<Monom> _original = this.iteretor();
		Iterator<Monom> temp = p1.iteretor();
		if(this.monoms.size() == p2.monoms.size() ){
			Monom _a = _original.next();
			Monom _b = temp.next();
			while (_original.hasNext()){
				if (_a.get_coefficient()!=_b.get_coefficient() && _a.get_power()!= _b.get_power())
					return !a;
				else { 
					_a = _original.next();
					_b = temp.next();
				}
			}
			if (_a.get_coefficient()==_b.get_coefficient() && _a.get_power()== _b.get_power())
				return a;
		}
		return !a;

	}
	/**
	 * Test if this is the Zero Polynom
	 * @return True if its Zero Polynom
	 */
	public boolean isZero(){
		Iterator<Monom> t = this.iteretor(); // makes an iterator
		Monom m = t.next();
		while (t.hasNext()){ 
			if (m.IsZero()) // cheks if the monom is zero
				t.remove();
			m = t.next();
		}
		if (m.IsZero())
			t.remove();
		if (this.monoms.isEmpty())  // if the list is empty after thr iteration return true
			return true;
		return false;
	}
	/**
	 * The root is found with the Bisection method to understand the method see video:
	 *  https://www.youtube.com/watch?v=QE86bad1-JQ&t=311s
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x2<=x2 && (ii) f(x2)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return
	 */
	public double root(double x0, double x1, double eps){
		double x = 0;
		x = (x0 + x1) / 2; // bring the middle beetwean two points
		if (Math.abs(this.f(x))<eps) // if the abs of the function is less than the epsilon
			return x;
		while ((x1-x0) > eps){
			if (this.f(x)< 0 ){// moving the left point to the middle
				x0 = (x0 + x1) / 2;
				x = (x0 + x1) / 2;
			}else if (this.f(x) > 0 ){// moving the right point to the middle
				x1 = (x0 + x1) / 2;
				x = (x0 + x1) / 2;
			}
		}
		return x; 
	}
	/**
	 * create a deep copy of this Polynum
	 * @return 
	 */
	public Polynom copy(){
		Iterator<Monom> it = this.iteretor();
		Monom m = it.next();
		Polynom p1 = new Polynom();
		while(it.hasNext()){
			p1.monoms.add(m);
			m = it.next();
		}
		p1.monoms.add(m);
		return p1;
	}
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return
	 */
	public Polynom_able derivative(){
		Polynom p_temp = this.copy();
		int i = 0;
		while(i<this.monoms.size()){
			p_temp.monoms.get(i).Derivate(); // takes avery monom that is in the polynom and make a derivation on it
			i++;
		}
		p_temp.isZero();
		return p_temp;
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	public double area(double x0,double x1, double eps){
		double _area = 0;
		Iterator<Monom> it = this.iteretor();
		Monom m = it.next();
		_area = _area + m.area(x0, x1, eps);
		while (it.hasNext()){
			m = it.next();
			_area = _area + m.area(x0, x1, eps);
		}
		return Math.abs(_area);
	}
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 * @return
	 */
	public Iterator<Monom> iteretor() {
		return this.monoms.iterator();

	}
	/**
	 * This fuction computes the value of F(x), by placing the x value
	 * in each monom in the polynom
	 */
	public double f(double x) {
		double sum = 0; 
		Iterator<Monom> a = iteretor();
		while (a.hasNext())
			sum = sum + a.next().f(x); // sum the value of each monom in the polynom
		return sum;
	}
	/**
	 * This function ptints the polynom to the screen by this structure
	 * [a.0X^b +/- a1.0x^b1 +/- .... an.0X^bn]
	 */
	public String toString(){
		String s = "";
		Iterator<Monom> a = iteretor();
		while (a.hasNext()){
			Monom m = new Monom(a.next());
			s = s + m.get_coefficient() + "X^" + m.get_power()+"+";
		}
		s = s.replace( "+-", "-");
		s = s.replace( "X^0", "");
		s = s.substring(0, s.length()-1);
		return s+"" ;
	}
	/**
	 * this function gets an polynom an make a make a graphic show
	 * of the polynom in xChart jar.
	 * @param p
	 * @return a drweing of the polynom.
	 */
	public void GraphicPolynom() {
		ArrayList<Double> xData = new ArrayList<Double>();
		ArrayList<Double> yData = new ArrayList<Double>();
		for (double i = -2; i < 2; i+=0.1) {
			xData.add(i);
			yData.add(this.f(i));
		}
		System.out.println(xData.toString());
		System.out.println(yData.toString());
		XYChart chart = QuickChart.getChart("sample", "X" , "Y" , "y(x)" , xData, yData);
		new SwingWrapper<XYChart>(chart).displayChart();
	      

	}
}

