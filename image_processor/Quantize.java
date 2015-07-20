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

public class Quantize {
	Frame Fr;
	JPanel panel;
    JLabel label;
    JTextField tf;
    JTextField tf_;
    JButton bt;
    JDialog jdlg;
    
    public Quantize(Frame frame) {
    	Fr = frame;
    }
    //quantize
  	public void quantize_window() {
  		panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        jdlg = new JDialog(Fr, "�Ҷȼ�", true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jdlg.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        jdlg.setSize(250, 100);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("������ͼ����»Ҷȼ�");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("ȷ��");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
         
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jdlg.dispose();
          		quantize(Integer.parseInt(tf.getText()));
        	}
        });
        jdlg.getContentPane().add(panel, BorderLayout.CENTER);
        jdlg.setVisible(true);
  	}
  	//quantize
  	public void quantize(int level) {
  		BufferedImage img = Fr.before;
        int wid = img.getWidth();
        int hei = img.getHeight();
        int beforeimg[] = new int[wid * hei];
        int afterimg[] = new int[wid * hei];
        img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
        
        afterimg = imgScale(beforeimg, wid, hei, level);
        DataBuffer dataBuffer = new DataBufferInt(afterimg, wid * hei);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = scaleimage;
        ImageIcon icon = new ImageIcon(scaleimage);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
  	}
  	//quantize
  	public int[] imgScale(int[] inPixelsData, int wid, int hei, int level) {
        int[][] inputdata = process(inPixelsData, hei, wid);
        int[][] outputdata = new int[wid * hei][4];
        int level_ = level - 1;
        float[] dev = new float[4];
        float[] tmp = new float[4];
        for (int i = 0; i < wid * hei; i++) {
            for (int j = 0; j < 4; j++) {
                dev[j] = 255;
            }
            for (int j = 0; j < level; j++) {
                for (int k = 0; k < 4; k++) {
                    if (255 / level_ * j > inputdata[i][k])
                        tmp[k] = 255 / level_ * j - inputdata[i][k];
                    else 
                        tmp[k] = inputdata[i][k] - 255 / level_ * j;
                    if (k == 0) {
                        outputdata[i][k] = 255;
                    } else {
                        if (tmp[k] < dev[k]) {
                            dev[k] = tmp[k];
                            outputdata[i][k] = Math.round(255 / level_ * j);
                        }
                    }
                }
            }
        }
        return back(outputdata, hei, wid);
    }
  	//quantize
    public int[][] process(int[] pixels, int rows, int cols) {
        int[][] tempdata = new int[rows * cols][4];
        for (int i = 0; i < rows * cols; i++) {
            tempdata[i][0] = (pixels[i] >> 24) & 0xff;
            tempdata[i][1] = (pixels[i] >> 16) & 0xff;
            tempdata[i][2] = (pixels[i] >> 8) & 0xff;
            tempdata[i][3] = (pixels[i]) & 0xff;
        }
        return tempdata;
    }
    //quantize
    public int[] back(int[][] pixels, int rows, int cols) {
        int[] tmp = new int[rows * cols];
        for (int i = 0; i < rows * cols; i++) {
            tmp[i] = ((pixels[i][0] << 24) & 0xff000000) | ((pixels[i][1] << 16) & 0x00ff0000) | ((pixels[i][2] << 8) & 0x0000ff00) | ((pixels[i][3]) & 0x000000ff);
        }
        return tmp;
    }
}
