// 定义一个包名 com.studentAdmi
package com.studentAdmi;

import javax.swing.*;  // 引入Java Swing库，用于创建图形界面
import java.util.HashMap; // 引入HashMap类，用于存储键值对
import java.util.Map; // 引入Map接口

// 定义主窗口类 MainWindow，继承自 JFrame
class MainWindow extends JFrame {
    static PrimePanel HomePanel; // 静态变量 HomePanel，用于主界面内容显示

    // 构造函数，用于初始化窗口
    MainWindow(){
        super(); // 调用父类构造方法
        this.setSize(800, 500); // 设置窗口大小为 800x500 像素
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.setLayout(null); // 清空布局管理器，使组件自由定位
        HomePanel = new PrimePanel(0, 0, 800, 500); // 初始化主面板，设置位置和大小
        this.getContentPane().add(HomePanel); // 将主面板添加到窗口内容面板
        this.setResizable(false); // 禁止用户调整窗口大小
        this.setLocation(300,150); // 设置窗口的显示位置（屏幕坐标）
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置关闭操作，仅销毁当前窗口
        this.setTitle("学生信息管理系统"); // 设置窗口标题
        this.setVisible(true); // 设置窗口可见
    }
}

// 定义主类 Main
public class Main {
    static MainWindow mainWindow; // 静态变量 mainWindow，表示主窗口
    static ImageIcon icon = new ImageIcon("studentAdmi-master\\src\\com\\resource\\sigh.jpg"); // 静态变量 icon，用于设置窗口图标（路径正确）
    static Map<String , Student> students = new HashMap<String, Student>(); // 使用HashMap存储学生信息，键为String，值为Student对象

    // 私有构造函数，防止实例化 Main 类
    private Main(){}

    // 主方法，程序入口
    public static void main(String[] args) {
        mainWindow = new MainWindow(); // 创建并显示主窗口
    }
}
