package cn.slycmiaoxi.object;

/**
 * <p>
 * 哈夫曼树数据结构
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-15
 */
public class HuffmNode implements java.io.Serializable {
    
    /**
     * 数据域
     */
    private int data;
    
    /**
     * 左子树
     */
    private HuffmNode left;
    
    /**
     * 右子树
     */
    private HuffmNode right;
    
    /**
     * 索引
     */
    private int index;
    
    public HuffmNode(int data) {
        this.data = data;
    }
    
    public HuffmNode(int data, int index) {
        this.data = data;
        this.index = index;
    }
    
    public int getData() {
        return data;
    }
    
    public void setData(int data) {
        this.data = data;
    }
    
    public HuffmNode getLeft() {
        return left;
    }
    
    public void setLeft(HuffmNode left) {
        this.left = left;
    }
    
    public HuffmNode getRight() {
        return right;
    }
    
    public void setRight(HuffmNode right) {
        this.right = right;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
}
