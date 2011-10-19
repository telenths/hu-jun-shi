package com.junshi.site.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import com.junshi.util.FileUtil;

public class PageGen {

    private final String lineBreak = System.getProperty("line.separator");

    private final String headReplacement = "<!--Head_Import_Start-->" + lineBreak
                           + " <script type='text/javascript' src='/junshi/js/googletrack.js'></script>      " + lineBreak
                           + " <script type='text/javascript' src='/junshi/js/jquery-1.6.4.min.js'></script> " + lineBreak
                           + " <script type='text/javascript' src='/junshi/js/page_init.js'></script>         " + lineBreak
                           + " <link type='text/css' rel='stylesheet' href='/junshi/css/main.css' />         " + lineBreak
                           + "<!--Head_Import_End-->";


    public static void main(String[] args) throws IOException {

        PageGen p = new PageGen();
        p.operate();

    }

    public void operate() throws IOException{
        File root = new File("./src/main/webapp/junshi/html/");
        operateFolder(root);
    }

    public void operateFolder(File file) throws IOException {

        File[] fileList = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || (pathname.isFile() && pathname.getName().endsWith(".html"));
            }
        });

        if(fileList == null )
            return;

        for (File f : fileList) {
            if (f.isFile()) {
                refineFile(f);
            } else {
                operateFolder(f);
            }
        }
    }

    public void refineFile(File file) throws IOException{
        System.out.println(" ---- refining " + file.getAbsolutePath());

        if(!file.getName().endsWith(".html"))
            return;

        String str = FileUtil.readFile(file);
        str = replaceHead(str);
        FileUtil.writeFile(file, str);
    }

    public String replaceHead(String str){
        return str.replaceAll("<!--Head_Import_Start-->[.\\s\\S]*<!--Head_Import_End-->", headReplacement);
    }



}
