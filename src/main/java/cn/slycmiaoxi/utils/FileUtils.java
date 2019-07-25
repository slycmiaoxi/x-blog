package cn.slycmiaoxi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String FILE_ROOT_PATH="/usr/local/software/tempHuffer/";

    //向文件中写入字符串
    public synchronized void writeStringToFile(String str,String url){
        FileWriter writer;
        File txt=new File(url);
        if(txt.exists()){
            txt.delete();
        }
        try {
            txt.createNewFile();
            writer = new FileWriter(url);
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取文件中内容
    public synchronized String readStringToFile(String url){
        BufferedReader br=null;
        StringBuffer sb=null;
        try {

            FileReader fr=new FileReader(url);
            br=new BufferedReader(fr);

            String s="";
            sb=new StringBuffer();
            while((s=br.readLine())!=null){
                sb.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public synchronized String readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        StringBuilder sb=null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            sb=new StringBuilder();
            while ((tempbyte = in.read()) != -1) {
                sb.append(String.valueOf(tempbyte));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }
    
}
