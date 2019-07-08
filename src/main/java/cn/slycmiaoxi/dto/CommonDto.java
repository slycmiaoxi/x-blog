package cn.slycmiaoxi.dto;

/**
 * @Auther: slycmiaoxi
 * @Date: 2019/6/17 22:31
 * @Description:
 */
public class CommonDto {
    
    private String id;
    
    private String name;
    
    public CommonDto() {
        
    }

    public CommonDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
