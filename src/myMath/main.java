package myMath;
import myMath.LinePlotTest;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "0.2X^4-1.5X^3+3.0X^2-1.0X^1-5";
		Polynom a = new Polynom();
		a.Init(s);
		double x0 = -2;
		double x1 =6;
		a.graphPlot(x0,x1);

	}

}
