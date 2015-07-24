package image_processor;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Frame extends JFrame {
    /**
	 * 
	 */
    private static final long serialVersionUID = 8781460130127454735L;
    
	JLabel Pic;
    BufferedImage before = null;
    BufferedImage after = null;
    BufferedImage haze = null;

    Stack<BufferedImage> st = new Stack<BufferedImage>();
    Stack<BufferedImage> st_ = new Stack<BufferedImage>();
    // 菜单栏
    JMenuBar menubar;
    // 文件菜单
    JMenu file;
    JMenuItem openFile;
    JMenuItem saveFile;
    JMenuItem exit;
    // 操作菜单
    JMenu process;
    JMenuItem rotate;
    JMenuItem scale;
    JMenuItem gray;
    JMenuItem quantize;
    
    JMenu plot_hist;
    JMenuItem plot_hist_gray;
    JMenuItem plot_hist_color;
    
    JMenu equalize_hist;
    JMenuItem equalize_hist_gray;
    JMenu equalize_hist_color;
    JMenuItem equalize_hist_color_1;
    JMenuItem equalize_hist_color_2;
    
    JMenu filter2d;
    JMenuItem smooth;
    JMenuItem sharpen;
    JMenuItem sobel3x3;
    JMenuItem sobel2x2;
    JMenuItem harmonic;
    JMenuItem contraharmonic;
    JMenuItem geometric;
    JMenuItem median;
    JMenuItem max;
    JMenuItem min;
    
    JMenu dft2d;
    JMenuItem dft2d_dft;
    JMenuItem dft2d_idft;
    
    JMenu filter2d_freq;
    JMenuItem filter2d_freq_smooth;
    JMenuItem filter2d_freq_sharpen;
    
    JMenuItem add_gaussian_noise;
    JMenuItem add_impulse_noise;
    JMenuItem haze_removal;
    JMenuItem guide_filter;
    // 动画菜单
    JMenu animate;
    JMenuItem animate_;
    // 重做菜单
    JMenu undo;
    JMenuItem step_before;
    JMenuItem step_after;
    JMenuItem original;
    // 帮助菜单
    JMenu help;
    JMenuItem about;

    JScrollPane ScrollPane;
    
    String currentPath;
    /*
     * create a frame
     */
    public Frame() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation((screenSize.width - 550) / 2, (screenSize.height - 550) / 2);
        
        setTitle("Image Processor");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buildPane();
        setVisible(true);
        Operation();
    }
    /*
     * add menu to this frame
     */
    public void buildPane() {
    	menubar = new JMenuBar();
        Pic = new JLabel();
        
        ScrollPane = new JScrollPane(Pic);
        ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        setJMenuBar(menubar);
        
        FileMenu();
        ProcessMenu();
        AnimateMenu();
        UndoMenu();
        HelpMenu();
        
        this.getContentPane().add(ScrollPane, BorderLayout.CENTER);
        Pic.setHorizontalAlignment(JLabel.CENTER);
    }
    /*
     * the action when click the JMenuItem
     */
    public void Operation() {
        new Files(this);
        new Processor(this);
        new Animate(this);
        new Undo(this);
        new Help(this);
    }
    /*
     * file menu
     */
    public void FileMenu() {
        file = new JMenu("文件");
        openFile = new JMenuItem("打开(O)");
        saveFile = new JMenuItem("保存(S)");
        exit = new JMenuItem("退出(E)");
        
        file.add(openFile);
        file.add(saveFile);
        file.add(exit);
        
        file.setMnemonic('F');
        
        openFile.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_MASK));
        
        menubar.add(file);
    }
    /*
     * process menu
     */
    public void ProcessMenu() {
        process = new JMenu("操作");
        rotate = new JMenuItem("旋转");
        scale = new JMenuItem("缩放");
        gray = new JMenuItem("灰度图");
        quantize = new JMenuItem("灰度级");
        plot_hist = new JMenu("直方图");
        plot_hist_gray = new JMenuItem("灰度直方图");
        plot_hist_color = new JMenuItem("彩色直方图");
        equalize_hist = new JMenu("直方图均衡化");
        equalize_hist_gray = new JMenuItem("灰度直方图均衡化");
        equalize_hist_color = new JMenu("彩色直方图均衡化");
        equalize_hist_color_1 = new JMenuItem("分别对RGB均衡化");
        equalize_hist_color_2 = new JMenuItem("对RGB求平均再均衡化");
        filter2d = new JMenu("滤波（灰度图）");
        smooth = new JMenuItem("平滑");
        sharpen = new JMenuItem("锐化");
        sobel3x3 = new JMenuItem("Sobel3x3");
        sobel2x2 = new JMenuItem("Sobel2x2");
        harmonic = new JMenuItem("谐波");
        contraharmonic = new JMenuItem("逆谐波");
        geometric = new JMenuItem("几何");
        median = new JMenuItem("中值");
        max = new JMenuItem("最大值");
        min = new JMenuItem("最小值");
        dft2d = new JMenu("离散傅里叶变换（灰度图）");
        dft2d_dft = new JMenuItem("DFT");
        dft2d_idft = new JMenuItem("IDFT");
        filter2d_freq = new JMenu("频率域滤波（灰度图）");
        filter2d_freq_smooth = new JMenuItem("平滑");
        filter2d_freq_sharpen = new JMenuItem("锐化");
        add_gaussian_noise = new JMenuItem("添加高斯噪声（灰度图）");
        add_impulse_noise = new JMenuItem("添加脉冲（椒盐）噪声（灰度图）");
        haze_removal = new JMenuItem("去雾");
        guide_filter = new JMenuItem("导向滤波");
        
        process.add(rotate);
        process.add(scale);
        process.add(gray);
        process.add(quantize);
        process.add(plot_hist);
        plot_hist.add(plot_hist_gray);
        plot_hist.add(plot_hist_color);
        process.add(equalize_hist);
        equalize_hist.add(equalize_hist_gray);
        equalize_hist.add(equalize_hist_color);
        equalize_hist_color.add(equalize_hist_color_1);
        equalize_hist_color.add(equalize_hist_color_2);
        process.add(filter2d);
        filter2d.add(smooth);
        filter2d.add(sharpen);
        filter2d.add(sobel3x3);
        filter2d.add(sobel2x2);
        filter2d.add(harmonic);
        filter2d.add(contraharmonic);
        filter2d.add(geometric);
        filter2d.add(median);
        filter2d.add(max);
        filter2d.add(min);
        process.add(dft2d);
        dft2d.add(dft2d_dft);
        dft2d.add(dft2d_idft);
        process.add(filter2d_freq);
        filter2d_freq.add(filter2d_freq_smooth);
        filter2d_freq.add(filter2d_freq_sharpen);
        process.add(add_gaussian_noise);
        process.add(add_impulse_noise);
        process.add(haze_removal);
        process.add(guide_filter);
        
        process.setMnemonic('P');
        
        menubar.add(process);
    }
    /*
     * animate menu
     */
    public void AnimateMenu() {
    	animate = new JMenu("打开动画");
    	menubar.add(animate);
    	
    	animate_ = new JMenuItem("选择文件");
    	
    	animate.add(animate_);
    }
    /*
     * undo menu
     */
    public void UndoMenu() {
    	undo = new JMenu("重做");
    	menubar.add(undo);
    	
    	step_before = new JMenuItem("上一步");
    	step_after = new JMenuItem("下一步");
    	original = new JMenuItem("原图");
    	
    	undo.add(step_before);
    	undo.add(step_after);
    	undo.add(original);
    }
    /*
     * help menu
     */
    public void HelpMenu() {
        help = new JMenu("帮助");
        menubar.add(help);
        
        about = new JMenuItem("关于");
        
        help.add(about);
        
        help.setMnemonic('H');
    }

    public static void main(String[] args) {
        new Frame();
    }
}
