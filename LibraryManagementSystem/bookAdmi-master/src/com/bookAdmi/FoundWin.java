package com.bookAdmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FoundWin 类
 * 用于展示某本书的详细信息，包括书名、作者、ISBN、出版社、出版年份和类别。
 * 所有字段显示为不可编辑状态，点击确认按钮可关闭窗口。
 */
public class FoundWin extends JFrame {

    // 定义各类组件
    private MyLabel titleLabel; // 书名标签
    private MyTextFiled titleText; // 书名显示框

    private MyLabel authorLabel; // 作者标签
    private MyTextFiled authorText; // 作者显示框

    private MyLabel isbnLabel; // ISBN 标签
    private MyTextFiled isbnText; // ISBN 显示框

    private MyLabel publisherLabel; // 出版社标签
    private MyTextFiled publisherText; // 出版社显示框

    private MyLabel yearLabel; // 出版年份标签
    private MyTextFiled yearText; // 出版年份显示框

    private MyLabel categoryLabel; // 类别标签
    private MyTextFiled categoryText; // 类别显示框

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
        BackgroundPanel backgroundPanel = new BackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg");

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
        titleLabel = new MyLabel(80, 15, 80, 40, "书名");
        this.add(titleLabel);
        titleText = new MyTextFiled(140, 20, 200, 30);
        titleText.setEditable(false); // 设置为不可编辑
        this.add(titleText);

        // 作者
        authorLabel = new MyLabel(80, 75, 80, 40, "作者");
        this.add(authorLabel);
        authorText = new MyTextFiled(140, 80, 200, 30);
        authorText.setEditable(false);
        this.add(authorText);

        // ISBN
        isbnLabel = new MyLabel(80, 135, 80, 40, "ISBN");
        this.add(isbnLabel);
        isbnText = new MyTextFiled(140, 140, 200, 30);
        isbnText.setEditable(false);
        this.add(isbnText);

        // 出版社
        publisherLabel = new MyLabel(80, 195, 80, 40, "出版社");
        this.add(publisherLabel);
        publisherText = new MyTextFiled(140, 200, 200, 30);
        publisherText.setEditable(false);
        this.add(publisherText);

        // 出版年份
        yearLabel = new MyLabel(80, 255, 80, 40, "出版年份");
        this.add(yearLabel);
        yearText = new MyTextFiled(140, 260, 200, 30);
        yearText.setEditable(false);
        this.add(yearText);

        // 类别
        categoryLabel = new MyLabel(80, 315, 80, 40, "类别");
        this.add(categoryLabel);
        categoryText = new MyTextFiled(140, 320, 200, 30);
        categoryText.setEditable(false);
        this.add(categoryText);
    }
}
