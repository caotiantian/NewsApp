package utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 2017/12/14.
 */

public class NetWorkUtil {

    public String getjson(String json_url){

        URL url = null;
        HttpURLConnection connection = null;
        String json_str = "";

        try {
            url = new URL(json_url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int code = connection.getResponseCode();
            if (code == 200){
                InputStream inputStream = connection.getInputStream();
               byte[] bytes = new byte[1024];
               int len = 0;
               while ((len = inputStream.read(bytes))!=-1){

                   json_str+= new String(bytes,0,len);
               }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }

        return json_str;
    }

}
