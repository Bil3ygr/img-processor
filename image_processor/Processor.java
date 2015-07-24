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
	/*
	//scale
	public void scale_window() {
		fr = new JFrame();
        panel = new JPanel();
        panel_ = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 100);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(3, 1));
        panel_.setLayout(new GridLayout(1, 2));
        label = new JLabel("Input size of new image");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel_.add(tf);
        panel_.add(tf_);
        panel.add(panel_);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		scale(Integer.parseInt(tf.getText()), Integer.parseInt(tf_.getText()));
        	}
        });
	}
	//scale
	public void scale(int w, int h) {
		BufferedImage img = Fr.before;
        int wid = img.getWidth();
        int hei = img.getHeight();
        int width = w;
        int height = h;
        int afterimg[] = new int[width * height];
        int beforeimg[] = new int[wid * hei];
        img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
        
        afterimg = imgScale(beforeimg, wid, hei, width, height);
        DataBuffer dataBuffer = new DataBufferInt(afterimg, width * height);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, width, height, width, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = scaleimage;
        ImageIcon icon = new ImageIcon(scaleimage);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
	}
	//scale
	public int[] imgScale(int[] inPixelsData, int wid, int hei, int width, int height) {
        int[][][] inputThreeDeminsionData = processOneToThreeDeminsion(inPixelsData, hei, wid);
        int[][][] outputThreeDeminsionData = new int[height][width][4];
        float rowRatio = ((float) hei) / ((float) height);
        float colRatio = ((float) wid) / ((float) width);
        for (int row = 0; row < height; row++) {
            int row_ = Math.round(((float) row) * rowRatio);
            if (row_ >= hei) {
                row_ = hei - 1;
            }
            for (int col = 0; col < width; col++) {
                int col_ = Math.round(((float) col) * colRatio);
                if (col_ >= wid) {
                    col_ = wid - 1;
                }
                outputThreeDeminsionData[row][col][0] = inputThreeDeminsionData[row_][col_][0];
                outputThreeDeminsionData[row][col][1] = inputThreeDeminsionData[row_][col_][1];
                outputThreeDeminsionData[row][col][2] = inputThreeDeminsionData[row_][col_][2];
                outputThreeDeminsionData[row][col][3] = inputThreeDeminsionData[row_][col_][3];
            }
        }
        return convertToOneDim(outputThreeDeminsionData, width, height);
    }
	//scale
	public int[][][] processOneToThreeDeminsion(int[] oneDPix2, int imgRows, int imgCols) {
        int[][][] tempData = new int[imgRows][imgCols][4];
        for (int row = 0; row < imgRows; row++) {
            int[] aRow = new int[imgCols];
            for (int col = 0; col < imgCols; col++) {
                int element = row * imgCols + col;
                aRow[col] = oneDPix2[element];
            }
            for (int col = 0; col < imgCols; col++) {
                tempData[row][col][0] = (aRow[col] >> 24) & 0xff;
                tempData[row][col][1] = (aRow[col] >> 16) & 0xff;
                tempData[row][col][2] = (aRow[col] >> 8) & 0xff;
                tempData[row][col][3] = (aRow[col]) & 0xff;
            }
        }
        return tempData;
    }
	//scale
	public int[] convertToOneDim(int[][][] data, int imgCols, int imgRows) {
        int[] oneDPix = new int[imgCols * imgRows * 4];
        for (int row = 0, cnt = 0; row < imgRows; row++) {
            for (int col = 0; col < imgCols; col++) {
                oneDPix[cnt] = ((data[row][col][0] << 24) & 0xff000000) | ((data[row][col][1] << 16) & 0x00ff0000) | ((data[row][col][2] << 8) & 0x0000ff00) | ((data[row][col][3]) & 0x000000ff);
                cnt++;
            }

        }
        return oneDPix;
    }
	*/
	/*
	//quantize
	public void quantize_window() {
		fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 100);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("Input new gray level of image");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		quantize(Integer.parseInt(tf.getText()));
        	}
        });
	}
	//quantize
	public void quantize(int level) {
		BufferedImage img = Fr.before;
        int wid = img.getWidth();
        int hei = img.getHeight();
        int beforeimg[] = new int[wid * hei];
        int afterimg[] = new int[wid * hei];
        img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
        
        afterimg = imgScale(beforeimg, wid, hei, level);
        DataBuffer dataBuffer = new DataBufferInt(afterimg, wid * hei);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = scaleimage;
        ImageIcon icon = new ImageIcon(scaleimage);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
	}
	//quantize
	public int[] imgScale(int[] inPixelsData, int wid, int hei, int level) {
        int[][] inputdata = process(inPixelsData, hei, wid);
        int[][] outputdata = new int[wid * hei][4];
        int level_ = level - 1;
        float[] dev = new float[4];
        float[] tmp = new float[4];
        for (int i = 0; i < wid * hei; i++) {
            for (int j = 0; j < 4; j++) {
                dev[j] = 255;
            }
            for (int j = 0; j < level; j++) {
                for (int k = 0; k < 4; k++) {
                    if (255 / level_ * j > inputdata[i][k])
                        tmp[k] = 255 / level_ * j - inputdata[i][k];
                    else 
                        tmp[k] = inputdata[i][k] - 255 / level_ * j;
                    if (k == 0) {
                        outputdata[i][k] = 255;
                    } else {
                        if (tmp[k] < dev[k]) {
                            dev[k] = tmp[k];
                            outputdata[i][k] = Math.round(255 / level_ * j);
                        }
                    }
                }
            }
        }
        return back(outputdata, hei, wid);
    }
	//quantize
    public int[][] process(int[] pixels, int rows, int cols) {
        int[][] tempdata = new int[rows * cols][4];
        for (int i = 0; i < rows * cols; i++) {
            tempdata[i][0] = (pixels[i] >> 24) & 0xff;
            tempdata[i][1] = (pixels[i] >> 16) & 0xff;
            tempdata[i][2] = (pixels[i] >> 8) & 0xff;
            tempdata[i][3] = (pixels[i]) & 0xff;
        }
        return tempdata;
    }
    //quantize
    public int[] back(int[][] pixels, int rows, int cols) {
        int[] tmp = new int[rows * cols];
        for (int i = 0; i < rows * cols; i++) {
            tmp[i] = ((pixels[i][0] << 24) & 0xff000000) | ((pixels[i][1] << 16) & 0x00ff0000) | ((pixels[i][2] << 8) & 0x0000ff00) | ((pixels[i][3]) & 0x000000ff);
        }
        return tmp;
    }
    */
    /*
	//plot_hist
    public void plot_hist(int flag) {
    	JFrame f = new JFrame();
    	JFrame f_ = new JFrame();
    	JFrame f__ = new JFrame();
    	JLabel pic = new JLabel();
        JLabel pic_ = new JLabel();
        JLabel pic__ = new JLabel();
        
        BufferedImage img = Fr.before;
        
        int wid = img.getWidth();
        int hei = img.getHeight();
        int[] countimg = new int[256];
        int[] rData = new int[256];
        int[] gData = new int[256];
        int[] bData = new int[256];
        
        if (flag == 0) {
        	f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Histogram_Gray");
            f.setVisible(true);
            f.setSize(400, 400);
            
            for (int i = 0; i < 256; i++) {
        		countimg[i] = 0;
        	}
        	//save the number of gray level
        	for (int i = 0; i < wid; i++) {
        		for (int j = 0; j < hei; j++) {
        			int gray = getGray(img.getRGB(i, j));
        			countimg[gray]++;
        		}
        	}
        	String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	//create the histogram image
        	BufferedImage scaleimage = paintHistogram(countimg, str, new Color[] {Color.BLACK});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        } else if (flag == 1) {
        	//show red
            f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Histogram_Red");
            f.setVisible(true);
            f.setSize(400, 400);
            //show green
            f_.getContentPane().add(pic_, BorderLayout.CENTER);
            f_.setTitle("Histogram_Green");
            f_.setVisible(true);
            f_.setSize(400, 400);
            //show blue
            f__.getContentPane().add(pic__, BorderLayout.CENTER);
            f__.setTitle("Histogram_Blue");
            f__.setVisible(true);
            f__.setSize(400, 400);
            
            for (int i = 0; i < 256; i++) {
        		rData[i] = 0;
        		gData[i] = 0;
        		bData[i] = 0;
        	}
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = getRed(img.getRGB(j, i));
        			rData[red]++;
        			int green = getGreen(img.getRGB(j, i));
        			gData[green]++;
        			int blue = getBlue(img.getRGB(j, i));
        			bData[blue]++;
        		}
        	}
        	String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	//create the histogram image
        	BufferedImage scaleimage = paintHistogram(rData, str, new Color[] {Color.RED});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        	scaleimage = paintHistogram(gData, str, new Color[] {Color.GREEN});
        	icon = new ImageIcon(scaleimage);
        	pic_.setIcon(icon);
        	pic_.repaint();
        	scaleimage = paintHistogram(bData, str, new Color[] {Color.BLUE});
        	icon = new ImageIcon(scaleimage);
        	pic__.setIcon(icon);
        	pic__.repaint();
        }
    }
    
    //plot_hist
    public int getGray(int rgb) {
        int gray = (int)(((rgb & 0x00ff0000) >> 16) * 0.299 + ((rgb & 0x0000ff00) >> 8) * 0.587 + (rgb & 0x000000ff) * 0.114);
        return gray;
    }
    public int getRed(int rgb) {
    	int red = (rgb & 0x00ff0000) >> 16;
    	return red;
    }
    public int getGreen(int rgb) {
    	int green = (rgb & 0x0000ff00) >> 8;
    	return green;
    }
    public int getBlue(int rgb) {
    	int blue = (rgb & 0x000000ff);
    	return blue;
    }
    
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
    */
    /*
	//equalize_hist
    public void equalize_hist(int flag) {
    	JFrame f = new JFrame();
    	JFrame f_ = new JFrame();
    	JFrame f__ = new JFrame();
    	JLabel pic = new JLabel();
        JLabel pic_ = new JLabel();
        JLabel pic__ = new JLabel();
        
        BufferedImage img = Fr.before;
        
        int wid = img.getWidth();
        int hei = img.getHeight();
        int[] beforeimg = new int[wid * hei];
        img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
        int[] afterimg = new int[wid * hei];
        int[] countimg = new int[256];
        int[] countimg_ = new int[256];
        int[] rData = new int[256];
        int[] gData = new int[256];
        int[] bData = new int[256];
        int[] rData_ = new int[256];
        int[] gData_ = new int[256];
        int[] bData_ = new int[256];
        int[] intensity = new int[256];
        int[] intensity_ = new int[256];

        if (flag == 0) {
        	//show gray
            f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Equalize_Gray");
            f.setVisible(true);
            f.setSize(400, 400);
            
            for (int i = 0; i < 256; i++) {
                countimg[i] = 0;
            }
            for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int gray = getGray(img.getRGB(j, i));
        			countimg[gray]++;
        		}
        	}
        	//count the gray level after equalize
        	for (int i = 0; i < 256; i++) {
        		if (i == 0) {
        			countimg_[i] = Math.round(255 * countimg[i] / (wid * hei));
        		} else {
        			countimg_[i] = Math.round(countimg_[i-1] + 255 * countimg[i] / (wid * hei));
        		}
        		if (countimg_[i] > 255) {
        			countimg_[i] = 255;
        		}
        	}
        	//create new image's array
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int gray = getGray(beforeimg[i * wid + j]);
        			afterimg[i * wid + j] = (beforeimg[i * wid + j] & 0xff000000) + (countimg_[gray] << 16) + (countimg_[gray] << 8) + countimg_[gray];
        		}
        	}
        	
        	String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	//create new histogram image
        	for (int i = 0; i < 256; i++) {
        		countimg[i] = 0;
        	}
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int gray = getGray(afterimg[i * wid + j]);
        			countimg[gray]++;
        		}
        	}
        	BufferedImage scaleimage = paintHistogram(countimg, str, new Color[] {Color.BLACK});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        } else if (flag == 1) {
        	//show red
            f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Equalize_Red");
            f.setVisible(true);
            f.setSize(400, 400);
            //show green
            f_.getContentPane().add(pic_, BorderLayout.CENTER);
            f_.setTitle("Equalize_Green");
            f_.setVisible(true);
            f_.setSize(400, 400);
            //show blue
            f__.getContentPane().add(pic__, BorderLayout.CENTER);
            f__.setTitle("Equalize_Blue");
            f__.setVisible(true);
            f__.setSize(400, 400);
           
            for (int i = 0; i < 256; i++) {
                rData[i] = 0;
                gData[i] = 0;
                bData[i] = 0;
            }
            for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = getRed(img.getRGB(j, i));
        			rData[red]++;
        			int green = getGreen(img.getRGB(j, i));
        			gData[green]++;
        			int blue = getBlue(img.getRGB(j, i));
        			bData[blue]++;
        		}
        	}
            //equalize
            for (int i = 0; i < 256; i++) {
        		if (i == 0) {
        			rData_[i] = Math.round(255 * rData[i] / (wid * hei));
        			gData_[i] = Math.round(255 * gData[i] / (wid * hei));
        			bData_[i] = Math.round(255 * bData[i] / (wid * hei));
        		} else {
        			rData_[i] = Math.round(rData_[i - 1] + 255 * rData[i] / (wid * hei));
        			gData_[i] = Math.round(gData_[i - 1] + 255 * gData[i] / (wid * hei));
        			bData_[i] = Math.round(bData_[i - 1] + 255 * bData[i] / (wid * hei));
        		}
        		if (rData_[i] > 255) {
        			rData_[i] = 255;
        		}
        		if (gData_[i] > 255) {
        			gData_[i] = 255;
        		}
        		if (bData_[i] > 255) {
        			bData_[i] = 255;
        		}
        	}
            //create new image's array
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = getRed(beforeimg[i * wid + j]);
        			int green = getGreen(beforeimg[i * wid + j]);
        			int blue = getBlue(beforeimg[i * wid + j]);
        			afterimg[i * wid + j] = (beforeimg[i * wid + j] & 0xff000000) + (rData_[red] << 16) + (gData_[green] << 8) + bData_[blue];
        		}
        	}
            //draw picture
            String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	//create new histogram image
        	for (int i = 0; i < 256; i++) {
        		rData[i] = 0;
        		gData[i] = 0;
        		bData[i] = 0;
        	}
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = getRed(afterimg[i * wid + j]);
        			rData[red]++;
        			int green = getGreen(afterimg[i * wid + j]);
        			gData[green]++;
        			int blue = getBlue(afterimg[i * wid + j]);
        			bData[blue]++;
        		}
        	}
        	BufferedImage scaleimage = paintHistogram(rData, str, new Color[] {Color.RED});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        	scaleimage = paintHistogram(gData, str, new Color[] {Color.GREEN});
        	icon = new ImageIcon(scaleimage);
        	pic_.setIcon(icon);
        	pic_.repaint();
        	scaleimage = paintHistogram(bData, str, new Color[] {Color.BLUE});
        	icon = new ImageIcon(scaleimage);
        	pic__.setIcon(icon);
        	pic__.repaint();
        } else if (flag == 2) {
        	//show red
            f.getContentPane().add(pic, BorderLayout.CENTER);
            f.setTitle("Equalize_Red");
            f.setVisible(true);
            f.setSize(400, 400);
            //show green
            f_.getContentPane().add(pic_, BorderLayout.CENTER);
            f_.setTitle("Equalize_Green");
            f_.setVisible(true);
            f_.setSize(400, 400);
            //show blue
            f__.getContentPane().add(pic__, BorderLayout.CENTER);
            f__.setTitle("Equalize_Blue");
            f__.setVisible(true);
            f__.setSize(400, 400);
           
            for (int i = 0; i < 256; i++) {
                rData[i] = 0;
                gData[i] = 0;
                bData[i] = 0;
                intensity[i] = 0;
                intensity_[i] = 0;
            }
            for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = getRed(img.getRGB(j, i));
        			rData[red]++;
        			int green = getGreen(img.getRGB(j, i));
        			gData[green]++;
        			int blue = getBlue(img.getRGB(j, i));
        			bData[blue]++;
        		}
        	}
            for (int i = 0; i <256; ++i) {
            	intensity[i] = (int) Math.round((1.0 * (rData[i] + gData[i] + bData[i]) / 3));
            }
            //equalize
            for (int i = 0; i < 256; i++) {
        		if (i == 0) {
        			intensity_[i] = Math.round(255 * intensity[i] / (wid * hei));
        		} else {
        			intensity_[i] = Math.round(intensity_[i - 1] + 255 * intensity[i] / (wid * hei));
        		}
        		if (intensity_[i] > 255) {
        			intensity_[i] = 255;
        		}
        	}
            //create new image's array
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = getRed(beforeimg[i * wid + j]);
        			int green = getGreen(beforeimg[i * wid + j]);
        			int blue = getBlue(beforeimg[i * wid + j]);
        			afterimg[i * wid + j] = (beforeimg[i * wid + j] & 0xff000000) + (intensity_[red] << 16) + (intensity_[green] << 8) + intensity_[blue];
        		}
        	}
            //draw picture
            String[] str = new String[256];
        	for (int i = 0; i < 256; i++) {
        		str[i] = String.valueOf(i);
        	}
        	//create new histogram image
        	for (int i = 0; i < 256; i++) {
        		rData[i] = 0;
        		gData[i] = 0;
        		bData[i] = 0;
        	}
        	for (int i = 0; i < hei; i++) {
        		for (int j = 0; j < wid; j++) {
        			int red = getRed(afterimg[i * wid + j]);
        			rData[red]++;
        			int green = getGreen(afterimg[i * wid + j]);
        			gData[green]++;
        			int blue = getBlue(afterimg[i * wid + j]);
        			bData[blue]++;
        		}
        	}
        	BufferedImage scaleimage = paintHistogram(rData, str, new Color[] {Color.RED});
        	ImageIcon icon = new ImageIcon(scaleimage);
        	pic.setIcon(icon);
        	pic.repaint();
        	scaleimage = paintHistogram(gData, str, new Color[] {Color.GREEN});
        	icon = new ImageIcon(scaleimage);
        	pic_.setIcon(icon);
        	pic_.repaint();
        	scaleimage = paintHistogram(bData, str, new Color[] {Color.BLUE});
        	icon = new ImageIcon(scaleimage);
        	pic__.setIcon(icon);
        	pic__.repaint();
        }
        //draw the image after equalize
        DataBuffer dataBuffer = new DataBufferInt(afterimg, wid * hei);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage scaleimage_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = scaleimage_;
        ImageIcon icon_ = new ImageIcon(scaleimage_);
        Fr.Pic.setIcon(icon_);
        Fr.Pic.repaint();
    }
    */
    /*
	//view_as_window
    public void view_as_window_() {
    	fr = new JFrame();
        panel = new JPanel();
        panel_ = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 100);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(3, 1));
        panel_.setLayout(new GridLayout(1, 2));
        label = new JLabel("Input size of new image");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel_.add(tf);
        panel_.add(tf_);
        panel.add(panel_);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		view_as_window(Integer.parseInt(tf.getText()), Integer.parseInt(tf_.getText()));
        	}
        });
    }
    //view_as_window
    public void view_as_window(int w, int h) {
    	BufferedImage img = Fr.after;
        BufferedImage img_;
        int wid = img.getWidth();
        int hei = img.getHeight();
        //get size of patches
        int width = w;
        int height = h;
        int count = 1;
        for (int i = 0; i <= hei - height; i++) {
            for (int j = 0; j <= wid - width; j++) {
                if (count % 100 == 0) {
                    //create new windows to show the patches
                    JFrame jf = new JFrame();
                    JLabel pic = new JLabel();
                
                    img_ = img.getSubimage(j, i, width, height);
                    
                    jf.getContentPane().add(pic, BorderLayout.CENTER);
                    jf.setVisible(true);
                    jf.setSize(100, 100);
                    ImageIcon icon = new ImageIcon(img_);
                    pic.setIcon(icon);
                    pic.repaint();
                }
                count++;
                if (count > 800)
                    break;
            }
            if (count > 800)
                break;
        }
    }
    */
    /*
	//filter2d
    public void filter2d_window() {
    	fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        JLabel label_ = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        JButton bt_Sharpen = new JButton();
        JButton bt_Sobel1 = new JButton();
        JButton bt_Sobel2 = new JButton();
        JButton bt_Harmonic = new JButton();
        JButton bt_ContraHarmonic = new JButton();
        JButton bt_Geometric = new JButton();
        JButton bt_Median = new JButton();
        JButton bt_Max = new JButton();
        JButton bt_Min = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 300);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(14, 1));
        label = new JLabel("Input size of filter");
        label.setHorizontalAlignment(JLabel.CENTER);
        label_ = new JLabel("Input value of Q");
        label_.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("Smooth");
        bt_Sharpen.setText("Sharpen");
        bt_Sobel1.setText("Sobel1");
        bt_Sobel2.setText("Sobel2");
        bt_Harmonic.setText("Harmonic");
        bt_ContraHarmonic.setText("ContraHarmonic");
        bt_Geometric.setText("Geometric");
        bt_Median.setText("Median");
        bt_Max.setText("Max");
        bt_Min.setText("Min");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        panel.add(bt_Sharpen);
        panel.add(bt_Sobel1);
        panel.add(bt_Sobel2);
        panel.add(bt_Harmonic);
        panel.add(label_);
        panel.add(tf_);
        panel.add(bt_ContraHarmonic);
        panel.add(bt_Geometric);
        panel.add(bt_Median);
        panel.add(bt_Max);
        panel.add(bt_Min);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 1);
        	}
        });
        bt_Sharpen.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 2);
        	}
        });
        bt_Sobel1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 3);
        	}
        });
        bt_Sobel2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 4);
        	}
        });
        bt_Harmonic.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 5);
        	}
        });
        bt_ContraHarmonic.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		Q = Double.parseDouble(tf_.getText());
        		filter2d(Integer.parseInt(tf.getText()), 6);
        	}
        });
        bt_Geometric.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 7);
        	}
        });
        bt_Median.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 8);
        	}
        });
        bt_Max.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 9);
        	}
        });
        bt_Min.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		filter2d(Integer.parseInt(tf.getText()), 10);
        	}
        });
    }
    //filter2d
    public void filter2d(int size, int flag) {
    	if (flag == 1) {	// smooth the image
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Smooth");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            
            f.setSize(wid + 20, hei + 50);
            //set size
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            //get RGB of original image
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            //smooth the image, save data into newimg
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int sum = 0;
                    int count = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                                sum += gray[x * wid + y];
                                count++;
                            }
                        }
                    }
                    int q = Math.round(sum / count);
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 2) {	// sharpen the image
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Sharpen");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int[] gray = new int[wid * hei];
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            //save the data into newimg
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int sum = 0;
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                                //center * 8, other * -1
                                if (j == 0 && i == 0) {
                                    sum = sum + 8 * gray[x * wid + y];
                                } else {
                                    sum -= gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum += gray[v * wid + u];
                    if (sum > 255) {
                        sum = 255;
                    } else if (sum < 0) {
                        sum = 0;
                    }
                    int q = sum;
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 3) {	// filter the iamge with 3x3 sobel filter
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Sobel3x3");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int[] gray = new int[wid * hei];
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int sum = 0;
                    //new gray level = |(z7 + 2 * z8 + z9) - (z1 + 2 * z2 + z3)| + |(z3 + 2 * z6 + z9) - (z1 + 2 * z4 + z7)|
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                                sum += 0;
                            } else {
                                int q = 1;
                                if (i == 0)
                                    q = 2;
                                if (j < 0) {
                                    sum -= q * gray[x * wid + y];
                                } else if (j > 0) {
                                    sum += q * gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum = Math.abs(sum);
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                                sum += 0;
                            } else {
                                int q = 1;
                                if (j == 0)
                                    q = 2;
                                if (i < 0) {
                                    sum -= q * gray[x * wid + y];
                                } else if (i > 0) {
                                    sum += q * gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum = Math.abs(sum);
                    int q = sum;
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 4) {	// filter the iamge with 2x2 sobel filter
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Sobel2x2");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int[] gray = new int[wid * hei];
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int sum = 0;
                    //new gray level = |z5 - z9| + |z6 - z8|
                    for (int j = 0; j <= 1; j++) {
                        for (int i = 0; i <= 1; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x > hei - 1 | y > wid - 1) {
                                sum += 0;
                            } else {
                                if (j == 0 && i == 0) {
                                    sum += gray[x * wid + y];
                                } else if (j == 1 && i == 1) {
                                    sum -= gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum = Math.abs(sum);
                    for (int j = 0; j <= 1; j++) {
                        for (int i = 0; i <= 1; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x > hei - 1 | y > wid - 1) {
                                sum += 0;
                            } else {
                                if (j == 0 && i == 1) {
                                    sum += gray[x * wid + y];
                                } else if (j == 1 && i == 0) {
                                    sum -= gray[x * wid + y];
                                }
                            }
                        }
                    }
                    sum = Math.abs(sum);
                    int q = sum;
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 5) {	// harmonic
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Harmonic");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            //set size
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = (level - 1) / 2;
            int[] gray = new int[wid * hei];
            //get RGB of original image
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    double sum = 0;
                    int count = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 || x > hei - 1 || y < 0 || y > wid - 1) {
                            } else {
                            	if (gray[x * wid + y] == 0) {
                            		sum += 1.0 / 1e-6;
                            	} else {
                            		sum += 1.0 / gray[x * wid + y];
                            	}
                            	count++;
                            }
                        }
                    }
                    int q = (int) (count / sum);
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 6) {	// contrahamonic
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Contra-Harmonic");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            //set size
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            //get RGB of original image
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    double sum1 = 0;
                    double sum2 = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 || x > hei - 1 || y < 0 || y > wid - 1) {
                            } else {
                            	sum1 += Math.pow(gray[x * wid + y], Q + 1);
                            	sum2 += Math.pow(gray[x * wid + y], Q);
                            }
                        }
                    }
                    int q = (int) (sum1 / sum2);
                    if (q > 255) {
                    	q = 255;
                    }
                    if (q < 0) {
                    	q = 0;
                    }
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 7) {	// geometric
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Geometric");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            //set size
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            //get RGB of original image
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            //smooth the image, save data into newimg
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    double sum = 1;
                    double mi = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                                sum *= gray[x * wid + y];
                                mi++;
                            }
                        }
                    }
                    int q = (int) Math.round(Math.pow(sum, 1.0 / mi));
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 8) {	// median
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Median");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            //set size
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            //get RGB of original image
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            //smooth the image, save data into newimg
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int[] sum = new int[level * level];
                    int k = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                                sum[k] = gray[x * wid + y];
                                ++k;
                            }
                        }
                    }
                    int count = 0;
                    while (count == 0) {
                    	for (int i = 0; i < k - 1; ++i) {
                    		if (sum[i] > sum[i + 1]) {
                    			int tmp = sum[i];
                    			sum[i] = sum[i + 1];
                    			sum[i + 1] = tmp;
                    			count++;
                    		}
                    	}
                    	if (count != 0) {
                    		count = 0;
                    	} else {
                    		break;
                    	}
                    }
                    int q = Math.round(sum[(k + 2) / 2]);
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 9) {	// max
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Max");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            //set size
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            //get RGB of original image
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            //smooth the image, save data into newimg
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                    int q = 0;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                            	if (gray[x * wid + y] > q) {
                            		q = gray[x * wid + y];
                            	}
                            }
                        }
                    }
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	} else if (flag == 10) {	// min
    		JFrame f = new JFrame();
            JLabel pic = new JLabel();
            
            f.getContentPane().add(pic, BorderLayout.CENTER);
            
            f.setTitle("Min");
            f.setVisible(true);
            
            BufferedImage img = Fr.before;
            int wid = img.getWidth();
            int hei = img.getHeight();
            f.setSize(wid + 20, hei + 50);
            //set size
            int level = size;
            int[] beforeimg = new int[wid * hei];
            int[] newimg = new int[wid * hei];
            img.getRGB(0, 0, wid, hei, beforeimg, 0, wid);
            int level_ = level / 2;
            int[] gray = new int[wid * hei];
            //get RGB of original image
            for (int i = 0; i < wid * hei; i++) {
                gray[i] = (int)(((beforeimg[i] & 0x00ff0000) >> 16) * 0.299 + ((beforeimg[i] & 0x0000ff00) >> 8) * 0.587 + (beforeimg[i] & 0x000000ff) * 0.114);
            }
            //smooth the image, save data into newimg
            for (int v = 0; v <= hei - 1; v++) {
                for (int u = 0; u <= wid - 1; u++) {
                	int q = 255;
                    for (int j = -level_; j <= level_; j++) {
                        for (int i = -level_; i <= level_; i++) {
                            int x = v + j;
                            int y = u + i;
                            if (x < 0 | x > hei - 1 | y < 0 | y > wid - 1) {
                            } else {
                            	if (gray[x * wid + y] < q) {
                            		q = gray[x * wid + y];
                            	}
                            }
                        }
                    }
                    newimg[v * wid + u] =  (255 << 24) | (q << 16) | (q <<8 )| q;
                }
            }
            DataBuffer dataBuffer = new DataBufferInt(newimg, wid * hei);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, wid, hei, wid, new int[]{0xff0000, 0xff00, 0xff}, null);
            DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
            BufferedImage scaleimage = new BufferedImage(directColorModel, raster, true, null);
            ImageIcon icon = new ImageIcon(scaleimage);
            pic.setIcon(icon);
            pic.repaint();
    	}
    }
    */
    /*
	//dft2d
    public void dft2d_window() {
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
        label = new JLabel("Input number, 0 for DFT, 1 for IDFT");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		dft2d(Integer.parseInt(tf.getText()));
        	}
        });
    }
    //dft2d
    public void dft2d(int flag) {
    	BufferedImage input = Fr.before;
    	//获取宽高
        int M = input.getWidth();
        int N = input.getHeight();
        //original存RGB值
        int[] original = new int[M * N];
        int[][] f = new int[N][M];
        double[][] F = new double[N][M];
        //变换后
        int[] after = new int[M * N];
        int[] after_ = new int[M * N];
        //DFT
        Complex[][] Fxv = new Complex[N][M];
        Complex[][] Fuv = new Complex[N][M];
        //IDFT
        Complex[][] fuy = new Complex[N][M];
        Complex[][] fxy = new Complex[N][M];
        //R存实部，I存虚部
        double[][] R = new double[N][M];
        double[][] I = new double[N][M];
        //获得RGB值，放入original
        input.getRGB(0, 0, M, N, original, 0, M);
        //把RGB值变成灰度值并翻转
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                f[i][j] = (int) ((original[i * M + j] & 0xff) * Math.pow(-1, i + j));
            }
        }
        //DFT
        for (int x = 0; x < M; x++) {
            for (int v = 0; v < N; v++) {
                R[v][x] = 0;
                I[v][x] = 0;
                for (int y = 0; y < N; y++) {
                    double w = 2 * Math.PI * v * y / N;
                    R[v][x] += f[y][x] * Math.cos(w);
                    I[v][x] += f[y][x] * -Math.sin(w);
                }
                Fxv[v][x] = new Complex(R[v][x], I[v][x]);
            }
        }
        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                R[v][u] = 0;
                I[v][u] = 0;
                for (int x = 0; x < M; x++) {
                    double w = 2 * Math.PI * u * x / M;
                    R[v][u] += Fxv[v][x].re() * Math.cos(w) - Fxv[v][x].im() * -Math.sin(w);
                    I[v][u] += Fxv[v][x].re() * -Math.sin(w) + Fxv[v][x].im() * Math.cos(w);
                }
                R[v][u] = R[v][u] / M / N;
                I[v][u] = I[v][u] / M / N; 
                Fuv[v][u] = new Complex(R[v][u], I[v][u]);
            }
        }
        //IDFT
        if (flag == 1) {
            for (int u = 0; u < M; u++) {
                for (int y = 0; y < N; y++) {
                    R[y][u] = 0;
                    I[y][u] = 0;
                    for (int v = 0; v < N; v++) {
                        double w = 2 * Math.PI * v * y / N;
                        R[y][u] += Fuv[v][u].re() * Math.cos(w) - Fuv[v][u].im() * Math.sin(w);
                        I[y][u] += Fuv[v][u].re() * Math.sin(w) + Fuv[v][u].im() * Math.cos(w);
                    }
                    fuy[y][u] = new Complex(R[y][u], I[y][u]);
                }
            }
            for (int x = 0; x < M; x++) {
                for (int y = 0; y < N; y++) {
                    R[y][x] = 0;
                    I[y][x] = 0;
                    for (int u = 0; u < M; u++) {
                        double w = 2 * Math.PI * u * x / M;
                        R[y][x] += fuy[y][u].re() * Math.cos(w) - fuy[y][u].im() * Math.sin(w);
                        I[y][x] += fuy[y][u].re() * Math.sin(w) + fuy[y][u].im() * Math.cos(w);
                    }
                    fxy[y][x] = new Complex(R[y][x], I[y][x]);
                }
            }
        }
        if (flag == 0) {
            //取模并用log计算
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    F[i][j] = Math.log(Fuv[i][j].abs() + 1);
                }
            }
            //求Min和Max
            double Max = 0;
            double Min = 1000000;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (F[i][j] > Max) {
                        Max = F[i][j];
                    }
                    if (F[i][j] < Min) {
                        Min = F[i][j];
                    }
                }
            }
            //把区间从Min~Max转换到0~255
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    after[i * M + j] = (int) ((F[i][j] - Min) * 255 / (Max - Min));
                }
            }
        } else if (flag == 1){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    after[i * M + j] = (int) (Math.round(fxy[i][j].re()) * Math.pow(-1, i + j));
                    if (after[i * M + j] < 0) {
                    	after[i * M + j] = 0;
                    }
                }
            }
        }
        //把灰度值变成RGB值
        for (int i = 0; i < M * N; i++) {
            after_[i] = 0xff << 24 | after[i] << 16 | after[i] << 8 | after[i];
        }
        //绘制新图像
        DataBuffer dataBuffer = new DataBufferInt(after_, M * N);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, M, N, M, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
    */
    /*
	//filter2d_freq
    public void filter2d_freq_window() {
    	fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 260) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(260, 100);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("Input number, 0 for Smooth, 1 for Sharpen");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                filter2d_freq(Integer.parseInt(tf.getText()));
            }
        });
    }
    //filter2d_freq
    public void filter2d_freq(int flag) {
    	BufferedImage input = Fr.before;
    	//图片宽高
    	int M = input.getWidth();
    	int N = input.getHeight();
    	int P = 2 * M;
    	int Q = 2 * N;
    	
    	int[] original = new int[M * N];
    	int[] after = new int[P * Q];
    	int[] after_ = new int[P * Q];
    	double[][] R = new double[Q][P];
    	double[][] I = new double[Q][P];
    	//fp,F(u,v)
    	int[][] fp = new int[Q][P];
    	Complex[][] Fxv = new Complex[Q][P];
    	Complex[][] Fuv = new Complex[Q][P];
    	//h,H(u,v)
    	double[][] h = new double[Q][P];
    	Complex[][] H = new Complex[Q][P];
    	Complex[][] Hxv = new Complex[Q][P];
    	//G(u,v),g
    	Complex[][] G = new Complex[Q][P];
    	Complex[][] guy = new Complex[Q][P];
    	Complex[][] g = new Complex[Q][P];
    	double[][] gp = new double[Q][P];

    	input.getRGB(0, 0, M, N, original, 0, M);
    	//fp(x,y)
    	for (int i = 0; i < Q; ++i) {
            for (int j = 0; j < P; ++j) {
            	if (i < N && j < M) {
            		fp[i][j] = (int) ((original[i * M + j] & 0xff) * Math.pow(-1, i + j));
            	} else {
            		fp[i][j] = 0;
            	}
            }
        }
    	//F(u,v)
        for (int x = 0; x < P; x++) {
            for (int v = 0; v < Q; v++) {
                R[v][x] = 0;
                I[v][x] = 0;
                for (int y = 0; y < Q; y++) {
                    double w = 2 * Math.PI * v * y / Q;
                    R[v][x] += fp[y][x] * Math.cos(w);
                    I[v][x] += fp[y][x] * -Math.sin(w);
                }
                Fxv[v][x] = new Complex(R[v][x], I[v][x]);
            }
        }
        for (int u = 0; u < P; u++) {
            for (int v = 0; v < Q; v++) {
                R[v][u] = 0;
                I[v][u] = 0;
                for (int x = 0; x < P; x++) {
                    double w = 2 * Math.PI * u * x / P;
                    R[v][u] += Fxv[v][x].re() * Math.cos(w) - Fxv[v][x].im() * -Math.sin(w);
                    I[v][u] += Fxv[v][x].re() * -Math.sin(w) + Fxv[v][x].im() * Math.cos(w);
                }
                Fuv[v][u] = new Complex(R[v][u], I[v][u]);
            }
        }
        //h(u,v)
        if (flag == 0) {
        	for (int i = 0; i < Q; i++) {
        		for (int j =0 ; j < P; j++) {
        			if (i < 11 && j < 11) {
        				h[i][j] = (1.0 / 121) * Math.pow(-1, i + j);
        			} else {
        				h[i][j] = 0;
        			}
        		}
        	}
        } else if (flag == 1) {
        	for (int i = 0; i < Q; i++) {
        		for (int j =0 ; j < P; j++) {
        			if (i < 3 && j < 3) {
        				h[i][j] = -Math.pow(-1, i + j);
        				if (i == 1 && j == 1) {
        					h[i][j] = 8 * Math.pow(-1, i + j);
        				}
        			} else {
        				h[i][j] = 0;
        			}
        		}
        	}
        }
    	//H(u,v)
    	for (int x = 0; x < P; x++) {
            for (int v = 0; v < Q; v++) {
                R[v][x] = 0;
                I[v][x] = 0;
                for (int y = 0; y < Q; y++) {
                    double w = 2 * Math.PI * v * y / Q;
                    R[v][x] += h[y][x] * Math.cos(w);
                    I[v][x] += h[y][x] * -Math.sin(w);
                }
                Hxv[v][x] = new Complex(R[v][x], I[v][x]);
            }
        }
        for (int u = 0; u < P; u++) {
            for (int v = 0; v < Q; v++) {
                R[v][u] = 0;
                I[v][u] = 0;
                for (int x = 0; x < P; x++) {
                    double w = 2 * Math.PI * u * x / P;
                    R[v][u] += Hxv[v][x].re() * Math.cos(w) - Hxv[v][x].im() * -Math.sin(w);
                    I[v][u] += Hxv[v][x].re() * -Math.sin(w) + Hxv[v][x].im() * Math.cos(w);
                }
                H[v][u] = new Complex(R[v][u], I[v][u]);
            }
        }
        //G(u,v)
        for (int v = 0; v < Q; ++v) {
        	for (int u = 0; u < P; ++u) {
        		R[v][u] = H[v][u].re() * Fuv[v][u].re() - H[v][u].im() * Fuv[v][u].im();
        		I[v][u] = H[v][u].re() * Fuv[v][u].im() + H[v][u].im() * Fuv[v][u].re();
        		G[v][u] = new Complex(R[v][u], I[v][u]);
        	}
        }
        //g(u,v)
        for (int u = 0; u < P; u++) {
            for (int y = 0; y < Q; y++) {
                R[y][u] = 0;
                I[y][u] = 0;
                for (int v = 0; v < Q; v++) {
                    double w = 2 * Math.PI * v * y / Q;
                    R[y][u] += G[v][u].re() * Math.cos(w) - G[v][u].im() * Math.sin(w);
                    I[y][u] += G[v][u].re() * Math.sin(w) + G[v][u].im() * Math.cos(w);
                }
                guy[y][u] = new Complex(R[y][u], I[y][u]);
            }
        }
        for (int x = 0; x < P; x++) {
            for (int y = 0; y < Q; y++) {
                R[y][x] = 0;
                I[y][x] = 0;
                for (int u = 0; u < P; u++) {
                    double w = 2 * Math.PI * u * x / P;
                    R[y][x] += guy[y][u].re() * Math.cos(w) - guy[y][u].im() * Math.sin(w);
                    I[y][x] += guy[y][u].re() * Math.sin(w) + guy[y][u].im() * Math.cos(w);
                }
                R[y][x] = R[y][x] / P / Q;
                I[y][x] = I[y][x] / P / Q;
                g[y][x] = new Complex(R[y][x], I[y][x]);
            }
        }
        //gp
        for (int i = 0; i < Q; ++i) {
        	for (int j = 0; j < P; ++j) {
        		gp[i][j] = (Math.round(g[i][j].re() * Math.pow(-1, i + j)));
        		if (gp[i][j] < 0) {
        			gp[i][j] = 0;
        		} else if (gp[i][j] > 255) {
        			gp[i][j] = 255;
        		}
        	}
        }
        for (int i = 0; i < N; ++i) {
        	for (int j = 0; j < M; ++j) {
        		after[i * M + j] = (int) gp[i][j];
        	}
        }
        //把灰度值变成RGB值
        for (int i = 0; i < M * N; i++) {
            after_[i] = 0xff << 24 | after[i] << 16 | after[i] << 8 | after[i];
        }
        //绘制新图像
        DataBuffer dataBuffer = new DataBufferInt(after_, M * N);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, M, N, M, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
    */
    /*
	//add_gaussian_noise
    public void add_gaussian_noise_window() {
    	fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        JLabel label_ = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 150);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(5, 1));
        label = new JLabel("Mean");
        label.setHorizontalAlignment(JLabel.CENTER);
        label_ = new JLabel("Standard variance");
        label_.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel.add(tf);
        panel.add(label_);
        panel.add(tf_);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		add_gaussian_noise(Integer.parseInt(tf.getText()), Integer.parseInt(tf_.getText()));
        	}
        });
    }
    //add_gaussian_noise
    public void add_gaussian_noise(int mean, int standard_variance) {
    	BufferedImage img = Fr.before;
    	int w = img.getWidth();
    	int h = img.getHeight();
    	int m_ = mean;
    	int s_ = standard_variance;
    	int[] beforeimg = new int[w * h];
    	int[] afterimg = new int[w * h];
    	int[] pixel = new int[w * h];
    	img.getRGB(0, 0, w, h, beforeimg, 0, w);
    	
    	for (int i = 0; i < w * h; ++i) {
    		pixel[i] = beforeimg[i] & 0xff;
    	}
    	//for (int i = 0; i < w * h; ++i) {
    		double r = new Random().nextGaussian();
    		double r1 = new Random().nextDouble();
    		double p1 = 1 / (Math.sqrt(2 * Math.PI) * s_) * Math.exp(-(r - m_) * (r - m_) / (2 * s_ * s_));
    		if (r1 <= p1) {
    			pixel[i] += r * 128;
    		}
    		if (pixel[i] > 255) {
    			pixel[i] = 255;
    		}
    		if (pixel[i] < 0) {
    			pixel[i] = 0;
    		}
    	}//
    	for (int i = 0; i < w * h; ++i) {
    		double r = new Random().nextGaussian();
    		pixel[i] = (int) (pixel[i] + m_ + s_ * r);
    		if (pixel[i] > 255) {
    			pixel[i] = 255;
    		}
    		if (pixel[i] < 0) {
    			pixel[i] = 0;
    		}
    	}
    	for (int i = 0; i < w * h; ++i) {
    		afterimg[i] = 0xff << 24 | pixel[i] << 16 | pixel[i] << 8 | pixel[i];
    	}
    	DataBuffer dataBuffer = new DataBufferInt(afterimg, w * h);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, w, h, w, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
	*/
    /*
	//add_impulse_noise
    public void add_impulse_noise_window() {
    	fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        JLabel label_ = new JLabel();
        tf = new JTextField();
        tf_ = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 250) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(250, 150);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(5, 1));
        label = new JLabel("Salt%");
        label.setHorizontalAlignment(JLabel.CENTER);
        label_ = new JLabel("Pepper%");
        label_.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf_.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel.add(tf);
        panel.add(label_);
        panel.add(tf_);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		add_impulse_noise(Integer.parseInt(tf.getText()), Integer.parseInt(tf_.getText()));
        	}
        });
    }
    //add_impulse_noise
    public void add_impulse_noise(int pa, int pb) {
    	BufferedImage img = Fr.before;
    	int w = img.getWidth();
    	int h = img.getHeight();
    	double p1 = 1.0 * pa / 100;
    	double p2 = 1.0 * pb / 100;
    	double p3 = p2 / (1 - p1);
    	int[] beforeimg = new int[w * h];
    	int[] afterimg = new int[w * h];
    	int[] pixel = new int[w * h];
    	img.getRGB(0, 0, w, h, beforeimg, 0, w);
    	
    	for (int i = 0; i < w * h; ++i) {
    		pixel[i] = beforeimg[i] & 0xff;
    	}
    	for (int i = 0; i < w * h; ++i) {
    		int noise = 1;
    		double random = new Random().nextDouble();
    		if (random < p1) {
    			noise = 255;
    		} else {
    			double temp = new Random().nextDouble();
    			if (temp < p3) {
    				noise = 0;
    			}
    		}
    		if (noise != 1) {
    			pixel[i] = noise;
    		}
    	}
    	for (int i = 0; i < w * h; ++i) {
    		afterimg[i] = 0xff << 24 | pixel[i] << 16 | pixel[i] << 8 | pixel[i];
    	}
    	DataBuffer dataBuffer = new DataBufferInt(afterimg, w * h);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, w, h, w, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
    */
    /*
    //equalize_hist_window
    public void equalize_hist_window() {
    	fr = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        tf = new JTextField();
        bt = new JButton();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - 260) / 2, (screenSize.height - 100) / 2);
        
        fr.setSize(260, 100);
        fr.setVisible(true);
        
        panel.setLayout(new GridLayout(3, 1));
        label = new JLabel("Input number, 1 for RGB, 2 for RGB/3");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                equalize_hist(Integer.parseInt(tf.getText()));
            }
        });
    }
    */
    /*
	//haze_removal_window
    public void haze_removal_window() {
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
        label = new JLabel("Input value of radius");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		haze_removal(Integer.parseInt(tf.getText()));
        	}
        });
    }
    //haze_removal
    public void haze_removal(int r) {
    	BufferedImage img = Fr.before;
    	int w = img.getWidth();
    	int h = img.getHeight();
    	
    	int[] img_i = new int[w * h];
    	int[] dark_i = new int[w * h];
    	int[] dark_i_ = new int[w * h];
    	int bf1 = (int) (0.001 * w * h);
    	int red, green, blue;
    	img.getRGB(0, 0, w, h, img_i, 0, w);
    	// 暗通道
    	for (int i = 0; i < w * h; i++) {
    		red = gc.getRed(img_i[i]);
    		green = gc.getGreen(img_i[i]);
    		blue = gc.getBlue(img_i[i]);
    		if (red <= green && red <= blue) {
    			dark_i[i] = red;
    		} else if (green <= red && green <= blue) {
    			dark_i[i] = green;
    		} else if (blue <= red && blue <= green) {
    			dark_i[i] = blue;
    		}
    		dark_i_[i] = 255;
    	}
    	for (int v = 0; v < h; v++) {
            for (int u = 0; u < w; u++) {
                for (int j = -r; j <= r; j++) {
                    for (int i = -r; i <= r; i++) {
                        int x = v + j;
                        int y = u + i;
                        if (x < 0 || x > h - 1 || y < 0 || y > w - 1) {
                        } else {
                            if (dark_i[x * w + y] < dark_i_[v * w + u]) {
                            	dark_i_[v * w + u] = dark_i[x * w + y];
                            }
                        }
                    }
                }
            }
        }
    	// 求A
    	int count = 0;
    	int gray = 255;
    	int[][] pos = new int[h][w];
    	while(count < bf1) {
    		for (int v = 0; v < h; v++) {
    			for (int u = 0; u < w; u++) {
    				if (gray == 255) {
    					pos[v][u] = 0;
    				}
    				if (dark_i_[v * w + u] == gray) {
    					pos[v][u] = 1;
    					count++;
    				}
    			}
    		}
    		gray--;
    	}
    	double A = 0;
    	for (int v = 0; v < h; v++) {
    		for (int u = 0; u < w; u++) {
    			if (pos[v][u] == 1) {
    				red = gc.getRed(img_i[v * w + u]);
    	    		green = gc.getGreen(img_i[v * w + u]);
    	    		blue = gc.getBlue(img_i[v * w + u]);
    				int I = (int) ((red + green + blue) / 3.0);
    				A += I;
    			}
    		}
    	}
    	A = A / count;
    	if (A > 220) {
    		A = 220;
    	}
    	// 求t(x)
    	double[] t = new double[w * h];
    	double[] t_ = new double[w * h];
    	for (int i = 0; i < w * h; i++) {
    		t[i] = 1.0 * dark_i[i] / A;
    		t_[i] = 2.0;
    	}
    	for (int v = 0; v < h; v++) {
    		for (int u = 0; u < w; u++) {
    			for (int j = -r; j <= r; j++) {
                    for (int i = -r; i <= r; i++) {
                        int x = v + j;
                        int y = u + i;
                        if (x < 0 || x > h - 1 || y < 0 || y > w - 1) {
                        } else {
                            if (t[x * w + y] < t_[v * w + u]) {
                            	t_[v * w + u] = t[x * w + y];
                            }
                        }
                    }
                }
    			t_[v * w + u] = 1 - 0.95 * t_[v * w + u];
    		}
    	}
    	// J(x)
    	int[] jx = new int[w * h];
    	for (int i = 0; i < w * h; i++) {
    		red = gc.getRed(img_i[i]);
    		green = gc.getGreen(img_i[i]);
    		blue = gc.getBlue(img_i[i]);
    		double t0;
    		if (t_[i] > 0.1) {
    			t0 = t_[i];
    		} else {
    			t0 = 0.1;
    		}
    		int red_ = (int) (1.0 * (red - A) / t0 + A);
    		if (red_ > 255) {
    			red_ = 255;
    		}
    		int green_ = (int) (1.0 * (green - A) / t0 + A);
    		if (green_ > 255) {
    			green_ = 255;
    		}
    		int blue_ = (int) (1.0 * (blue - A) / t0 + A);
    		if (blue_ > 255) {
    			blue_ = 255;
    		}
    		jx[i] = 0xff << 24 | red_ << 16 | green_ << 8 | blue_;
    	}
    	
    	DataBuffer dataBuffer = new DataBufferInt(jx, w * h);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, w, h, w, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.haze = image_;
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
    */
    /*
	//guide filter
    public void guide_filter_window() {
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
        label = new JLabel("Input value of radius");
        label.setHorizontalAlignment(JLabel.CENTER);
        tf.setHorizontalAlignment(JTextField.CENTER);
        bt.setText("set");
        panel.add(label);
        panel.add(tf);
        panel.add(bt);
        
        fr.getContentPane().add(panel, BorderLayout.CENTER);
        
        bt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fr.dispose();
        		haze_removal(Integer.parseInt(tf.getText()));
        		guide_filter(Integer.parseInt(tf.getText()));
        	}
        });
    }
    // guide filter
    public void guide_filter(int R) {
    	// 4r < R < 8R
    	int r = 6 * R;
    	BufferedImage image = Fr.before;
    	BufferedImage img = Fr.haze;
    	int w = img.getWidth();
    	int h = img.getHeight();
    	
    	int[] img_p = new int[w * h];
    	int[] img_p_r = new int[w * h];
    	int[] img_p_g = new int[w * h];
    	int[] img_p_b = new int[w * h];
    	int[] img_i = new int[w * h];
    	int[] img_i_r = new int[w * h];
    	int[] img_i_g = new int[w * h];
    	int[] img_i_b = new int[w * h];
    	image.getRGB(0, 0, w, h, img_i, 0, w);
    	img.getRGB(0, 0, w, h, img_p, 0, w);
    	
    	for (int i = 0; i < w * h; i++) {
    		img_p_r[i] = gc.getRed(img_p[i]);
    		img_p_g[i] = gc.getGreen(img_p[i]);
    		img_p_b[i] = gc.getBlue(img_p[i]);
    		img_i_r[i] = gc.getRed(img_i[i]);
    		img_i_g[i] = gc.getGreen(img_i[i]);
    		img_i_b[i] = gc.getBlue(img_i[i]);
    	}
    	// 第一步
    	double[] mean_i_r = new double[w * h];
    	double[] mean_i_g = new double[w * h];
    	double[] mean_i_b = new double[w * h];
    	double[] mean_p_r = new double[w * h];
    	double[] mean_p_g = new double[w * h];
    	double[] mean_p_b = new double[w * h];
    	double[] corr_i_r = new double[w * h];
    	double[] corr_i_g = new double[w * h];
    	double[] corr_i_b = new double[w * h];
    	double[] corr_ip_r = new double[w * h];
    	double[] corr_ip_g = new double[w * h];
    	double[] corr_ip_b = new double[w * h];
    	for (int v = 0; v < h; v++) {
            for (int u = 0; u < w; u++) {
            	double count = 0;
            	double tmp_i_r = 0;
            	double tmp_i_g = 0;
            	double tmp_i_b = 0;
            	double tmp_p_r = 0;
            	double tmp_p_g = 0;
            	double tmp_p_b = 0;
            	double tmp_ii_r = 0;
            	double tmp_ii_g = 0;
            	double tmp_ii_b = 0;
            	double tmp_ip_r = 0;
            	double tmp_ip_g = 0;
            	double tmp_ip_b = 0;
                for (int j = -r; j <= r; j++) {
                    for (int i = -r; i <= r; i++) {
                        int x = v + j;
                        int y = u + i;
                        if (x < 0 || x > h - 1 || y < 0 || y > w - 1) {
                        } else {
                        	count++;
                        	tmp_i_r += img_i_r[x * w + y];
                        	tmp_i_g += img_i_g[x * w + y];
                        	tmp_i_b += img_i_b[x * w + y];
                        	tmp_p_r += img_p_r[x * w + y];
                        	tmp_p_g += img_p_g[x * w + y];
                        	tmp_p_b += img_p_b[x * w + y];
                        	tmp_ii_r += img_i_r[x * w + y] * img_i_r[x * w + y];
                        	tmp_ii_g += img_i_g[x * w + y] * img_i_g[x * w + y];
                        	tmp_ii_b += img_i_b[x * w + y] * img_i_b[x * w + y];
                        	tmp_ip_r += img_i_r[x * w + y] * img_p_r[x * w + y];
                        	tmp_ip_g += img_i_g[x * w + y] * img_p_g[x * w + y];
                        	tmp_ip_b += img_i_b[x * w + y] * img_p_b[x * w + y];
                        }
                    }
                }
                mean_i_r[v * w + u] = 1.0 * tmp_i_r / count;
                mean_i_g[v * w + u] = 1.0 * tmp_i_g / count;
                mean_i_b[v * w + u] = 1.0 * tmp_i_b / count;
                mean_p_r[v * w + u] = 1.0 * tmp_p_r / count;
                mean_p_g[v * w + u] = 1.0 * tmp_p_g / count;
                mean_p_b[v * w + u] = 1.0 * tmp_p_b / count;
                corr_i_r[v * w + u] = 1.0 * tmp_ii_r / count;
                corr_i_g[v * w + u] = 1.0 * tmp_ii_g / count;
                corr_i_b[v * w + u] = 1.0 * tmp_ii_b / count;
                corr_ip_r[v * w + u] = 1.0 * tmp_ip_r / count;
                corr_ip_g[v * w + u] = 1.0 * tmp_ip_g / count;
                corr_ip_b[v * w + u] = 1.0 * tmp_ip_b / count;
            }
        }
    	// 第二步
    	double[] var_r = new double[w * h];
    	double[] var_g = new double[w * h];
    	double[] var_b = new double[w * h];
    	double[] cov_r = new double[w * h];
    	double[] cov_g = new double[w * h];
    	double[] cov_b = new double[w * h];
    	for (int i = 0; i < w * h; i++) {
    		var_r[i] = corr_i_r[i] - mean_i_r[i] * mean_i_r[i];
    		var_g[i] = corr_i_g[i] - mean_i_g[i] * mean_i_g[i];
    		var_b[i] = corr_i_b[i] - mean_i_b[i] * mean_i_b[i];
    		cov_r[i] = corr_ip_r[i] - mean_i_r[i] * mean_p_r[i];
    		cov_g[i] = corr_ip_g[i] - mean_i_g[i] * mean_p_g[i];
    		cov_b[i] = corr_ip_b[i] - mean_i_b[i] * mean_p_b[i];
    	}
    	// 第三步
    	double[] a_r = new double[w * h];
    	double[] a_g = new double[w * h];
    	double[] a_b = new double[w * h];
    	double[] b_r = new double[w * h];
    	double[] b_g = new double[w * h];
    	double[] b_b = new double[w * h];
    	for (int i = 0; i < w * h; i++) {
    		a_r[i] = cov_r[i] / (var_r[i] + 0.000001);
    		b_r[i] = mean_p_r[i] - a_r[i] * mean_i_r[i];
    		a_g[i] = cov_g[i] / (var_g[i] + 0.000001);
    		b_g[i] = mean_p_g[i] - a_g[i] * mean_i_g[i];
    		a_b[i] = cov_b[i] / (var_b[i] + 0.000001);
    		b_b[i] = mean_p_b[i] - a_b[i] * mean_i_b[i];
    	}
    	// 第四步
    	double[] mean_a_r = new double[w * h];
    	double[] mean_a_g = new double[w * h];
    	double[] mean_a_b = new double[w * h];
    	double[] mean_b_r = new double[w * h];
    	double[] mean_b_g = new double[w * h];
    	double[] mean_b_b = new double[w * h];
    	for (int v = 0; v < h; v++) {
    		for (int u = 0; u < w; u++) {
    			double count = 0;
    			double tmp_a_r = 0;
    			double tmp_a_g = 0;
    			double tmp_a_b = 0;
    			double tmp_b_r = 0;
    			double tmp_b_g = 0;
    			double tmp_b_b = 0;
    			for (int j = -r; j <= r; j++) {
                    for (int i = -r; i <= r; i++) {
                        int x = v + j;
                        int y = u + i;
                        if (x < 0 || x > h - 1 || y < 0 || y > w - 1) {
                        } else {
                        	count++;
                        	tmp_a_r += a_r[x * w + y];
                        	tmp_a_g += a_g[x * w + y];
                        	tmp_a_b += a_b[x * w + y];
                        	tmp_b_r += b_r[x * w + y];
                        	tmp_b_g += b_g[x * w + y];
                        	tmp_b_b += b_b[x * w + y];
                        }
                    }
                }
    			mean_a_r[v * w + u] = tmp_a_r / count;
    			mean_a_g[v * w + u] = tmp_a_g / count;
    			mean_a_b[v * w + u] = tmp_a_b / count;
    			mean_b_r[v * w + u] = tmp_b_r / count;
    			mean_b_g[v * w + u] = tmp_b_g / count;
    			mean_b_b[v * w + u] = tmp_b_b / count;
    		}
    	}
    	// 第五步
    	int[] q = new int[w * h];
    	int[] q_r = new int[w * h];
    	int[] q_g = new int[w * h];
    	int[] q_b = new int[w * h];
    	for (int i = 0; i < w * h; i++) {
    		q_r[i] = (int) (mean_a_r[i] * img_i_r[i] + mean_b_r[i]);
    		if (q_r[i] > 255) {
    			q_r[i] = 255;
    		}
    		if (q_r[i] < 0) {
    			q_r[i] = 0;
    		}
    		q_g[i] = (int) (mean_a_g[i] * img_i_g[i] + mean_b_g[i]);
    		if (q_g[i] > 255) {
    			q_g[i] = 255;
    		}
    		if (q_g[i] < 0) {
    			q_g[i] = 0;
    		}
    		q_b[i] = (int) (mean_a_b[i] * img_i_b[i] + mean_b_b[i]);
    		if (q_b[i] > 255) {
    			q_b[i] = 255;
    		}
    		if (q_b[i] < 0) {
    			q_b[i] = 0;
    		}
    		q[i] = 0xff << 24 | q_r[i] << 16 | q_g[i] << 8 | q_b[i];
    	}
    	
    	DataBuffer dataBuffer = new DataBufferInt(q, w * h);
        WritableRaster raster = Raster.createPackedRaster(dataBuffer, w, h, w, new int[]{0xff0000, 0xff00, 0xff}, null);
        DirectColorModel directColorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        BufferedImage image_ = new BufferedImage(directColorModel, raster, true, null);
        Fr.after = image_;
        ImageIcon icon = new ImageIcon(image_);
        Fr.Pic.setIcon(icon);
        Fr.Pic.repaint();
    }
    */
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
