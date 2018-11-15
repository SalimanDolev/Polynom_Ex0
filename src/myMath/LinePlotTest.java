package myMath;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {
    public LinePlotTest(Polynom a , double x0 , double x1) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);// the graph databse
        DataTable XYmax_data = new DataTable(Double.class, Double.class);// the x & y max points database.
        //String s = "0.2X^4-1.5X^3+3.0X^2-1.0X^1-5";
		//Polynom a = new Polynom();
		//a.Init(s);
		System.out.println(a.area(-2, 6 , 30));
		// this for loop entring the data for the function.
		System.out.println(a.toString());
        for (double x = x0; x <= x1; x+=0.1) {
            double y = a.f(x) ;
            data.add(x, y);
        }
        // making a copy of the original polynom, and derivative it.
        Polynom b = new Polynom();
        b = a.copy();
        b = (Polynom) b.derivative();
        
        
        // find the max nd min points
        for (double x = -2.0; x <= 6.0; x+=0.1) {
        	 double value = b.f(x);
        	 value = Double.parseDouble(new DecimalFormat("#.##").format(value));
			if ( value < 0.15&& value >-0.10){
				XYmax_data.add(x , a.f(x));
			}
		}
        
        //get the graph scale right.
        XYPlot plot = new XYPlot(data , XYmax_data);
        plot.getAxis(XYPlot.AXIS_X).setRange(0.0, 20.0);
        plot.getAxis(XYPlot.AXIS_Y).setRange(0.0, 20.0);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        
        //add the max and min points to the data series
        DataSeries series1 = new DataSeries("Series 1", XYmax_data,0 ,0 );
        PointRenderer points1 = new DefaultPointRenderer2D();
        points1.setShape(new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
        points1.setColor(new Color(0.0f, 0.3f, 1.0f, 0.3f));
        
        //add the max and min points to the graph. 
        plot.setPointRenderers(series1, points1);
        
        // get the graph be without points.
        plot.setPointRenderers(data, null);
        
    }
    // Get the graph displayed.
   // public static void main(String[] args) {
     //   LinePlotTest frame = new LinePlotTest(null, securityWarningPointX, securityWarningPointX);
       // frame.setVisible(true);
    //}
}