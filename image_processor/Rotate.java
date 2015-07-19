package image_processor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Rotate {
	Frame Fr;
	JFrame fr;
	JPanel panel;
	JLabel label;
	JTextField tf;
	JButton bt;
	
	public Rotate(Frame frame) {
		Fr = frame;
	}
	// 旋转
	public void rotate_window() {
		fr = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		tf = new JTextField();
		bt = new JButton();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
		fr.setSize(250, 100);
		fr.setVisible(true);
		
		panel.setLayout(new GridLayout(3, 1));
		label = new JLabel("旋转角度");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		bt.setText("确定");
		panel.add(label);
		panel.add(tf);
		panel.add(bt);
		
		fr.getContentPane().add(panel, BorderLayout.CENTER);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				fr.dispose();
				rotate(Integer.parseInt(tf.getText()));
			}
		});
	}
	
	public void rotate(int degree) {
		//BufferedImage img = Fr.st.peek();
		BufferedImage img = Fr.before;
		int ih = img.getHeight();
		int iw = img.getWidth();
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		degree = degree % 360;
		
		if (degree < 0) {
			degree = 360 + degree;//将角度转换到0-360度之间
		}
		
		double ang = Math.toRadians(degree);//将角度转为弧度
		/**
		 *确定旋转后的图象的高度和宽度
		 */
		if (degree == 180 || degree == 0 || degree == 360) {
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) {
			w = ih;
			h = iw;
		} else {
			int angel = degree % 180;
			if (angel < 0) {
				angel = Math.abs(angel);
			}
			if (angel > 90) {
				angel = 180 - angel;
			}
			double r = Math.sqrt(ih * ih + iw * iw) / 2;
			double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
			double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
			double angel_dalta_width = Math.atan((double) ih / iw);
			double angel_dalta_height = Math.atan((double) iw / ih);
			int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
			int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
			w = iw + len_dalta_width * 2;
			h = ih + len_dalta_height * 2;
		}
		x = (w - iw) / 2;//确定原点坐标
		y = (h - ih) / 2;
		BufferedImage image = new BufferedImage(w, h, img.getType());
		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);//旋转图象
		at.translate(x, y);//平移至原点
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		op.filter(img, image);
		Fr.st.push(image);
		Fr.after = image;
		ImageIcon icon = new ImageIcon(image);
		Fr.Pic.setIcon(icon);
		Fr.Pic.repaint();
	}
}
