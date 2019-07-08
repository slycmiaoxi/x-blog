package cn.slycmiaoxi.enumerate;

/**
 * <p>
 * 用户角色类型状态
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public enum AuthorEnum {
    VISIT(1, "游客"), USER(2, "普通用户"), ADMIN(3, "管理员");

    private Integer code;

    private String message;

    AuthorEnum(Integer code, String message) {
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
