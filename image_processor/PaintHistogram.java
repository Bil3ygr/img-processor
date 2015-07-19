package image_processor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PaintHistogram {
	//plot_hist
    public BufferedImage paintHistogram(int[] v, String[] str, Color[] color) {
    	int hWidth = 1;
        int hPitch = 0;
        float scaling = 1f;
        int maxStrWidth = 0;
        
        int width = str.length * hWidth + str.length * hPitch + 20;
        int height = 255;
        scaling = calculateScale(v, height);
        
        BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setFont(new Font(null, Font.PLAIN, 12));

        g.setColor(Color.BLACK);

        g.drawLine(10, 0, 10, height - 15);
        g.drawLine(10, height - 15, width, height - 15);
        
        int j = 0;
        int colorCount = color.length;
        
        for (int i = 0; i < v.length; ++i) {

            if (color != null){
                g.setColor(color[0]);
                if (j + 1 < colorCount){
                    j++;
                } else {
                    j=0;
                }
            }else{
                g.setColor(Color.RED);
            }

            int x = 10 + i * (hPitch + hWidth + (maxStrWidth >> 1));
            int y = height - 16 - (int) (v[i] * scaling);

            g.drawRect(x, y, hWidth, (int) (v[i] * scaling));
            g.fillRect(x, y, hWidth, (int) (v[i] * scaling));
        }
        return bufferImage;
    }
    //plot_hist
    public float calculateScale(int[] v , int h){
        float scale = 1f;
        int max = Integer.MIN_VALUE;
        for (int i = 0, len = v.length; i < len ; ++i) {
            if (v[i] > h && v[i] > max){
                max = v[i];
            }
        }
        if (max > h) {
            scale = ((int)(h * 1.0f / max * 1000)) * 1.0f / 1000;
        }
        return scale;
    }
}
