package cn.slycmiaoxi.object;

import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 增强版，控制层数据返回形式对象
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientObject<T> implements Serializable {
    
    /**
     * 返回码
     */
    int code;
    
    /**
     * 返回提示信息
     */
    String msg;
    
    /**
     * 返回数据
     */
    T obj;
}