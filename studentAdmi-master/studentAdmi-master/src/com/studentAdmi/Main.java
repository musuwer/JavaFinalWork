package com.studentAdmi;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 主类：负责初始化学生信息管理系统
 */
public class Main {
    // 主窗口对象，用于显示用户界面
    static MainWindow mainWindow;

    // 加载图片资源，作为窗口图标
    // 路径正确：指向项目的实际文件夹位置
    static ImageIcon icon = new ImageIcon("studentAdmi-master\\src\\com\\resource\\sigh.jpg");
    // 路径错误：如果工作目录或路径结构不匹配，将无法加载图片
    // static ImageIcon icon = new ImageIcon("src\\com\\studentAdmi\\sigh.jpg");

    // 存储学生信息的哈希表，键为学生编号，值为学生对象
    static Map<String, Student> students = new HashMap<String, Student>();

    // 私有构造函数，防止创建 Main 类的实例
    private Main() {}

    /**
     * 主方法：程序入口
     */
    public static void main(String[] args) {
        // 初始化并显示主窗口
        mainWindow = new MainWindow();
    }
}

/**
 * 主窗口类：继承 JFrame，用于创建主界面
 */
class MainWindow extends JFrame {
    // 主界面内容面板
    static PrimePanel HomePanel;

    /**
     * 构造函数：初始化主窗口的属性和内容
     */
    MainWindow() {
        super();  // 调用父类构造函数

        // 设置窗口大小为 800x500 像素
        this.setSize(800, 500);

        // 设置窗口的图标为之前加载的 ImageIcon 对象
        this.setIconImage(Main.icon.getImage());

        // 清空布局管理器，自定义组件布局
        this.setLayout(null);

        // 初始化主界面内容面板，并设置大小和位置
        HomePanel = new PrimePanel(0, 0, 800, 500);
        this.getContentPane().add(HomePanel);  // 将内容面板添加到窗口中

        // 禁止调整窗口大小
        this.setResizable(false);

        // 设置窗口在屏幕上的初始位置
        this.setLocation(300, 150);

        // 设置关闭窗口时的操作：仅关闭当前窗口而不退出程序
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 设置窗口标题
        this.setTitle("学生信息管理系统");

        // 显示窗口
        this.setVisible(true);
    }
}
