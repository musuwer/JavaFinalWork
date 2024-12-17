// 定义包名 com.studentAdmi
package com.studentAdmi;

import java.awt.Color; // 引入 Color 类用于设置颜色
import java.awt.event.ActionEvent; // 引入 ActionEvent 用于事件处理
import java.awt.event.ActionListener; // 引入 ActionListener 接口用于实现事件监听

import javax.swing.ImageIcon; // 引入 ImageIcon 用于设置窗口图标
import javax.swing.JFrame; // 引入 JFrame 类用于创建窗口
import javax.swing.JOptionPane; // 引入 JOptionPane 类用于弹出提示框

// 定义修改学生信息窗口类 ChangedWin，继承自 JFrame
public class ChangedWin extends JFrame {
    // 定义标签和文本框
    private MyLabel nameLabel; // 姓名标签
    private MyTextFiled nameText; // 姓名输入框

    private MyLabel sexLabel; // 性别标签
    private MyTextFiled sexText; // 性别输入框

    private MyLabel placeLabel; // 籍贯标签
    private MyTextFiled placeText; // 籍贯输入框

    private MyLabel codeLabel; // 学号标签
    private MyTextFiled codeText; // 学号输入框

    private MyLabel deptLabel; // 系别标签
    private MyTextFiled deptText; // 系别输入框

    private MyLabel banLabel; // 班级标签
    private MyTextFiled banText; // 班级输入框

    private MyButton OKButton; // 确认按钮

    // 构造方法，初始化窗口和组件
    public ChangedWin(String name, String sex, String place, String code, String dept, String ban) {
        super(); // 调用父类构造方法
        this.setIconImage(Main.icon.getImage()); // 设置窗口图标
        this.setSize(500, 600); // 设置窗口大小
        this.setLayout(null); // 清空布局器
        this.setResizable(false); // 禁止用户调整窗口大小
        this.setVisible(true); // 设置窗口可见
        this.setLocation(450, 200); // 设置窗口生成位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置关闭操作，仅销毁当前窗口
        this.setTitle("修改学生信息"); // 设置窗口标题
        this.getContentPane().setBackground(Color.pink); // 设置背景颜色

        this.addPart(); // 添加组件

        // 设置初始值
        this.nameText.setText(name);
        this.sexText.setText(sex);
        this.placeText.setText(place);
        this.codeText.setText(code); // 学号设置为不可编辑
        this.deptText.setText(dept);
        this.banText.setText(ban);

        // 初始化确认按钮
        OKButton = new MyButton(200, 400, 80, 40, "确认");
        this.add(OKButton);

        // 添加确认按钮的监听器
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 校验输入框内容是否为空或包含非法空格
                if (nameText.getText().length() == 0 || nameText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "名字未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (sexText.getText().length() == 0 || sexText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "性别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (placeText.getText().length() == 0 || placeText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "籍贯未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (codeText.getText().length() == 0 || codeText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "学号未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (deptText.getText().length() == 0 || deptText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "系别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (banText.getText().length() == 0 || banText.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "班别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE);
                } else if (sexText.getText().equals("男") || sexText.getText().equals("女")) {
                    // 修改学生信息
                    Main.students.get(codeText.getText()).setName(nameText.getText());
                    Main.students.get(codeText.getText()).setSex(sexText.getText());
                    Main.students.get(codeText.getText()).setPlace(placeText.getText());
                    Main.students.get(codeText.getText()).setDept(deptText.getText());
                    Main.students.get(codeText.getText()).setBan(banText.getText());
                    JOptionPane.showMessageDialog(null, "修改完成！", "提示", JOptionPane.WARNING_MESSAGE);
                    PrimePanel.refreshFile(); // 刷新文件
                } else {
                    JOptionPane.showMessageDialog(null, "请输入正确的性别", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // 添加组件的方法
    private void addPart() {
        // 初始化并添加标签和文本框
        nameLabel = new MyLabel(80, 15, 80, 40, "姓名");
        this.add(nameLabel);
        nameText = new MyTextFiled(140, 20, 200, 30);
        this.add(nameText);

        sexLabel = new MyLabel(80, 75, 80, 40, "性别");
        this.add(sexLabel);
        sexText = new MyTextFiled(140, 80, 200, 30);
        this.add(sexText);

        placeLabel = new MyLabel(80, 135, 80, 40, "籍贯");
        this.add(placeLabel);
        placeText = new MyTextFiled(140, 140, 200, 30);
        this.add(placeText);

        codeLabel = new MyLabel(80, 195, 80, 40, "学号");
        this.add(codeLabel);
        codeText = new MyTextFiled(140, 200, 200, 30);
        codeText.setEditable(false); // 学号不可编辑
        this.add(codeText);

        deptLabel = new MyLabel(80, 255, 80, 40, "系别");
        this.add(deptLabel);
        deptText = new MyTextFiled(140, 260, 200, 30);
        this.add(deptText);

        banLabel = new MyLabel(80, 315, 80, 40, "班级");
        this.add(banLabel);
        banText = new MyTextFiled(140, 320, 200, 30);
        this.add(banText);
    }
}
