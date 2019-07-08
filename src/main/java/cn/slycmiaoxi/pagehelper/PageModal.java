package cn.slycmiaoxi.pagehelper;

import java.util.List;

/**
 * <p>
 * 分页模型域，用于数据与分页展示
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
public class PageModal {
    
    /**
     * 集合数据
     */
    private List<?> list;
    
    /**
     * 分页对象
     */
    private Page page;
    
    public PageModal() {
    }
    
    public PageModal(List<?> list, Page page) {
        this.list = list;
        this.page = page;
    }
    
    public List<?> getList() {
        return list;
    }
    
    public void setList(List<?> list) {
        this.list = list;
    }
    
    public Page getPage() {
        return page;
    }
    
    public void setPage(Page page) {
        this.page = page;
    }
}
