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
        filter2d = new JMenuItem("�˲�");
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
