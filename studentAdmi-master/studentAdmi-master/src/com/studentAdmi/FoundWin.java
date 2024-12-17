package com.studentAdmi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * FoundWin 类
 * 用于展示某个学生的详细信息，包括姓名、性别、籍贯、学号、系别和班级。
 * 所有字段显示为不可编辑状态，点击确认按钮可关闭窗口。
 */
public class FoundWin extends JFrame {

    // 定义各类组件
    private MyLabel nameLabel; // 姓名标签
    private MyTextFiled nameText; // 姓名显示框

    private MyLabel sexLabel; // 性别标签
    private MyTextFiled sexText; // 性别显示框

    private MyLabel placeLabel; // 籍贯标签
    private MyTextFiled placeText; // 籍贯显示框

    private MyLabel codeLabel; // 学号标签
    private MyTextFiled codeText; // 学号显示框

    private MyLabel deptLabel; // 系别标签
    private MyTextFiled deptText; // 系别显示框

    private MyLabel banLabel; // 班级标签
    private MyTextFiled banText; // 班级显示框

    private MyButton OKButton; // 确认按钮，用于关闭窗口

    public FoundWin(String name, String sex, String place, String code, String dept, String ban) {
        super();

        // 设置窗口属性
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.setSize(500, 600); // 设置窗口大小
        this.setLayout(null); // 使用空布局，手动设置组件位置
        this.setResizable(false); // 禁止调整窗口大小
        this.setVisible(true); // 设置窗口可见
        this.setLocation(450, 200); // 设置窗口显示位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口时释放资源
        this.setTitle("查看学生信息"); // 设置窗口标题
        this.getContentPane().setBackground(Color.pink); // 设置窗口背景颜色为粉色

        // 添加组件并设置其初始状态
        this.addPart();

        // 填充学生信息到对应文本框
        this.nameText.setText(name);
        this.sexText.setText(sex);
        this.placeText.setText(place);
        this.codeText.setText(code);
        this.deptText.setText(dept);
        this.banText.setText(ban);

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
        // 姓名
        nameLabel = new MyLabel(80, 15, 80, 40, "姓名");
        this.add(nameLabel);
        nameText = new MyTextFiled(140, 20, 200, 30);
        nameText.setEditable(false); // 设置为不可编辑
        this.add(nameText);

        // 性别
        sexLabel = new MyLabel(80, 75, 80, 40, "性别");
        this.add(sexLabel);
        sexText = new MyTextFiled(140, 80, 200, 30);
        sexText.setEditable(false);
        this.add(sexText);

        // 籍贯
        placeLabel = new MyLabel(80, 135, 80, 40, "籍贯");
        this.add(placeLabel);
        placeText = new MyTextFiled(140, 140, 200, 30);
        placeText.setEditable(false);
        this.add(placeText);

        // 学号
        codeLabel = new MyLabel(80, 195, 80, 40, "学号");
        this.add(codeLabel);
        codeText = new MyTextFiled(140, 200, 200, 30);
        codeText.setEditable(false);
        this.add(codeText);

        // 系别
        deptLabel = new MyLabel(80, 255, 80, 40, "系别");
        this.add(deptLabel);
        deptText = new MyTextFiled(140, 260, 200, 30);
        deptText.setEditable(false);
        this.add(deptText);

        // 班级
        banLabel = new MyLabel(80, 315, 80, 40, "班级");
        this.add(banLabel);
        banText = new MyTextFiled(140, 320, 200, 30);
        banText.setEditable(false);
        this.add(banText);
    }
}
