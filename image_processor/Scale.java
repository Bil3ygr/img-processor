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

public class Scale {
	Frame Fr;
	JFrame fr;
    JPanel panel;
    JPanel panel_;
    JLabel label;
    JTextField tf;
    JTextField tf_;
    JButton bt;
    public Scale(Frame frame) {
    	Fr = frame;
    }
	// 输入缩放后图片的大小
	public void scale_window() {
		fr = new JFrame();
        panel = new JPanel();
        panel_ = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 100);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(3, 1));
        panel_.setLayout(new GridLayout(1, 2));
        label = new JLabel("请输入新图像的大小（宽高）");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("确定");
        panel.add(label);
        panel_.add(tf);
        panel_.add(tf_);
        panel.add(panel_);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		scale(Integer.parseInt(tf.getText()), Integer.parseInt(tf_.getText()));
        	}
        });
	}
	// 进行缩放
	public void scale(int w, int h) {
		BufferedImage img = Fr.before;
        int wid = img.getWidth();
        int hei = img.getHeight();
        int width = w;
        int height = h;
        int afterimg[] = new int[width * height];
        int beforeimg[] = new int[wid * hei];
        img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
        
        afterimg = imgScale(beforeimg, wid, hei, width, height);
        DataBuffer dataBuffer = new DataBufferInt(afterimg, width * height);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, width, height, width, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = scaleimage;
        ImageIcon icon = new ImageIcon(scaleimage);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
	}
	//scale
	public int[] imgScale(int[] inPixelsData, int wid, int hei, int width, int height) {
        int[][][] inputThreeDeminsionData = processOneToThreeDeminsion(inPixelsData, hei, wid);
        int[][][] outputThreeDeminsionData = new int[height][width][4];
        float rowRatio = ((float) hei) / ((float) height);
        float colRatio = ((float) wid) / ((float) width);
        for (int row = 0; row < height; row++) {
            int row_ = Math.round(((float) row) * rowRatio);
            if (row_ >= hei) {
                row_ = hei - 1;
            }
            for (int col = 0; col < width; col++) {
                int col_ = Math.round(((float) col) * colRatio);
                if (col_ >= wid) {
                    col_ = wid - 1;
                }
                outputThreeDeminsionData[row][col][0] = inputThreeDeminsionData[row_][col_][0];
                outputThreeDeminsionData[row][col][1] = inputThreeDeminsionData[row_][col_][1];
                outputThreeDeminsionData[row][col][2] = inputThreeDeminsionData[row_][col_][2];
                outputThreeDeminsionData[row][col][3] = inputThreeDeminsionData[row_][col_][3];
            }
        }
        return convertToOneDim(outputThreeDeminsionData, width, height);
    }
	//scale
	public int[][][] processOneToThreeDeminsion(int[] oneDPix2, int imgRows, int imgCols) {
        int[][][] tempData = new int[imgRows][imgCols][4];
        for (int row = 0; row < imgRows; row++) {
            int[] aRow = new int[imgCols];
            for (int col = 0; col < imgCols; col++) {
                int element = row * imgCols + col;
                aRow[col] = oneDPix2[element];
            }
            for (int col = 0; col < imgCols; col++) {
                tempData[row][col][0] = (aRow[col] >> 24) & 0xff;
                tempData[row][col][1] = (aRow[col] >> 16) & 0xff;
                tempData[row][col][2] = (aRow[col] >> 8) & 0xff;
                tempData[row][col][3] = (aRow[col]) & 0xff;
            }
        }
        return tempData;
    }
	//scale
	public int[] convertToOneDim(int[][][] data, int imgCols, int imgRows) {
        int[] oneDPix = new int[imgCols * imgRows * 4];
        for (int row = 0, cnt = 0; row < imgRows; row++) {
            for (int col = 0; col < imgCols; col++) {
                oneDPix[cnt] = ((data[row][col][0] << 24) & 0xff000000) | ((data[row][col][1] << 16) & 0x00ff0000) | ((data[row][col][2] << 8) & 0x0000ff00) | ((data[row][col][3]) & 0x000000ff);
                cnt++;
            }
	        }
        return oneDPix;
    }
}