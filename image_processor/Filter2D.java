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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Filter2D {
	Frame Fr;
	JPanel panel;
    JLabel label;
    JLabel label_;
    JTextField tf;
    JTextField tf_;
    JButton bt;
    JDialog jdlg;
    double Q = 0;
    
    public Filter2D(Frame frame) {
    	Fr = frame;
    }
    // 平滑
    public void smooth() {
    	if (judge()) {
    		one_tf(1);
    	}
    }
    // 锐化
    public void sharpen() {
    	if (judge()) {
    		one_tf(2);
    	}
    }
    // Sobel 3x3
    public void sobel3x3() {
    	if (judge()) {
    		filter2d(0, 3);
    	}
    }
    // Sobel 2x2
    public void sobel2x2() {
    	if (judge()) {
    		filter2d(0, 4);
    	}
    }
    // 谐波
    public void harmonic() {
    	if (judge()) {
    		one_tf(5);
    	}
    }
    // 逆谐波
    public void contraharmonic() {
    	if (judge()) {
    		two_tf(6);
    	}
    }
    // 几何
    public void geometric() {
    	if (judge()) {
    		one_tf(7);
    	}
    }
    // 中值
    public void median() {
    	if (judge()) {
    		one_tf(8);
    	}
    }
    // 最大值
    public void max() {
    	if (judge()) {
    		one_tf(9);
    	}
    }
    // 最小值
    public void min() {
    	if (judge()) {
    		one_tf(10);
    	}
    }
    // 判断是否有图片
    public boolean judge() {
		if (Fr.before != null) {
			return true;
		} else {
			String message = "未打开任何图片！";
        	JOptionPane.showMessageDialog(Fr, message, "提醒", JOptionPane.DEFAULT_OPTION);
		}
		return false;
	}
    // 一个输入框
    public void one_tf(int flag) {
    	panel = new JPanel();
		label = new JLabel();
		tf = new JTextField();
		bt = new JButton();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		jdlg = new JDialog(Fr, "窗口大小", true);
		jdlg.setLocation((screenSize.width - 280) / 2, (screenSize.height - 100) / 2);
		jdlg.setSize(280, 100);
		jdlg.getRootPane().setDefaultButton(bt);
		
		panel.setLayout(new GridLayout(3, 1));
		label = new JLabel("请输入滤波窗口大小（奇数，且小于100）");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		bt.setText("确定");
		panel.add(label);
		panel.add(tf);
		panel.add(bt);
		// 指定输入格式
		tf.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				// 按下ESC退出对话框
				if (arg0.getKeyChar() == KeyEvent.VK_ESCAPE)
					jdlg.dispose();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				// 限制最大只能输入8位数
				if (tf.getText().length() < 8)
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
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				try {
					filter2d(Integer.parseInt(tf.getText()), flag);
					jdlg.dispose();
				} catch (NumberFormatException e){
					String message = "请输入数字！";
		        	JOptionPane.showMessageDialog(Fr, message, "提醒", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		jdlg.getContentPane().add(panel, BorderLayout.CENTER);
		jdlg.setVisible(true);
    }
    // 两个输入框
    public void two_tf(int flag) {
    	panel = new JPanel();
		label = new JLabel();
		label_ = new JLabel();
		tf = new JTextField();
		tf_ = new JTextField();
		bt = new JButton();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		jdlg = new JDialog(Fr, "窗口大小", true);
		jdlg.setLocation((screenSize.width - 280) / 2, (screenSize.height - 150) / 2);
		jdlg.setSize(280, 150);
		jdlg.getRootPane().setDefaultButton(bt);
		
		panel.setLayout(new GridLayout(5, 1));
		label = new JLabel("请输入滤波窗口大小（奇数，且小于100）");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label_ = new JLabel("请输入Q的值");
        label_.setHorizontalAlignment(JLabel.CENTER);
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		tf_.setHorizontalAlignment(JTextField.CENTER);
		bt.setText("确定");
		panel.add(label);
		panel.add(tf);
		panel.add(label_);
		panel.add(tf_);
		panel.add(bt);
		// 指定输入格式
		tf.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				// 按下ESC退出对话框
				if (arg0.getKeyChar() == KeyEvent.VK_ESCAPE)
					jdlg.dispose();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				// 限制最大只能输入8位数
				if (tf.getText().length() < 8)
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
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				try {
					Q = Double.parseDouble(tf_.getText());
					filter2d(Integer.parseInt(tf.getText()), flag);
					jdlg.dispose();
				} catch (NumberFormatException e){
					String message = "请输入数字！";
		        	JOptionPane.showMessageDialog(Fr, message, "提醒", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		jdlg.getContentPane().add(panel, BorderLayout.CENTER);
		jdlg.setVisible(true);
    }
  //filter2d
    public void filter2d(int size, int flag) {
    	if (flag == 1) {	// smooth the image
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Smooth");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            
            f.setSize(wid + 20, hei + 50);
            /*
             * set size
             */
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            /*
             * get RGB of original image
             */
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            /*
             * smooth the image, save data into newimg
             */
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int sum = 0;
                    int count = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                                sum += gray[x * wid + y];
                                count++;
                            }
                        }
                    }
                    int q = Math.round(sum / count);
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 2) {	// sharpen the image
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Sharpen");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int[] gray = new int[wid * hei];
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            /*
             * save the data into newimg
             */
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int sum = 0;
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                                /*
                                 * center * 8, other * -1
                                 */
                                if (j == 0 && i == 0) {
                                    sum = sum + 8 * gray[x * wid + y];
                                } else {
                                    sum -= gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum += gray[v * wid + u];
                    if (sum > 255) {
                        sum = 255;
                    } else if (sum < 0) {
                        sum = 0;
                    }
                    int q = sum;
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 3) {	// filter the iamge with 3x3 sobel filter
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Sobel3x3");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int[] gray = new int[wid * hei];
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int sum = 0;
                    /*
                     * new gray level = 
                     * |(z7 + 2 * z8 + z9) - (z1 + 2 * z2 + z3)| + |(z3 + 2 * z6 + z9) - (z1 + 2 * z4 + z7)|
                     */
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                                sum += 0;
                            } else {
                                int q = 1;
                                if (i == 0)
                                    q = 2;
                                if (j < 0) {
                                    sum -= q * gray[x * wid + y];
                                } else if (j > 0) {
                                    sum += q * gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum = Math.abs(sum);
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                                sum += 0;
                            } else {
                                int q = 1;
                                if (j == 0)
                                    q = 2;
                                if (i < 0) {
                                    sum -= q * gray[x * wid + y];
                                } else if (i > 0) {
                                    sum += q * gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum = Math.abs(sum);
                    int q = sum;
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 4) {	// filter the iamge with 2x2 sobel filter
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Sobel2x2");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int[] gray = new int[wid * hei];
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int sum = 0;
                    /*
                     * new gray level =
                     * |z5 - z9| + |z6 - z8|
                     */
                    for (int j = 0; j <= 1; j++) {
                        for (int i = 0; i <= 1; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x > hei - 1 | y > wid - 1) {
                                sum += 0;
                            } else {
                                if (j == 0 && i == 0) {
                                    sum += gray[x * wid + y];
                                } else if (j == 1 && i == 1) {
                                    sum -= gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum = Math.abs(sum);
                    for (int j = 0; j <= 1; j++) {
                        for (int i = 0; i <= 1; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x > hei - 1 | y > wid - 1) {
                                sum += 0;
                            } else {
                                if (j == 0 && i == 1) {
                                    sum += gray[x * wid + y];
                                } else if (j == 1 && i == 0) {
                                    sum -= gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum = Math.abs(sum);
                    int q = sum;
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 5) {	// harmonic
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Harmonic");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            /*
             * set size
             */
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = (level - 1) / 2;
            int[] gray = new int[wid * hei];
            /*
             * get RGB of original image
             */
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    double sum = 0;
                    int count = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 || x > hei - 1 || y < 0 || y > wid - 1) {
                            } else {
                            	if (gray[x * wid + y] == 0) {
                            		sum += 1.0 / 1e-6;
                            	} else {
                            		sum += 1.0 / gray[x * wid + y];
                            	}
                            	count++;
                            }
                        }
                    }
                    int q = (int) (count / sum);
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 6) {	// contrahamonic
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Contra-Harmonic");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            /*
             * set size
             */
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            /*
             * get RGB of original image
             */
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    double sum1 = 0;
                    double sum2 = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 || x > hei - 1 || y < 0 || y > wid - 1) {
                            } else {
                            	sum1 += Math.pow(gray[x * wid + y], Q + 1);
                            	sum2 += Math.pow(gray[x * wid + y], Q);
                            }
                        }
                    }
                    int q = (int) (sum1 / sum2);
                    if (q > 255) {
                    	q = 255;
                    }
                    if (q < 0) {
                    	q = 0;
                    }
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 7) {	// geometric
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Geometric");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            /*
             * set size
             */
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            /*
             * get RGB of original image
             */
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            /*
             * smooth the image, save data into newimg
             */
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    double sum = 1;
                    double mi = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                                sum *= gray[x * wid + y];
                                mi++;
                            }
                        }
                    }
                    int q = (int) Math.round(Math.pow(sum, 1.0 / mi));
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 8) {	// median
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Median");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            /*
             * set size
             */
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            /*
             * get RGB of original image
             */
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            /*
             * smooth the image, save data into newimg
             */
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int[] sum = new int[level * level];
                    int k = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                                sum[k] = gray[x * wid + y];
                                ++k;
                            }
                        }
                    }
                    int count = 0;
                    while (count == 0) {
                    	for (int i = 0; i < k - 1; ++i) {
                    		if (sum[i] > sum[i + 1]) {
                    			int tmp = sum[i];
                    			sum[i] = sum[i + 1];
                    			sum[i + 1] = tmp;
                    			count++;
                    		}
                    	}
                    	if (count != 0) {
                    		count = 0;
                    	} else {
                    		break;
                    	}
                    }
                    int q = Math.round(sum[(k + 2) / 2]);
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 9) {	// max
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Max");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            /*
             * set size
             */
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            /*
             * get RGB of original image
             */
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            /*
             * smooth the image, save data into newimg
             */
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int q = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                            	if (gray[x * wid + y] > q) {
                            		q = gray[x * wid + y];
                            	}
                            }
                        }
                    }
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 10) {	// min
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Min");
            f.setVisible(true);
            
            BufferedImage img = Fr.st.peek();
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            /*
             * set size
             */
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            /*
             * get RGB of original image
             */
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            /*
             * smooth the image, save data into newimg
             */
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                	int q = 255;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                            	if (gray[x * wid + y] < q) {
                            		q = gray[x * wid + y];
                            	}
                            }
                        }
                    }
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage image = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(image);
            pic.setIcon(icon);
            pic.repaint();
    	}
    }
}
