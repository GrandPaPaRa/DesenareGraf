import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Button extends JButton{
    public Button(String text, int x, int y, int w, int h){
        setText(text);
        setBounds(x, y, w, h);
    }
    public void ordered(){
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("orientat");
            }
        });
    }
    public void unOrdered(){
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("neorientat");
            }
        });
    }


}
