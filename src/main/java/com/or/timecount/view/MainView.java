package com.or.timecount.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
}
