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
    BufferedImage after = null;
    BufferedImage haze = null;

    Stack<BufferedImage> st = new Stack<BufferedImage>();
    // �˵���
    JMenuBar menubar;
    // �ļ��˵�
    JMenu file;
    JMenuItem openFile;
    JMenuItem saveFile;
    JMenuItem exit;
    // �����˵�
    JMenu process;
    JMenuItem rotate;
    JMenuItem scale;
    JMenuItem quantize;
    JMenuItem plot_hist_gray;
    JMenuItem equalize_hist_gray;
    JMenuItem view_as_window;
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
    JMenuItem dft2d;
    JMenuItem filter2d_freq;
    JMenuItem add_gaussian_noise;
    JMenuItem add_impulse_noise;
    JMenuItem plot_hist_color;
    JMenuItem equalize_hist_color;
    JMenuItem haze_removal;
    JMenuItem guide_filter;
    // �����˵�
    JMenu animate;
    JMenuItem animate_;
    // �����˵�
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
    	menubar = new JMenuBar();
        Pic = new JLabel();
        
        ScrollPane = new JScrollPane(Pic);
        ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        setJMenuBar(menubar);
        
        FileMenu();
        ProcessMenu();
        AnimateMenu();
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
        new Help(this);
    }
    /*
     * file menu
     */
    public void FileMenu() {
        file = new JMenu("�ļ�");
        openFile = new JMenuItem("��(O)");
        saveFile = new JMenuItem("����(S)");
        exit = new JMenuItem("�˳�(E)");
        
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
        process = new JMenu("����");
        rotate = new JMenuItem("��ת");
        scale = new JMenuItem("����");
        quantize = new JMenuItem("�Ҷȼ�");
        plot_hist_gray = new JMenuItem("�Ҷ�ֱ��ͼ");
        equalize_hist_gray = new JMenuItem("�Ҷ�ֱ��ͼ���⻯");
        view_as_window = new JMenuItem("��ͼ");
        filter2d = new JMenu("�˲�");
        smooth = new JMenuItem("ƽ��");
        sharpen = new JMenuItem("��");
        sobel3x3 = new JMenuItem("Sobel3x3");
        sobel2x2 = new JMenuItem("Sobel2x2");
        harmonic = new JMenuItem("г��");
        contraharmonic = new JMenuItem("��г��");
        geometric = new JMenuItem("����");
        median = new JMenuItem("��ֵ");
        max = new JMenuItem("���ֵ");
        min = new JMenuItem("��Сֵ");
        dft2d = new JMenuItem("��ɢ����Ҷ�任");
        filter2d_freq = new JMenuItem("Ƶ�����˲�");
        add_gaussian_noise = new JMenuItem("��Ӹ�˹����");
        add_impulse_noise = new JMenuItem("������壨���Σ�����");
        plot_hist_color = new JMenuItem("��ɫֱ��ͼ");
        equalize_hist_color = new JMenuItem("��ɫֱ��ͼ���⻯");
        haze_removal = new JMenuItem("ȥ��");
        guide_filter = new JMenuItem("�����˲�");
        
        process.add(rotate);
        process.add(scale);
        process.add(quantize);
        process.add(plot_hist_gray);
        process.add(equalize_hist_gray);
        process.add(view_as_window);
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
    	animate = new JMenu("�򿪶���");
    	menubar.add(animate);
    	
    	animate_ = new JMenuItem("ѡ���ļ�");
    	
    	animate.add(animate_);
    }
    /*
     * help menu
     */
    public void HelpMenu() {
        help = new JMenu("����");
        menubar.add(help);
        
        about = new JMenuItem("����");
        
        help.add(about);
        
        help.setMnemonic('H');
    }

    public static void main(String[] args) {
        new Frame();
    }
}
