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
    JFileChooser dlg;
    // 文件类型
    String[] jpg = {"jpg", "jpeg", "JPG", "JPEG"};
    String[] bmp = {"bmp", "BMP"};
    String[] png = {"png", "PNG"};
    // 文件类型过滤器
    FileFilter filter_jpg = new FileNameExtensionFilter("*.jpg", jpg);
    FileFilter filter_bmp = new FileNameExtensionFilter("*.bmp", bmp);
    FileFilter filter_png = new FileNameExtensionFilter("*.png", png);
    // 当前绝对路径
    String absolutePath;
    
    public Files(Frame frame) {
        Fr = frame;
        // 点击事件
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
         * 打开文件
         */
        if (source == Fr.openFile) {
        	// 打开默认目录
        	if (absolutePath != null) {
        		dlg = new JFileChooser(new File(absolutePath));
        	} else {
        		dlg = new JFileChooser();
        	}
        	// 设置过滤器
            dlg.setFileFilter(filter_bmp);
            dlg.setFileFilter(filter_png);
            dlg.setFileFilter(filter_jpg);
            dlg.setDialogTitle("打开文件");
            int result = dlg.showOpenDialog(Fr);
            if (result == JFileChooser.APPROVE_OPTION) {
            	// 选中文件的绝对路径
                String path = dlg.getSelectedFile().getAbsolutePath();
                // 设置绝对路径
                absolutePath = path;
                try {
                    BufferedImage read = ImageIO.read(new File(path));
                    // 不为空，读取正确
                    if (read != null) {
                        Fr.before = read;
                        Fr.after = read;
                        
                        if (!Fr.st.isEmpty()) {
                        	Fr.st.clear();
                        }
                        if (!Fr.st_.isEmpty()) {
                        	Fr.st_.clear();
                        }
                        Fr.st.push(read);
                    
                        ImageIcon icon = new ImageIcon(Fr.before);
                        Fr.Pic.setIcon(icon);
                        Fr.Pic.repaint();
                    } else {
                    	String message = "此文件不是图片！";
                    	JOptionPane.showMessageDialog(Fr, message, "提醒", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        /*
         * 保存文件
         */
        else if (source == Fr.saveFile) {
        	// 打开选中文件的默认目录
        	dlg = new JFileChooser(new File(absolutePath));
        	// 设置过滤器
        	dlg.setFileFilter(filter_bmp);
            dlg.setFileFilter(filter_png);
            dlg.setFileFilter(filter_jpg);
            dlg.setDialogTitle("保存文件");
            int result = dlg.showSaveDialog(Fr);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = dlg.getSelectedFile().getAbsolutePath();
                try {
                    File file;
                    BufferedImage bi = new BufferedImage(Fr.after.getWidth(null), Fr.after.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = bi.createGraphics();
                    g2.drawImage(Fr.after, 0, 0, null);
                    g2.dispose();
                    // 保存文件，只需输入文件名，自动完成后缀
                    if (dlg.getFileFilter() == filter_bmp) {
                    	file = new File(path + ".bmp");
                    	ImageIO.write(bi, "bmp", file);
                    } else if (dlg.getFileFilter() == filter_png) {
                    	file = new File(path + ".png");
                    	ImageIO.write(bi, "png", file);
                    } else {
                    	file = new File(path + ".jpg");
                    	ImageIO.write(bi, "jpg", file);
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        /*
         * 退出
         */
        else {
            System.exit(0);
        }
    }
}