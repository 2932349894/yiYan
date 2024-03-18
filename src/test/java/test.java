import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class test {
    public static Map<String, String> mapApi;

    @Test
    public void test1() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            // 通过类加载器加载资源文件
            inputStream = test.class.getClassLoader().getResourceAsStream("apis.properties");

            if (inputStream != null) {
                // 加载配置文件
                properties.load(new InputStreamReader(inputStream, "UTF-8"));

                // 读取配置项
                mapApi = new HashMap<>() ;
                for(int i = 0;i < properties.size() - 1;i++){
                    mapApi.put("api"+i,properties.getProperty("api"+i));
                }
                System.out.println(mapApi);

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
