package myMath;

import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.axes.Axis;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.axes.LinearRenderer2D;
import de.erichseifert.gral.plots.axes.LogarithmicRenderer2D;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {
    public LinePlotTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        DataTable XYmax_data = new DataTable(Double.class, Double.class);
        String s = "0.2x^4-1.5x^3+3.0x^2-1.0x^1-5";
		Polynom a = new Polynom();
		a.Init(s);
		System.out.println(a.toString());
        for (double x = -2.0; x <= 6.0; x+=0.5) {
            double y = a.f(x) ;
            data.add(x, y);
        }
        Polynom a_d = new Polynom();
        a_d = (Polynom) a.derivative();
        System.out.println(a_d.toString());
        for (double x = -2.0; x <= 6.0; x+=0.01) {
        	 double value = a_d.f(x);
        	 value =Double.parseDouble(new DecimalFormat("##.#").format(value));
        	 //System.out.println(value);
			if ( value == 00.0){
				XYmax_data.add(x , a.f(x));
				System.out.println(a.f(x));
			}
		}
        
        
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);      

    }

    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
    }
}