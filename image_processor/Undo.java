package image_processor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Undo implements ActionListener {
	Frame Fr;
	String message;

	public Undo(Frame frame) {
		Fr = frame;
		Fr.step_before.addActionListener(this);
		Fr.step_after.addActionListener(this);
		Fr.original.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == Fr.step_before) {
			before();
		} else if (source == Fr.step_after) {
			after();
		} else if (source == Fr.original) {
			original();
		}
	}
	
	public void before() {
		if (Fr.st.isEmpty()) {
			message = "δ��ͼƬ��";
			JOptionPane.showMessageDialog(Fr, message, "����", JOptionPane.ERROR_MESSAGE);
		} else {
			if (Fr.st.size() == 1) {
				message = "û����һ����";
				JOptionPane.showMessageDialog(Fr, message, "����", JOptionPane.ERROR_MESSAGE);
			} else {
				Fr.st_.push(Fr.st.peek());
				Fr.st.pop();
				Fr.after = Fr.st.peek();
				ImageIcon icon = new ImageIcon(Fr.st.peek());
                Fr.Pic.setIcon(icon);
                Fr.Pic.repaint();
			}
		}
	}
	
	public void after() {
		if (Fr.st_.isEmpty()) {
			if (Fr.st.isEmpty()) {
				message = "δ��ͼƬ��";
			} else {
				message = "û����һ����";
			}
			JOptionPane.showMessageDialog(Fr, message, "����", JOptionPane.ERROR_MESSAGE);
		} else {
			Fr.st.push(Fr.st_.peek());
			Fr.st_.pop();
			Fr.after = Fr.st.peek();
			ImageIcon icon = new ImageIcon(Fr.st.peek());
            Fr.Pic.setIcon(icon);
            Fr.Pic.repaint();
		}
	}
	
	public void original() {
		if (Fr.st.isEmpty()) {
			message = "δ��ͼƬ��";
			JOptionPane.showMessageDialog(Fr, message, "����", JOptionPane.ERROR_MESSAGE);
		} else {
			message = "�˲��費���棬�Ƿ�ִ�У�";
			int n = JOptionPane.showConfirmDialog(Fr, message, "����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); // yes = 0, no = 1
			if (n == 0) {
				Fr.st.clear();
				Fr.st_.clear();
				Fr.st.push(Fr.before);
				ImageIcon icon = new ImageIcon(Fr.st.peek());
	            Fr.Pic.setIcon(icon);
	            Fr.Pic.repaint();
			}
		}
	}
}
