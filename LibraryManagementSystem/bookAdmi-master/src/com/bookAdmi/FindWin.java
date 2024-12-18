package com.bookAdmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 定义 FindWin 类，继承 JFrame，用于查找图书信息
public class FindWin extends JFrame {
    private MyTools.MyLabel titleLabel; // 书名标签
    private MyTools.MyTextFiled titleText; // 书名输入框

    private MyTools.MyLabel isbnLabel; // ISBN 标签
    private MyTools.MyTextFiled isbnText; // ISBN 输入框

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
        this.setContentPane(new MyBackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg"));

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

    // 添加组件
    private void addPart() {
        // 书名标签和输入框
        titleLabel = new MyTools.MyLabel(80, 15, 80, 40, "书名");
        this.add(titleLabel);

        titleText = new MyTools.MyTextFiled(140, 20, 200, 30);
        this.add(titleText);

        // ISBN 标签和输入框
        isbnLabel = new MyTools.MyLabel(80, 65, 80, 40, "ISBN");
        this.add(isbnLabel);

        isbnText = new MyTools.MyTextFiled(140, 70, 200, 30);
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

    /**
     * FoundWin 类
     * 用于展示某本书的详细信息，包括书名、作者、ISBN、出版社、出版年份和类别。
     * 所有字段显示为不可编辑状态，点击确认按钮可关闭窗口。
     */
    public static class FoundWin extends JFrame {

        // 定义各类组件
        private MyTools.MyLabel titleLabel; // 书名标签
        private MyTools.MyTextFiled titleText; // 书名显示框

        private MyTools.MyLabel authorLabel; // 作者标签
        private MyTools.MyTextFiled authorText; // 作者显示框

        private MyTools.MyLabel isbnLabel; // ISBN 标签
        private MyTools.MyTextFiled isbnText; // ISBN 显示框

        private MyTools.MyLabel publisherLabel; // 出版社标签
        private MyTools.MyTextFiled publisherText; // 出版社显示框

        private MyTools.MyLabel yearLabel; // 出版年份标签
        private MyTools.MyTextFiled yearText; // 出版年份显示框

        private MyTools.MyLabel categoryLabel; // 类别标签
        private MyTools.MyTextFiled categoryText; // 类别显示框

        private MyButton OKButton; // 确认按钮，用于关闭窗口

        public FoundWin(String title, String author, String isbn, String publisher, int year, String category) {
            super();

            // 设置窗口属性
            this.setIconImage(Main.icon.getImage()); // 设置窗口图标
            this.setSize(500, 600); // 设置窗口大小
            this.setLayout(null); // 使用空布局，手动设置组件位置
            this.setResizable(false); // 禁止调整窗口大小
            this.setVisible(true); // 设置窗口可见
            this.setLocation(450, 200); // 设置窗口显示位置
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口时释放资源
            this.setTitle("查看书籍信息"); // 设置窗口标题
            this.getContentPane().setBackground(Color.pink); // 设置窗口背景颜色为粉色

            // 创建自定义背景面板
            MyBackgroundPanel backgroundPanel = new MyBackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg");

            // 添加组件并设置其初始状态
            this.addPart();

            // 填充书籍信息到对应文本框
            this.titleText.setText(title);
            this.authorText.setText(author);
            this.isbnText.setText(isbn);
            this.publisherText.setText(publisher);
            this.yearText.setText(String.valueOf(year));
            this.categoryText.setText(category);

            // 添加确认按钮
            OKButton = new MyButton(200, 400, 80, 40, "确认");
            this.add(OKButton);

            // 为确认按钮添加点击事件监听器
            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FoundWin.this.dispose(); // 关闭窗口
                }
            });
        }

        /**
         * 添加组件方法
         * 负责初始化各组件（标签和文本框），设置其位置、大小和属性。
         */
        private void addPart() {
            // 书名
            titleLabel = new MyTools.MyLabel(80, 15, 80, 40, "书名");
            this.add(titleLabel);
            titleText = new MyTools.MyTextFiled(140, 20, 200, 30);
            titleText.setEditable(false); // 设置为不可编辑
            this.add(titleText);

            // 作者
            authorLabel = new MyTools.MyLabel(80, 75, 80, 40, "作者");
            this.add(authorLabel);
            authorText = new MyTools.MyTextFiled(140, 80, 200, 30);
            authorText.setEditable(false);
            this.add(authorText);

            // ISBN
            isbnLabel = new MyTools.MyLabel(80, 135, 80, 40, "ISBN");
            this.add(isbnLabel);
            isbnText = new MyTools.MyTextFiled(140, 140, 200, 30);
            isbnText.setEditable(false);
            this.add(isbnText);

            // 出版社
            publisherLabel = new MyTools.MyLabel(80, 195, 80, 40, "出版社");
            this.add(publisherLabel);
            publisherText = new MyTools.MyTextFiled(140, 200, 200, 30);
            publisherText.setEditable(false);
            this.add(publisherText);

            // 出版年份
            yearLabel = new MyTools.MyLabel(80, 255, 80, 40, "出版年份");
            this.add(yearLabel);
            yearText = new MyTools.MyTextFiled(140, 260, 200, 30);
            yearText.setEditable(false);
            this.add(yearText);

            // 类别
            categoryLabel = new MyTools.MyLabel(80, 315, 80, 40, "类别");
            this.add(categoryLabel);
            categoryText = new MyTools.MyTextFiled(140, 320, 200, 30);
            categoryText.setEditable(false);
            this.add(categoryText);
        }
    }
}
