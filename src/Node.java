import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class Node
{
	private int coordX;
	private int coordY;
	private int number;
	
	public Node(int coordX, int coordY, int number)
	{
		this.coordX = coordX;
		this.coordY = coordY;
		this.number = number;
	}

	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Point getPoint(){return new Point(this.coordX, this.coordY);}

	public void drawNode(Graphics g, int node_diam)
	{
		g.setColor(Color.ORANGE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 18));

        g.fillOval(coordX - node_diam / 2, coordY - node_diam / 2, node_diam , node_diam);
        g.setColor(Color.WHITE);
        g.drawOval(coordX - node_diam / 2, coordY - node_diam / 2, node_diam, node_diam);
        if(number < 10)
        	g.drawString(((Integer)number).toString(), coordX+17 - node_diam / 2, coordY+28 - node_diam / 2);
        else
        	g.drawString(((Integer)number).toString(), coordX+12 - node_diam / 2, coordY+28 - node_diam / 2);
	}

}
