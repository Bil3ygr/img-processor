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
    // �ļ�����
    String[] jpg = {"jpg", "jpeg", "JPG", "JPEG"};
    String[] bmp = {"bmp", "BMP"};
    String[] png = {"png", "PNG"};
    // �ļ����͹�����
    FileFilter filter_jpg = new FileNameExtensionFilter("*.jpg", jpg);
    FileFilter filter_bmp = new FileNameExtensionFilter("*.bmp", bmp);
    FileFilter filter_png = new FileNameExtensionFilter("*.png", png);
    // ��ǰ����·��
    String absolutePath;
    
    public Files(Frame frame) {
        Fr = frame;
        // ����¼�
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
         * ���ļ�
         */
        if (source == Fr.openFile) {
        	// ��Ĭ��Ŀ¼
        	if (absolutePath != null) {
        		dlg = new JFileChooser(new File(absolutePath));
        	} else {
        		dlg = new JFileChooser();
        	}
        	// ���ù�����
            dlg.setFileFilter(filter_bmp);
            dlg.setFileFilter(filter_png);
            dlg.setFileFilter(filter_jpg);
            dlg.setDialogTitle("���ļ�");
            int result = dlg.showOpenDialog(Fr);
            if (result == JFileChooser.APPROVE_OPTION) {
            	// ѡ���ļ��ľ���·��
                String path = dlg.getSelectedFile().getAbsolutePath();
                // ���þ���·��
                absolutePath = path;
                Fr.currentPath = path;
                try {
                    BufferedImage read = ImageIO.read(new File(path));
                    // ��Ϊ�գ���ȡ��ȷ
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
                    	String message = "���ļ�����ͼƬ��";
                    	JOptionPane.showMessageDialog(Fr, message, "����", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        /*
         * �����ļ�
         */
        else if (source == Fr.saveFile) {
        	// ��ѡ���ļ���Ĭ��Ŀ¼
        	dlg = new JFileChooser(new File(absolutePath));
        	// ���ù�����
        	dlg.setFileFilter(filter_bmp);
            dlg.setFileFilter(filter_png);
            dlg.setFileFilter(filter_jpg);
            dlg.setDialogTitle("�����ļ�");
            int result = dlg.showSaveDialog(Fr);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = dlg.getSelectedFile().getAbsolutePath();
                try {
                    File file;
                    BufferedImage bi = new BufferedImage(Fr.after.getWidth(null), Fr.after.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = bi.createGraphics();
                    g2.drawImage(Fr.after, 0, 0, null);
                    g2.dispose();
                    // �����ļ���ֻ�������ļ������Զ���ɺ�׺
                    if (dlg.getFileFilter() == filter_bmp) {
                    	// �ļ�����β�Ƿ�Ϊbmp
                    	if (!path.endsWith(".bmp") && !path.endsWith(".BMP")) {
                    		file = new File(path + ".bmp");
                    	} else {
                    		file = new File(path);
                    	}
                    	// �ļ��Ƿ��Ѵ���
                    	if (file.exists()) {
                    		String message = "�ļ��Ѵ��ڣ��Ƿ񸲸ǣ�";
                    		int n = JOptionPane.showConfirmDialog(Fr, message, "����", JOptionPane.OK_CANCEL_OPTION);
                    		if (n == 0) {
                    			ImageIO.write(bi, "bmp", file);
                    		}
                    	} else {
                    		ImageIO.write(bi, "bmp", file);
                    	}
                    } else if (dlg.getFileFilter() == filter_png) {
                    	// �ļ�����β�Ƿ�Ϊpng
                    	if (!path.endsWith(".png") && !path.endsWith(".PNG")) {
                    		file = new File(path + ".png");
                    	} else {
                    		file = new File(path);
                    	}
                    	// �ļ��Ƿ��Ѵ���
                    	if (file.exists()) {
                    		String message = "�ļ��Ѵ��ڣ��Ƿ񸲸ǣ�";
                    		int n = JOptionPane.showConfirmDialog(Fr, message, "����", JOptionPane.OK_CANCEL_OPTION);
                    		if (n == 0) {
                    			ImageIO.write(bi, "png", file);
                    		}
                    	} else {
                    		ImageIO.write(bi, "png", file);
                    	}
                    } else {
                    	// �ļ�����β�Ƿ�Ϊjpg
                    	if (!path.endsWith(".jpg") && !path.endsWith(".JPG") && !path.endsWith(".jpeg") && !path.endsWith(".JPEG")) {
                    		file = new File(path + ".jpg");
                    	} else {
                    		file = new File(path);
                    	}
                    	// �ļ��Ƿ��Ѵ���
                    	if (file.exists()) {
                    		String message = "�ļ��Ѵ��ڣ��Ƿ񸲸ǣ�";
                    		int n = JOptionPane.showConfirmDialog(Fr, message, "����", JOptionPane.OK_CANCEL_OPTION);
                    		if (n == 0) {
                    			ImageIO.write(bi, "jpg", file);
                    		}
                    	} else {
                    		ImageIO.write(bi, "jpg", file);
                    	}
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        /*
         * �˳�
         */
        else {
            System.exit(0);
        }
    }
}