package cn.slycmiaoxi.object;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;

/**
 * <p>
 * 哈夫曼树之压缩
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-30
 */
public class Compress implements java.io.Serializable{
    private String str;
    public int[] times=new int[256];
    public String[] HuffmCodes=new String[256];
    public LinkedList<HuffmNode> list = new LinkedList<HuffmNode>();
    //初始化
    public Compress(){
        for(int i=0;i<HuffmCodes.length;i++)
        {
            HuffmCodes[i]="";
        }
    }

    public void countTimes(String path)throws Exception{
        //构造文件输入流
        FileInputStream fis=new FileInputStream(path);
        //读取文件
        int value=fis.read();
        while(value!=-1){
            times[value]++;
            value=fis.read();
        }
        fis.close();
    }

    //构造哈夫曼树
    public HuffmNode createTree(){
        //将次数作为权值构造森林
        for (int i = 0; i < times.length; i++) {
            if(times[i]!=0){
                HuffmNode node = new HuffmNode(times[i],i);
                //将构造好的节点加入到容器中的正确位置
                list.add(getIndex(node), node);
            }
        }

        //将森林（容器中的各个节点）构造成哈夫曼树
        while(list.size()>1) {
            //获取容器中第一个元素（权值最小的节点）
            HuffmNode firstNode =list.removeFirst();
            //获取中新的第一个元素，原来的第一个元素已经被移除了（权值次小的节点）
            HuffmNode secondNode =list.removeFirst();
            //将权值最小的两个节点构造成父节点
            HuffmNode fatherNode =
                    new HuffmNode(firstNode.getData()+secondNode.getData(),-1);
            fatherNode.setLeft(firstNode);
            fatherNode.setRight(secondNode);
            //父节点加入到容器中的正确位置
            list.add(getIndex(fatherNode),fatherNode);
        }
        //返回整颗树的根节点
        return list.getFirst();
    }

    //利用前序遍历获取编码表
    public void getHuffmCode(HuffmNode root,String code){
        //往左走，哈夫曼编码加0
        if(root.getLeft()!=null){
            getHuffmCode(root.getLeft(),code+"0");
        }
        //往右走，哈夫曼编码加1
        if(root.getRight()!=null){
            getHuffmCode(root.getRight(),code+"1");
        }
        //如果是叶子节点，返回该叶子节点的哈夫曼编码
        if(root.getLeft()==null && root.getRight()==null){
            HuffmCodes[root.getIndex()]=code;
        }
    }

    //压缩文件
    public void compress(String path,String destpath)throws Exception{
        //构建文件输出流
        FileOutputStream fos=new FileOutputStream(destpath);
        FileInputStream fis=new FileInputStream(path);

        //读文件，并将对应的哈夫曼编码串接成字符串
        int value=fis.read();
        String str="";
        while(value!=-1){
            str+=HuffmCodes[value];
            value=fis.read();
        }
        this.str=str;
        fis.close();

        String s="";
        while(str.length()>=8){
            s=str.substring(0, 8);
            int b=changeStringToInt(s);
            fos.write(b);
            fos.flush();
            str=str.substring(8);
        }

        int last1=8-str.length();
        for(int i=0;i<last1;i++){
            str+="0";
        }
        s=str.substring(0, 8);
        int d=changeStringToInt(s);
        fos.write(d);
        fos.close();
    }

    //插入元素位置的索引
    public int getIndex(HuffmNode node) {
        for (int i = 0; i < list.size(); i++) {
            if(node.getData()<=list.get(i).getData()){
                return i;
            }
        }
        return list.size();
    }

    //将字符串转换成整数
    public int changeStringToInt(String s){
        int v1=(s.charAt(0)-48)*128;
        int v2=(s.charAt(1)-48)*64;
        int v3=(s.charAt(2)-48)*32;
        int v4=(s.charAt(3)-48)*16;
        int v5=(s.charAt(4)-48)*8;
        int v6=(s.charAt(5)-48)*4;
        int v7=(s.charAt(6)-48)*2;
        int v8=(s.charAt(7)-48)*1;
        return v1+v2+v3+v4+v5+v6+v7+v8;
    }

    public String getStr(){
        return this.str;
    }
}