package com.bookAdmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JRadioButton adminRadioButton;
    private JRadioButton studentRadioButton;

    public LoginWin() {
        super("学生信息管理系统 - 登录");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 2));
        this.setLocation(800, 400);

        // 初始化组件
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        ButtonGroup roleGroup = new ButtonGroup();
        adminRadioButton = new JRadioButton("管理员");
        studentRadioButton = new JRadioButton("学生");

        roleGroup.add(adminRadioButton);
        roleGroup.add(studentRadioButton);

        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");

        // 添加组件到窗体
        this.add(new JLabel("       用户名:"));
        this.add(usernameField);
        this.add(new JLabel("       密码:"));
        this.add(passwordField);
        this.add(adminRadioButton);
        this.add(studentRadioButton);
        this.add(loginButton);
        this.add(registerButton);

        // 登录按钮的事件处理
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // 显示窗体
        this.setVisible(true);
    }

    // 登录处理逻辑
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean isAdmin = adminRadioButton.isSelected();
        boolean isStudent = studentRadioButton.isSelected();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入用户名和密码", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isAdmin && !isStudent) {
            JOptionPane.showMessageDialog(this, "请选择一种角色", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 验证登录
        if ("admin".equals(username) && "123456".equals(password) && isAdmin) {
            this.dispose(); // 关闭登录窗口
            Main.mainWindow = new MainWindow();  // 创建并显示主窗口; // 打开主窗口
        } else if ("student".equals(username) && "123456".equals(password) && isStudent) {
            this.dispose();
            Main.mainWindow = new MainWindow();  // 创建并显示主窗口; // 打开主窗口
        } else {
            JOptionPane.showMessageDialog(this, "用户名或密码错误", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }
}
