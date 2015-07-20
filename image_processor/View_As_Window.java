package image_processor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View_As_Window {
	Frame Fr;
	JPanel panel;
    JPanel panel_;
    JLabel label;
    JTextField tf;
    JTextField tf_;
    JButton bt;
    JDialog jdlg;

    public View_As_Window(Frame frame) {
    	Fr = frame;
    }
	//view_as_window
    public void view_as_window_() {
    	panel = new JPanel();
        panel_ = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        jdlg = new JDialog(Fr, "截图", true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jdlg.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        jdlg.setSize(250, 100);
         
        panel.setLayout(new GridLayout(3, 1));
        panel_.setLayout(new GridLayout(1, 2));
        label = new JLabel("请输入截图的大小（宽高）");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel_.add(tf);
        panel_.add(tf_);
        panel.add(panel_);
        panel.add(bt);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jdlg.dispose();
        		view_as_window(Integer.parseInt(tf.getText()), Integer.parseInt(tf_.getText()));
        	}
        });
        jdlg.getContentPane().add(panel, BorderLayout.CENTER);
        jdlg.setVisible(true);
    }
    //view_as_window
    public void view_as_window(int w, int h) {
    	BufferedImage img = Fr.after;
        BufferedImage img_;
        int wid = img.getWidth();
        int hei = img.getHeight();
        /*
         * get size of patches
         */
        int width = w;
        int height = h;
        int count = 1;
        for (int i = 0; i <= hei - height; i++) {
            for (int j = 0; j <= wid - width; j++) {
                if (count % 100 == 0) {
                    /*
                     * create new windows to show the patches
                     */
                    JFrame jf = new JFrame();
                    JLabel pic = new JLabel();
                
                    img_ = img.getSubimage(j, i, width, height);
                    
                    jf.getContentPane().add(pic, BorderLayout.CENTER);
                    jf.setVisible(true);
                    jf.setSize(100, 100);
                    ImageIcon icon = new ImageIcon(img_);
                    pic.setIcon(icon);
                    pic.repaint();
                }
                count++;
                if (count > 800)
                    break;
            }
            if (count > 800)
                break;
        }
    }
}
