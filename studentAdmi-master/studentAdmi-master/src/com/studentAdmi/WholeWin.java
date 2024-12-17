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
    private DefaultTableModel model = null;
    private JTable table = null;
    private JButton sortButton; // 按学号排序按钮
    private JButton countGenderButton; // 统计性别人数按钮
    private static final String FILE_PATH = "studentAdmi-master\\src\\com\\resource\\students.txt";

    WholeWin() {
        super("学生信息表");

        // 创建表格并写入数据
        table = new JTable(this.writingTable(loadStudentsFromFile()));
        table.setEnabled(false);
        table.setFont(new Font("宋体", Font.PLAIN, 20));

        JTableHeader tableHeader = table.getTableHeader(); // 表格首行
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30)); // 行高
        tableHeader.setFont(new Font("楷体", Font.BOLD, 22)); // 设置表格头行字体
        tableHeader.setEnabled(false);
        tableHeader.setBackground(Color.orange);

        table.setRowHeight(26); // 行高
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(); // 设置table内容居中
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        table.setBackground(Color.yellow);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 0, 800, 500); // 表格大小
        jScrollPane.setOpaque(false);

        // 按学号排序按钮
        sortButton = new JButton("按学号排序");
        sortButton.setBounds(220, 520, 150, 40);
        sortButton.setFont(new Font("楷体", Font.BOLD, 18));
        sortButton.setBackground(Color.CYAN);

        // 统计性别按钮
        countGenderButton = new JButton("统计性别");
        countGenderButton.setBounds(420, 520, 150, 40);
        countGenderButton.setFont(new Font("楷体", Font.BOLD, 18));
        countGenderButton.setBackground(Color.CYAN);

        // 添加排序按钮的监听事件
        sortButton.addActionListener(e -> {
            sortStudentsByCode(); // 排序操作
        });

        // 添加统计性别按钮的监听事件
        countGenderButton.addActionListener(e -> {
            List<Student> students = loadStudentsFromFile();
            int[] genderCount = countGender(students);
            showGenderStatistics(genderCount);
        });

        // 设置主面板
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setLayout(null);
        jScrollPane.getViewport().setOpaque(false);
        panel.add(jScrollPane);
        panel.add(sortButton);
        panel.add(countGenderButton);

        this.setResizable(false); // 不可改变大小
        this.setSize(800, 600);
        this.setIconImage(Main.icon.getImage());
        this.getContentPane().add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    // 从文件加载学生信息
    private List<Student> loadStudentsFromFile() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_PATH)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("--");
                if (parts.length == 6) {
                    students.add(new Student(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // 排序学生信息并更新文件
    private void sortStudentsByCode() {
        // 从文件加载学生信息
        List<Student> students = loadStudentsFromFile();

        // 按学号排序
        students.sort(Comparator.comparing(Student::getCode));

        // 刷新表格显示排序结果
        refreshTable(students);

        // 将排序后的数据写回文件
        saveStudentsToFile(students);

        // 弹出粉色背景的排序完成窗口
        showSortCompletePopup();
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
                writer.write(line + "\n");
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
        statsFrame.getContentPane().setBackground(Color.PINK); // 设置粉色背景

        JLabel statsLabel = new JLabel("<html>男生人数: " + genderCount[0] + "<br>女生人数: " + genderCount[1] + "</html>");
        statsLabel.setFont(new Font("楷体", Font.BOLD, 18));
        statsLabel.setBounds(50, 50, 200, 100);
        statsFrame.add(statsLabel);

        statsFrame.setLocationRelativeTo(null);
        statsFrame.setVisible(true);
    }

    // 显示排序完成的窗口
    private void showSortCompletePopup() {
        JFrame sortFrame = new JFrame("排序完成");
        sortFrame.setSize(300, 200);
        sortFrame.setLayout(null);
        sortFrame.getContentPane().setBackground(Color.PINK); // 设置粉色背景

        JLabel sortLabel = new JLabel("学号排序完成！");
        sortLabel.setFont(new Font("楷体", Font.BOLD, 18));
        sortLabel.setBounds(80, 70, 200, 50);
        sortFrame.add(sortLabel);

        sortFrame.setLocationRelativeTo(null);
        sortFrame.setVisible(true);
    }

    // 统计性别人数
    private int[] countGender(List<Student> students) {
        int maleCount = 0;
        int femaleCount = 0;
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
        table.setModel(this.writingTable(students));
    }

    // 填充表格数据
    private DefaultTableModel writingTable(List<Student> students) {
        String[][] datas = new String[students.size()][6];
        String[] titles = {"姓名", "性别", "籍贯", "学号", "系别", "班别"};
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            datas[i] = new String[]{
                    student.getName(),
                    student.getSex(),
                    student.getPlace(),
                    student.getCode(),
                    student.getDept(),
                    student.getBan()
            };
        }
        return model = new DefaultTableModel(datas, titles);
    }
}
