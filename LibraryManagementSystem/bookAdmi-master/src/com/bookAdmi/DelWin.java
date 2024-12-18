package com.bookAdmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 定义 DelWin 类，继承 JFrame，用于通过书名和 ISBN 删除图书信息
public class DelWin extends JFrame {
    private MyTools.MyLabel titleLabel; // 书名标签
    private MyTools.MyTextFiled titleText; // 书名输入框

    private MyTools.MyLabel isbnLabel; // ISBN 标签
    private MyTools.MyTextFiled isbnText; // ISBN 输入框

    private MyButton OKButton; // 确认按钮
    private MyButton clearButton; // 清空按钮

    // 构造方法，初始化窗口和组件
    DelWin() {
        super();
        this.setSize(500, 300); // 设置窗口大小
        this.setLayout(null); // 使用绝对布局
        this.setResizable(false); // 禁止调整窗口大小
        this.setLocation(400, 200); // 设置窗口位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置窗口关闭操作
        this.setTitle("删除书籍信息"); // 设置窗口标题

        // 设置背景面板
        this.setContentPane(new MyBackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg"));

        // 添加组件到窗口
        this.addPart();
        this.addListen(); // 添加监听器

        this.setVisible(true); // 显示窗口

        // 监听鼠标点击位置（调试用）
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked at: x = " + e.getX() + ", y = " + e.getY());
            }
        });
    }

    // 添加组件
    private void addPart() {
        titleLabel = new MyTools.MyLabel(80, 15, 80, 40, "书名");
        this.add(titleLabel);

        titleText = new MyTools.MyTextFiled(140, 20, 200, 30);
        this.add(titleText);

        isbnLabel = new MyTools.MyLabel(80, 65, 80, 40, "ISBN");
        this.add(isbnLabel);

        isbnText = new MyTools.MyTextFiled(140, 70, 200, 30);
        this.add(isbnText);

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
            // 获取输入的书名和 ISBN
            String bookTitle = titleText.getText().trim();
            String bookISBN = isbnText.getText().trim();

            // 校验输入是否为空
            if (bookTitle.isEmpty() || bookISBN.isEmpty()) {
                JOptionPane.showMessageDialog(null, "书名和 ISBN 不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 判断 books 中是否存在对应 ISBN 的记录，并校验书名是否一致
            if (Main.books.containsKey(bookISBN) && Main.books.get(bookISBN).getTitle().equals(bookTitle)) {
                // 删除图书记录
                Main.books.remove(bookISBN);

                // 删除成功后刷新文件
                JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                PrimePanel.refreshFile();
            } else {
                // ISBN 不存在或书名不匹配
                JOptionPane.showMessageDialog(null, "没有该书籍信息或书名与 ISBN 不匹配", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // 清空按钮的监听器类
    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 清空书名和 ISBN 输入框
            titleText.setText("");
            isbnText.setText("");
            JOptionPane.showMessageDialog(null, "输入框已清空！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
