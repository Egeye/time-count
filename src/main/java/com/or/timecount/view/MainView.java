package com.or.timecount.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainView extends Frame {
    public MainView() throws HeadlessException {
        super();
        // TODO Auto-generated constructor stub
    }

    public MainView(String title) throws HeadlessException {
        super(title);
        // TODO Auto-generated constructor stub
        Toolkit tool = getToolkit();
        Dimension dim = tool.getScreenSize();
        setBounds(0, 0, 352, 288);
        // MenuBar menubar = new MenuBar();
        // Menu menu = new Menu("File");

        // MenuItem item1, item2;
        // item1 = new MenuItem("Hey?");
        // item2 = new MenuItem("What?");
        // menu.add(item1);
        // menu.add(item2);
        // menubar.add(menu);
        // setMenuBar(menubar);


        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        new TimerFrame().setVisible(true);

    }
}

class TimerFrame extends JFrame {

    private long time = (long) (1.2 * 60 * 1000L);  //倒计时时间（单位毫秒）
    private JLabel lblTime;
    private Thread runner;

    public TimerFrame() {
        super("TimerFrame");
        this.lblTime = new JLabel("单击开始");
        this.lblTime.setFont(new Font("Monospaced", Font.BOLD, 60));
        this.lblTime.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblTime.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (runner != null) runner.start();
            }
        });
        this.runner = new Thread(new Runnable() {
            public void run() {
                Date t = new Date();
                StringWriter sw = new StringWriter();
                StringBuffer sb = sw.getBuffer();
                PrintWriter pw = new PrintWriter(sw);
                long cur = 0L, end = System.currentTimeMillis() + time;
                while ((cur = end - System.currentTimeMillis()) > 0) {
                    t.setTime(cur);
                    pw.format("%1$tM:%1$tS.%tL", t);
                    pw.flush();
                    lblTime.setText(sb.toString());
                    sb.setLength(0);
                    try {
                        Thread.sleep(6L);
                    } catch (InterruptedException e) {
                    }
                }
                lblTime.setText("00:00.000");
                try {
                    Thread.sleep(1200L);
                } catch (InterruptedException e) {
                }
                lblTime.setText("Bomb!!!");
            }
        });
        this.runner.setDaemon(true);
        getContentPane().add(this.lblTime, BorderLayout.CENTER);
        setResizable(false);
        setBounds(0, 0, 460, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

