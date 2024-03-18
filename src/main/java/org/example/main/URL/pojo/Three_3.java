package org.example.main.URL.pojo;

import org.example.main.URL.connface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.main.Apis.properties;

public class Three_3 implements connface {
    int id = 3;
    String apiUrl = properties.getProperty("api3");
    String[] str = new String[3];



    public static void main(String[] args) {
        Three_3 three3 = new Three_3();
        three3.conn();
    }

    @Override
    public String[] conn() {
        try {
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            

            // 设置请求方法为 
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            // 设置请求参数


            // 获取响应状态码
            int responseCode = connection.getResponseCode();


            // 如果响应状态码为 200，表示请求成功
            if (responseCode == 200) {
                // 读取响应数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder response = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                //关闭流
                reader.close();
                //关闭连接
                connection.disconnect();

                String s = removeHtmlTags("" + response);
                System.out.println(id + ":" + apiUrl + ":" + s);

                str[0] = "";
                str[1] = "";

                str[0] = s;
                str[1] = "";
                str[2] = "" + id;
                return str;
            }
        } catch (Exception e) {
        }
        str[0] = "出错了" + id;
        str[1] = "";
        str[2] = "" + id;
        return str;
    }


    public String removeHtmlTags(String htmlString) {
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlString);
        return matcher.replaceAll("");
    }

    @Override
    public int getId() {
        return id;
    }


}
