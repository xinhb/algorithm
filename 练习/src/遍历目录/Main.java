package 遍历目录;

import java.io.File;

/**
 * 需求：遍历某个目录下的所有文件、包含子目录下的文件
 * */

public class Main {
    public static void main(String[] args) {
        //1、传入要遍历路径
        String path = "E:\\战春招实习";
        //2、路径的file对象
       // System.out.println(path);
        File file = new File(path);
        //System.out.println(file);
        //3、传入file对象，实现功能
        func(file);
    }
    private static void func(File file){
        //1、获取根路径下的所有文件对象
        File[] fs = file.listFiles();
        //2、遍历当前层的文件对象，判断是目录还是文件
        for(File f : fs){
            //是目录就递归打印其下的文件
            if(f.isDirectory()){
                func(f);
            }
            if(f.isFile()){
                System.out.println(f);
            }
        }
    }
}
