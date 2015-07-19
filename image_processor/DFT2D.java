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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DFT2D {
	Frame Fr;
	JFrame fr;
	JPanel panel;
	JLabel label;
	JTextField tf;
	JButton bt;
	
	public DFT2D(Frame frame) {
		Fr = frame;
	}
	//dft2d
    public void dft2d_window() {
    	fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 100);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("请选择操作，0（DFT）或1（IDFT）");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("确定");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		dft2d(Integer.parseInt(tf.getText()));
        	}
        });
    }
    //dft2d
    public void dft2d(int flag) {
    	BufferedImage input = Fr.before;
    	/*
         * 获取宽高
         */
        int M = input.getWidth();
        int N = input.getHeight();
        /*
         * original存RGB值
         */
        int[] original = new int[M * N];
        int[][] f = new int[N][M];
        double[][] F = new double[N][M];
        /*
         * 变换后
         */
        int[] after = new int[M * N];
        int[] after_ = new int[M * N];
        /*
         * DFT
         */
        Complex[][] Fxv = new Complex[N][M];
        Complex[][] Fuv = new Complex[N][M];
        /*
         * IDFT
         */
        Complex[][] fuy = new Complex[N][M];
        Complex[][] fxy = new Complex[N][M];
        /*
         * R存实部，I存虚部
         */
        double[][] R = new double[N][M];
        double[][] I = new double[N][M];
        /*
         * 获得RGB值，放入original
         */
        input.getRGB(0, 0, M, N, original, 0, M);
        /*
         * 把RGB值变成灰度值并翻转
         */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                f[i][j] = (int) ((original[i * M + j] & 0xff) * Math.pow(-1, i + j));
            }
        }
        /*
         * DFT
         */
        for (int x = 0; x < M; x++) {
            for (int v = 0; v < N; v++) {
                R[v][x] = 0;
                I[v][x] = 0;
                for (int y = 0; y < N; y++) {
                    double w = 2 * Math.PI * v * y / N;
                    R[v][x] += f[y][x] * Math.cos(w);
                    I[v][x] += f[y][x] * -Math.sin(w);
                }
                Fxv[v][x] = new Complex(R[v][x], I[v][x]);
            }
        }
        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                R[v][u] = 0;
                I[v][u] = 0;
                for (int x = 0; x < M; x++) {
                    double w = 2 * Math.PI * u * x / M;
                    R[v][u] += Fxv[v][x].re() * Math.cos(w) - Fxv[v][x].im() * -Math.sin(w);
                    I[v][u] += Fxv[v][x].re() * -Math.sin(w) + Fxv[v][x].im() * Math.cos(w);
                }
                R[v][u] = R[v][u] / M / N;
                I[v][u] = I[v][u] / M / N; 
                Fuv[v][u] = new Complex(R[v][u], I[v][u]);
            }
        }
        /*
         * IDFT
         */
        if (flag == 1) {
            for (int u = 0; u < M; u++) {
                for (int y = 0; y < N; y++) {
                    R[y][u] = 0;
                    I[y][u] = 0;
                    for (int v = 0; v < N; v++) {
                        double w = 2 * Math.PI * v * y / N;
                        R[y][u] += Fuv[v][u].re() * Math.cos(w) - Fuv[v][u].im() * Math.sin(w);
                        I[y][u] += Fuv[v][u].re() * Math.sin(w) + Fuv[v][u].im() * Math.cos(w);
                    }
                    fuy[y][u] = new Complex(R[y][u], I[y][u]);
                }
            }
            for (int x = 0; x < M; x++) {
                for (int y = 0; y < N; y++) {
                    R[y][x] = 0;
                    I[y][x] = 0;
                    for (int u = 0; u < M; u++) {
                        double w = 2 * Math.PI * u * x / M;
                        R[y][x] += fuy[y][u].re() * Math.cos(w) - fuy[y][u].im() * Math.sin(w);
                        I[y][x] += fuy[y][u].re() * Math.sin(w) + fuy[y][u].im() * Math.cos(w);
                    }
                    fxy[y][x] = new Complex(R[y][x], I[y][x]);
                }
            }
        }
        if (flag == 0) {
            /*
             * 取模并用log计算
             */
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    F[i][j] = Math.log(Fuv[i][j].abs() + 1);
                }
            }
            /*
             * 求Min和Max
             */
            double Max = 0;
            double Min = 1000000;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (F[i][j] > Max) {
                        Max = F[i][j];
                    }
                    if (F[i][j] < Min) {
                        Min = F[i][j];
                    }
                }
            }
            /*
             * 把区间从Min~Max转换到0~255
             */
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    after[i * M + j] = (int) ((F[i][j] - Min) * 255 / (Max - Min));
                }
            }
        } else if (flag == 1){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    after[i * M + j] = (int) (Math.round(fxy[i][j].re()) * Math.pow(-1, i + j));
                    if (after[i * M + j] < 0) {
                    	after[i * M + j] = 0;
                    }
                }
            }
        }
        /*
         * 把灰度值变成RGB值
         */
        for (int i = 0; i < M * N; i++) {
            after_[i] = 0xff << 24 | after[i] << 16 | after[i] << 8 | after[i];
        }
        /*
         * 绘制新图像
         */
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
