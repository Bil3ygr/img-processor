package image_processor;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

public class Files implements ActionListener {
    Frame Fr;
    JFileChooser dlg = new JFileChooser();
    String[] jpg = {"jpg", "jpeg", "JPG", "JPEG"};
    String[] bmp = {"bmp", "BMP"};
    String[] png = {"png", "PNG"};
    FileFilter filter_jpg = new FileNameExtensionFilter("*.jpg", jpg);
    FileFilter filter_bmp = new FileNameExtensionFilter("*.bmp", bmp);
    FileFilter filter_png = new FileNameExtensionFilter("*.png", png);
    
    public Files(Frame frame) {
        Fr = frame;
        Fr.openFile.addActionListener(this);
        Fr.saveFile.addActionListener(this);
        Fr.exit.addActionListener(this);
    }
    /*
     * actions when click the JMenuItem
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        /*
         * open file
         */
        if (source == Fr.openFile) {
        	dlg.setFileFilter(filter_bmp);
        	dlg.setFileFilter(filter_png);
        	dlg.setFileFilter(filter_jpg);
            dlg.setDialogTitle("Open file");
            int result = dlg.showOpenDialog(Fr);
            if (result == JFileChooser.APPROVE_OPTION) {
                File f = dlg.getSelectedFile();
                String path = f.getAbsolutePath();
                try {
                    BufferedImage read = ImageIO.read(new File(path));
                    
                    Fr.before = read;
                    Fr.after = read;
                    
                    ImageIcon icon = new ImageIcon(Fr.before);
                    Fr.Pic.setIcon(icon);
                    Fr.Pic.repaint();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        /*
         * save file
         */
        else if (source == Fr.saveFile) {
            dlg.setDialogTitle("Save file");
            int result = dlg.showSaveDialog(Fr);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = dlg.getSelectedFile().getAbsolutePath();
                try {
                	File file = new File(path);
                    BufferedImage bi = new BufferedImage(Fr.after.getWidth(null), Fr.after.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = bi.createGraphics();
                    g2.drawImage(Fr.after, 0, 0, null);
                    g2.dispose();
                    ImageIO.write(bi, "jpg", file);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        /*
         * exit
         */
        else {
            System.exit(0);
        }
    }
}