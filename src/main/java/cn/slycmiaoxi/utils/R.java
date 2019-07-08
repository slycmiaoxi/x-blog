package cn.slycmiaoxi.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * map 扩展
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    
    public R() {
        put("code", 1);
    }
    
    public static R error() {
        return error(500, "error?");
    }
    
    public static R error(String msg) {
        return error(500, msg);
    }
    
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
    
    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }
    
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }
    
    public static R ok() {
        return new R();
    }
    
    public static R fail() {
        R r = new R();
        r.put("code", 0);
        return r;
    }
    
    public static R code(int i) {
        R r = new R();
        r.put("code", i);
        return r;
    }
    
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
