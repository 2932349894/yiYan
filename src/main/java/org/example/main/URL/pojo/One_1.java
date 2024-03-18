package org.example.main.URL.pojo;

import org.example.main.URL.connface;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.example.main.Apis.properties;

public class One_1 implements connface {
    int id = 1;
    String apiUrl = properties.getProperty("api1");
    String[] str = new String[3];
    
    

    public String[] conn() {
        try {
            // 创建 URL 对象
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 POST
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
                JSONObject jsonResponse = new JSONObject(response.toString());
                String hitokoto = jsonResponse.getString("hitokoto");
                String source = jsonResponse.getString("from_who");
                try {
                    source = jsonResponse.getString("from_who");
                } catch (Exception e) {
                }


                System.out.println(id + ":" + apiUrl + ":" + hitokoto + " -- " + source);

                str[0] = "";
                str[1] = "";

                str[0] = hitokoto;
                str[1] = source;
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
        One_1 one1 = new One_1();
        one1.conn();
    }
}