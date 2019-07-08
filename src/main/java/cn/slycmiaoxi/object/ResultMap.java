package cn.slycmiaoxi.object;

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
public class ResultMap {
    private Map<String, Object> map;
    
    public ResultMap() {
        this.map = new HashMap<String, Object>(1);
    }
    
    public ResultMap(String key, Object value) {
        this.map = new HashMap<String, Object>(1);
        this.map.put(key, value);
    }
    
    public ResultMap add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
    
    public ResultMap addAll(Map<String, Object> map) {
        this.map.putAll(map);
        return this;
    }
    
    public ResultMap success(String message) {
        this.map.put("success", 1);
        this.map.put("message", message);
        return this;
    }
    
    public ResultMap success() {
        this.map.put("success", 1);
        this.map.put("message", "操作成功！");
        return this;
    }
    
    public ResultMap failed(String message) {
        this.map.put("success", 0);
        this.map.put("message", message);
        return this;
    }
    
    public ResultMap failed() {
        this.map.put("success", 0);
        this.map.put("message", "操作失败！");
        return this;
    }
    
    public Map<String, Object> toMap() {
        return this.map;
    }
}
