package cn.slycmiaoxi.object;

/**
 * <p>
 * 用于对象与名称组合的数据结构
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public class TBean<T> {
    
    /**
     * 对象域
     */
    Object object;
    
    /**
     * 数据值
     */
    T value;
    
    public TBean() {
    }
    
    public TBean(Object object, T value) {
        this.object = object;
        this.value = value;
    }
    
    public Object getObject() {
        return object;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setObject(Object object) {
        this.object = object;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
}
