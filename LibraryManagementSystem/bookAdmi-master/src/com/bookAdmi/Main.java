// 定义一个包名 com.studentAdmi
package com.bookAdmi;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

class BaseWindow extends JFrame {

    BaseWindow(boolean isAdmin) {
        super();
        this.setSize(800, 500);
        this.setIconImage(new ImageIcon("bookAdmi-master\\src\\com\\resource\\sigh.jpg").getImage());
        this.setLayout(null);
        // 根据角色设置主面板
        PrimePanel homePanel = new PrimePanel(0, 0, 800, 500, isAdmin);
        this.getContentPane().add(homePanel);
        this.setResizable(false);
        this.setLocation(300, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 根据角色设置窗口标题
        this.setTitle(isAdmin ? "管理员管理系统" : "学生信息管理系统");
        this.setVisible(true);
    }
}

// 管理员窗口
class AdminWindow extends BaseWindow {
    AdminWindow() {
        super(true); // 传递管理员标志
    }
}

// 学生窗口
class StudentWindow extends BaseWindow {
    StudentWindow() {
        super(false); // 传递学生标志
    }
}


// 定义主类 Main
public class Main {
    static AdminWindow adminWindow;  // 主窗口
    static StudentWindow studentWindow;  // 主窗口

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

