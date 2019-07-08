package cn.slycmiaoxi.object;

/**
 * <p>
 * 链表结构
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
public class ListNode<T> implements java.io.Serializable {
    
    /**
     * 数值域对象
     */
    public T val;
    
    /**
     * 下一个结点
     */
    public ListNode next;
    
    public ListNode(T val) {
        this.val = val;
    }
    
}
