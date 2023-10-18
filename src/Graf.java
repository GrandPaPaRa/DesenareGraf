import javax.swing.*;
import java.awt.*;

public class Graf
{	
	private static void initUI() {
        JFrame f = new JFrame("Algoritmica TopG");
        //sa se inchida aplicatia atunci cand inchid fereastra
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //imi creez ob MyPanel
        f.setLayout(new BorderLayout());
        MyPanel myPanel = new MyPanel();
        f.add(myPanel, BorderLayout.CENTER);
        //setez dimensiunea ferestrei
        f.setSize(500, 500);
        //fac fereastra vizibila
       // f.pack(); - daca vreau sa arate minuscula
        //setez butoanele orientat / neorientat
        JPanel bPanel = new JPanel();
        Button unOrdered = new Button("Neorientat", 0,0,100,50, myPanel);
        Button ordered = new Button("Orientat", 0,0,100,50, myPanel);
        ordered.ordered();
        unOrdered.unOrdered();
        bPanel.add(unOrdered);
        bPanel.add(ordered);
        f.add(bPanel, BorderLayout.NORTH);
        //setez butoanele Place / Grab
        JPanel pPanel = new JPanel();
        Button place = new Button("Place", 0,0,100,50, myPanel);
        Button grab = new Button("Grab", 0,0,100,50, myPanel);
        place.place();
        grab.grab();
        pPanel.add(place);
        pPanel.add(grab);
        f.add(pPanel, BorderLayout.SOUTH);

        f.setLocationRelativeTo(null);
        //f.setLayout(null); //nu merge grafu daca o activez
        f.setVisible(true);

    }
	
	public static void main(String[] args)
	{
		//pornesc firul de executie grafic
		//fie prin implementarea interfetei Runnable, fie printr-un ob al clasei Thread
		SwingUtilities.invokeLater(new Runnable() //new Thread()
		{
            public void run() 
            {
            	initUI(); 
            }
        });
	}	
}
