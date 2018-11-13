package myMath;

import java.util.ArrayList;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.MatlabTheme;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "0.2X^4-1.5X^3+3.0X^2-1.0X^1-5.0";
		Polynom a = new Polynom();
		a.Init(s);
		System.out.println(a.area(-2.0, 6.0, 40));
		System.out.println(a.toString());
		ArrayList<Double> xData = new ArrayList<Double>();
		ArrayList<Double> yData = new ArrayList<Double>();
		for (double i = -2; i <= 6; i+=0.5) {
			xData.add(i);
			yData.add(a.f(i));
		}
		XYChart chart = QuickChart.getChart("sample", "X" , "Y" , "y(x)" , xData, yData);
		 new SwingWrapper(chart).displayChart();

	}

}
