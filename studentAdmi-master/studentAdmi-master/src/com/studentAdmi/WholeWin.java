package com.studentAdmi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.List;

public class WholeWin extends JFrame {
    private DefaultTableModel model = null; // 表格数据模型
    private JTable table = null; // 用于显示学生信息的表格
    private JButton sortButton; // 按学号排序按钮
    private JButton countGenderButton; // 统计性别人数按钮
    private static final String FILE_PATH = "studentAdmi-master\\src\\com\\resource\\students.txt"; // 数据文件路径

    WholeWin() {
        super("学生信息表");

        // 创建表格并写入数据
        table = new JTable(this.writingTable(loadStudentsFromFile())); // 从文件加载学生数据并显示在表格中
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

        // 按学号排序按钮
        sortButton = new JButton("按学号排序");
        sortButton.setBounds(220, 520, 150, 40); // 设置按钮位置和大小
        sortButton.setFont(new Font("楷体", Font.BOLD, 18)); // 设置按钮字体
        sortButton.setBackground(Color.CYAN); // 设置按钮背景颜色

        // 统计性别按钮
        countGenderButton = new JButton("统计性别");
        countGenderButton.setBounds(420, 520, 150, 40); // 设置按钮位置和大小
        countGenderButton.setFont(new Font("楷体", Font.BOLD, 18)); // 设置按钮字体
        countGenderButton.setBackground(Color.CYAN); // 设置按钮背景颜色

        // 添加排序按钮的监听事件
        sortButton.addActionListener(e -> {
            sortStudentsByCode(); // 调用排序方法
        });

        // 添加统计性别按钮的监听事件
        countGenderButton.addActionListener(e -> {
            List<Student> students = loadStudentsFromFile(); // 从文件加载学生数据
            int[] genderCount = countGender(students); // 统计性别人数
            showGenderStatistics(genderCount); // 显示统计结果
        });

        // 设置主面板
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); // 设置主面板背景颜色
        panel.setLayout(null);
        jScrollPane.getViewport().setOpaque(false); // 设置滚动视图透明
        panel.add(jScrollPane);
        panel.add(sortButton);
        panel.add(countGenderButton);

        this.setResizable(false); // 禁止调整窗口大小
        this.setSize(800, 600); // 设置窗口大小
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.getContentPane().add(panel);
        this.setLocationRelativeTo(null); // 居中显示窗口
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口时销毁
        this.setVisible(true); // 显示窗口
    }

    // 从文件加载学生信息
    private List<Student> loadStudentsFromFile() {
        List<Student> students = new ArrayList<>(); // 创建用于存储学生信息的列表
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_PATH)))) {
            String line;
            while ((line = reader.readLine()) != null) { // 按行读取文件内容
                String[] parts = line.split("--"); // 按分隔符解析学生信息
                if (parts.length == 6) {
                    students.add(new Student(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5])); // 添加到列表
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // 排序学生信息并更新文件
    private void sortStudentsByCode() {
        List<Student> students = loadStudentsFromFile(); // 从文件加载学生信息
        students.sort(Comparator.comparing(Student::getCode)); // 按学号排序
        refreshTable(students); // 刷新表格显示排序结果
        saveStudentsToFile(students); // 将排序后的数据写回文件
        showSortCompletePopup(); // 显示排序完成提示窗口
    }

    // 将学生信息写回文件
    private void saveStudentsToFile(List<Student> students) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Student student : students) {
                String line = String.join("--",
                        student.getName(),
                        student.getSex(),
                        student.getPlace(),
                        student.getCode(),
                        student.getDept(),
                        student.getBan());
                writer.write(line + "\n"); // 按行写入文件
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 显示性别统计的窗口
    private void showGenderStatistics(int[] genderCount) {
        JFrame statsFrame = new JFrame("性别统计");
        statsFrame.setSize(300, 200);
        statsFrame.setLayout(null);
        statsFrame.getContentPane().setBackground(Color.PINK); // 设置窗口背景为粉色

        JLabel statsLabel = new JLabel("<html>男生人数: " + genderCount[0] + "<br>女生人数: " + genderCount[1] + "</html>");
        statsLabel.setFont(new Font("楷体", Font.BOLD, 18)); // 设置字体样式
        statsLabel.setBounds(50, 50, 200, 100); // 设置标签位置和大小
        statsFrame.add(statsLabel);

        statsFrame.setLocationRelativeTo(null); // 居中显示窗口
        statsFrame.setVisible(true); // 显示窗口
    }

    // 显示排序完成的窗口
    private void showSortCompletePopup() {
        JFrame sortFrame = new JFrame("排序完成");
        sortFrame.setSize(300, 200);
        sortFrame.setLayout(null);
        sortFrame.getContentPane().setBackground(Color.PINK); // 设置窗口背景为粉色

        JLabel sortLabel = new JLabel("学号排序完成！");
        sortLabel.setFont(new Font("楷体", Font.BOLD, 18)); // 设置字体样式
        sortLabel.setBounds(80, 70, 200, 50); // 设置标签位置和大小
        sortFrame.add(sortLabel);

        sortFrame.setLocationRelativeTo(null); // 居中显示窗口
        sortFrame.setVisible(true); // 显示窗口
    }

    // 统计性别人数
    private int[] countGender(List<Student> students) {
        int maleCount = 0; // 男生人数
        int femaleCount = 0; // 女生人数
        for (Student student : students) {
            if ("男".equals(student.getSex())) {
                maleCount++;
            } else if ("女".equals(student.getSex())) {
                femaleCount++;
            }
        }
        return new int[]{maleCount, femaleCount};
    }

    // 刷新表格内容
    private void refreshTable(List<Student> students) {
        table.setModel(this.writingTable(students)); // 更新表格数据模型
    }

    // 填充表格数据
    private DefaultTableModel writingTable(List<Student> students) {
        String[][] datas = new String[students.size()][6]; // 定义表格数据数组
        String[] titles = {"姓名", "性别", "籍贯", "学号", "系别", "班别"}; // 定义表头
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            datas[i] = new String[]{
                    student.getName(),
                    student.getSex(),
                    student.getPlace(),
                    student.getCode(),
                    student.getDept(),
                    student.getBan()
            }; // 填充每行数据
        }
        return model = new DefaultTableModel(datas, titles); // 返回表格数据模型
    }
}
