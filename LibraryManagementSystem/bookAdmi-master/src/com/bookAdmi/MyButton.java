package com.bookAdmi;

import javax.swing.*;
import java.awt.*;

/**
 * MyButton 类
 * 自定义按钮类，继承自 JButton，用于统一设置图书管理系统中按钮的样式和属性。
 */
public class MyButton extends JButton {

    /**
     * 构造方法
     * 初始化按钮位置、大小、标题和样式。
     *
     * @param x 按钮的 X 坐标
     * @param y 按钮的 Y 坐标
     * @param width 按钮的宽度
     * @param height 按钮的高度
     * @param title 按钮的标题
     */
    MyButton(int x, int y, int width, int height, String title) {
        setText(title); // 设置按钮标题
        setBounds(x, y, width, height); // 设置按钮位置和大小

        // 设置按钮内边距
        setMargin(new Insets(0, 0, 0, 0));

        // 是否使按钮透明（取消填充区域）
        // setContentAreaFilled(false);

        // 设置是否绘制按钮边框
        setBorderPainted(false);

        // 设置按钮字体样式
        setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));

        // 设置按钮背景颜色
        setBackground(Color.CYAN); // 按钮背景为青色
    }
}
