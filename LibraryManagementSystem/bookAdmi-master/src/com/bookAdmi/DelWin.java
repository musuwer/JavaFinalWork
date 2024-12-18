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
    DelWin(){
        super();
        this.setSize(500, 300); // 设置窗口大小
        this.setLayout(null); // 使用绝对布局
        this.setResizable(false); // 禁止调整窗口大小
        this.setLocation(400, 200); // 设置窗口位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置窗口关闭操作
        this.setTitle("删除书籍信息"); // 设置窗口标题

        this.setContentPane(new MyBackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg"));

        // 添加组件到窗口
        this.addPart();

        this.setVisible(true); // 显示窗口

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
            int startNum = Main.books.size(); // 获取初始 books 集合大小
            String isbn = isbnText.getText(); // 获取输入的 ISBN
            String title = titleText.getText(); // 获取输入的书名

            // 判断图书信息是否匹配
            if (Main.books.containsKey(isbn) && Main.books.get(isbn).getTitle().equals(title)) {
                Main.books.remove(isbn); // 删除图书信息

                // 验证删除是否成功
                if ((Main.books.size() == startNum - 1) && (!Main.books.containsKey(isbn))) {
                    JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.WARNING_MESSAGE); // 弹出成功提示框
                    PrimePanel.refreshFile(); // 刷新文件
                }
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
