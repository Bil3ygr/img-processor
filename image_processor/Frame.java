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
    BufferedImage before;
    BufferedImage after;
    BufferedImage haze;

    Stack<BufferedImage> st = new Stack<BufferedImage>();
    JMenuBar menubar;

    JMenu file;
    JMenuItem openFile;
    JMenuItem saveFile;
    JMenuItem exit;

    JMenu process;
    JMenuItem rotate;
    JMenuItem scale;
    JMenuItem quantize;
    JMenuItem plot_hist_gray;
    JMenuItem equalize_hist_gray;
    JMenuItem view_as_window;
    JMenuItem filter2d;
    JMenuItem dft2d;
    JMenuItem filter2d_freq;
    JMenuItem add_gaussian_noise;
    JMenuItem add_impulse_noise;
    JMenuItem plot_hist_color;
    JMenuItem equalize_hist_color;
    JMenuItem haze_removal;
    JMenuItem guide_filter;
    
    JMenu animate;
    JMenuItem animate_;

    JMenu help;
    JMenuItem about;

    JScrollPane ScrollPane;
    /*
     * create a frame
     */
    public Frame() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - 500) / 2, (screenSize.height - 500) / 2);
        
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
        ScrollPane = new JScrollPane();
        ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        menubar = new JMenuBar();
        Pic = new JLabel();
        
        this.getContentPane().add(Pic, BorderLayout.CENTER);
        Pic.setHorizontalAlignment(JLabel.CENTER);
        
        setJMenuBar(menubar);
        
        FileMenu();
        ProcessMenu();
        AnimateMenu();
        HelpMenu();
    }
    /*
     * the action when click the JMenuItem
     */
    public void Operation() {
        new Files(this);
        new Processor(this);
        new Animate(this);
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
        quantize = new JMenuItem("灰度级");
        plot_hist_gray = new JMenuItem("灰度直方图");
        equalize_hist_gray = new JMenuItem("灰度直方图均衡化");
        view_as_window = new JMenuItem("截图");
        filter2d = new JMenuItem("滤波");
        dft2d = new JMenuItem("离散傅里叶变换");
        filter2d_freq = new JMenuItem("频率域滤波");
        add_gaussian_noise = new JMenuItem("添加高斯噪声");
        add_impulse_noise = new JMenuItem("添加脉冲（椒盐）噪声");
        plot_hist_color = new JMenuItem("彩色直方图");
        equalize_hist_color = new JMenuItem("彩色直方图均衡化");
        haze_removal = new JMenuItem("去雾");
        guide_filter = new JMenuItem("导向滤波");
        
        process.add(rotate);
        process.add(scale);
        process.add(quantize);
        process.add(plot_hist_gray);
        process.add(equalize_hist_gray);
        process.add(view_as_window);
        process.add(filter2d);
        process.add(dft2d);
        process.add(filter2d_freq);
        process.add(add_gaussian_noise);
        process.add(add_impulse_noise);
        process.add(plot_hist_color);
        process.add(equalize_hist_color);
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
