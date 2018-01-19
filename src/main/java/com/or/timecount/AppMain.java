package com.or.timecount;

import com.or.timecount.view.MainView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Random;

public class AppMain {

    public static void main(String[] args) {
        // 文字标题
        MainView demo = new MainView("");

        // 背景颜色
        // demo.setBackground(Color.BLUE);

        // 大小
        demo.setSize(300, 200);

        Label labelTime = new Label("0:0:0.00");
        Button btnStart = new Button("start");
        Button btnCount = new Button("count");
        Button btnReset = new Button("reset");

        // demo.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        demo.setLayout(new BorderLayout());

        final Panel northPanel = new Panel();
        final JScrollPane southPanel = new JScrollPane();
        Panel centerPanel = new Panel();

        northPanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new FlowLayout());
        // southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));

        demo.add(BorderLayout.NORTH, northPanel);
        demo.add(BorderLayout.CENTER, centerPanel);
        demo.add(BorderLayout.AFTER_LAST_LINE, southPanel);


        northPanel.add(labelTime);
        centerPanel.add(btnStart);
        centerPanel.add(btnCount);
        centerPanel.add(btnReset);

        String[][] datas = {};
        String[] titles = { "列一", "列二" };
        DefaultTableModel model = new DefaultTableModel(datas, titles);
        model.addRow(new String[] { "test", "sdfsdf" });
        model.addRow(new String[] { "test", "sdfsdf" });
        model.addRow(new String[] { "test", "sdfsdf" });
        model.addRow(new String[] { "test", "sdfsdf" });
        model.addRow(new String[] { "test", "sdfsdf" });
        model.addRow(new String[] { "test", "sdfsdf" });
        JTable table = new JTable(model);

        southPanel.add(table);






        // 设置可见。
        demo.setVisible(true);
        demo.setLocationRelativeTo(null);


        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                southPanel.add(new Label("hello"));
                northPanel.add(new Label("hello"));
                System.out.println("hello");
            }
        });

    }

    private static String getRandomData()
    {
        String source = "0123456789abcdefghijklmnopqrstuvwxyz";
        int len = source.length();
        Random random = new Random(System.currentTimeMillis());
        return MessageFormat.format("{0}{0}{0}", source.charAt(random.nextInt(len)));
    }

}
