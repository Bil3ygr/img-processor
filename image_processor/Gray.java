package image_processor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;

public class Gray {
	Frame Fr;
	GetColor gc;
	
	public Gray(Frame frame) {
		Fr = frame;
		gc = new GetColor();
	}
	//
	public void gray() {
		BufferedImage img = Fr.st.peek();
        int wid = img.getWidth();
        int hei = img.getHeight();
        int afterimg[] = new int[wid * hei];
        
        for (int i = 0; i < hei; i++) {
    		for (int j = 0; j < wid; j++) {
    			int gray = gc.getGray(img.getRGB(j, i));
    			afterimg[i * wid + j] = (255 << 24) | (gray << 16) | (gray << 8 ) | gray;
    		}
    	}
        
        DataBuffer dataBuffer = new DataBufferInt(afterimg, wid * hei);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
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
