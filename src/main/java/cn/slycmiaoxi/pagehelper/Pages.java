package cn.slycmiaoxi.pagehelper;

import java.util.List;

/**
 * <p>
 * 分页对象，与page对象类似
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
public class Pages {
    
    private int currentPage;
    
    private int pageSize;
    
    private int recordCount;
    
    private List<?> recordList;
    
    private int pageCount;
    
    private int beginPageIndex;
    
    private int endPageIndex;
    
    public Pages(int currentPage, int pageSize, int recordCount, List<?> recordList) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.recordList = recordList;
        
        pageCount = (recordCount + pageSize - 1) / pageSize;
        
        if (pageCount <= 10) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        }
        
        else {
            
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }
    }
    
    public List<?> getRecordList() {
        return recordList;
    }
    
    public void setRecordList(List<?> recordList) {
        this.recordList = recordList;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public int getPageCount() {
        return pageCount;
    }
    
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getRecordCount() {
        return recordCount;
    }
    
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
    
    public int getBeginPageIndex() {
        return beginPageIndex;
    }
    
    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }
    
    public int getEndPageIndex() {
        return endPageIndex;
    }
    
    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }
}
