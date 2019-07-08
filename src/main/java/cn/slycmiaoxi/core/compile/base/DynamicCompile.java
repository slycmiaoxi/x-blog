package cn.slycmiaoxi.core.compile.base;

/**
 * <p>
 * 动态编译
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-16
 */
public interface DynamicCompile {
    
    /**
     * 动态编译代码
     * 
     * @param originStr 源代码
     * @return 编译结果
     * @author slycmiaoxi
     * @since 2019-06-16
     */
    String getCompileResult(String originStr);
}
