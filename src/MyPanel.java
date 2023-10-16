import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private int nodeNr = 1;
	private int node_diam = 30;
	private Vector<Node> listaNoduri;
	private Vector<Arc> listaArce;
	Point pointStart = null;
	Point pointEnd = null;
	boolean isDragging = false;
	public MyPanel()
	{
		listaNoduri = new Vector<Node>();
		listaArce = new Vector<Arc>();
		// borderul panel-ului
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseListener(new MouseAdapter() {
			//evenimentul care se produce la apasarea mousse-ului
			public void mousePressed(MouseEvent e) {
				pointStart = e.getPoint();
			}
			
			//evenimentul care se produce la eliberarea mousse-ului
			public void mouseReleased(MouseEvent e) {
				if (!isDragging) {
					addNode(e.getX(), e.getY());
				}
				else {
					if(!(checkColisionNode((int)pointStart.getX(), (int)pointStart.getY(), node_diam/2) ||
					checkColisionNode((int)pointEnd.getX(), (int)pointEnd.getY(), node_diam/2))) {
						pointStart = Objects.requireNonNull(returnNodeFromPoint((int) pointStart.getX(), (int) pointStart.getY())).getPoint();
						pointEnd = Objects.requireNonNull(returnNodeFromPoint((int) pointEnd.getX(), (int) pointEnd.getY())).getPoint();
						Arc arc = new Arc(pointStart, pointEnd);
						listaArce.add(arc);
					}
					repaint();
				}
				pointStart = null;
				isDragging = false;
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			//evenimentul care se produce la drag&drop pe mousse
			public void mouseDragged(MouseEvent e) {
				pointEnd = e.getPoint();
				isDragging = true;
				repaint();
			}
		});
	}

	private boolean checkColisionNode(int targetX, int targetY, int distance){
		int x,y;
		for(Node it : listaNoduri){
			x = it.getCoordX();
			y = it.getCoordY();
			if((float)Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2)) <= distance)
				return false;
		}
		return true;
	}

	private Node returnNodeFromPoint(int targetX, int targetY){
		for(Node it:listaNoduri){
			if((float)Math.sqrt(Math.pow(targetX - it.getCoordX(), 2) + Math.pow(targetY - it.getCoordY(), 2)) <= node_diam)
				return it;
		}
		return null; //nu s-a gasit punctul in vecinatatea niciunui nod
	}

	//metoda care se apeleaza la eliberarea mouse-ului
	private void addNode(int x, int y) {
		if(!checkColisionNode(x, y, node_diam))
			return;
		Node node = new Node(x, y, nodeNr);
		listaNoduri.add(node);
		nodeNr++;
		repaint();
	}
	
	//se executa atunci cand apelam repaint()
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
		g.drawString("This is my Graph!", 10, 20);
		//deseneaza arcele existente in lista
		/*for(int i=0;i<listaArce.size();i++)
		{
			listaArce.elementAt(i).drawArc(g);
		}*/
		for (Arc a : listaArce)
		{
			a.drawArc(g);
		}
		//deseneaza arcul curent; cel care e in curs de desenare
		if (pointStart != null)
		{
			g.setColor(Color.RED);
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
		}
		//deseneaza lista de noduri
		for(int i=0; i<listaNoduri.size(); i++)
		{
			listaNoduri.elementAt(i).drawNode(g, node_diam);
		}
		/*for (Node nod : listaNoduri)
		{
			nod.drawNode(g, node_diam, node_Diam);
		}*/
	}
}
