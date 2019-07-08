package cn.slycmiaoxi.pagehelper;

/**
 * <p>
 * 分页对象（包含分页最基本的属性)
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
public class Page {
    /**
     * 页的起始页
     */
    private int beginIndex;
    
    /**
     * 当前页
     */
    private int currentPage;
    
    /**
     * 每个页面大小
     */
    private int everyPage;
    
    /**
     * 是否有下一页
     */
    public boolean hasNextPage;
    
    /**
     * 是否有上一页
     */
    public boolean hasPrePage;
    
    /**
     * 总记录数
     */
    private int totalCount;
    
    /**
     * 总页数
     */
    private int totalPage;
    
    public Page(int currentPage, int everyPage) {
        super();
        this.currentPage = currentPage;
        this.everyPage = everyPage;
    }
    
    public Page(int everyPage, int totalCount, int totalPage, int currentPage, int beginIndex, boolean hasPrePage,
        boolean hasNextPage) {
        this.everyPage = everyPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.beginIndex = beginIndex;
        this.hasPrePage = hasPrePage;
        this.hasNextPage = hasNextPage;
    }
    
    public Page() {
    }
    
    public int getBeginIndex() {
        return beginIndex;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public int getEveryPage() {
        return everyPage;
    }
    
    public int getTotalCount() {
        return totalCount;
    }
    
    public int getTotalPage() {
        return totalPage;
    }
    
    public boolean isHasNextPage() {
        return hasNextPage;
    }
    
    public boolean isHasPrePage() {
        return hasPrePage;
    }
    
    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public void setEveryPage(int everyPage) {
        this.everyPage = everyPage;
    }
    
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
    
    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }
    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
}
