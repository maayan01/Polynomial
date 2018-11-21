package draw;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

import myMath.Monom;
import myMath.Polynom;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.XYPlot.XYLegend;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;


public class LinePlotTest extends JFrame
{

	//----------------------Constructors----------------------------
	public LinePlotTest() 
	{
		super("Polynom Graph");
		//setLayout(new FlowLayout());
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 400);

		DataTable data = new DataTable(Double.class, Double.class);
		DataTable data1 = new DataTable();

		Polynom p1 = new Polynom();
		p1.add(new Monom(0.2,4));
		p1.add(new Monom(-1.5,3));
		p1.add(new Monom(3.0,2));
		p1.add(new Monom(-1,1));
		p1.add(new Monom(-5,0));
		//--------------------X values between --------------------------
		for (double x = -2.0; x <= 6.0; x+=0.5) 
		{
			double y = p1.f(x);
			data.add(x, y);
			System.out.println(x+", "+y);
		}

		XYPlot plot = new XYPlot(data);

		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);

	}
	public static void main(String[] args)
	{
		LinePlotTest frame = new LinePlotTest();
		frame.setVisible(true);

	}
}
