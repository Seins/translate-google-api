package translate.google.api.utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by CM on 16/4/3.
 */
public class URLConnectionReader {

    /**
     * 获取URL请求的内容,get方式,超时时间为30秒
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String getText(String url) throws Exception {
        //String charset = java.nio.charset.StandardCharsets.UTF_8.name();//"UTF-8"
        String charset = "UTF-8";

        URLConnection connection = new URL(url).openConnection();

        connection.setRequestProperty("Accept-Charset", charset);
        connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30)");
        connection.setConnectTimeout(30000);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }
}
