package translate.google.api.utils;

import java.net.URLEncoder;

/**
 * Created by CM on 16/4/3.
 */
public class SimpleTranslate {

    /**
     * 翻译
     *
     * @param to_translate 需要翻译的文字
     * @param to_langage   目标语言
     * @param from_langage 源语言
     * @return
     */
    public static String translate(String to_translate, String to_langage, String from_langage) throws Exception {
        String page, result, hl, sl, q;

        String before_trans = "class=\"t0\">";

        String charset = "UTF-8";

        try {
            hl = URLEncoder.encode(to_langage, charset);
            sl = URLEncoder.encode(from_langage, charset);
            q = URLEncoder.encode(to_translate, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        //构建查询地址
        String query = String.format("https://translate.google.cn/m?hl=%s&sl=%s&q=%s", hl, sl, q);

        //获取查询结果
        page = URLConnectionReader.getText(query);

        //截取需要的结果,从标签头开始,到标签结尾
        result = page.substring(page.indexOf(before_trans) + before_trans.length());
        result = result.split("<")[0];

        //返回翻译结果
        return result;
    }
}
