package cn.slycmiaoxi.enumerate;

import lombok.Getter;

/**
 * <p>
 * 返回值标记量
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
@Getter
public enum Enable {
    NO(0), YES(1);
    
    private int value;
    
    Enable(int value) {
        this.value = value;
    }
}
