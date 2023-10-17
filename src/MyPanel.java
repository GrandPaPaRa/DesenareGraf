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
	enum State{
		ORDERED,
		UNORDERED
	}
	private int nodeNr = 1;
	public static int node_diam = 30;
	public static State state = State.UNORDERED;
	private Vector<Node> listaNoduri;
	private Vector<Arc> listaArce;
	private Save save;
	Point pointStart = null;
	Point pointEnd = null;
	boolean isDragging = false;
	//getters
	public Vector<Node> getVectorNode(){
		return listaNoduri;
	}
	public Vector<Arc> getListaArce(){
		return listaArce;
	}
	public Save getSave(){
		return save;
	}
	public MyPanel()
	{
		listaNoduri = new Vector<Node>();
		listaArce = new Vector<Arc>();
		save = new Save("adiacent.txt");
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
						if(!checkIfArcExist(pointStart, pointEnd)){
							Arc arc = new Arc(pointStart, pointEnd);
							listaArce.add(arc);
							save.save(listaArce,listaNoduri);
						}
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
	private boolean checkIfArcExist(Point start, Point end){
		for(Arc it : listaArce){
			if(it.getStart().equals(start) && it.getEnd().equals(end))
				return true;
		}
		return false;
	}

	//metoda care se apeleaza la eliberarea mouse-ului
	private void addNode(int x, int y) {
		if(!checkColisionNode(x, y, node_diam + 10))
			return;
		Node node = new Node(x, y, nodeNr);
		listaNoduri.add(node);
		nodeNr++;
		save.save(listaArce,listaNoduri);
		repaint();
	}
	
	//se executa atunci cand apelam repaint()
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
		//g.drawString("TopG B Color!?", 10, 20);
		//deseneaza arcele existente in lista
		/*for(int i=0;i<listaArce.size();i++)
		{
			listaArce.elementAt(i).drawArc(g);
		}*/
		for (Arc a : listaArce)
		{
			a.drawArc(g);
			//a.drawArrowLine(g);
		}
		//deseneaza arcul curent; cel care e in curs de desenare
		if (pointStart != null)
		{
			g.setColor(Color.RED);
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
			if(state == State.ORDERED)
				Arc.drawArrowLine(g,pointStart.x,pointStart.y,pointEnd.x, pointEnd.y,7,7);
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
