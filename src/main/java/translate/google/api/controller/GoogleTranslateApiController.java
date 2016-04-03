package translate.google.api.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import translate.google.api.utils.SimpleTranslate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CM on 16/4/2.
 */

@Controller
@RequestMapping("translate/google/")
public class GoogleTranslateApiController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleTranslateApiController.class);

    /**
     *
     * @param modelMap 数据容器
     * @param value 待翻译的文字
     * @param lang 当前语言
     * @param targetLang 翻译目标语言
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{lang}/{targetLang}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String translate(ModelMap modelMap, @RequestParam(value = "value") String value,
                            @PathVariable(value = "lang") String lang,
                            @PathVariable(value = "targetLang") String targetLang) {
        modelMap.clear();
        LOGGER.info("lang:{},target lang:{},value :{}", lang, targetLang, value);

        try {
            Map data = new HashMap();
            data.put("source", value);
            data.put("lang", lang);
            data.put("target lang", targetLang);

            String result = SimpleTranslate.translate(value,targetLang,lang);
            if (StringUtils.isEmpty(result)) {
                throw new RuntimeException("translate failed!");
            }
            data.put("translation", result);
            modelMap.put("data", data);
            this.success(modelMap);

        } catch (Exception e) {
            this.failed(modelMap, "translate occur error,try again!");
            LOGGER.error("translate occur error", e);
        }

        return JSON.toJSONString(modelMap);
    }

}
