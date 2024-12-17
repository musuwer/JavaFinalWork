package com.bookAdmi;

import javax.swing.*;
import java.awt.*;

/**
 * MyLabel 类
 * 自定义标签类，继承自 JLabel，用于统一设置图书管理系统中标签的样式和属性。
 */
public class MyLabel extends JLabel {

    /**
     * 构造方法
     * 初始化标签的位置、大小、文本和样式。
     *
     * @param x 标签的 X 坐标
     * @param y 标签的 Y 坐标
     * @param width 标签的宽度
     * @param height 标签的高度
     * @param text 标签显示的文本内容
     */
    MyLabel(int x, int y, int width, int height, String text) {
        super(); // 调用父类构造方法
        setBounds(x, y, width, height); // 设置标签位置和大小
        setText(text); // 设置标签显示的文本内容

        // 设置标签字体样式和大小
        setFont(new Font("微软雅黑", Font.BOLD, 25));

        // 设置标签文本颜色
        setForeground(Color.black); // 文本颜色为黑色
    }
}
