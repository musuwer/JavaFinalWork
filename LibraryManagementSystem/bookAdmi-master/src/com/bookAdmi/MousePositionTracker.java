package com.bookAdmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MousePositionTracker {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Click Position Tracker");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建一个自定义的面板，监听鼠标点击事件
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString("Click anywhere on the panel", 10, 20);
            }
        };

        // 添加 MouseListener 监听鼠标点击事件
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 获取鼠标点击的位置
                int x = e.getX();
                int y = e.getY();

                // 打印鼠标点击的位置
                System.out.println("Mouse clicked at: x = " + x + ", y = " + y);
            }
        });

        // 设置面板并显示窗口
        frame.add(panel);
        frame.setVisible(true);
    }
}
