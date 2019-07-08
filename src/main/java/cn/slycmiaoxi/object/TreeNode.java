package cn.slycmiaoxi.object;

/**
 * <p>
 * 二叉树数据结构
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public class TreeNode<T> implements java.io.Serializable {
    
    /**
     * 数值域
     */
    public T value;
    
    public TreeNode(T value) {
        this.value = value;
    }
    
    /**
     * 左子树
     */
    public TreeNode left;
    
    /**
     * 右子树
     */
    public TreeNode right;
    
}
