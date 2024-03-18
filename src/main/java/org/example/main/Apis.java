package org.example.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Apis {
    public static Properties properties = new Properties();;

    
    static{
        
        InputStream inputStream = null;
        try {
            // 通过类加载器加载资源文件
            inputStream = Apis.class.getClassLoader().getResourceAsStream("apis.properties");

            if (inputStream != null) {
                // 加载配置文件
                properties.load(new InputStreamReader(inputStream, "UTF-8"));

                // 读取配置项

                System.out.println("api读取成功");
                System.out.println(properties);
                System.out.println("\n");

            } else {
                System.out.println("资源文件未找到！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
