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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Guide_Filter {
	Frame Fr;
	JPanel panel;
	JLabel label;
	JTextField tf;
	JButton bt;
	JDialog jdlg;

	GetColor gc;
	Haze_Removal hr;
	
	public Guide_Filter(Frame frame) {
		Fr = frame;
		gc = new GetColor();
		hr = new Haze_Removal(Fr);
	}
	//guide filter
    public void guide_filter_window() {
    	panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        bt = new JButton();
        jdlg = new JDialog(Fr, "导向滤波", true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jdlg.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        jdlg.setSize(250, 100);
        jdlg.getRootPane().setDefaultButton(bt);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("请输入窗口半径");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("确定");
        panel.add(label);
        panel.add(tf);
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
				// 限制最大只能输入3位数
				if (tf.getText().length() < 3)
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
        		jdlg.dispose();
        		hr.haze_removal(Integer.parseInt(tf.getText()));
        		guide_filter(Integer.parseInt(tf.getText()));
        	}
        });
        jdlg.getContentPane().add(panel, BorderLayout.CENTER);
        jdlg.setVisible(true);
    }
    // guide filter
    public void guide_filter(int R) {
    	// 4r < R < 8R
    	int r = 6 * R;
    	BufferedImage image = Fr.st.peek();
    	BufferedImage img = Fr.haze;
    	int w = img.getWidth();
    	int h = img.getHeight();
    	
    	int[] img_p = new int[w * h];
    	int[] img_p_r = new int[w * h];
    	int[] img_p_g = new int[w * h];
    	int[] img_p_b = new int[w * h];
    	int[] img_i = new int[w * h];
    	int[] img_i_r = new int[w * h];
    	int[] img_i_g = new int[w * h];
    	int[] img_i_b = new int[w * h];
    	image.getRGB(0, 0, w, h, img_i, 0, w);
    	img.getRGB(0, 0, w, h, img_p, 0, w);
    	
    	for (int i = 0; i < w * h; i++) {
    		img_p_r[i] = gc.getRed(img_p[i]);
    		img_p_g[i] = gc.getGreen(img_p[i]);
    		img_p_b[i] = gc.getBlue(img_p[i]);
    		img_i_r[i] = gc.getRed(img_i[i]);
    		img_i_g[i] = gc.getGreen(img_i[i]);
    		img_i_b[i] = gc.getBlue(img_i[i]);
    	}
    	// 第一步
    	double[] mean_i_r = new double[w * h];
    	double[] mean_i_g = new double[w * h];
    	double[] mean_i_b = new double[w * h];
    	double[] mean_p_r = new double[w * h];
    	double[] mean_p_g = new double[w * h];
    	double[] mean_p_b = new double[w * h];
    	double[] corr_i_r = new double[w * h];
    	double[] corr_i_g = new double[w * h];
    	double[] corr_i_b = new double[w * h];
    	double[] corr_ip_r = new double[w * h];
    	double[] corr_ip_g = new double[w * h];
    	double[] corr_ip_b = new double[w * h];
    	for (int v = 0; v < h; v++) {
            for (int u = 0; u < w; u++) {
            	double count = 0;
            	double tmp_i_r = 0;
            	double tmp_i_g = 0;
            	double tmp_i_b = 0;
            	double tmp_p_r = 0;
            	double tmp_p_g = 0;
            	double tmp_p_b = 0;
            	double tmp_ii_r = 0;
            	double tmp_ii_g = 0;
            	double tmp_ii_b = 0;
            	double tmp_ip_r = 0;
            	double tmp_ip_g = 0;
            	double tmp_ip_b = 0;
                for (int j = -r; j <= r; j++) {
                    for (int i = -r; i <= r; i++) {
                        int x = v + j;
                        int y = u + i;
                        if (x < 0 || x > h - 1 || y < 0 || y > w - 1) {
                        } else {
                        	count++;
                        	tmp_i_r += img_i_r[x * w + y];
                        	tmp_i_g += img_i_g[x * w + y];
                        	tmp_i_b += img_i_b[x * w + y];
                        	tmp_p_r += img_p_r[x * w + y];
                        	tmp_p_g += img_p_g[x * w + y];
                        	tmp_p_b += img_p_b[x * w + y];
                        	tmp_ii_r += img_i_r[x * w + y] * img_i_r[x * w + y];
                        	tmp_ii_g += img_i_g[x * w + y] * img_i_g[x * w + y];
                        	tmp_ii_b += img_i_b[x * w + y] * img_i_b[x * w + y];
                        	tmp_ip_r += img_i_r[x * w + y] * img_p_r[x * w + y];
                        	tmp_ip_g += img_i_g[x * w + y] * img_p_g[x * w + y];
                        	tmp_ip_b += img_i_b[x * w + y] * img_p_b[x * w + y];
                        }
                    }
                }
                mean_i_r[v * w + u] = 1.0 * tmp_i_r / count;
                mean_i_g[v * w + u] = 1.0 * tmp_i_g / count;
                mean_i_b[v * w + u] = 1.0 * tmp_i_b / count;
                mean_p_r[v * w + u] = 1.0 * tmp_p_r / count;
                mean_p_g[v * w + u] = 1.0 * tmp_p_g / count;
                mean_p_b[v * w + u] = 1.0 * tmp_p_b / count;
                corr_i_r[v * w + u] = 1.0 * tmp_ii_r / count;
                corr_i_g[v * w + u] = 1.0 * tmp_ii_g / count;
                corr_i_b[v * w + u] = 1.0 * tmp_ii_b / count;
                corr_ip_r[v * w + u] = 1.0 * tmp_ip_r / count;
                corr_ip_g[v * w + u] = 1.0 * tmp_ip_g / count;
                corr_ip_b[v * w + u] = 1.0 * tmp_ip_b / count;
            }
        }
    	// 第二步
    	double[] var_r = new double[w * h];
    	double[] var_g = new double[w * h];
    	double[] var_b = new double[w * h];
    	double[] cov_r = new double[w * h];
    	double[] cov_g = new double[w * h];
    	double[] cov_b = new double[w * h];
    	for (int i = 0; i < w * h; i++) {
    		var_r[i] = corr_i_r[i] - mean_i_r[i] * mean_i_r[i];
    		var_g[i] = corr_i_g[i] - mean_i_g[i] * mean_i_g[i];
    		var_b[i] = corr_i_b[i] - mean_i_b[i] * mean_i_b[i];
    		cov_r[i] = corr_ip_r[i] - mean_i_r[i] * mean_p_r[i];
    		cov_g[i] = corr_ip_g[i] - mean_i_g[i] * mean_p_g[i];
    		cov_b[i] = corr_ip_b[i] - mean_i_b[i] * mean_p_b[i];
    	}
    	// 第三步
    	double[] a_r = new double[w * h];
    	double[] a_g = new double[w * h];
    	double[] a_b = new double[w * h];
    	double[] b_r = new double[w * h];
    	double[] b_g = new double[w * h];
    	double[] b_b = new double[w * h];
    	for (int i = 0; i < w * h; i++) {
    		a_r[i] = cov_r[i] / (var_r[i] + 0.000001);
    		b_r[i] = mean_p_r[i] - a_r[i] * mean_i_r[i];
    		a_g[i] = cov_g[i] / (var_g[i] + 0.000001);
    		b_g[i] = mean_p_g[i] - a_g[i] * mean_i_g[i];
    		a_b[i] = cov_b[i] / (var_b[i] + 0.000001);
    		b_b[i] = mean_p_b[i] - a_b[i] * mean_i_b[i];
    	}
    	// 第四步
    	double[] mean_a_r = new double[w * h];
    	double[] mean_a_g = new double[w * h];
    	double[] mean_a_b = new double[w * h];
    	double[] mean_b_r = new double[w * h];
    	double[] mean_b_g = new double[w * h];
    	double[] mean_b_b = new double[w * h];
    	for (int v = 0; v < h; v++) {
    		for (int u = 0; u < w; u++) {
    			double count = 0;
    			double tmp_a_r = 0;
    			double tmp_a_g = 0;
    			double tmp_a_b = 0;
    			double tmp_b_r = 0;
    			double tmp_b_g = 0;
    			double tmp_b_b = 0;
    			for (int j = -r; j <= r; j++) {
                    for (int i = -r; i <= r; i++) {
                        int x = v + j;
                        int y = u + i;
                        if (x < 0 || x > h - 1 || y < 0 || y > w - 1) {
                        } else {
                        	count++;
                        	tmp_a_r += a_r[x * w + y];
                        	tmp_a_g += a_g[x * w + y];
                        	tmp_a_b += a_b[x * w + y];
                        	tmp_b_r += b_r[x * w + y];
                        	tmp_b_g += b_g[x * w + y];
                        	tmp_b_b += b_b[x * w + y];
                        }
                    }
                }
    			mean_a_r[v * w + u] = tmp_a_r / count;
    			mean_a_g[v * w + u] = tmp_a_g / count;
    			mean_a_b[v * w + u] = tmp_a_b / count;
    			mean_b_r[v * w + u] = tmp_b_r / count;
    			mean_b_g[v * w + u] = tmp_b_g / count;
    			mean_b_b[v * w + u] = tmp_b_b / count;
    		}
    	}
    	// 第五步
    	int[] q = new int[w * h];
    	int[] q_r = new int[w * h];
    	int[] q_g = new int[w * h];
    	int[] q_b = new int[w * h];
    	for (int i = 0; i < w * h; i++) {
    		q_r[i] = (int) (mean_a_r[i] * img_i_r[i] + mean_b_r[i]);
    		if (q_r[i] > 255) {
    			q_r[i] = 255;
    		}
    		if (q_r[i] < 0) {
    			q_r[i] = 0;
    		}
    		q_g[i] = (int) (mean_a_g[i] * img_i_g[i] + mean_b_g[i]);
    		if (q_g[i] > 255) {
    			q_g[i] = 255;
    		}
    		if (q_g[i] < 0) {
    			q_g[i] = 0;
    		}
    		q_b[i] = (int) (mean_a_b[i] * img_i_b[i] + mean_b_b[i]);
    		if (q_b[i] > 255) {
    			q_b[i] = 255;
    		}
    		if (q_b[i] < 0) {
    			q_b[i] = 0;
    		}
    		q[i] = 0xff << 24 | q_r[i] << 16 | q_g[i] << 8 | q_b[i];
    	}
    	
    	DataBuffer dataBuffer = new DataBufferInt(q, w * h);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, w, h, w, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        
        Fr.st.push(image_);
        Fr.st_.clear();
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
}
