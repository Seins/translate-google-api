package translate.google.api.controller;

import java.util.Map;

/**
 * Created by apple on 16/4/2.
 */
public abstract class BaseController {

    /**
     * request action execute success
     * @param data
     */
    public void success(Map data){
        data.put("result","0");
        data.put("msg","success");
    }


    /**
     * business error on request
     * @param data
     * @param msg
     */
    public void failed(Map data,String msg){
        data.put("result","-1");
        data.put("msg",msg);
    }


    /**
     * request occur error
     * @param data
     * @param ex
     */
    public void failed(Map data,Throwable ex){
        data.put("result","-2");
        data.put("msg",ex.getMessage());
    }
}
