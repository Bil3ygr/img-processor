package image_processor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Help implements ActionListener {
    Frame Fr;
    
    public Help(Frame frame) {
        Fr = frame;
        Fr.about.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == Fr.about) {
            String message = "Made by : 12330225";
            JOptionPane.showMessageDialog(Fr, message, "¹ØÓÚ", JOptionPane.DEFAULT_OPTION);
        }
    }
}