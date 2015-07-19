package image_processor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Add_Impulse_Noise {
	Frame Fr;
	JFrame fr;
    JPanel panel;
    JLabel label;
    JTextField tf;
    JTextField tf_;
    JButton bt;
    
    public Add_Impulse_Noise(Frame frame) {
    	Fr = frame;
    }
	//add_impulse_noise
    public void add_impulse_noise_window() {
    	fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        JLabel label_ = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 150);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(5, 1));
        label = new JLabel("盐百分比（0-100）");
        label.setHorizontalAlignment(JLabel.CENTER);
        label_ = new JLabel("椒百分比（0-100）");
        label_.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("确定");
        panel.add(label);
        panel.add(tf);
        panel.add(label_);
        panel.add(tf_);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		add_impulse_noise(Integer.parseInt(tf.getText()), Integer.parseInt(tf_.getText()));
        	}
        });
    }
    //add_impulse_noise
    public void add_impulse_noise(int pa, int pb) {
    	BufferedImage img = Fr.before;
    	int w = img.getWidth();
    	int h = img.getHeight();
    	double p1 = 1.0 * pa / 100;
    	double p2 = 1.0 * pb / 100;
    	double p3 = p2 / (1 - p1);
    	int[] beforeimg = new int[w * h];
    	int[] afterimg = new int[w * h];
    	int[] pixel = new int[w * h];
    	img.getRGB(0, 0, w, h, beforeimg, 0, w);
    	
    	for (int i = 0; i < w * h; ++i) {
    		pixel[i] = beforeimg[i] & 0xff;
    	}
    	for (int i = 0; i < w * h; ++i) {
    		int noise = 1;
    		double random = new Random().nextDouble();
    		if (random < p1) {
    			noise = 255;
    		} else {
    			double temp = new Random().nextDouble();
    			if (temp < p3) {
    				noise = 0;
    			}
    		}
    		if (noise != 1) {
    			pixel[i] = noise;
    		}
    	}
    	for (int i = 0; i < w * h; ++i) {
    		afterimg[i] = 0xff << 24 | pixel[i] << 16 | pixel[i] << 8 | pixel[i];
    	}
    	DataBuffer dataBuffer = new DataBufferInt(afterimg, w * h);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, w, h, w, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
}
