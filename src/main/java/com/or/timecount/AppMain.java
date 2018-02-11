package com.or.timecount;

import com.or.timecount.view.MainView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class AppMain {
    // 定时器
    private static Timer timer = null;

    // 表格组件内容
    private static String[][] datas = {};
    private static String[] titles = {"计时时间", "上次时间", "两次差值"};
    private static DefaultTableModel model = new DefaultTableModel(datas, titles);

    // 时间标签
    private static Label labelTime = new Label("0:0:0.00");

    private static int hour = 0;
    private static int minute = 0;
    private static int second = 0;
    private static int millisecond = 0;//毫秒
    private static String ms;
    // var int = null; // 定时器
    private static String val;

    // 开始标志
    private static boolean isRunning = false;

    public static void main(String[] args) {
        // 文字标题
        MainView demo = new MainView("");

        // 背景颜色
        // demo.setBackground(Color.BLUE);

        // 大小
        demo.setSize(300, 200);


        Button btnStart = new Button("start");
        Button btnCount = new Button("count");
        Button btnReset = new Button("reset");

        // demo.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        demo.setLayout(new BorderLayout());

        final Panel northPanel = new Panel();
        final Panel southPanel = new Panel();
        Panel centerPanel = new Panel();

        northPanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new FlowLayout());
        // southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));


        northPanel.add(labelTime);
        centerPanel.add(btnStart);
        centerPanel.add(btnCount);
        centerPanel.add(btnReset);

        northPanel.add(centerPanel);

        // String[][] datas = {};
        // String[] titles = {"计时时间", "上次时间", "两次差值"};
        // DefaultTableModel model = new DefaultTableModel(datas, titles);
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        // model.addRow(new String[]{"test", "sdfsdf", "3"});
        JTable table = new JTable(model);

        JScrollPane jScrollPane = new JScrollPane(table);


        // 1. time label
        demo.add(BorderLayout.NORTH, northPanel);

        // 2. button failed
        // demo.add(centerPanel);

        // 3. table
        demo.add(jScrollPane);
        // southPanel.add(jScrollPane);
        // demo.add(southPanel);

        // 设置可见。
        demo.setVisible(true);
        demo.setLocationRelativeTo(null);


        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        btnCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                count();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

    }

    private static void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                millisecond = millisecond + 10;
                if (millisecond >= 1000) {
                    millisecond = 0;
                    second = second + 1;
                }
                if (second >= 60) {
                    second = 0;
                    minute = minute + 1;
                }

                if (minute >= 60) {
                    minute = 0;
                    hour = hour + 1;
                }

                ms = Integer.toString(millisecond);
                // if (ms.length() < 2) {
                // ms += "0";
                // } else
                if (ms.length() > 2) {
                    ms = ms.substring(0, 2);
                }


                val = hour + ":" + minute + ":" + second + "." + ms;
                labelTime.setText(val);
            }
        }, 0, 10); // 10 ms
    }

    private static void count() {
        model.addRow(new String[]{"test", "sdfsdf", "3"});
    }

    private static void reset() {
        isRunning = false;
        timer.cancel();
        timer = null;
        hour = 0;
        minute = 0;
        second = 0;
        millisecond = 0;//毫秒
        labelTime.setText("0:0:0.00");
        model.setRowCount(0);
    }

}
