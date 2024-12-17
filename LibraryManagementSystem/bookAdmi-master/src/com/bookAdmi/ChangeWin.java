package com.bookAdmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 定义 ChangeWin 类，继承自 JFrame
public class ChangeWin extends JFrame {
    private MyLabel titleLabel; // 书名标签
    private MyTextFiled titleText; // 书名输入框

    private MyLabel isbnLabel; // ISBN 标签
    private MyTextFiled isbnText; // ISBN 输入框

    private MyButton OKButton; // 确认按钮
    private MyButton clearButton; // 清空按钮

    // 构造方法，初始化窗口和组件
    ChangeWin() {
        super(); // 调用父类构造方法
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.setSize(500, 300); // 设置窗口大小
        this.setLayout(null); // 清空布局器
        this.setResizable(false); // 禁止调整窗口大小
        this.setLocation(400, 200); // 设置窗口生成位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 仅销毁当前窗口
        this.setTitle("请输入书名和 ISBN"); // 设置窗口标题
        this.setContentPane(new BackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg"));
        this.setLayout(null); // 设置布局为手动布局
        this.setVisible(true); // 显示窗口

        this.addPart(); // 添加组件
        this.addListen(); // 添加监听器

        // 监听鼠标点击位置
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 获取鼠标点击的坐标
                int x = e.getX();
                int y = e.getY();
                // 打印坐标
                System.out.println("Mouse clicked at: x = " + x + ", y = " + y);
            }
        });

    }

    // 添加组件的方法
    private void addPart() {
        // 初始化书名标签和输入框
        titleLabel = new MyLabel(80, 15, 80, 40, "书名");
        this.add(titleLabel);

        titleText = new MyTextFiled(140, 20, 200, 30);
        this.add(titleText);

        // 初始化 ISBN 标签和输入框
        isbnLabel = new MyLabel(80, 65, 80, 40, "ISBN");
        this.add(isbnLabel);

        isbnText = new MyTextFiled(140, 70, 200, 30);
        this.add(isbnText);

        // 初始化按钮
        OKButton = new MyButton(40, 200, 80, 40, "确认");
        this.add(OKButton);

        clearButton = new MyButton(360, 200, 80, 40, "清空");
        this.add(clearButton);
    }

    // 添加按钮的监听器
    private void addListen() {
        OKButton.addActionListener(new OKListener()); // 确认按钮监听
        clearButton.addActionListener(new ClearListener()); // 清空按钮监听
    }

    // 确认按钮的监听器类
    private class OKListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String isbn = isbnText.getText(); // 获取 ISBN
            String title = titleText.getText(); // 获取书名

            // 校验图书信息是否存在
            if (Main.books.containsKey(isbn) && Main.books.get(isbn).getTitle().equals(title)) {
                // 打开修改窗口
                new ChangedWin(
                        Main.books.get(isbn).getTitle(),
                        Main.books.get(isbn).getAuthor(),
                        Main.books.get(isbn).getIsbn(),
                        Main.books.get(isbn).getPublisher(),
                        Main.books.get(isbn).getYear(),
                        Main.books.get(isbn).getCategory()
                );
            } else {
                // 弹出提示框
                JOptionPane.showMessageDialog(null, "没有该 ISBN 的书籍或书名与 ISBN 不匹配", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // 清空按钮的监听器类
    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            titleText.setText(""); // 清空书名输入框
            isbnText.setText(""); // 清空 ISBN 输入框
        }
    }
}
