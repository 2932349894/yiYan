package org.example.main.URL.pojo;

import org.example.main.URL.connface;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import static org.example.main.Apis.properties;

public class Twelve_12 implements connface {
    public int id = 12;
    public String apiUrl = properties.getProperty("api12");
    String[] str = new String[3];


    public String[] conn() {
        try {
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);



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
                // 解析 JSON 数据
                JSONObject jsonResponse = new JSONObject(""+response);
                String hitokoto = jsonResponse.getString("pyq");//内容



                System.out.println(id + ":" + apiUrl + ":" + hitokoto );


                str[0] = "";
                str[1] = "";


                str[0] = hitokoto;
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

    @Override
    public int getId() {
        return id;
    }

    public static void main(String[] args) {
        Twelve_12 twelve12 = new Twelve_12();
        twelve12.conn();
    }

    @Override
    public String toString() {
        return "Zero_0{" +
                "str=" + Arrays.toString(str) +
                '}';
    }
}