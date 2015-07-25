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
            String message = "Made by : \n12330225 Â½ÑåÁØ\n12330223 Â³Éý¸Ù\n12330253 ÅËÂ·Ã÷\n12330224 Â½ìÅÓî";
            JOptionPane.showMessageDialog(Fr, message, "¹ØÓÚ", JOptionPane.DEFAULT_OPTION);
        }
    }
}