package com.studentAdmi;

import javax.swing.*; // 导入 Swing 库
import java.awt.*; // 导入 AWT 库
import java.awt.event.ActionEvent; // 导入事件类
import java.awt.event.ActionListener; // 导入事件监听器接口

// 定义 FindWin 类，继承 JFrame，用于查找学生信息
public class FindWin extends JFrame {
    private MyLabel nameLabel; // 姓名标签
    private MyTextFiled nameText; // 姓名输入框

    private MyLabel codeLabel; // 学号标签
    private MyTextFiled codeText; // 学号输入框

    private MyButton OKButton; // 确认按钮
    private MyButton clearButton; // 清空按钮

    // 构造方法，初始化窗口和组件
    FindWin() {
        super();
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.setSize(500, 300); // 设置窗口大小
        this.setLayout(null); // 清空布局管理器
        this.setResizable(false); // 禁止调整窗口大小
        this.setLocation(400, 200); // 设置窗口位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置关闭操作
        this.setTitle("查找学生信息"); // 设置窗口标题
        this.getContentPane().setBackground(Color.pink); // 设置背景颜色
        this.setVisible(true); // 显示窗口

        this.addPart(); // 添加组件
        this.addListen(); // 添加监听器
    }

    // 添加组件
    private void addPart() {
        nameLabel = new MyLabel(80, 15, 80, 40, "姓名");
        this.add(nameLabel);

        nameText = new MyTextFiled(140, 20, 200, 30);
        this.add(nameText);

        codeLabel = new MyLabel(80, 65, 80, 40, "学号");
        this.add(codeLabel);

        codeText = new MyTextFiled(140, 70, 200, 30);
        this.add(codeText);

        OKButton = new MyButton(40, 200, 80, 40, "确认");
        this.add(OKButton);

        clearButton = new MyButton(360, 200, 80, 40, "清空");
        this.add(clearButton);
    }

    // 添加按钮监听器
    private void addListen() {
        OKButton.addActionListener(new OKListener());
        clearButton.addActionListener(new ClearListener());
    }

    // 确认按钮的监听器类
    private class OKListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = codeText.getText(); // 获取输入的学号
            String name = nameText.getText(); // 获取输入的姓名

            // 判断学生信息是否匹配
            if (Main.students.containsKey(code) && Main.students.get(code).getName().equals(name)) {
                // 打开 FoundWin 窗口显示学生详细信息
                new FoundWin(
                        Main.students.get(code).getName(),
                        Main.students.get(code).getSex(),
                        Main.students.get(code).getPlace(),
                        Main.students.get(code).getCode(),
                        Main.students.get(code).getDept(),
                        Main.students.get(code).getBan()
                );
            } else {
                // 信息不匹配时弹出警告框
                JOptionPane.showMessageDialog(null, "没有该学号信息或名字与学号不匹配", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // 清空按钮的监听器类
    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameText.setText(""); // 清空姓名输入框
            codeText.setText(""); // 清空学号输入框
        }
    }
}
