package myMath;
import myMath.LinePlotTest;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// this main is shows how the graph plot is working and showing the function.
		String s = "0.2X^4-1.5x^3+3.0x^2-1.0x^1-5";
		Polynom a = new Polynom();
		a.Init(s);
		double x0 = -2;
		double x1 =6;
		a.graphPlot(x0,x1);
	}

}
