package image_processor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Plot_Hist {
	Frame Fr;
	GetColor gc;
	PaintHistogram ph;
	
	public Plot_Hist(Frame frame) {
		Fr = frame;
		gc = new GetColor();
		ph = new PaintHistogram();
	}
	//plot_hist
    public void plot_hist(int flag) {
    	JFrame f = new JFrame();
    	JFrame f_ = new JFrame();
    	JFrame f__ = new JFrame();
    	JLabel pic = new JLabel();
        JLabel pic_ = new JLabel();
        JLabel pic__ = new JLabel();
        
        BufferedImage img = Fr.before;
        
        int wid = img.getWidth();
        int hei = img.getHeight();
        int[] countimg = new int[256];
        int[] rData = new int[256];
        int[] gData = new int[256];
        int[] bData = new int[256];
        
        if (flag == 0) {
        	f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Histogram_Gray");
            f.setVisible(true);
            f.setSize(400, 400);
            
            for (int i = 0; i < 256; i++) {
        		countimg[i] = 0;
        	}
        	/*
        	 * save the number of gray level
        	 */
        	for (int i = 0; i < wid; i++) {
        		for (int j = 0; j < hei; j++) {
        			int gray = gc.getGray(img.getRGB(i, j));
        			countimg[gray]++;
        		}
        	}
        	String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	/*
        	 * create the histogram image
        	 */
        	BufferedImage scaleimage = ph.paintHistogram(countimg, str, new Color[] {Color.BLACK});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        } else if (flag == 1) {
        	//show red
            f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Histogram_Red");
            f.setVisible(true);
            f.setSize(400, 400);
            //show green
            f_.getContentPane().add(pic_, BorderLayout.CENTER);
            f_.setTitle("Histogram_Green");
            f_.setVisible(true);
            f_.setSize(400, 400);
            //show blue
            f__.getContentPane().add(pic__, BorderLayout.CENTER);
            f__.setTitle("Histogram_Blue");
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
        	String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	/*
        	 * create the histogram image
        	 */
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
    }
}
