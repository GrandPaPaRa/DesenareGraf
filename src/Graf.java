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

        f.add(new MyPanel(), BorderLayout.CENTER);
        //setez dimensiunea ferestrei
        f.setSize(500, 500);
        //fac fereastra vizibila
       // f.pack(); - daca vreau sa arate minuscula
        //setez butoanele
        JPanel bPanel = new JPanel();
        Button unOrdered = new Button("Neorientat", 0,0,100,50);
        Button ordered = new Button("Orientat", 0,0,100,50);
        ordered.ordered();
        unOrdered.unOrdered();
        bPanel.add(unOrdered);
        bPanel.add(ordered);
        f.add(bPanel, BorderLayout.NORTH);
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
