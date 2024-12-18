package com.bookAdmi;

import javax.swing.*;
import java.awt.*;

/**
 * BackgroundPanel 类
 * 用于设置带有背景图的面板
 */
public class MyBackgroundPanel extends JPanel {
    private Image backgroundImage;

    public MyBackgroundPanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(null); // 使组件可以自由定位
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景图像
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
