package com.bookAdmi;

import javax.swing.*; // 导入 Swing 库
import java.awt.*; // 导入 AWT 库
import java.awt.event.ActionEvent; // 导入事件类
import java.awt.event.ActionListener; // 导入事件监听器接口

// 定义 FindWin 类，继承 JFrame，用于查找图书信息
public class FindWin extends JFrame {
    private MyLabel titleLabel; // 书名标签
    private MyTextFiled titleText; // 书名输入框

    private MyLabel isbnLabel; // ISBN 标签
    private MyTextFiled isbnText; // ISBN 输入框

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
        this.setTitle("查找书籍信息"); // 设置窗口标题
        this.getContentPane().setBackground(Color.pink); // 设置背景颜色
        this.setVisible(true); // 显示窗口

        this.addPart(); // 添加组件
        this.addListen(); // 添加监听器
    }

    // 添加组件
    private void addPart() {
        // 书名标签和输入框
        titleLabel = new MyLabel(80, 15, 80, 40, "书名");
        this.add(titleLabel);

        titleText = new MyTextFiled(140, 20, 200, 30);
        this.add(titleText);

        // ISBN 标签和输入框
        isbnLabel = new MyLabel(80, 65, 80, 40, "ISBN");
        this.add(isbnLabel);

        isbnText = new MyTextFiled(140, 70, 200, 30);
        this.add(isbnText);

        // 确认按钮
        OKButton = new MyButton(40, 200, 80, 40, "确认");
        this.add(OKButton);

        // 清空按钮
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
            String isbn = isbnText.getText(); // 获取输入的 ISBN
            String title = titleText.getText(); // 获取输入的书名

            // 判断图书信息是否匹配
            if (Main.books.containsKey(isbn) && Main.books.get(isbn).getTitle().equals(title)) {
                // 打开 FoundWin 窗口显示图书详细信息
                new FoundWin(
                        Main.books.get(isbn).getTitle(),
                        Main.books.get(isbn).getAuthor(),
                        Main.books.get(isbn).getIsbn(),
                        Main.books.get(isbn).getPublisher(),
                        Main.books.get(isbn).getYear(),
                        Main.books.get(isbn).getCategory()
                );
            } else {
                // 信息不匹配时弹出警告框
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
