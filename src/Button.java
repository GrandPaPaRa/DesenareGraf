import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Button extends JButton{
    public Button(String text, int x, int y, int w, int h, MyPanel p){
        setText(text);
        setBounds(x, y, w, h);
        panel = p;
    }
    private MyPanel panel;
    public void ordered(){
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("orientat");
                MyPanel.state = MyPanel.State.ORDERED;
                panel.getSave().save(panel.getListaArce(),panel.getVectorNode());
                panel.repaint();
            }
        });
    }
    public void unOrdered(){
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("neorientat");
                MyPanel.state = MyPanel.State.UNORDERED;
                panel.getSave().save(panel.getListaArce(),panel.getVectorNode());
                panel.repaint();
            }
        });
    }


}
