package org.example.main.URL.pojo;

import org.example.main.URL.connface;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import static org.example.main.Apis.properties;

public class Zero_0 implements connface {
    public int id = 0;
    //public String apiUrl = "https://tenapi.cn/v2/yiyan?format=json";
    public String apiUrl = properties.getProperty("api0");
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
                JSONObject jsonResponse = new JSONObject("" + response);
                JSONObject data = jsonResponse.getJSONObject("data");
                String hitokoto = data.getString("hitokoto");//内容
                String source = data.getString("source");//来源

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
        Zero_0 zero = new Zero_0();
        zero.conn();
    }

    @Override
    public String toString() {
        return "Zero_0{" +
                "str=" + Arrays.toString(str) +
                '}';
    }
}
