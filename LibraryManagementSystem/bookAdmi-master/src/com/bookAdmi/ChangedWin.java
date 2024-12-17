package com.bookAdmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangedWin extends JFrame {
    // 定义标签和文本框
    private MyLabel titleLabel; // 书名标签
    private MyTextFiled titleText; // 书名输入框

    private MyLabel authorLabel; // 作者标签
    private MyTextFiled authorText; // 作者输入框

    private MyLabel isbnLabel; // ISBN 标签
    private MyTextFiled isbnText; // ISBN 输入框

    private MyLabel publisherLabel; // 出版社标签
    private MyTextFiled publisherText; // 出版社输入框

    private MyLabel yearLabel; // 出版年份标签
    private MyTextFiled yearText; // 出版年份输入框

    private MyLabel categoryLabel; // 类别标签
    private MyTextFiled categoryText; // 类别输入框

    private MyButton OKButton; // 确认按钮

    // 构造方法，初始化窗口和组件
    public ChangedWin(String title, String author, String isbn, String publisher, int year, String category) {
        super(); // 调用父类构造方法
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.setSize(500, 600); // 设置窗口大小
        this.setLayout(null); // 清空布局器
        this.setResizable(false); // 禁止用户调整窗口大小
        this.setVisible(true); // 设置窗口可见
        this.setLocation(450, 200); // 设置窗口生成位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置关闭操作，仅销毁当前窗口
        this.setTitle("修改书籍信息"); // 设置窗口标题

        this.setContentPane(new BackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg"));
        this.setLayout(null); // 设置布局为手动布局

        this.addPart(); // 添加组件



        // 设置初始值
        this.titleText.setText(title);
        this.authorText.setText(author);
        this.isbnText.setText(isbn); // ISBN 设置为不可编辑
        this.publisherText.setText(publisher);
        this.yearText.setText(String.valueOf(year));
        this.categoryText.setText(category);

        // 初始化确认按钮
        OKButton = new MyButton(200, 400, 80, 40, "确认");
        this.add(OKButton);

        // 添加确认按钮的监听器
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 校验输入框内容是否为空或包含非法空格
                if (titleText.getText().length() == 0 || titleText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "书名未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (authorText.getText().length() == 0 || authorText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "作者未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (isbnText.getText().length() == 0 || isbnText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "ISBN未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (publisherText.getText().length() == 0 || publisherText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "出版社未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (yearText.getText().length() == 0 || yearText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "出版年份未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (categoryText.getText().length() == 0 || categoryText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "书籍类别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 修改书籍信息
                    Main.books.get(isbnText.getText()).setTitle(titleText.getText());
                    Main.books.get(isbnText.getText()).setAuthor(authorText.getText());
                    Main.books.get(isbnText.getText()).setPublisher(publisherText.getText());
                    Main.books.get(isbnText.getText()).setYear(Integer.parseInt(yearText.getText()));
                    Main.books.get(isbnText.getText()).setCategory(categoryText.getText());

                    JOptionPane.showMessageDialog(null, "修改完成！", "提示", JOptionPane.WARNING_MESSAGE);
                    PrimePanel.refreshFile(); // 刷新文件
                }
            }
        });
    }

    // 添加组件的方法
    private void addPart() {
        // 初始化并添加标签和文本框
        titleLabel = new MyLabel(80, 15, 80, 40, "书名");
        this.add(titleLabel);
        titleText = new MyTextFiled(140, 20, 200, 30);
        this.add(titleText);

        authorLabel = new MyLabel(80, 75, 80, 40, "作者");
        this.add(authorLabel);
        authorText = new MyTextFiled(140, 80, 200, 30);
        this.add(authorText);

        isbnLabel = new MyLabel(80, 135, 80, 40, "ISBN");
        this.add(isbnLabel);
        isbnText = new MyTextFiled(140, 140, 200, 30);
        isbnText.setEditable(false); // ISBN 不可编辑
        this.add(isbnText);

        publisherLabel = new MyLabel(80, 195, 80, 40, "出版社");
        this.add(publisherLabel);
        publisherText = new MyTextFiled(140, 200, 200, 30);
        this.add(publisherText);

        yearLabel = new MyLabel(80, 255, 80, 40, "出版年份");
        this.add(yearLabel);
        yearText = new MyTextFiled(140, 260, 200, 30);
        this.add(yearText);

        categoryLabel = new MyLabel(80, 315, 80, 40, "书籍类别");
        this.add(categoryLabel);
        categoryText = new MyTextFiled(140, 320, 200, 30);
        this.add(categoryText);
    }
}
