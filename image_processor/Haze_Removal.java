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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Haze_Removal {
	Frame Fr;
	JPanel panel;
	JLabel label;
	JTextField tf;
	JButton bt;
	JDialog jdlg;
	
	GetColor gc;
	
	public Haze_Removal(Frame frame) {
		Fr = frame;
		gc = new GetColor();
	}
	//haze_removal_window
    public void haze_removal_window() {
    	panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        bt = new JButton();
        jdlg = new JDialog(Fr, "去雾", true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jdlg.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        jdlg.setSize(250, 100);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("请输入窗口半径");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("确定");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jdlg.dispose();
        		haze_removal(Integer.parseInt(tf.getText()));
        	}
        });
        jdlg.getContentPane().add(panel, BorderLayout.CENTER);
        jdlg.setVisible(true);
    }
    //haze_removal
    public void haze_removal(int r) {
    	BufferedImage img = Fr.st.peek();
    	int w = img.getWidth();
    	int h = img.getHeight();
    	
    	int[] img_i = new int[w * h];
    	int[] dark_i = new int[w * h];
    	int[] dark_i_ = new int[w * h];
    	int bf1 = (int) (0.001 * w * h);
    	int red, green, blue;
    	img.getRGB(0, 0, w, h, img_i, 0, w);
    	// 暗通道
    	for (int i = 0; i < w * h; i++) {
    		red = gc.getRed(img_i[i]);
    		green = gc.getGreen(img_i[i]);
    		blue = gc.getBlue(img_i[i]);
    		if (red <= green && red <= blue) {
    			dark_i[i] = red;
    		} else if (green <= red && green <= blue) {
    			dark_i[i] = green;
    		} else if (blue <= red && blue <= green) {
    			dark_i[i] = blue;
    		}
    		dark_i_[i] = 255;
    	}
    	for (int v = 0; v < h; v++) {
            for (int u = 0; u < w; u++) {
                for (int j = -r; j <= r; j++) {
                    for (int i = -r; i <= r; i++) {
                        int x = v + j;
                        int y = u + i;
                        if (x < 0 || x > h - 1 || y < 0 || y > w - 1) {
                        } else {
                            if (dark_i[x * w + y] < dark_i_[v * w + u]) {
                            	dark_i_[v * w + u] = dark_i[x * w + y];
                            }
                        }
                    }
                }
            }
        }
    	// 求A
    	int count = 0;
    	int gray = 255;
    	int[][] pos = new int[h][w];
    	while(count < bf1) {
    		for (int v = 0; v < h; v++) {
    			for (int u = 0; u < w; u++) {
    				if (gray == 255) {
    					pos[v][u] = 0;
    				}
    				if (dark_i_[v * w + u] == gray) {
    					pos[v][u] = 1;
    					count++;
    				}
    			}
    		}
    		gray--;
    	}
    	double A = 0;
    	for (int v = 0; v < h; v++) {
    		for (int u = 0; u < w; u++) {
    			if (pos[v][u] == 1) {
    				red = gc.getRed(img_i[v * w + u]);
    	    		green = gc.getGreen(img_i[v * w + u]);
    	    		blue = gc.getBlue(img_i[v * w + u]);
    				int I = (int) ((red + green + blue) / 3.0);
    				A += I;
    			}
    		}
    	}
    	A = A / count;
    	if (A > 220) {
    		A = 220;
    	}
    	// 求t(x)
    	double[] t = new double[w * h];
    	double[] t_ = new double[w * h];
    	for (int i = 0; i < w * h; i++) {
    		t[i] = 1.0 * dark_i[i] / A;
    		t_[i] = 2.0;
    	}
    	for (int v = 0; v < h; v++) {
    		for (int u = 0; u < w; u++) {
    			for (int j = -r; j <= r; j++) {
                    for (int i = -r; i <= r; i++) {
                        int x = v + j;
                        int y = u + i;
                        if (x < 0 || x > h - 1 || y < 0 || y > w - 1) {
                        } else {
                            if (t[x * w + y] < t_[v * w + u]) {
                            	t_[v * w + u] = t[x * w + y];
                            }
                        }
                    }
                }
    			t_[v * w + u] = 1 - 0.95 * t_[v * w + u];
    		}
    	}
    	// J(x)
    	int[] jx = new int[w * h];
    	for (int i = 0; i < w * h; i++) {
    		red = gc.getRed(img_i[i]);
    		green = gc.getGreen(img_i[i]);
    		blue = gc.getBlue(img_i[i]);
    		double t0;
    		if (t_[i] > 0.1) {
    			t0 = t_[i];
    		} else {
    			t0 = 0.1;
    		}
    		int red_ = (int) (1.0 * (red - A) / t0 + A);
    		if (red_ > 255) {
    			red_ = 255;
    		}
    		int green_ = (int) (1.0 * (green - A) / t0 + A);
    		if (green_ > 255) {
    			green_ = 255;
    		}
    		int blue_ = (int) (1.0 * (blue - A) / t0 + A);
    		if (blue_ > 255) {
    			blue_ = 255;
    		}
    		jx[i] = 0xff << 24 | red_ << 16 | green_ << 8 | blue_;
    	}
    	
    	DataBuffer dataBuffer = new DataBufferInt(jx, w * h);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, w, h, w, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
        
        Fr.st.push(image);
        Fr.st_.clear();
        Fr.haze = image;
        Fr.after = image;
        ImageIcon icon = new ImageIcon(image);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
}
