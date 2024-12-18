package com.bookAdmi;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MyButton extends JButton {
    public MyButton(int x, int y, int width, int height, String title) {
        setText(title);
        setBounds(x, y, width, height);
        setMargin(new Insets(5, 5, 5, 5));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));
        setBackground(Color.CYAN);

        try {
            URL imageUrl = getClass().getResource("/com/resource/Button.jpg");
            ImageIcon icon = new ImageIcon(imageUrl);
            Image image = icon.getImage().getScaledInstance(width + 25, height + 25, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(image));
            setHorizontalTextPosition(SwingConstants.CENTER);
            setVerticalTextPosition(SwingConstants.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
