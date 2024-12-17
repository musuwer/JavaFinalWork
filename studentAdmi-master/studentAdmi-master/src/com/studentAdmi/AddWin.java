// 定义包名 com.studentAdmi
package com.studentAdmi;

import javax.swing.*; // 引入 Swing 库用于创建图形界面
import java.awt.*; // 引入 AWT 库用于设置界面颜色等
import java.awt.event.ActionEvent; // 引入 ActionEvent 用于事件处理
import java.awt.event.ActionListener; // 引入 ActionListener 接口用于实现事件监听

// 定义添加学生信息窗口类 AddWin，继承自 JFrame
public class AddWin extends JFrame {
    // 定义标签和文本框
    private MyLabel nameLabel; // 姓名标签
    private MyTextFiled nameText; // 姓名输入框

    private MyLabel sexLabel; // 性别标签
    private MyTextFiled sexText; // 性别输入框

    private MyLabel placeLabel; // 籍贯标签
    private MyTextFiled placeText; // 籍贯输入框

    private MyLabel codeLabel; // 学号标签
    private MyTextFiled codeText; // 学号输入框

    private MyLabel deptLabel; // 系别标签
    private MyTextFiled deptText; // 系别输入框

    private MyLabel banLabel; // 班级标签
    private MyTextFiled banText; // 班级输入框

    private MyButton OKButton; // 确认按钮
    private MyButton clearButton; // 清空按钮

    // 构造方法，初始化窗口
    AddWin(){
        super(); // 调用父类构造方法
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.setSize(500, 600); // 设置窗口大小
        this.setLayout(null); // 清空布局器
        this.setResizable(false); // 禁止用户调整窗口大小
        this.setVisible(true); // 设置窗口可见
        this.setLocation(450, 200); // 设置窗口生成位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置关闭操作，仅销毁当前窗口
        this.setTitle("添加学生信息(不能有空格)"); // 设置窗口标题
        this.getContentPane().setBackground(Color.pink); // 设置背景颜色

        this.addPart(); // 添加组件
        this.addListen(); // 添加事件监听
    }

    // 添加组件到窗口的方法
    private void addPart() {
        // 初始化并添加标签和文本框
        nameLabel = new MyLabel(80, 15, 80, 40, "姓名");
        this.add(nameLabel);
        nameText = new MyTextFiled(140, 20, 200, 30);
        this.add(nameText);

        sexLabel = new MyLabel(80, 75, 80, 40, "性别");
        this.add(sexLabel);
        sexText = new MyTextFiled(140, 80, 200,30);
        this.add(sexText);

        placeLabel = new MyLabel(80, 135, 80, 40, "籍贯");
        this.add(placeLabel);
        placeText = new MyTextFiled(140, 140, 200, 30);
        this.add(placeText);

        codeLabel = new MyLabel(80, 195, 80, 40, "学号");
        this.add(codeLabel);
        codeText = new MyTextFiled(140, 200, 200, 30);
        this.add(codeText);

        deptLabel = new MyLabel(80, 255, 80, 40, "系别");
        this.add(deptLabel);
        deptText = new MyTextFiled(140, 260, 200,30);
        this.add(deptText);

        banLabel = new MyLabel(80, 315, 80, 40, "班级");
        this.add(banLabel);
        banText = new MyTextFiled(140, 320, 200, 30);
        this.add(banText);

        // 初始化并添加按钮
        OKButton = new MyButton(40, 420, 80, 40, "确认");
        this.add(OKButton);
        clearButton = new MyButton(360, 420, 80, 40, "清空");
        this.add(clearButton);
    }

    // 添加事件监听的方法
    private void addListen() {
        OKButton.addActionListener(new OKListener()); // 确认按钮事件
        clearButton.addActionListener(new clearListener()); // 清空按钮事件
    }

    // 内部类，用于监听确认按钮
    private class OKListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 校验输入框内容是否为空或包含非法空格
            if (nameText.getText().length() == 0 || nameText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "名字未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
            } else if (sexText.getText().length() == 0 || sexText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "性别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
            } else if (placeText.getText().length() == 0 || placeText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "籍贯未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
            } else if (codeText.getText().length() == 0 || codeText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "学号未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
            } else if (deptText.getText().length() == 0 || deptText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "系别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
            } else if (banText.getText().length() == 0 || banText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "班别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
            } else if (sexText.getText().equals("男") || sexText.getText().equals("女")) {
                int startNum = Main.students.size(); // 获取当前学生集合大小
                if (!Main.students.containsKey(codeText.getText())) { // 判断学号是否已存在
                    // 添加新学生到学生集合
                    Main.students.put(codeText.getText(), new Student(nameText.getText(), sexText.getText(),
                            placeText.getText(), codeText.getText(), deptText.getText(), banText.getText()));
                    // 检查是否成功添加
                    if (Main.students.size() == startNum + 1) {
                        JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.WARNING_MESSAGE);
                        PrimePanel.refreshFile(); // 刷新文件
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "该学号信息已存在！", "提示", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确的性别", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // 内部类，用于监听清空按钮
    private class clearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 清空所有输入框内容
            nameText.setText("");
            sexText.setText("");
            placeText.setText("");
            deptText.setText("");
            codeText.setText("");
            banText.setText("");
        }
    }
}
