package com.bookAdmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddWin extends JFrame {
    private MyLabel titleLabel;  // 书名标签
    private MyTextFiled titleText;  // 书名输入框

    private MyLabel authorLabel;  // 作者标签
    private MyTextFiled authorText;  // 作者输入框

    private MyLabel isbnLabel;  // ISBN标签
    private MyTextFiled isbnText;  // ISBN输入框

    private MyLabel publisherLabel;  // 出版社标签
    private MyTextFiled publisherText;  // 出版社输入框

    private MyLabel yearLabel;  // 出版年份标签
    private MyTextFiled yearText;  // 出版年份输入框

    private MyLabel categoryLabel;  // 书籍类别标签
    private MyTextFiled categoryText;  // 书籍类别输入框

    private MyButton OKButton;  // 确认按钮
    private MyButton clearButton;  // 清空按钮

    private Image backgroundImage;

    // 构造方法，初始化窗口和组件
    AddWin() {
        super();  // 调用父类构造方法
        this.setIconImage(Main.icon.getImage());  // 设置窗口图标
        this.setSize(500, 600);  // 设置窗口大小
        this.setResizable(false);  // 禁止调整窗口大小
        this.setVisible(true);  // 设置窗口可见
        this.setLocation(450, 200);  // 设置窗口显示位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 设置关闭操作，仅销毁当前窗口
        this.setTitle("添加书籍信息");  // 设置窗口标题
// 替换窗口内容面板为带背景的自定义面板
        this.setContentPane(new BackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg"));
        this.setLayout(null); // 设置布局为手动布局



        // 设置背景颜色为粉色
        this.getContentPane().setBackground(Color.PINK);

        // 添加组件到窗口
        this.addPart();

        // 创建确认按钮
        OKButton = new MyButton(40, 420, 80, 40, "确认");
        this.add(OKButton);

        // 创建清空按钮
        clearButton = new MyButton(360, 420, 80, 40, "清空");
        this.add(clearButton);

        // 为按钮添加监听事件
        OKButton.addActionListener(new OKListener());
        clearButton.addActionListener(new ClearListener());


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

    @Override
    public void paint(Graphics g) {
        // 绘制背景图片
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        // 调用父类的 paint 方法以确保其他组件正常显示
        super.paint(g);
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
        this.add(isbnText);

        publisherLabel = new MyLabel(60, 195, 100, 40, "出版社");
        this.add(publisherLabel);
        publisherText = new MyTextFiled(140, 200, 200, 30);
        this.add(publisherText);

        yearLabel = new MyLabel(40, 255, 120, 40, "出版年份");
        this.add(yearLabel);
        yearText = new MyTextFiled(140, 260, 200, 30);
        this.add(yearText);

        categoryLabel = new MyLabel(80, 315, 80, 40, "类别");
        this.add(categoryLabel);
        categoryText = new MyTextFiled(140, 320, 200, 30);
        this.add(categoryText);
    }

    // 确认按钮的监听器
    private class OKListener implements ActionListener {
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
                // 如果没有错误，则创建新的 Book 对象并添加到书籍集合
                Main.books.put(isbnText.getText(), new Book(titleText.getText(), authorText.getText(),
                        isbnText.getText(), publisherText.getText(), Integer.parseInt(yearText.getText()), categoryText.getText()));
                JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.WARNING_MESSAGE);
                PrimePanel.refreshFile(); // 刷新文件
            }
        }
    }

    // 清空按钮的监听器
    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 清空所有输入框内容
            titleText.setText("");
            authorText.setText("");
            isbnText.setText("");
            publisherText.setText("");
            yearText.setText("");
            categoryText.setText("");
        }
    }
}
