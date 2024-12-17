// 定义一个包名 com.studentAdmi
package com.bookAdmi;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

class MainWindow extends JFrame {
    static PrimePanel HomePanel;

    MainWindow() {
        super();
        this.setSize(800, 500);
        this.setIconImage(new ImageIcon("bookAdmi-master\\src\\com\\resource\\sigh.jpg").getImage());
        this.setLayout(null);
        HomePanel = new PrimePanel(0, 0, 800, 500);
        this.getContentPane().add(HomePanel);
        this.setResizable(false);
        this.setLocation(300, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("学生信息管理系统");
        this.setVisible(true);
    }
}

// 定义主类 Main
public class Main {
    static MainWindow mainWindow;  // 主窗口
    static ImageIcon icon = new ImageIcon("bookAdmi-master\\src\\com\\resource\\sigh.jpg"); // 窗口图标
    static Map<String, Book> books = new HashMap<String, Book>();  // 书籍集合，使用 ISBN 作为唯一标识符

    // 私有构造方法，防止实例化 Main 类
    private Main(){}

    // 主方法，程序入口
    public static void main(String[] args) {
        new LoginWin(); // 从登录窗口开始程序
        //mainWindow = new MainWindow();  // 创建并显示主窗口
    }
}

