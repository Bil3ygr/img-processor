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

public class Filter2D_Freq {
	Frame Fr;
	JPanel panel;
	JLabel label;
	JTextField tf;
	JButton bt;
	JDialog jdlg;
	
	public Filter2D_Freq(Frame frame) {
		Fr = frame;
	}
	//filter2d_freq
    public void filter2d_freq_window() {
    	panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        bt = new JButton();
        jdlg = new JDialog(Fr, "频率域滤波", true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jdlg.setLocation((screenSize.width - 260) / 2, (screenSize.height - 100) / 2);
        jdlg.setSize(260, 100);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("请选择操作，0（平滑），1（锐化）");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("确定");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        bt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jdlg.dispose();
                filter2d_freq(Integer.parseInt(tf.getText()));
            }
        });
        jdlg.getContentPane().add(panel, BorderLayout.CENTER);
        jdlg.setVisible(true);
    }
    //filter2d_freq
    public void filter2d_freq(int flag) {
    	BufferedImage input = Fr.before;
    	//图片宽高
    	int M = input.getWidth();
    	int N = input.getHeight();
    	int P = 2 * M;
    	int Q = 2 * N;
    	
    	int[] original = new int[M * N];
    	int[] after = new int[P * Q];
    	int[] after_ = new int[P * Q];
    	double[][] R = new double[Q][P];
    	double[][] I = new double[Q][P];
    	//fp,F(u,v)
    	int[][] fp = new int[Q][P];
    	Complex[][] Fxv = new Complex[Q][P];
    	Complex[][] Fuv = new Complex[Q][P];
    	//h,H(u,v)
    	double[][] h = new double[Q][P];
    	Complex[][] H = new Complex[Q][P];
    	Complex[][] Hxv = new Complex[Q][P];
    	//G(u,v),g
    	Complex[][] G = new Complex[Q][P];
    	Complex[][] guy = new Complex[Q][P];
    	Complex[][] g = new Complex[Q][P];
    	double[][] gp = new double[Q][P];

    	input.getRGB(0, 0, M, N, original, 0, M);
    	//fp(x,y)
    	for (int i = 0; i < Q; ++i) {
            for (int j = 0; j < P; ++j) {
            	if (i < N && j < M) {
            		fp[i][j] = (int) ((original[i * M + j] & 0xff) * Math.pow(-1, i + j));
            	} else {
            		fp[i][j] = 0;
            	}
            }
        }
    	//F(u,v)
        for (int x = 0; x < P; x++) {
            for (int v = 0; v < Q; v++) {
                R[v][x] = 0;
                I[v][x] = 0;
                for (int y = 0; y < Q; y++) {
                    double w = 2 * Math.PI * v * y / Q;
                    R[v][x] += fp[y][x] * Math.cos(w);
                    I[v][x] += fp[y][x] * -Math.sin(w);
                }
                Fxv[v][x] = new Complex(R[v][x], I[v][x]);
            }
        }
        for (int u = 0; u < P; u++) {
            for (int v = 0; v < Q; v++) {
                R[v][u] = 0;
                I[v][u] = 0;
                for (int x = 0; x < P; x++) {
                    double w = 2 * Math.PI * u * x / P;
                    R[v][u] += Fxv[v][x].re() * Math.cos(w) - Fxv[v][x].im() * -Math.sin(w);
                    I[v][u] += Fxv[v][x].re() * -Math.sin(w) + Fxv[v][x].im() * Math.cos(w);
                }
                Fuv[v][u] = new Complex(R[v][u], I[v][u]);
            }
        }
        //h(u,v)
        if (flag == 0) {
        	for (int i = 0; i < Q; i++) {
        		for (int j =0 ; j < P; j++) {
        			if (i < 11 && j < 11) {
        				h[i][j] = (1.0 / 121) * Math.pow(-1, i + j);
        			} else {
        				h[i][j] = 0;
        			}
        		}
        	}
        } else if (flag == 1) {
        	for (int i = 0; i < Q; i++) {
        		for (int j =0 ; j < P; j++) {
        			if (i < 3 && j < 3) {
        				h[i][j] = -Math.pow(-1, i + j);
        				if (i == 1 && j == 1) {
        					h[i][j] = 8 * Math.pow(-1, i + j);
        				}
        			} else {
        				h[i][j] = 0;
        			}
        		}
        	}
        }
    	//H(u,v)
    	for (int x = 0; x < P; x++) {
            for (int v = 0; v < Q; v++) {
                R[v][x] = 0;
                I[v][x] = 0;
                for (int y = 0; y < Q; y++) {
                    double w = 2 * Math.PI * v * y / Q;
                    R[v][x] += h[y][x] * Math.cos(w);
                    I[v][x] += h[y][x] * -Math.sin(w);
                }
                Hxv[v][x] = new Complex(R[v][x], I[v][x]);
            }
        }
        for (int u = 0; u < P; u++) {
            for (int v = 0; v < Q; v++) {
                R[v][u] = 0;
                I[v][u] = 0;
                for (int x = 0; x < P; x++) {
                    double w = 2 * Math.PI * u * x / P;
                    R[v][u] += Hxv[v][x].re() * Math.cos(w) - Hxv[v][x].im() * -Math.sin(w);
                    I[v][u] += Hxv[v][x].re() * -Math.sin(w) + Hxv[v][x].im() * Math.cos(w);
                }
                H[v][u] = new Complex(R[v][u], I[v][u]);
            }
        }
        //G(u,v)
        for (int v = 0; v < Q; ++v) {
        	for (int u = 0; u < P; ++u) {
        		R[v][u] = H[v][u].re() * Fuv[v][u].re() - H[v][u].im() * Fuv[v][u].im();
        		I[v][u] = H[v][u].re() * Fuv[v][u].im() + H[v][u].im() * Fuv[v][u].re();
        		G[v][u] = new Complex(R[v][u], I[v][u]);
        	}
        }
        //g(u,v)
        for (int u = 0; u < P; u++) {
            for (int y = 0; y < Q; y++) {
                R[y][u] = 0;
                I[y][u] = 0;
                for (int v = 0; v < Q; v++) {
                    double w = 2 * Math.PI * v * y / Q;
                    R[y][u] += G[v][u].re() * Math.cos(w) - G[v][u].im() * Math.sin(w);
                    I[y][u] += G[v][u].re() * Math.sin(w) + G[v][u].im() * Math.cos(w);
                }
                guy[y][u] = new Complex(R[y][u], I[y][u]);
            }
        }
        for (int x = 0; x < P; x++) {
            for (int y = 0; y < Q; y++) {
                R[y][x] = 0;
                I[y][x] = 0;
                for (int u = 0; u < P; u++) {
                    double w = 2 * Math.PI * u * x / P;
                    R[y][x] += guy[y][u].re() * Math.cos(w) - guy[y][u].im() * Math.sin(w);
                    I[y][x] += guy[y][u].re() * Math.sin(w) + guy[y][u].im() * Math.cos(w);
                }
                R[y][x] = R[y][x] / P / Q;
                I[y][x] = I[y][x] / P / Q;
                g[y][x] = new Complex(R[y][x], I[y][x]);
            }
        }
        //gp
        for (int i = 0; i < Q; ++i) {
        	for (int j = 0; j < P; ++j) {
        		gp[i][j] = (Math.round(g[i][j].re() * Math.pow(-1, i + j)));
        		if (gp[i][j] < 0) {
        			gp[i][j] = 0;
        		} else if (gp[i][j] > 255) {
        			gp[i][j] = 255;
        		}
        	}
        }
        for (int i = 0; i < N; ++i) {
        	for (int j = 0; j < M; ++j) {
        		after[i * M + j] = (int) gp[i][j];
        	}
        }
        //把灰度值变成RGB值
        for (int i = 0; i < M * N; i++) {
            after_[i] = 0xff << 24 | after[i] << 16 | after[i] << 8 | after[i];
        }
        //绘制新图像
        DataBuffer dataBuffer = new DataBufferInt(after_, M * N);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, M, N, M, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
}
