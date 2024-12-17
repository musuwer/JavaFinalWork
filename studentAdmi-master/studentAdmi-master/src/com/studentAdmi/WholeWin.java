package com.studentAdmi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Map;

/**
 * WholeWin 类
 * 显示所有学生信息的窗口。
 */

// 统计男生和女生人数
public class WholeWin extends JFrame {
    private DefaultTableModel model  = null;
    private JTable table = null;

    WholeWin() {
        super("学生信息表");
        table = new JTable(this.writingTable(Main.students));
        table.setEnabled(false);
        table.setFont(new Font("宋体", Font.LAYOUT_NO_LIMIT_CONTEXT, 20));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30));
        tableHeader.setFont(new Font("楷体", Font.BOLD, 22));
        tableHeader.setEnabled(false);
        tableHeader.setBackground(Color.orange);

        table.setRowHeight(26);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        table.setBackground(Color.yellow);
        this.setSize(800, 600);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 0, this.getWidth(), this.getHeight());
        jScrollPane.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setLayout(null);
        jScrollPane.getViewport().setOpaque(false);
        panel.add(jScrollPane);

        // 获取性别统计信息
        String[] genderStats = getGenderStatistics(Main.students);

        // 创建显示统计信息的面板
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout());
        statsPanel.setBounds(10, 520, 300, 60);
        statsPanel.setBackground(new Color(255, 255, 204)); // 浅黄色背景
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 边框

        // 创建一个标签显示统计信息
        JLabel genderStatsLabel = new JLabel(genderStats[0] + "    " + genderStats[1]);
        genderStatsLabel.setFont(new Font("宋体", Font.BOLD, 18));
        genderStatsLabel.setForeground(Color.BLACK);

        statsPanel.add(genderStatsLabel);
        panel.add(statsPanel);

        this.setResizable(false);
        this.setIconImage(Main.icon.getImage());
        this.getContentPane().add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    DefaultTableModel writingTable(Map<String , Student> students) {
        String[][] datas = new String[students.size()][6];
        String[] titles = { "姓名", "性别", "籍贯", "学号", "系别", "班别" };
        int index = 0;
        for (String key : students.keySet()){
            datas[index] = new String[]{students.get(key).getName(), students.get(key).getSex(), students.get(key).getPlace(),
                    students.get(key).getCode(), students.get(key).getDept(), students.get(key).getBan()};
            index++;
        }
        return model = new DefaultTableModel(datas, titles);
    }

    // 统计男生和女生人数
    private String[] getGenderStatistics(Map<String, Student> students) {
        int maleCount = 0;
        int femaleCount = 0;

        for (String key : students.keySet()) {
            Student student = students.get(key);
            if ("男".equals(student.getSex())) {
                maleCount++;
            } else if ("女".equals(student.getSex())) {
                femaleCount++;
            }
        }

        return new String[]{"男生人数: " + maleCount, "女生人数: " + femaleCount};
    }
}

