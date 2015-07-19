package image_processor;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class Equalize_Hist {
	Frame Fr;
	GetColor gc;
	PaintHistogram ph;
	
	JFrame fr;
    JPanel panel;
    JLabel label;
    JTextField tf;
    JButton bt;
	
	public Equalize_Hist(Frame frame) {
		Fr = frame;
		gc = new GetColor();
		ph = new PaintHistogram();
	}
	//equalize_hist_window
    public void equalize_hist_window() {
    	fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 260) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(260, 100);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("请选择操作，1（RGB），2（RGB/3）");
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
                equalize_hist(Integer.parseInt(tf.getText()));
            }
        });
    }
	//equalize_hist
    public void equalize_hist(int flag) {
    	JFrame f = new JFrame();
    	JFrame f_ = new JFrame();
    	JFrame f__ = new JFrame();
    	JLabel pic = new JLabel();
        JLabel pic_ = new JLabel();
        JLabel pic__ = new JLabel();
        
        BufferedImage img = Fr.before;
        
        int wid = img.getWidth();
        int hei = img.getHeight();
        int[] beforeimg = new int[wid * hei];
        img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
        int[] afterimg = new int[wid * hei];
        int[] countimg = new int[256];
        int[] countimg_ = new int[256];
        int[] rData = new int[256];
        int[] gData = new int[256];
        int[] bData = new int[256];
        int[] rData_ = new int[256];
        int[] gData_ = new int[256];
        int[] bData_ = new int[256];
        int[] intensity = new int[256];
        int[] intensity_ = new int[256];

        if (flag == 0) {
        	//show gray
            f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Equalize_Gray");
            f.setVisible(true);
            f.setSize(400, 400);
            
            for (int i = 0; i < 256; i++) {
                countimg[i] = 0;
            }
            for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int gray = gc.getGray(img.getRGB(j, i));
        			countimg[gray]++;
        		}
        	}
        	/*
        	 * count the gray level after equalize
        	 */
        	for (int i = 0; i < 256; i++) {
        		if (i == 0) {
        			countimg_[i] = Math.round(255 * countimg[i] / (wid * hei));
        		} else {
        			countimg_[i] = Math.round(countimg_[i-1] + 255 * countimg[i] / (wid * hei));
        		}
        		if (countimg_[i] > 255) {
        			countimg_[i] = 255;
        		}
        	}
        	/*
        	 * create new image's array
        	 */
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int gray = gc.getGray(beforeimg[i * wid + j]);
        			afterimg[i * wid + j] = (beforeimg[i * wid + j] & 0xff000000) + (countimg_[gray] << 16) + (countimg_[gray] << 8) + countimg_[gray];
        		}
        	}
        	
        	String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	/*
        	 * create new histogram image
        	 */
        	for (int i = 0; i < 256; i++) {
        		countimg[i] = 0;
        	}
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int gray = gc.getGray(afterimg[i * wid + j]);
        			countimg[gray]++;
        		}
        	}
        	BufferedImage scaleimage = ph.paintHistogram(countimg, str, new Color[] {Color.BLACK});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        } else if (flag == 1) {
        	//show red
            f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Equalize_Red");
            f.setVisible(true);
            f.setSize(400, 400);
            //show green
            f_.getContentPane().add(pic_, BorderLayout.CENTER);
            f_.setTitle("Equalize_Green");
            f_.setVisible(true);
            f_.setSize(400, 400);
            //show blue
            f__.getContentPane().add(pic__, BorderLayout.CENTER);
            f__.setTitle("Equalize_Blue");
            f__.setVisible(true);
            f__.setSize(400, 400);
           
            for (int i = 0; i < 256; i++) {
                rData[i] = 0;
                gData[i] = 0;
                bData[i] = 0;
            }
            for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = gc.getRed(img.getRGB(j, i));
        			rData[red]++;
        			int green = gc.getGreen(img.getRGB(j, i));
        			gData[green]++;
        			int blue = gc.getBlue(img.getRGB(j, i));
        			bData[blue]++;
        		}
        	}
            //equalize
            for (int i = 0; i < 256; i++) {
        		if (i == 0) {
        			rData_[i] = Math.round(255 * rData[i] / (wid * hei));
        			gData_[i] = Math.round(255 * gData[i] / (wid * hei));
        			bData_[i] = Math.round(255 * bData[i] / (wid * hei));
        		} else {
        			rData_[i] = Math.round(rData_[i - 1] + 255 * rData[i] / (wid * hei));
        			gData_[i] = Math.round(gData_[i - 1] + 255 * gData[i] / (wid * hei));
        			bData_[i] = Math.round(bData_[i - 1] + 255 * bData[i] / (wid * hei));
        		}
        		if (rData_[i] > 255) {
        			rData_[i] = 255;
        		}
        		if (gData_[i] > 255) {
        			gData_[i] = 255;
        		}
        		if (bData_[i] > 255) {
        			bData_[i] = 255;
        		}
        	}
            /*
        	 * create new image's array
        	 */
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = gc.getRed(beforeimg[i * wid + j]);
        			int green = gc.getGreen(beforeimg[i * wid + j]);
        			int blue = gc.getBlue(beforeimg[i * wid + j]);
        			afterimg[i * wid + j] = (beforeimg[i * wid + j] & 0xff000000) + (rData_[red] << 16) + (gData_[green] << 8) + bData_[blue];
        		}
        	}
            //draw picture
            String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	/*
        	 * create new histogram image
        	 */
        	for (int i = 0; i < 256; i++) {
        		rData[i] = 0;
        		gData[i] = 0;
        		bData[i] = 0;
        	}
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = gc.getRed(afterimg[i * wid + j]);
        			rData[red]++;
        			int green = gc.getGreen(afterimg[i * wid + j]);
        			gData[green]++;
        			int blue = gc.getBlue(afterimg[i * wid + j]);
        			bData[blue]++;
        		}
        	}
        	BufferedImage scaleimage = ph.paintHistogram(rData, str, new Color[] {Color.RED});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        	scaleimage = ph.paintHistogram(gData, str, new Color[] {Color.GREEN});
        	icon = new ImageIcon(scaleimage);
        	pic_.setIcon(icon);
        	pic_.repaint();
        	scaleimage = ph.paintHistogram(bData, str, new Color[] {Color.BLUE});
        	icon = new ImageIcon(scaleimage);
        	pic__.setIcon(icon);
        	pic__.repaint();
        } else if (flag == 2) {
        	//show red
            f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Equalize_Red");
            f.setVisible(true);
            f.setSize(400, 400);
            //show green
            f_.getContentPane().add(pic_, BorderLayout.CENTER);
            f_.setTitle("Equalize_Green");
            f_.setVisible(true);
            f_.setSize(400, 400);
            //show blue
            f__.getContentPane().add(pic__, BorderLayout.CENTER);
            f__.setTitle("Equalize_Blue");
            f__.setVisible(true);
            f__.setSize(400, 400);
           
            for (int i = 0; i < 256; i++) {
                rData[i] = 0;
                gData[i] = 0;
                bData[i] = 0;
                intensity[i] = 0;
                intensity_[i] = 0;
            }
            for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = gc.getRed(img.getRGB(j, i));
        			rData[red]++;
        			int green = gc.getGreen(img.getRGB(j, i));
        			gData[green]++;
        			int blue = gc.getBlue(img.getRGB(j, i));
        			bData[blue]++;
        		}
        	}
            for (int i = 0; i <256; ++i) {
            	intensity[i] = (int) Math.round((1.0 * (rData[i] + gData[i] + bData[i]) / 3));
            }
            //equalize
            for (int i = 0; i < 256; i++) {
        		if (i == 0) {
        			intensity_[i] = Math.round(255 * intensity[i] / (wid * hei));
        		} else {
        			intensity_[i] = Math.round(intensity_[i - 1] + 255 * intensity[i] / (wid * hei));
        		}
        		if (intensity_[i] > 255) {
        			intensity_[i] = 255;
        		}
        	}
            /*
        	 * create new image's array
        	 */
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = gc.getRed(beforeimg[i * wid + j]);
        			int green = gc.getGreen(beforeimg[i * wid + j]);
        			int blue = gc.getBlue(beforeimg[i * wid + j]);
        			afterimg[i * wid + j] = (beforeimg[i * wid + j] & 0xff000000) + (intensity_[red] << 16) + (intensity_[green] << 8) + intensity_[blue];
        		}
        	}
            //draw picture
            String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	/*
        	 * create new histogram image
        	 */
        	for (int i = 0; i < 256; i++) {
        		rData[i] = 0;
        		gData[i] = 0;
        		bData[i] = 0;
        	}
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = gc.getRed(afterimg[i * wid + j]);
        			rData[red]++;
        			int green = gc.getGreen(afterimg[i * wid + j]);
        			gData[green]++;
        			int blue = gc.getBlue(afterimg[i * wid + j]);
        			bData[blue]++;
        		}
        	}
        	BufferedImage scaleimage = ph.paintHistogram(rData, str, new Color[] {Color.RED});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        	scaleimage = ph.paintHistogram(gData, str, new Color[] {Color.GREEN});
        	icon = new ImageIcon(scaleimage);
        	pic_.setIcon(icon);
        	pic_.repaint();
        	scaleimage = ph.paintHistogram(bData, str, new Color[] {Color.BLUE});
        	icon = new ImageIcon(scaleimage);
        	pic__.setIcon(icon);
        	pic__.repaint();
        }
        /*
         * draw the image after equalize
         */
        DataBuffer dataBuffer = new DataBufferInt(afterimg, wid * hei);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage scaleimage_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = scaleimage_;
        ImageIcon icon_ = new ImageIcon(scaleimage_);
        Fr.Pic.setIcon(icon_);
        Fr.Pic.repaint();
    }
}
