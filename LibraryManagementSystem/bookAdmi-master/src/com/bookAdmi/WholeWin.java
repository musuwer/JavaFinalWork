package com.bookAdmi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.List;
import java.util.*;

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
        MyBackgroundPanel backgroundPanel = new MyBackgroundPanel("bookAdmi-master\\src\\com\\resource\\background.jpg");
        backgroundPanel.setLayout(null); // 设置布局为绝对布局
        this.setContentPane(backgroundPanel); // 设置内容面板为背景面板

        // 创建表格并写入数据
        table = new JTable(this.writingTable(loadBooksFromFile()));
        table.setEnabled(false);
        table.setFont(new Font("宋体", Font.PLAIN, 20));

        // 设置表格头部样式
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30));
        tableHeader.setFont(new Font("楷体", Font.BOLD, 22));
        tableHeader.setEnabled(false);
        tableHeader.setBackground(Color.orange);

        // 设置表格内容样式
        table.setRowHeight(26);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        table.setBackground(Color.yellow);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 0, 800, 500);
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);

        // 按书名排序按钮
        sortButton = new JButton("按书出版年份排序");
        sortButton.setBounds(200, 520, 200, 40);
        sortButton.setFont(new Font("楷体", Font.BOLD, 18));
        sortButton.setBackground(Color.CYAN);

        // 统计书籍类别按钮
        countCategoryButton = new JButton("统计类别");
        countCategoryButton.setBounds(420, 520, 150, 40);
        countCategoryButton.setFont(new Font("楷体", Font.BOLD, 18));
        countCategoryButton.setBackground(Color.CYAN);

        // 添加组件到背景面板
        backgroundPanel.add(jScrollPane);
        backgroundPanel.add(sortButton);
        backgroundPanel.add(countCategoryButton);


        // 监听鼠标点击位置
        backgroundPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 获取鼠标点击的位置
                int x = e.getX();
                int y = e.getY();
                // 打印鼠标点击位置
                System.out.println("Mouse clicked at: x = " + x + ", y = " + y);
            }
        });


        // 按钮点击事件处理
        sortButton.addActionListener(e -> sortBooksByYear());
        countCategoryButton.addActionListener(e -> {
            List<Book> books = loadBooksFromFile(); // 从文件加载书籍数据
            Map<String, Integer> categoryCount = countCategory(books); // 统计书籍类别
            showCategoryStatistics(categoryCount); // 显示统计结果
        });

        // 设置窗口属性
        this.setResizable(false);
        this.setSize(800, 600);
        this.setIconImage(Main.icon.getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
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
        System.out.println("Loaded books: " + books.size());  // 打印加载的书籍数量
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
        // 输出统计结果
        System.out.println("Category statistics: " + categoryCount);

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
            System.out.println("统计类别: " + book.getCategory()); // 输出类别
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
