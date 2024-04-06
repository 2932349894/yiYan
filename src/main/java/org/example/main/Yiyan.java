package org.example.main;

import org.example.main.URL.ALL;
import org.example.main.URL.connface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class Yiyan {
    JFrame frame;
    private JLabel label = null;
    private Timer fadeTimer;
    private float alpha = 0.0f;
    ALL suiji = new ALL();
    boolean networkState = true;


    boolean juZhong = true;
    boolean zuoZhe = false;


    public void judge() {
        // 创建一个 JFrame 对象，表示窗口
        frame = new JFrame("自适应窗口大小");


        // 设置窗口的类型为 Type.UTILITY，以在启动时不显示在任务栏上
        frame.setType(JFrame.Type.UTILITY);

        // 设置窗口的内容面板为透明
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0)); // 设置背景透明


        // 创建一个标签来显示文字
        label = new JLabel("  ");


        // 设置文本颜色为RGB颜色(0, 255, 191)
        label.setForeground(new Color(0, 255, 191));

        Font customFont = new Font("最深的夜里最温柔", Font.PLAIN, 26);
        label.setFont(customFont);

        // 添加标签到容器
        frame.add(label, BorderLayout.CENTER);


        // 自动调整窗口大小以适应标签文本
        frame.pack();
        //-------------------------------------------------


        // 设置窗口关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 使窗口居中显示
        frame.setLocationRelativeTo(null);

        // 创建一个鼠标监听器来实现窗口的拖动
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point offset;

            @Override
            public void mousePressed(MouseEvent e) {
                offset = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - offset.x;
                int y = e.getYOnScreen() - offset.y;
                frame.setLocation(x, y);

                //屏幕大小
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int screenWidth = screenSize.width;

                //窗口在屏幕中的位置



            }
        };

        // 在容器上添加鼠标监听器，以实现拖动窗口
        frame.addMouseListener(mouseAdapter);
        frame.addMouseMotionListener(mouseAdapter);

        // 设置窗口始终置顶
        frame.setAlwaysOnTop(false);

//-------------
        // 创建一个右击菜单
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("关闭居中");
        popupMenu.add(menuItem);
        JMenuItem menuItem2 = new JMenuItem("开启作者");
        popupMenu.add(menuItem2);


        // 在窗口上添加鼠标适配器来处理右击事件
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(frame, e.getX(), e.getY());
                }
            }
        });

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (juZhong == true) {  //点击时，如果当前居中，则设置为不居中
                    juZhong = false;
                    menuItem.setText("设置为居中");

                } else {
                    juZhong = true;
                    menuItem.setText("关闭居中");
                }
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (zuoZhe == true) {
                    zuoZhe = false;
                    menuItem2.setText("关闭作者");

                } else {
                    zuoZhe = true;
                    menuItem2.setText("开启作者");
                }
            }
        });

//-----------------------

        //建并启动一个定时器来控制淡入淡出效果
        change();





        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        show();

                        Thread.sleep(generateRandomNumber());

                    }
                } catch (Exception e) {
                    System.exit(0);
                }
            }
        }).start();
        // 使窗口可见
        frame.setVisible(true);

    }

    //显示
    public void show()  {
        try {
            // 淡出效果
            fadeOut();
            String[] yiyan;
            do {
                connface suiji1 = suiji.suiji();
                yiyan = suiji1.conn();// 在定时任务中更新文本
                //如果错误则检查网络状态

                if (yiyan[0].contains("出错了")) {
                    yiyan[0] = "网络错误";
                    if (!networkState) {
                        return;
                    }
                    networkState = false;

                } else {
                    networkState = true;
                }

            } while ((yiyan[0].length()) > 60);

            if (zuoZhe && yiyan[1].length() > 0) {
                label.setText(yiyan[0] + " -- " + yiyan[1]);
            } else {
                label.setText(" " + yiyan[0]);
            }
            
            BufferedWriter bufferedWriter = null;
            FileWriter fileWriter = null;
            File yYText;
            if(!yiyan[0].equals("网路错误")){

                try {
                    yYText = new File("一言.txt");
                    fileWriter = new FileWriter(yYText, true);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    if(yiyan[1].length() > 0){
                        bufferedWriter.write(yiyan[2]+" : " + yiyan[0] +" -- "+ yiyan[1] + "\n");
                    }else{
                        bufferedWriter.write(yiyan[2]+" : " + yiyan[0] + "\n");
                    }


                    bufferedWriter.flush();
                } catch (Exception e) {
                } finally {
                    bufferedWriter.close();
                    fileWriter.close();
                }
            }else {
                try {
                    yYText = new File("log.txt");
                    fileWriter = new FileWriter(yYText, true);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(yiyan[2]+" : " + yiyan[0] +"\n");
                    bufferedWriter.flush();
                } catch (Exception e) {
                } finally {
                    bufferedWriter.close();
                    fileWriter.close();
                } 
            }
            


            // 淡入效果
            fadeIn();
            frame.pack();


            if (juZhong) {
                //窗口
                int width = frame.getWidth();

                // 获取屏幕大小
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int screenWidth = screenSize.width;

                // 计算窗口水平中心点
                int centerX = (screenWidth - width) / 2;

                // 获取窗口在屏幕上的位置
                Point windowLocation = frame.getLocationOnScreen();

                int windowY = windowLocation.y;

                // 计算窗口的新位置，使其水平居中
                int newWindowX = centerX;

                frame.setLocation(newWindowX, windowY);
            }
            System.gc();

        } catch (Exception ex) {
        }
    }

    private void change() {
        fadeTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 逐渐增加或减小透明度
                alpha += 0.02f; // 调整透明度的步进值

                // 控制透明度在0到1之间
                alpha = Math.min(1.0f, alpha);
                alpha = Math.max(0.0f, alpha);

                label.setForeground(new Color(0, 255, 191, Math.round(alpha * 255)));

                if (alpha >= 1.0f || alpha <= 0.0f) {
                    // 到达完全不透明或完全透明时停止定时器
                    fadeTimer.stop();
                }
            }
        });
        fadeTimer.setRepeats(true);
    }


    //随机时间
    public long generateRandomNumber() {
        Random random = new Random();
        int lowerBound = 5 * 1000 * 60;
        int upperBound = 30 * 1000 * 60;
        // 生成一个介于 lowerBound（包含）和 upperBound（不包含）之间的随机数
        int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;

        if (!networkState) {  //如果网路不可用则每十秒刷新一次
            return 10000;
        }
        System.out.println(randomNumber / 1000/60+"分钟");
        return randomNumber;
    }

    private void fadeOut() {
        alpha = 1.0f;
        fadeTimer.start();
    }

    private void fadeIn() {
        alpha = 0.0f;
        fadeTimer.start();
    }
}
