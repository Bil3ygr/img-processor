package image_processor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Rotate {
	Frame Fr;
	JPanel panel;
	JLabel label;
	JTextField tf;
	JButton bt;
	JDialog jdlg;
	
	public Rotate(Frame frame) {
		Fr = frame;
	}
	// ��ת
	public void rotate_window() {
		panel = new JPanel();
		label = new JLabel();
		tf = new JTextField();
		bt = new JButton();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		jdlg = new JDialog(Fr, "��ת", true);
		jdlg.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
		jdlg.setSize(250, 100);
		jdlg.getRootPane().setDefaultButton(bt);
		
		panel.setLayout(new GridLayout(3, 1));
		label = new JLabel("��ת�Ƕȣ���С��0��");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		bt.setText("ȷ��");
		panel.add(label);
		panel.add(tf);
		panel.add(bt);
		// ָ�������ʽ
		tf.addKeyListener(new keyListener());
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				try {
					rotate(Integer.parseInt(tf.getText()));
					jdlg.dispose();
				} catch (NumberFormatException e){
					String message = "���������֣�";
		        	JOptionPane.showMessageDialog(Fr, message, "����", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		jdlg.getContentPane().add(panel, BorderLayout.CENTER);
		jdlg.setVisible(true);
	}
	
	public void rotate(int degree) {
		//BufferedImage img = Fr.st.peek();
		BufferedImage img = Fr.st.peek();
		int ih = img.getHeight();
		int iw = img.getWidth();
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		degree = degree % 360;
		
		if (degree < 0) {
			degree = 360 + degree;//���Ƕ�ת����0-360��֮��
		}
		
		double ang = Math.toRadians(degree);//���Ƕ�תΪ����
		/**
		 *ȷ����ת���ͼ��ĸ߶ȺͿ��
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
		x = (w - iw) / 2;//ȷ��ԭ������
		y = (h - ih) / 2;
		BufferedImage image = new BufferedImage(w, h, img.getType());
		// ͸��������
		Graphics2D g2d = image.createGraphics();
		image = g2d.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
		g2d.dispose();
		
		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);//��תͼ��
		at.translate(x, y);//ƽ����ԭ��
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		op.filter(img, image);
		
		Fr.st.push(image);
		Fr.st_.clear();
		Fr.after = image;
		ImageIcon icon = new ImageIcon(image);
		Fr.Pic.setIcon(icon);
		Fr.Pic.repaint();
	}
	
	class keyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			// ����ESC�˳��Ի���
			if (e.getKeyChar() == KeyEvent.VK_ESCAPE)
				jdlg.dispose();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			// �������ֻ������8λ��
			if (tf.getText().length() < 8)
				// ����ֻ���������°��������֣����򣬻س���ESC��tab��ɾ�����˸�
				if ((e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9) 
				      || e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB
				      || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE 
				      || e.getKeyChar() == KeyEvent.VK_LEFT || e.getKeyChar() == KeyEvent.VK_RIGHT 
				      || e.getKeyChar() == KeyEvent.VK_ESCAPE)
				      return;
			e.consume();
		}
	}
}
