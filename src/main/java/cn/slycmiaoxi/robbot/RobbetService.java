package cn.slycmiaoxi.robbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 图灵机器人service处理
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Slf4j
public class RobbetService {
    /**
     * API地址
     */
    private static String baseUrl = "http://www.tuling123.com/openapi/api";
    
    /**
     * APIKEY
     */
    private static String APIKEY = "2c55436d70aa411a8116062502613e54";
    
    /**
     * 完整请求地址
     */
    private static String queryUrl = baseUrl + "?key=" + APIKEY + "&info=";
    
    /**
     * 返回结果
     * 
     * @param info 请求文字
     * @return 答复的结果
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    public static String getTheResult(String info) {
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(queryUrl + new String(info.getBytes("UTF-8")));
            URLConnection con = url.openConnection();
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        }
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            log.error("error", e);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("error", e);
        }
        finally {
            RobbetUtil.closeDB(in);
        }
        return sb.toString();
    }
    
}
