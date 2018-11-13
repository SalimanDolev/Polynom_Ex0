
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Dolev Saliman
 *
 */

public class Monom implements function{
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	// ***************** add your code below **********************
	public int get_power() {
		return this._power;
	}
	public double get_coefficient() {
		return this._coefficient;
	}	
	/**
	 * The function f(x) copmutes the value of f(x) in x point
	 * @param the x is from the user. 
	 */

	public double f(double x){
		double f = Math.pow(x, get_power());
		f = f*get_coefficient();
		return f;
	}
	/**
	 * Compute Riemann's Integral over this Monom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @param x0
	 * @param x1
	 * @param eps represents the number of rectantangles.
	 * @return
	 */
	public double area(double x0, double x1, double eps){
		double area_above = 0;
		double deltaX = (x1-x0)/eps;
		if(this.get_power()==0)
			return this.get_coefficient();
		for (int i = 0; i <= eps; i++) {
			area_above =area_above + (deltaX * f(x0 + i*deltaX));
		}

		return area_above;
	}
	/**
	 * this fuction adding monom a to this monom
	 * @param a is monom
	 */

	public void Add(Monom a){
		if (a.get_power() == this.get_power()){
			this.set_coefficient(a.get_coefficient() + this._coefficient);
			this.set_power(this.get_power());

			if (this._coefficient  == 0){System.out.println("the monom coefficient is zero");}
		}
		else System.out.println("the monom power is not the same");
	}
	/**
	 * This fuction derivate the monom, a.0X^b ==> a.0*bX^(b-1)
	 * 
	 */

	public void Derivate(){
		this._coefficient = this._coefficient * (double)this._power;
		this._power = this._power - 1;
	}

	//Multiply a Monom

	public void Multiply (Monom a){
		this._coefficient = this._coefficient * a.get_coefficient();
		this._power = this._power + a._power;
	}

	// checks if the monom isZero

	public boolean IsZero(){
		boolean a = true;
		if(this.get_coefficient()==0)
			return a;
		else return !a;
	}

	public boolean PowerIsZero(){
		boolean a = true;
		if(this.get_power()==0)
			return a;
		else return !a;
	}

	//****************** Private Methods and Data *****************

	public void set_coefficient(double a){
		this._coefficient = a;
	}
	public void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; // coefficient of the monom 
	private int _power; // the power of the monom
}
