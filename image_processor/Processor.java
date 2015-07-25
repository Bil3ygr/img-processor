package image_processor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Processor implements ActionListener {
	Frame Fr;
	
	Rotate rotate;
    Scale scale;
    Gray gray;
    Quantize quantize;
    Plot_Hist plot_hist;
    Equalize_Hist equalize_hist;
    Filter2D filter2d;
    DFT2D dft2d;
    Filter2D_Freq filter2d_freq;
    Add_Gaussian_Noise add_gaussian_noise;
    Add_Impulse_Noise add_impulse_noise;
    Haze_Removal haze_removal;
    Guide_Filter guide_filter;

	public Processor(Frame frame) {
        Fr = frame;
        Fr.rotate.addActionListener(this);
        Fr.scale.addActionListener(this);
        Fr.gray.addActionListener(this);
        Fr.quantize.addActionListener(this);
        Fr.plot_hist_gray.addActionListener(this);
        Fr.equalize_hist_gray.addActionListener(this);
        Fr.smooth.addActionListener(this);
        Fr.sharpen.addActionListener(this);
        Fr.sobel3x3.addActionListener(this);
        Fr.sobel2x2.addActionListener(this);
        Fr.harmonic.addActionListener(this);
        Fr.contraharmonic.addActionListener(this);
        Fr.geometric.addActionListener(this);
        Fr.median.addActionListener(this);
        Fr.max.addActionListener(this);
        Fr.min.addActionListener(this);
        Fr.dft2d_dft.addActionListener(this);
        Fr.dft2d_idft.addActionListener(this);
        Fr.filter2d_freq_smooth.addActionListener(this);
        Fr.filter2d_freq_sharpen.addActionListener(this);
        Fr.add_gaussian_noise.addActionListener(this);
        Fr.add_impulse_noise.addActionListener(this);
        Fr.plot_hist_color.addActionListener(this);
        Fr.equalize_hist_color_1.addActionListener(this);
        Fr.equalize_hist_color_2.addActionListener(this);
        Fr.haze_removal.addActionListener(this);
        Fr.guide_filter.addActionListener(this);
    }

	public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == Fr.rotate) {
        	rotate = new Rotate(Fr);
        	if (Judge()) {
        		rotate.rotate_window();
        	}
        } else if (source == Fr.scale) {
        	scale = new Scale(Fr);
        	if (Judge()) {
        		scale.scale_window();
        	}
        } else if (source == Fr.gray) {
        	gray = new Gray(Fr);
        	if (Judge()) {
        		gray.gray();
        	}
        } else if (source == Fr.quantize) {
        	quantize = new Quantize(Fr);
        	if (Judge()) {
        		quantize.quantize_window();
        	}
        } else if (source == Fr.plot_hist_gray) {
        	plot_hist = new Plot_Hist(Fr);
        	if (Judge()) {
        		plot_hist.plot_hist(0);
        	}
        } else if (source == Fr.equalize_hist_gray) {
        	equalize_hist = new Equalize_Hist(Fr);
        	if (Judge()) {
        		equalize_hist.equalize_hist(0);
        	}
        } else if (source == Fr.smooth) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.smooth();
        	}
        } else if (source == Fr.sharpen) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.sharpen();
        	}
        } else if (source == Fr.sobel3x3) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.sobel3x3();
        	}
        } else if (source == Fr.sobel2x2) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.sobel2x2();
        	}
        } else if (source == Fr.harmonic) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.harmonic();
        	}
        } else if (source == Fr.contraharmonic) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.contraharmonic();
        	}
        } else if (source == Fr.geometric) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.geometric();
        	}
        } else if (source == Fr.median) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.median();
        	}
        } else if (source == Fr.max) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.max();
        	}
        } else if (source == Fr.min) {
        	filter2d = new Filter2D(Fr);
        	if (Judge()) {
        		filter2d.min();
        	}
        } else if (source == Fr.dft2d_dft) {
        	dft2d = new DFT2D(Fr);
        	if (Judge()) {
        		dft2d.dft2d(0);
        	}
        } else if (source == Fr.dft2d_idft) {
        	dft2d = new DFT2D(Fr);
        	if (Judge()) {
        		dft2d.dft2d(1);
        	}
        } else if (source == Fr.filter2d_freq_smooth) {
        	filter2d_freq = new Filter2D_Freq(Fr);
        	if (Judge()) {
        		filter2d_freq.filter2d_freq(0);
        	}
        } else if (source == Fr.filter2d_freq_sharpen) {
        	filter2d_freq = new Filter2D_Freq(Fr);
        	if (Judge()) {
        		filter2d_freq.filter2d_freq(1);
        	}
        } else if (source == Fr.add_gaussian_noise) {
        	add_gaussian_noise = new Add_Gaussian_Noise(Fr);
        	if (Judge()) {
        		add_gaussian_noise.add_gaussian_noise_window();
        	}
        } else if (source == Fr.add_impulse_noise) {
        	add_impulse_noise = new Add_Impulse_Noise(Fr);
        	if (Judge()) {
        		add_impulse_noise.add_impulse_noise_window();
        	}
        } else if (source == Fr.plot_hist_color) {
        	plot_hist = new Plot_Hist(Fr);
        	if (Judge()) {
        		plot_hist.plot_hist(1);
        	}
        } else if (source == Fr.equalize_hist_color_1) {
        	equalize_hist = new Equalize_Hist(Fr);
        	if (Judge()) {
        		equalize_hist.equalize_hist(1);
        	}
        } else if (source == Fr.equalize_hist_color_2) {
        	equalize_hist = new Equalize_Hist(Fr);
        	if (Judge()) {
        		equalize_hist.equalize_hist(2);
        	}
        } else if (source == Fr.haze_removal) {
        	haze_removal = new Haze_Removal(Fr);
        	if (Judge()) {
        		haze_removal.haze_removal_window();
        	}
        } else if (source == Fr.guide_filter) {
        	guide_filter = new Guide_Filter(Fr);
        	if (Judge()) {
        		guide_filter.guide_filter_window();
        	}
        }
	}
	// 判断是否有打开图片
	public boolean Judge() {
		if (Fr.before != null) {
			return true;
		} else {
			String message = "未打开任何图片！";
        	JOptionPane.showMessageDialog(Fr, message, "提醒", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
}
