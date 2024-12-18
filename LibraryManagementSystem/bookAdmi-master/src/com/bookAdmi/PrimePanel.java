package com.bookAdmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * PrimePanel 类
 * 图书管理系统的主面板，包含各个按钮来执行添加、删除、查找、修改、显示全部图书信息等功能。
 */


/**
 * PrimePanel 类
 * 根据用户角色显示不同功能的主面板。
 */
public class PrimePanel extends JPanel {
    // 按钮成员变量
    private MyButton addButton;     // 增按钮
    private MyButton delButton;     // 删按钮
    private MyButton findButton;    // 查按钮
    private MyButton changeButton;  // 改按钮
    private MyButton wholeButton;   // 全部按钮
    private MyButton readButton;    // 阅读按钮（仅学生可见）

    // 图书信息文件路径
    static File booksFile = new File("bookAdmi-master\\src\\com\\resource\\books.txt");

    /**
     * 构造方法
     * 根据用户角色初始化面板，设置布局、字体颜色等，并添加按钮。
     *
     * @param x 面板的 X 坐标
     * @param y 面板的 Y 坐标
     * @param width 面板的宽度
     * @param height 面板的高度
     * @param isAdmin 是否为管理员
     */
    public PrimePanel(int x, int y, int width, int height, boolean isAdmin) {
        super();
        this.setLayout(null);  // 清空布局器
        this.setBounds(x, y, width, height);
        this.setFont(new Font("serif", Font.BOLD, 23));  // 设置字体
        this.setForeground(Color.pink);  // 设置字体颜色为粉色

        // 根据用户角色添加按钮
        addButtons(isAdmin);
        addListeners(isAdmin);

        // 读取图书信息文件
        readBooksFile();
    }

    /**
     * 添加按钮到面板
     *
     * @param isAdmin 是否为管理员
     */
    private void addButtons(boolean isAdmin) {
        if (isAdmin) {
            // 管理员可见的按钮
            addButton = new MyButton(160, 280, 80, 40, "增加");
            this.add(addButton);

            delButton = new MyButton(260, 280, 80, 40, "删除");
            this.add(delButton);

            changeButton = new MyButton(360, 280, 80, 40, "修改");
            this.add(changeButton);
        }

        // 公共按钮
        findButton = new MyButton(460, 280, 80, 40, "查找");
        this.add(findButton);

        wholeButton = new MyButton(560, 280, 80, 40, "全部");
        this.add(wholeButton);

        // 学生专用按钮
        if (!isAdmin) {
            readButton = new MyButton(660, 280, 80, 40, "阅读");
            this.add(readButton);
        }
    }

    /**
     * 为各个按钮添加事件监听器
     *
     * @param isAdmin 是否为管理员
     */
    private void addListeners(boolean isAdmin) {
        if (isAdmin) {
            addButton.addActionListener(e -> new AddWin());  // 增加
            delButton.addActionListener(e -> new DelWin());  // 删除
            changeButton.addActionListener(e -> new ChangedWin.ChangeWin());  // 修改
        }

        findButton.addActionListener(e -> new FindWin());  // 查找
        wholeButton.addActionListener(e -> new WholeWin());  // 全部

        if (!isAdmin) {
            readButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                    "打开小说阅读页面", "提示", JOptionPane.INFORMATION_MESSAGE));
        }
    }

    /**
     * 读取图书文件并将信息加载到图书集合中
     */
    private void readBooksFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(booksFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split("--");
                if (bookData.length == 6) {
                    Main.books.put(bookData[2], new Book(
                            bookData[0],  // 书名
                            bookData[1],  // 作者
                            bookData[2],  // ISBN
                            bookData[3],  // 出版社
                            Integer.parseInt(bookData[4]),  // 出版年份
                            bookData[5]   // 类别
                    ));
                } else {
                    JOptionPane.showMessageDialog(null, "图书文件格式错误", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            System.out.println("读取图书文件时出现错误: " + e);
        }
    }

    /**
     * 重写 paintComponent 方法，在面板上绘制背景图片和文本
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Image backgroundImage = new ImageIcon("bookAdmi-master\\src\\com\\resource\\background.jpg").getImage();
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawString("图书管理系统", 300, 150);
        g.drawString("23Yang Class Cha1", 310, 190);
    }

    private void addButtons() {
        addButton = new MyButton(160, 280, 80, 40, "增加");
        this.add(addButton);

        delButton = new MyButton(260, 280, 80, 40, "删除");
        this.add(delButton);

        findButton = new MyButton(360, 280, 80, 40, "查找");
        this.add(findButton);

        changeButton = new MyButton(460, 280, 80, 40, "修改");
        this.add(changeButton);

        wholeButton = new MyButton(560, 280, 80, 40, "全部");
        this.add(wholeButton);
    }

    /**
     * 为各个按钮添加事件监听器
     */
    private void addListen() {
        addButton.addActionListener(new AddListener());
        delButton.addActionListener(new DelListener());
        findButton.addActionListener(new FindListener());
        changeButton.addActionListener(new ChangeListener());
        wholeButton.addActionListener(new WholeListener());
    }



    private class AddListener implements ActionListener {  // 监听“增加”按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddWin();  // 创建新窗口用于添加图书信息
        }
    }

    private class DelListener implements ActionListener {  // 监听“删除”按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            new DelWin();  // 创建新窗口用于删除图书信息
        }
    }

    private class FindListener implements ActionListener {  // 监听“查找”按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            new FindWin();  // 创建新窗口用于查找图书信息
        }
    }

    private class ChangeListener implements ActionListener {  // 监听“修改”按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            new ChangedWin.ChangeWin();  // 创建新窗口用于修改图书信息
        }
    }

    private class WholeListener implements ActionListener {  // 监听“全部”按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            new WholeWin();  // 创建新窗口显示所有图书信息
        }
    }

    /**
     * 刷新文件中的图书信息，通常在图书信息的增加、删除或修改操作后调用
     */
    static public void refreshFile() {
        try (FileWriter writer = new FileWriter(booksFile)) {
            for (String key : Main.books.keySet()) {
                Book book = Main.books.get(key);
                String bookData = String.join("--",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getPublisher(),
                        String.valueOf(book.getYear()),
                        book.getCategory()
                );
                writer.write(bookData + "\n");
            }
        } catch (Exception e) {
            System.out.println("保存图书信息时出现错误: " + e);
        }
    }
    /**
     * 监听“阅读”按钮（学生专用）
     */
    private class ReadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 模拟打开小说阅读页面
            JOptionPane.showMessageDialog(PrimePanel.this,
                    "小说阅读功能暂未实现，敬请期待！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

