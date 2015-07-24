package image_processor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Add_Gaussian_Noise {
	Frame Fr;
	JPanel panel;
    JLabel label;
    JTextField tf;
    JTextField tf_;
    JButton bt;
    JDialog jdlg;
    
    public Add_Gaussian_Noise(Frame frame) {
    	Fr = frame;
    }
	//add_gaussian_noise
    public void add_gaussian_noise_window() {
    	panel = new JPanel();
        label = new JLabel();
        JLabel label_ = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        jdlg = new JDialog(Fr, "添加高斯噪声", true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jdlg.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        jdlg.setSize(250, 150);
        jdlg.getRootPane().setDefaultButton(bt);
        
        panel.setLayout(new GridLayout(5, 1));
        label = new JLabel("均值");
        label.setHorizontalAlignment(JLabel.CENTER);
        label_ = new JLabel("标准差");
        label_.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("确定");
        panel.add(label);
        panel.add(tf);
        panel.add(label_);
        panel.add(tf_);
        panel.add(bt);
        
        tf.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				// 按下ESC退出对话框
        		if (e.getKeyChar() == KeyEvent.VK_ESCAPE)
    				jdlg.dispose();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				// 限制最大只能输入4位数
				if (tf.getText().length() < 4)
					// 限制只能输入以下按键（数字，方向，回车，ESC，tab，删除，退格）
					if ((e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9) 
					      || e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB
					      || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE 
					      || e.getKeyChar() == KeyEvent.VK_LEFT || e.getKeyChar() == KeyEvent.VK_RIGHT 
					      || e.getKeyChar() == KeyEvent.VK_ESCAPE || e.getKeyChar() == KeyEvent.VK_MINUS)
					      return;
				e.consume();
			}
        });
        
        tf_.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				// 按下ESC退出对话框
        		if (e.getKeyChar() == KeyEvent.VK_ESCAPE)
    				jdlg.dispose();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				// 限制最大只能输入4位数
				if (tf.getText().length() < 4)
					// 限制只能输入以下按键（数字，方向，回车，ESC，tab，删除，退格）
					if ((e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9) 
					      || e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB
					      || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE 
					      || e.getKeyChar() == KeyEvent.VK_LEFT || e.getKeyChar() == KeyEvent.VK_RIGHT 
					      || e.getKeyChar() == KeyEvent.VK_ESCAPE)
					      return;
				e.consume();
			}
        });
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			jdlg.dispose();
        			add_gaussian_noise(Integer.parseInt(tf.getText()), Integer.parseInt(tf_.getText()));
        		} catch (NumberFormatException e1) {
        			String message = "请输入数字！";
		        	JOptionPane.showMessageDialog(Fr, message, "提醒", JOptionPane.DEFAULT_OPTION);
        		}
        	}
        });
        jdlg.getContentPane().add(panel, BorderLayout.CENTER);
        jdlg.setVisible(true);
    }
    //add_gaussian_noise
    public void add_gaussian_noise(int mean, int standard_variance) {
    	BufferedImage img = Fr.st.peek();
    	int w = img.getWidth();
    	int h = img.getHeight();
    	int m_ = mean;
    	int s_ = standard_variance;
    	int[] beforeimg = new int[w * h];
    	int[] afterimg = new int[w * h];
    	int[] pixel = new int[w * h];
    	img.getRGB(0, 0, w, h, beforeimg, 0, w);
    	
    	for (int i = 0; i < w * h; ++i) {
    		pixel[i] = beforeimg[i] & 0xff;
    	}
    	/*for (int i = 0; i < w * h; ++i) {
    		double r = new Random().nextGaussian();
    		double r1 = new Random().nextDouble();
    		double p1 = 1 / (Math.sqrt(2 * Math.PI) * s_) * Math.exp(-(r - m_) * (r - m_) / (2 * s_ * s_));
    		if (r1 <= p1) {
    			pixel[i] += r * 128;
    		}
    		if (pixel[i] > 255) {
    			pixel[i] = 255;
    		}
    		if (pixel[i] < 0) {
    			pixel[i] = 0;
    		}
    	}*/
    	for (int i = 0; i < w * h; ++i) {
    		double r = new Random().nextGaussian();
    		pixel[i] = (int) (pixel[i] + m_ + s_ * r);
    		if (pixel[i] > 255) {
    			pixel[i] = 255;
    		}
    		if (pixel[i] < 0) {
    			pixel[i] = 0;
    		}
    	}
    	for (int i = 0; i < w * h; ++i) {
    		afterimg[i] = 0xff << 24 | pixel[i] << 16 | pixel[i] << 8 | pixel[i];
    	}
    	DataBuffer dataBuffer = new DataBufferInt(afterimg, w * h);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, w, h, w, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
        
        Fr.st.push(image);
        Fr.st_.clear();
        Fr.after = image;
        ImageIcon icon = new ImageIcon(image);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
}
