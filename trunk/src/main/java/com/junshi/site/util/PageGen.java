package com.junshi.site.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import com.junshi.util.FileUtil;


public class PageGen {

    private final String lineBreak = System.getProperty("line.separator");

    private final String headReplacement = "<!--Head_Import_Start-->" + lineBreak
                           + " <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>                         ".trim() + lineBreak
                           + " <title>胡君实（上海）</title>                                                                ".trim() + lineBreak
                           + " <script type='text/javascript' src='/js/googletrack.js?t=#DATETIME#'></script>             ".trim() + lineBreak
                           + " <script type='text/javascript' src='/js/jquery-1.6.4.min.js?t=#DATETIME#'></script>        ".trim() + lineBreak
                           + " <script type='text/javascript' src='/js/page_init.js?t=#DATETIME#'></script>               ".trim() + lineBreak
                           + " <link type='text/css' rel='stylesheet' href='/css/main.css?t=#DATETIME#' />                ".trim() + lineBreak
                           + "<!--Head_Import_End-->";

    private final String pageHeaderReplacement = "<!--Page_Header_Start-->" + lineBreak
                            + " <table border='0' cellspacing='0' cellpadding='0'>                                                        ".trim() + lineBreak
                            + "   <tr>                                                                                                    ".trim() + lineBreak
                            + "     <td>                                                                                                  ".trim() + lineBreak
                            + "       <object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'                                        ".trim() + lineBreak
                            + "           codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0' ".trim() + lineBreak
                            + "           width='969' height='200'>                                                                       ".trim() + lineBreak
                            + "         <param name='movie' value='/images/bcastr3.swf'>                                                  ".trim() + lineBreak
                            + "         <param name='quality' value='high'>                                                               ".trim() + lineBreak
                            + "         <param name='wmode' value='transparent'>                                                          ".trim() + lineBreak
                            + "         <param name='FlashVars' value='bcastr_xml_url=/images/bcastr.xml?t=#DATETIME#'>                                ".trim() + lineBreak
                            + "         <embed src='/images/bcastr3.swf'                                                                  ".trim() + lineBreak
                            + "          FlashVars='bcastr_xml_url=/images/bcastr.xml?t=#DATETIME#' quality='high'                                     ".trim() + lineBreak
                            + "        pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash'     ".trim() + lineBreak
                            + "              width='969' height='200'></embed>                                                            ".trim() + lineBreak
                            + "       </object>                                                                                           ".trim() + lineBreak
                            + "     </td>                                                                                                 ".trim() + lineBreak
                            + "   </tr>                                                                                                   ".trim() + lineBreak
                            + " </table>                                                                                                  ".trim() + lineBreak
                            + "  <table border='0' cellspacing='0' cellpadding='0' id='menu'>                                                  ".trim() + lineBreak
                            + "    <tbody>                                                                                                     ".trim() + lineBreak
                            + "      <tr id='menu_nav'>                                                                                        ".trim() + lineBreak
                            + "        <td class='menu_nav_item'><a style='width: 100%' href='/index.html'><div style='width: 100%'>首页</div></a></td>                          ".trim() + lineBreak
                            + "        <td class='menu_nav_item'><a style='width: 100%' href='/html/solutions/solution_00.html'><div style='width: 100%'>解决方案</div></a></td> ".trim() + lineBreak
                            + "        <td class='menu_nav_item'><a style='width: 100%' href='/html/download.html'><div style='width: 100%'>资料下载</div></a></td>              ".trim() + lineBreak
                            + "        <td class='menu_nav_item'><a style='width: 100%' href='/html/contact.html'><div style='width: 100%'>联系我们</div></a></td>               ".trim() + lineBreak
                            + "      </tr>                                                                                                                                       ".trim() + lineBreak
                            + "    </tbody>                                                                                                                                      ".trim() + lineBreak
                            + "  </table>                                                                                                                                        ".trim() + lineBreak
                            + "<!--Page_Header_End-->";

    private final String pageFooterReplacement = "<!--Page_Footer_Start-->"            + lineBreak
                          + " <table border='0' cellspacing='0' cellpadding='0' id='footer_links'>                       ".trim() + lineBreak
                          + "   <tr>                                                                                     ".trim() + lineBreak
                          + "     <td>                                                                                   ".trim() + lineBreak
                          + "       <a target='_blank' href='www.acrel.cn'><img src='/images/logos/logo_acrel.jpg'></a>                  ".trim() + lineBreak
                          + "       <a target='_blank' href='www.abb.com.cn'><img src='/images/logos/logo_abb.gif'></a>                  ".trim() + lineBreak
                          + "       <a target='_blank' href='www.schneider-electric.com'><img src='/images/logos/logo_schneider.gif'></a>".trim() + lineBreak
                          + "       <a target='_blank' href='www.bjx.com.cn'><img src='/images/logos/logo_bjx.gif'></a>                  ".trim() + lineBreak
                          + "     </td>                                                                                  ".trim() + lineBreak
                          + "   </tr>                                                                                    ".trim() + lineBreak
                          + " </table>                                                                                   ".trim() + lineBreak
                          + " <table border='0' cellspacing='0' cellpadding='0' id='footer'>                             ".trim() + lineBreak
                          + "   <tr>                                                                                     ".trim() + lineBreak
                          + "     <td id='footer_text'>胡君实（上海）©2011 <br>上海嘉定马东工业园区育绿路253号</td>         ".trim() + lineBreak
                          + "   </tr>                                                                                    ".trim() + lineBreak
                          + " </table>                                                                                   ".trim() + lineBreak
                          + "<!--Page_Footer_End-->";

    private final String pageSideMenuReplacement = "<!--Page_Side_Menu_Start-->" + lineBreak
                           + " <div class='menu_nolink'>联系销售</div>                                     ".trim() + lineBreak
                           + " <table border='0' cellspacing='0' cellpadding='0' class='salse_section'>    ".trim() + lineBreak
                           + "   <tr><td>蔡亮&nbsp;&nbsp;</td><td></td></tr>                               ".trim() + lineBreak
                           + "   <tr><td>电话:</td><td>13764193800</td></tr>                               ".trim() + lineBreak
                           + "   <tr><td>QQ:</td><td>1272489503</td></tr>                                  ".trim() + lineBreak
                           + "   <tr><td>Email:</td><td></td></tr>                                         ".trim() + lineBreak
                           + " </table>                                                                    ".trim() + lineBreak
                           + "<!--Page_Side_Menu_End-->";


    public static void main(String[] args) throws IOException {

        PageGen p = new PageGen();
        p.operate();

    }

    public void operate() throws IOException{
        File root = new File("./src/main/webapp/html/");
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
        str = replace(str, "Head_Import", headReplacement);
        str = replace(str, "Page_Header", pageHeaderReplacement);
        str = replace(str, "Page_Footer", pageFooterReplacement);
        str = replace(str, "Page_Side_Menu", pageSideMenuReplacement);
        FileUtil.writeFile(file, str);
    }

    public String replace(String str, String mark, String replacement){
        return str.replaceAll("<!--"+mark+"_Start-->[.\\s\\S]*<!--"+mark+"_End-->", replacement);
    }

}
