package com.bookAdmi;

import javax.swing.*;
import java.io.*;

public class LoginWin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    // 定义下拉菜单用于选择角色，使用字符串数组初始化选项
    private JComboBox<String> roleComboBox;
    static ImageIcon icon = new ImageIcon("bookAdmi-master\\src\\com\\resource\\sigh.jpg"); // 窗口图标

    public LoginWin() {
        super("学生信息管理系统 - 登录");

        // 设置窗口大小和关闭行为
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocation(800, 400);

        this.setResizable(false);
        this.setIconImage(icon.getImage()); // 设置窗口图标

        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon("bookAdmi-master\\src\\com\\resource\\background.jpg"));
        background.setBounds(0, 0, 400, 300);
        this.getContentPane().add(background);

        // 初始化组件
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        // 初始化下拉菜单并设置选项
        String[] roles = {"管理员", "学生"};
        roleComboBox = new JComboBox<>(new DefaultComboBoxModel<>(roles));

        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");

        // 设置组件位置
        usernameField.setBounds(120, 50, 150, 25);
        passwordField.setBounds(120, 90, 150, 25);
        roleComboBox.setBounds(120, 130, 160, 25);
        loginButton.setBounds(90, 180, 80, 25);
        registerButton.setBounds(200, 180, 80, 25);

        // 添加组件到背景上
        background.setLayout(null);
        background.add(new JLabel("用户名:")).setBounds(60, 50, 50, 25);
        background.add(usernameField);
        background.add(new JLabel("密码:")).setBounds(60, 90, 50, 25);
        background.add(passwordField);
        background.add(roleComboBox);
        background.add(loginButton);
        background.add(registerButton);

        // 登录按钮的事件处理
        loginButton.addActionListener(e -> handleLogin());

        // 注册按钮的事件处理
        registerButton.addActionListener(e -> handleRegister());

        // 显示窗口
        this.setVisible(true);
    }

    // 登录逻辑
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // 从下拉菜单获取选择的角色文本
        String selectedRole = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入用户名和密码", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("bookAdmi-master\\src\\com\\resource\\users.txt"))) {
            String line;
            int LoginTag = 0; //判断账号密码是否输入正确
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split("--");
                if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(password)) {
                    LoginTag = 1;
                    // 根据下拉菜单选择的角色文本进行验证
                    if ("管理员".equals(selectedRole) && "admin".equals(parts[2])) {
                        JOptionPane.showMessageDialog(this, "管理员登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        Main.adminWindow = new AdminWindow();
                        return;
                    }
                    if ("学生".equals(selectedRole) && "student".equals(parts[2])) {
                        JOptionPane.showMessageDialog(this, "学生登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        Main.studentWindow = new StudentWindow();
                        return;
                    }
                }
            }
            // 登录失败处理
            if (LoginTag == 0) {
                JOptionPane.showMessageDialog(this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "用户数据加载失败", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 注册逻辑
    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // 从下拉菜单获取选择的角色文本
        String selectedRole = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入用户名和密码", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String role = "student";
        if ("管理员".equals(selectedRole)) {
            role = "admin";
        }
        String userEntry = username + "--" + password + "--" + role;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookAdmi-master\\src\\com\\resource\\users.txt", true))) {
            writer.write(userEntry);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "注册失败", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }
}