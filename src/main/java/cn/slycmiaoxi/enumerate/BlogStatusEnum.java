package cn.slycmiaoxi.enumerate;

/**
 * <p>
 * 帖子类型状态
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public enum BlogStatusEnum {
    COMMON(0, "普通帖"), TOP(1, "置顶帖"), HOT(2, "精帖"), TOP_HOT(3, "置顶精帖");
    
    private Integer code;
    
    private String message;
    
    BlogStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
}
