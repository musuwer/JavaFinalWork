package com.bookAdmi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * WholeWin 类
 * 图书管理系统的窗口，用于展示所有书籍信息，提供书籍排序和统计功能。
 */
public class WholeWin extends JFrame {
    private DefaultTableModel model = null; // 表格数据模型
    private JTable table = null; // 用于显示书籍信息的表格
    private JButton sortButton; // 按书名排序按钮
    private JButton countCategoryButton; // 统计书籍类别按钮
    private static final String FILE_PATH = "bookAdmi-master\\src\\com\\resource\\books.txt"; // 图书文件路径

    WholeWin() {
        super("图书信息管理");

        // 创建自定义背景面板
        BackgroundPanel backgroundPanel = new BackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg");

        // 创建表格并写入数据
        table = new JTable(this.writingTable(loadBooksFromFile())); // 从文件加载书籍数据并显示在表格中
        table.setEnabled(false); // 禁止用户编辑表格内容
        table.setFont(new Font("宋体", Font.PLAIN, 20)); // 设置表格字体


        // 设置表格头部样式
        JTableHeader tableHeader = table.getTableHeader(); // 获取表格的头部
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30)); // 设置表格头行高
        tableHeader.setFont(new Font("楷体", Font.BOLD, 22)); // 设置表格头字体
        tableHeader.setEnabled(false); // 禁止表格头交互
        tableHeader.setBackground(Color.orange); // 设置表格头背景颜色

        // 设置表格内容样式
        table.setRowHeight(26); // 设置表格行高
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(); // 创建表格内容渲染器
        tcr.setHorizontalAlignment(JLabel.CENTER); // 设置内容居中显示
        table.setDefaultRenderer(Object.class, tcr); // 应用渲染器
        table.setBackground(Color.yellow); // 设置表格内容背景颜色

        JScrollPane jScrollPane = new JScrollPane(table); // 添加滚动条支持
        jScrollPane.setBounds(0, 0, 800, 500); // 设置表格大小
        jScrollPane.setOpaque(false);

        // 按书名排序按钮
        sortButton = new JButton("按书出版年份排序");
        sortButton.setBounds(220, 520, 150, 40); // 设置按钮位置和大小
        sortButton.setFont(new Font("楷体", Font.BOLD, 18)); // 设置按钮字体
        sortButton.setBackground(Color.CYAN); // 设置按钮背景颜色

        // 统计书籍类别按钮
        countCategoryButton = new JButton("统计类别");
        countCategoryButton.setBounds(420, 520, 150, 40); // 设置按钮位置和大小
        countCategoryButton.setFont(new Font("楷体", Font.BOLD, 18)); // 设置按钮字体
        countCategoryButton.setBackground(Color.CYAN); // 设置按钮背景颜色

        // 添加排序按钮的监听事件
        sortButton.addActionListener(e -> {
            sortBooksByYear(); // 调用排序方法
        });

        // 添加统计书籍类别按钮的监听事件
        countCategoryButton.addActionListener(e -> {
            List<Book> books = loadBooksFromFile(); // 从文件加载书籍数据
            Map<String, Integer> categoryCount = countCategory(books); // 统计书籍类别
            showCategoryStatistics(categoryCount); // 显示统计结果
        });

        // 设置主面板
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); // 设置主面板背景颜色
        panel.setLayout(null);
        jScrollPane.getViewport().setOpaque(false); // 设置滚动视图透明
        panel.add(jScrollPane);
        panel.add(sortButton);
        panel.add(countCategoryButton);

        this.setResizable(false); // 禁止调整窗口大小
        this.setSize(800, 600); // 设置窗口大小
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.getContentPane().add(panel);
        this.setLocationRelativeTo(null); // 居中显示窗口
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口时销毁
        this.setVisible(true); // 显示窗口
    }

    // 从文件加载书籍信息
    private List<Book> loadBooksFromFile() {
        List<Book> books = new ArrayList<>(); // 创建用于存储书籍信息的列表
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_PATH)))) {
            String line;
            while ((line = reader.readLine()) != null) { // 按行读取文件内容
                String[] parts = line.split("--"); // 按分隔符解析书籍信息
                if (parts.length == 6) {
                    books.add(new Book(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5])); // 添加到列表
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    // 修改按出版年份排序的功能
    private void sortBooksByYear() {
        List<Book> books = loadBooksFromFile(); // 从文件加载书籍信息

        // 按出版年份排序
        books.sort(Comparator.comparingInt(Book::getYear)); // 按年份升序排序

        // 刷新表格显示排序结果
        refreshTable(books);

        // 将排序后的数据写回文件
        saveBooksToFile(books);

        // 显示排序完成的提示
        showSortCompletePopup();
    }

    // 将书籍信息写回文件
    private void saveBooksToFile(List<Book> books) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Book book : books) {
                String line = String.join("--",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getPublisher(),
                        String.valueOf(book.getYear()),  // 使用出版年份
                        book.getCategory());
                writer.write(line + "\n"); // 按行写入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 显示书籍类别统计的窗口
    private void showCategoryStatistics(Map<String, Integer> categoryCount) {
        JFrame statsFrame = new JFrame("书籍类别统计");
        statsFrame.setSize(300, 200);
        statsFrame.setLayout(null);
        statsFrame.getContentPane().setBackground(Color.PINK); // 设置窗口背景为粉色

        // 创建 JTextArea 来显示统计信息
        StringBuilder statsText = new StringBuilder();
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            statsText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        JTextArea statsArea = new JTextArea(statsText.toString());
        statsArea.setFont(new Font("楷体", Font.BOLD, 18)); // 设置字体样式
        statsArea.setEditable(false); // 设置不可编辑
        statsArea.setLineWrap(true); // 启用自动换行
        statsArea.setWrapStyleWord(true); // 启用单词断行
        statsArea.setBackground(Color.PINK); // 设置 JTextArea 背景为粉色
        statsArea.setForeground(Color.BLACK); // 设置文字颜色为黑色

        // 使用 JScrollPane 来使内容可滚动
        JScrollPane scrollPane = new JScrollPane(statsArea);
        scrollPane.setBounds(20, 20, 250, 120); // 设置滚动区域的大小
        scrollPane.setBackground(Color.PINK); // 设置 JScrollPane 背景为粉色
        scrollPane.getViewport().setBackground(Color.PINK); // 设置滚动视图的背景为粉色

        statsFrame.add(scrollPane);

        statsFrame.setLocationRelativeTo(null); // 居中显示窗口
        statsFrame.setVisible(true); // 显示窗口
    }



    // 显示排序完成的窗口
    private void showSortCompletePopup() {
        JFrame sortFrame = new JFrame("排序完成");
        sortFrame.setSize(300, 200);
        sortFrame.setLayout(null);
        sortFrame.getContentPane().setBackground(Color.PINK); // 设置窗口背景为粉色

        JLabel sortLabel = new JLabel("书名排序完成！");
        sortLabel.setFont(new Font("楷体", Font.BOLD, 18)); // 设置字体样式
        sortLabel.setBounds(80, 70, 200, 50); // 设置标签位置和大小
        sortFrame.add(sortLabel);

        sortFrame.setLocationRelativeTo(null); // 居中显示窗口
        sortFrame.setVisible(true); // 显示窗口
    }

    // 统计书籍类别数量
    private Map<String, Integer> countCategory(List<Book> books) {
        Map<String, Integer> categoryCount = new HashMap<>(); // 创建类别统计字典
        for (Book book : books) {
            categoryCount.put(book.getCategory(), categoryCount.getOrDefault(book.getCategory(), 0) + 1); // 更新统计数量
        }
        return categoryCount;
    }

    // 刷新表格内容
    private void refreshTable(List<Book> books) {
        table.setModel(this.writingTable(books)); // 更新表格数据模型
    }

    // 填充表格数据
    private DefaultTableModel writingTable(List<Book> books) {
        String[][] datas = new String[books.size()][6]; // 定义表格数据数组
        String[] titles = {"书名", "作者", "ISBN", "出版社", "出版年份", "类别"}; // 定义表头
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            datas[i] = new String[]{
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getPublisher(),
                    String.valueOf(book.getYear()),
                    book.getCategory()
            }; // 填充每行数据
        }
        return model = new DefaultTableModel(datas, titles); // 返回表格数据模型
    }
}
