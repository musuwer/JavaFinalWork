package com.studentAdmi;

import javax.swing.*;
import java.awt.*;

/**
 * MyTextFiled 类
 * 自定义文本框类，继承自 JTextField，用于统一设置文本框的样式和属性。
 */
public class MyTextFiled extends JTextField {

    /**
     * 构造方法
     * 初始化文本框的位置、大小和样式。
     *
     * @param x 文本框的 X 坐标
     * @param y 文本框的 Y 坐标
     * @param width 文本框的宽度
     * @param height 文本框的高度
     */
    MyTextFiled(int x, int y, int width, int height) {
        super(); // 调用父类构造方法
        setBounds(x, y, width, height); // 设置文本框的位置和大小

        setBorder(null); // 去除文本框的默认边框
        setFont(new Font("微软雅黑", Font.BOLD, 22)); // 设置文本框内字体为加粗的微软雅黑，大小为22
        setForeground(Color.BLUE); // 设置文本框内文字的颜色为蓝色
    }
}
