package image_processor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Animate implements ActionListener {
	Frame Fr;
	String[] last_name = {"gif"};
	JFileChooser dlg = new JFileChooser();
	FileFilter filter = new FileNameExtensionFilter("*.gif", last_name);
	String path;
	JFrame fr;
	
	public Animate(Frame frame) {
		Fr = frame;
		Fr.animate_.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		Object source = e.getSource();
		/*
		 * open file
		 */
		if (source == Fr.animate_) {
			dlg.setFileFilter(filter);
			dlg.setDialogTitle("打开文件");
			int result = dlg.showOpenDialog(Fr);
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = dlg.getSelectedFile();
				if (f.toString().endsWith(".gif")) {
					path = f.getAbsolutePath();
					createGifPanel(path);
				} else {
					String message = "不是指定格式的动画！";
					JOptionPane.showMessageDialog(Fr, message, "提醒", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	void createGifPanel(String s) {
		fr = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fr.setTitle("GifPlayer");
		fr.setLocation((screenSize.width - 500) / 2, (screenSize.height - 500) / 2);
		fr.setSize(500, 500);
		fr.setVisible(true);
		
		Image image = Toolkit.getDefaultToolkit().getImage(s);
		JLabel label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		fr.add(label, BorderLayout.CENTER);
		ImageIcon icon = new ImageIcon(image);
		label.setIcon(icon);
	}
}
