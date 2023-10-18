import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Arc
{
	private Point start;
	private Point end;
	
	public Arc(Point start, Point end)
	{
		this.start = start;
		this.end = end;
	}

	public Point getStart(){return start;}
	public Point getEnd(){return end;}
	public void setStart(int x, int y){
		this.start.x = x;
		this.start.y = y;
	}
	public void setEnd(int x, int y){
		this.end.x = x;
		this.end.y = y;
	}
	public void drawArc(Graphics g)
	{
		if (start != null)
		{
            g.setColor(Color.RED);
            g.drawLine(start.x, start.y, end.x, end.y);
			if(MyPanel.state == MyPanel.State.ORDERED)
				drawArrowLine(g);
        }
	}
	/*
		public void drawArrow(Graphics g){//m : n  9 : 10
		Point arrowHead = new Point((int)Math.round((9.0f* end.x + 10.0f* start.x)/19.0f),(int)Math.round((9.0f * end.y + 10.0f * start.y)/19.0));
		//m : n 7 : 10
		Point arrowBody = new Point((int)Math.round((7.0f* end.x + 10.0f* start.x)/17.0f),(int)Math.round((7.0f * end.y + 10.0f * start.y)/17.0));
	}*/
	private void drawArrowLine(Graphics g) {
		float ratio = (float)(MyPanel.node_diam/2)/(float)Math.sqrt((Math.pow(start.x - end.x,2) + Math.pow(start.y - end.y,2)));
		Point arrowHead = new Point((int)Math.round((1-ratio)*end.x + ratio*start.x),(int)Math.round((1-ratio)*end.y + ratio * start.y));
		int dx = arrowHead.x - start.x, dy = arrowHead.y - start.y;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - 7, xn = xm, ym = 7, yn = -7, x;
		double sin = dy / D, cos = dx / D;

		x = xm*cos - ym*sin + start.x;
		ym = xm*sin + ym*cos + start.y;
		xm = x;

		x = xn*cos - yn*sin + start.x;
		yn = xn*sin + yn*cos + start.y;
		xn = x;

		int[] xpoints = {arrowHead.x, (int) xm, (int) xn};
		int[] ypoints = {arrowHead.y, (int) ym, (int) yn};

		//g.drawLine(start.x, start.y, arrowHead.x, arrowHead.y);
		g.fillPolygon(xpoints, ypoints, 3);
	}
	public static void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm*cos - ym*sin + x1;
		ym = xm*sin + ym*cos + y1;
		xm = x;

		x = xn*cos - yn*sin + x1;
		yn = xn*sin + yn*cos + y1;
		xn = x;

		int[] xpoints = {x2, (int) xm, (int) xn};
		int[] ypoints = {y2, (int) ym, (int) yn};

		g.drawLine(x1, y1, x2, y2);
		g.fillPolygon(xpoints, ypoints, 3);
	}
}
