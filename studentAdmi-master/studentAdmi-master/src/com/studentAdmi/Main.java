package com.studentAdmi;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

// Main 类：程序的入口类，包含 main 方法，负责初始化应用程序的主界面
public class Main {
    static MainWindow mainWindow;  // 主窗口对象
    // 设置窗口图标，图片路径为绝对路径
    static ImageIcon icon = new ImageIcon("studentAdmi-master\\src\\com\\resource\\sigh.jpg");  // 路径正确
    // static ImageIcon icon = new ImageIcon("src\\com\\studentAdmi\\sigh.jpg"); // 路径错误，示例注释
    static Map<String, Student> students = new HashMap<String, Student>();  // 存储学生信息的集合，键为学生ID，值为学生对象
    private Main() {}  // 私有构造方法，防止直接实例化 Main 类

    // main 方法，程序的入口
    public static void main(String[] args) {
        mainWindow = new MainWindow();  // 创建并显示主窗口
    }
}

// MainWindow 类：继承自 JFrame，用于创建程序的主窗口
class MainWindow extends JFrame {
    static PrimePanel HomePanel;  // 主面板对象

    // 构造方法：初始化主窗口
    MainWindow() {
        super();  // 调用父类 JFrame 的构造方法
        this.setSize(800, 500);  // 设置窗口的大小
        this.setIconImage(Main.icon.getImage());  // 设置窗口图标
        this.setLayout(null);  // 清空布局管理器，手动管理组件的位置和大小
        HomePanel = new PrimePanel(0, 0, 800, 500);  // 创建并初始化主面板，大小为800x500
        this.getContentPane().add(HomePanel);  // 将主面板添加到窗口的内容面板中
        this.setResizable(false);  // 禁止窗口大小调整
        this.setLocation(300, 150);  // 设置窗口的位置（x=300, y=150）
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 设置关闭窗口时释放资源
        this.setTitle("学生信息管理系统");  // 设置窗口标题
        this.setVisible(true);  // 设置窗口可见
    }
}
