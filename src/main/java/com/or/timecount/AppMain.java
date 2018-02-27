package com.or.timecount;

import com.or.timecount.view.MainView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

public class AppMain {
    // 定时器
    private static Timer timer = null;

    // 表格组件内容
    private static String[][] tableData = {};
    private static String[] titles = {"No.", "计时时间", "两次差值"};
    private static DefaultTableModel model = new DefaultTableModel(tableData, titles);
    private static ArrayList<String> list = new ArrayList<String>();
    private static JTable table = new JTable(model);

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

        // String[][] tableData = {};
        // String[] titles = {"计时时间", "上次时间", "两次差值"};
        // DefaultTableModel model = new DefaultTableModel(tableData, titles);
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
        // JTable table = new JTable(model);

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
                } else if (ms.length() == 1) {
                    ms = ("0" + ms);
                }


                val = hour + ":" + minute + ":" + second + "." + ms;
                labelTime.setText(val);
            }
        }, 0, 10); // 10 ms
    }

    private static void count() {
        if (!isRunning) {
            return;
        }
        // 每按一次计算，往list里面塞一个时间字符串
        list.add(val);
        System.out.println("val : "+val);

        // 第一次按，所以记录时间
        if (list.size() < 2) {
            model.addRow(new String[]{Integer.toString(list.size()), list.get(0), list.get(0)});
        } else {
            String[] newTimeArr = list.get(list.size() - 1).split(":");
            String[] lastTimeArr = list.get(list.size() - 2).split(":");

            String[] oldSAndMS = lastTimeArr[2].split("\\.");
            String[] newSAndMS = newTimeArr[2].split("\\.");

            int lastTime = (parseInt(lastTimeArr[0]) * 3600000)
                    + (parseInt(lastTimeArr[1]) * 60000)
                    + (parseInt(oldSAndMS[0]) * 1000)
                    + parseInt(oldSAndMS[1] + "0");
            int newTime = (parseInt(newTimeArr[0]) * 3600000)
                    + (parseInt(newTimeArr[1]) * 60000)
                    + (parseInt(newSAndMS[0]) * 1000)
                    + parseInt(newSAndMS[1] + "0");

            int hh = ((newTime - lastTime) / 3600000);
            int mm = ((newTime - lastTime) / 60000);
            int ss = ((newTime - lastTime) / 1000);
            int ms = (newTime - lastTime) % 1000;
            // int hh = parseInt(newTimeArr[0]) - parseInt(lastTimeArr[0]);
            // int mm = parseInt(newTimeArr[1]) - parseInt(lastTimeArr[1]);
            // int ss = parseInt(newSAndMS[0]) - parseInt(oldSAndMS[0]);
            // int ms = parseInt(newSAndMS[1]) - parseInt(oldSAndMS[1]);
            //
            // if (ms < 0) {
            //     String lastSAndMs = lastTimeArr[2];
            //     String nextSAndMs = newTimeArr[2];
            //     String x = Float.toString(Float.parseFloat(nextSAndMs) - Float.parseFloat(lastSAndMs)).substring(2, 4);
            //     ms = parseInt(x.replace(".", ""));
            // }

            String pms;
            if (Integer.toString(ms).length() == 1) {
                pms = "0" + ms;
            } else {
                pms = (ms + "").substring(0, 2);
            }
            String str = hh + ":" + mm + ":" + ss + "." + pms;
            model.addRow(new String[]{
                    Integer.toString(list.size()),
                    list.get(list.size() - 1),
                    str});
        }

    }

    private static void reset() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        list.clear();
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
