package com.bookAdmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddWin extends JFrame {
    private MyTools.MyLabel titleLabel;  // 书名标签
    private MyTools.MyTextFiled titleText;  // 书名输入框

    private MyTools.MyLabel authorLabel;  // 作者标签
    private MyTools.MyTextFiled authorText;  // 作者输入框

    private MyTools.MyLabel isbnLabel;  // ISBN标签
    private MyTools.MyTextFiled isbnText;  // ISBN输入框

    private MyTools.MyLabel publisherLabel;  // 出版社标签
    private MyTools.MyTextFiled publisherText;  // 出版社输入框

    private MyTools.MyLabel yearLabel;  // 出版年份标签
    private MyTools.MyTextFiled yearText;  // 出版年份输入框

    private MyTools.MyLabel categoryLabel;  // 书籍类别标签
    private JRadioButton literatureButton;  // 文学类单选按钮
    private JRadioButton historyButton;  // 历史类单选按钮
    private JRadioButton scienceButton;  // 科学类单选按钮
    private JRadioButton artButton;  // 艺术类单选按钮
    private JRadioButton otherButton;  // 其它类单选按钮
    private JTextField otherCategoryTextField;  // 用于输入“其它”类别具体内容的文本框

    private MyButton OKButton;  // 确认按钮
    private MyButton clearButton;  // 清空按钮

    private Image backgroundImage;

    // 构造方法，初始化窗口和组件
    AddWin() {
        super();
        this.setIconImage(Main.icon.getImage());
        this.setSize(500, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocation(450, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("添加书籍信息");
        // 替换窗口内容面板为带背景的自定义面板
        this.setContentPane(new MyBackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg"));
        this.setLayout(null);

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

        // 初始化图书类别相关单选按钮及“其它”类别输入文本框，并设置透明背景
        literatureButton = new JRadioButton("文学类");
        literatureButton.setBounds(140, 320, 80, 30);
        literatureButton.setOpaque(false);  // 设置按钮透明
        getContentPane().add(literatureButton);

        historyButton = new JRadioButton("历史类");
        historyButton.setBounds(220, 320, 80, 30);
        historyButton.setOpaque(false);
        getContentPane().add(historyButton);

        scienceButton = new JRadioButton("科学类");
        scienceButton.setBounds(140, 350, 80, 30);
        scienceButton.setOpaque(false);
        getContentPane().add(scienceButton);

        artButton = new JRadioButton("艺术类");
        artButton.setBounds(220, 350, 80, 30);
        artButton.setOpaque(false);
        getContentPane().add(artButton);

        otherButton = new JRadioButton("其它");
        otherButton.setBounds(140, 380, 80, 30);
        otherButton.setOpaque(false);
        getContentPane().add(otherButton);

        otherCategoryTextField = new JTextField();
        otherCategoryTextField.setBounds(220, 380, 135, 30);
        otherCategoryTextField.setVisible(false);
        getContentPane().add(otherCategoryTextField);

        // 将单选按钮分组，确保同一时刻只有一个被选中
        ButtonGroup categoryGroup = new ButtonGroup();
        categoryGroup.add(literatureButton);
        categoryGroup.add(historyButton);
        categoryGroup.add(scienceButton);
        categoryGroup.add(artButton);
        categoryGroup.add(otherButton);

        // 为“其它”类别单选按钮添加事件监听，用于控制“其它”类别文本框的显示隐藏
        otherButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    otherCategoryTextField.setVisible(true);
                } else {
                    otherCategoryTextField.setVisible(false);
                }
            }
        });

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
        titleLabel = new MyTools.MyLabel(80, 15, 80, 40, "书名");
        getContentPane().add(titleLabel);
        titleText = new MyTools.MyTextFiled(140, 20, 200, 30);
        getContentPane().add(titleText);

        authorLabel = new MyTools.MyLabel(80, 75, 80, 40, "作者");
        getContentPane().add(authorLabel);
        authorText = new MyTools.MyTextFiled(140, 80, 200, 30);
        getContentPane().add(authorText);

        isbnLabel = new MyTools.MyLabel(80, 135, 80, 40, "ISBN");
        getContentPane().add(isbnLabel);
        isbnText = new MyTools.MyTextFiled(140, 140, 200, 30);
        getContentPane().add(isbnText);

        publisherLabel = new MyTools.MyLabel(60, 195, 100, 40, "出版社");
        getContentPane().add(publisherLabel);
        publisherText = new MyTools.MyTextFiled(140, 200, 200, 30);
        getContentPane().add(publisherText);

        yearLabel = new MyTools.MyLabel(40, 255, 120, 40, "出版年份");
        getContentPane().add(yearLabel);
        yearText = new MyTools.MyTextFiled(140, 260, 200, 30);
        getContentPane().add(yearText);

        categoryLabel = new MyTools.MyLabel(80, 315, 80, 40, "类别");
        getContentPane().add(categoryLabel);
    }

    // 确认按钮的监听器
    private class OKListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 校验输入框内容是否为空或包含非法空格
            if (titleText.getText().length() == 0 || titleText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "书名未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (authorText.getText().length() == 0 || authorText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "作者未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (isbnText.getText().length() == 0 || isbnText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "ISBN未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (publisherText.getText().length() == 0 || publisherText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "出版社未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (yearText.getText().length() == 0 || yearText.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "出版年份未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String category;
            if (otherButton.isSelected()) {
                category = otherCategoryTextField.getText().trim();
                if (category.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入‘其它’类别的具体名称", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else if (literatureButton.isSelected()) {
                category = "文学类";
            } else if (historyButton.isSelected()) {
                category = "历史类";
            } else if (scienceButton.isSelected()) {
                category = "科学类";
            } else if (artButton.isSelected()) {
                category = "艺术类";
            } else {
                category = ""; // 这里可以根据实际情况处理默认值或者提示选择类别等逻辑
                JOptionPane.showMessageDialog(null, "请选择图书类别", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 如果没有错误，则创建新的Book对象并添加到书籍集合
            Main.books.put(isbnText.getText(), new Book(titleText.getText(), authorText.getText(),
                    isbnText.getText(), publisherText.getText(), Integer.parseInt(yearText.getText()), category));
            JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.WARNING_MESSAGE);
            PrimePanel.refreshFile(); // 刷新文件
        }
    }

    // 清空按钮的监听器，添加对类别相关组件的清空处理
    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 清空所有输入框内容
            titleText.setText("");
            authorText.setText("");
            isbnText.setText("");
            publisherText.setText("");
            yearText.setText("");
            otherCategoryTextField.setText("");
            literatureButton.setSelected(false);
            historyButton.setSelected(false);
            scienceButton.setSelected(false);
            artButton.setSelected(false);
            otherButton.setSelected(false);
        }
    }
}