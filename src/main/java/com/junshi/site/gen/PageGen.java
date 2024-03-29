package com.junshi.site.gen;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import com.junshi.util.FileUtil;


public class PageGen {

    private final String lineBreak = System.getProperty("line.separator");
    private final String fileSeparator = System.getProperty("file.separator");

    private final String headReplacement = "<!--Head_Import_Start-->" + lineBreak
                           + " <meta name='keywords' content='电表,智能电网,解决方案,安科瑞,传感器,智能电力监控仪表,智能马达控制器,电量传感器,导轨式安装电表,火灾监控装置,数显继电器,电力监控系统,能耗监测系统' />".trim() + lineBreak
                           + " <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>                         ".trim() + lineBreak
//                           + " <title>上海安科瑞能源管理有限公司</title>                                                                ".trim() + lineBreak
                           + " <title>蔡亮</title>                                                                ".trim() + lineBreak
                           + " <link type='text/css' rel='stylesheet' href='/css/main.css?t=#DATETIME#' />                ".trim() + lineBreak
                           + " <script type='text/javascript' src='/js/googletrack.js?t=#DATETIME#'></script>             ".trim() + lineBreak
                           + " <script type='text/javascript' src='/js/jquery-1.6.4.min.js?t=#DATETIME#'></script>        ".trim() + lineBreak
                           + " <script type='text/javascript' src='/js/jquery.memu-0.1.min.js?t=#DATETIME#'></script>     ".trim() + lineBreak
                           + " <script type='text/javascript' src='/js/page_init.js?t=#DATETIME#'></script>               ".trim() + lineBreak
                           + "<!--Head_Import_End-->";

    private final String pageHeaderReplacement = "<!--Page_Header_Start-->" + lineBreak
                            + " <table border='0' cellspacing='0' cellpadding='0'>                                              ".trim() + lineBreak
                            + "   <tr>                                                                                                    ".trim() + lineBreak
                            + "     <td>                                                                                                  ".trim() + lineBreak
                            + "       <object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'                                        ".trim() + lineBreak
                            + "           codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0' ".trim() + lineBreak
                            + "           width='969' height='200'>                                                                       ".trim() + lineBreak
                            + "         <param name='movie' value='/images/bcastr3.swf'>                                                  ".trim() + lineBreak
                            + "         <param name='quality' value='high'>                                                               ".trim() + lineBreak
                            + "         <param name='wmode' value='transparent'>                                                          ".trim() + lineBreak
                            + "         <param name='FlashVars' value='bcastr_xml_url=/images/bcastr.xml?t=#DATETIME#'>                   ".trim() + lineBreak
                            + "         <embed src='/images/bcastr3.swf'                                                                  ".trim() + lineBreak
                            + "          FlashVars='bcastr_xml_url=/images/bcastr.xml?t=#DATETIME#' quality='high'                        ".trim() + lineBreak
                            + "        pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash'     ".trim() + lineBreak
                            + "              width='969' height='200'></embed>                                                            ".trim() + lineBreak
                            + "       </object>                                                                                           ".trim() + lineBreak
                            + "     </td>                                                                                                 ".trim() + lineBreak
                            + "   </tr>                                                                                                   ".trim() + lineBreak
                            + " </table>                                                                                                  ".trim() + lineBreak
                            + "  <table border='0' cellspacing='0' cellpadding='0' id='menu'>                                             ".trim() + lineBreak
                            + "      <tr id='menu_nav'>                                                                                   ".trim() + lineBreak
                            + "        <td class='menu_nav_item'>                                                                         ".trim() + lineBreak
                            + "   <ul class='memu js-enabled'>                                                                                      ".trim() + lineBreak
                            + "     <li class='memu-root'><a href='/index.html'><span class='menu_nav_item_text'>首页</span></a></li>                   ".trim() + lineBreak
                            + "     <li class='memu-root'><span class='menu_nav_item_text'>解决方案</span>                                                ".trim() + lineBreak
                            + "       <ul>                                                                                                            ".trim() + lineBreak
                            + "         <li><a href='/html/solutions/2011/solution_00.html'><span class='menu_nav_sub_item_text'>2011</span></a></li>         ".trim() + lineBreak
                            + "         <li><a href='/html/solutions/2012/solution_00.html'><span class='menu_nav_sub_item_text'>2012</span></a></li>         ".trim() + lineBreak
                            + "       </ul>                                                                                                           ".trim() + lineBreak
                            + "     </li>                                                                                                             ".trim() + lineBreak
                            + "     <li class='memu-root'><span class='menu_nav_item_text'>资料下载</span>                                                ".trim() + lineBreak
                            + "       <ul>                                                                                                            ".trim() + lineBreak
                            + "         <li><a href='/html/downloads/download_01.html'><span class='menu_nav_sub_item_text'>系统案例</span></a></li>      ".trim() + lineBreak
                            + "         <li><a href='/html/downloads/download_02.html'><span class='menu_nav_sub_item_text'>产品资料</span></a></li>      ".trim() + lineBreak
                            + "         <li><a href='/html/downloads/download_03.html'><span class='menu_nav_sub_item_text'>相关论文</span></a></li>      ".trim() + lineBreak
                            + "       </ul>                                                                                                           ".trim() + lineBreak
                            + "     </li>                                                                                                             ".trim() + lineBreak
                            + "     <li class='memu-root'><a href='/html/about.html'><span class='menu_nav_item_text'>关于我们</span></a></li>            ".trim() + lineBreak
                            + "   </ul>                                                                                                               ".trim() + lineBreak
                            + "        </td>                                                                                               ".trim() + lineBreak
                            + "      </tr>                                                                                                 ".trim() + lineBreak
                            + "  </table>                                                                                                  ".trim() + lineBreak
                            + "<!--Page_Header_End-->";



    private final String pageFooterReplacement = "<!--Page_Footer_Start-->"            + lineBreak
    		              + " <table border='0' cellspacing='0' cellpadding='0' id='page_footer'>                           ".trim() + lineBreak
    		              + "   <tr><td colspan='2'>最新价格及详细内容欢迎咨询。电话：13764193800 邮件：bkcailiang@163.com</td></tr> ".trim() + lineBreak
    		              + " </table>                                                                                      ".trim() + lineBreak
                          + " <table border='0' cellspacing='0' cellpadding='0' id='footer_links'>                 ".trim() + lineBreak
                          + "   <tr>                                                                               ".trim() + lineBreak
                          + "     <td>合作伙伴</td>                                                                  ".trim() + lineBreak
                          + "   </tr>                                                                              ".trim() + lineBreak
                          + "   <tr>                                                                               ".trim() + lineBreak
                          + "     <td id='coop_links'></td>                                                        ".trim() + lineBreak
                          + "   </tr>                                                                              ".trim() + lineBreak
                          + " </table>                                                                             ".trim() + lineBreak
                          + " <table border='0' cellspacing='0' cellpadding='0' id='footer'>                       ".trim() + lineBreak
                          + "   <tr>                                                                               ".trim() + lineBreak
                          + "     <td id='footer_text'>上海安科瑞能源管理有限公司 © 2011 <br>上海嘉定马东工业园区育绿路253号</td>         ".trim() + lineBreak
                          + "   </tr>                                                                              ".trim() + lineBreak
                          + " </table>                                                                             ".trim() + lineBreak
                          + "   <table border='0' cellspacing='0' cellpadding='0' id='out_dir_links'>                                                                                                              ".trim() + lineBreak
                          + "     <tr><td>                                                                                                                                                                         ".trim() + lineBreak
                          + "   <a href='http://www.ttuu.com/' target='_blank'><img src='http://www.ttuu.com/dir_en.gif' border='0' width='80' height='15' alt='网站提交-免费收录各类优秀网站的中文网站目录' /></a>  ".trim() + lineBreak
                          + "   <a href='http://www.coodir.com/' target='_blank' ><img alt='Coodir' src='http://img.coodir.com/dir8015_en.gif' width=80 height=15 border=0></a>                                    ".trim() + lineBreak
                          + "   <a href='http://www.dzhai.com/' target='_blank'><img src='http://www.dzhai.com/Config/Images/Logo8015_en.gif' border='0' width='80' height='15' alt='第一摘网站目录' /></a>        ".trim() + lineBreak
                          + "   <a href='http://www.ratedir.com/' target='_blank'><img src='http://www.ratedir.com/images/logo8015_en.gif' border='0' width='80' height='15' alt='锐帝网站目录-免费收录各行业优秀网站的中文网站目录' /></a> ".trim() + lineBreak
                          + "     </td></tr>                                                                                                                                                                       ".trim() + lineBreak
                          + "   </table>                                                                                                                                                                          ".trim() + lineBreak
                          + "<!--Page_Footer_End-->";


    private final InjectRefiner solutionMenuRefiner = new InjectRefinerSolution(
			  "<!--Page_Side_Menu_Start-->"
            , "<!--Page_Side_Menu_End-->"
    		, "./src/main/webapp/json/menu_solutions.json"
    		, "html"+fileSeparator+"solutions"+fileSeparator //+"2011"+fileSeparator+"solution_"
    		);

    private final InjectRefiner downloadMenuRefiner = new InjectRefinerDownload(
			  "<!--Page_Side_Menu_Start-->"
            , "<!--Page_Side_Menu_End-->"
    		, "./src/main/webapp/json/menu_downloads.json"
    		, "html"+fileSeparator+"downloads"+fileSeparator+"download_"
    		);

    public static void main(String[] args) throws IOException {

        PageGen p = new PageGen();
        p.operate();

    }

    public void operate() throws IOException{
        File root = new File("./src/main/webapp/html/");
        operateFolder(root);
        refineFile(new File("./src/main/webapp/index.html"));
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
		// str = replace(str, "Page_Side_Menu", pageSideMenuReplacement);
        FileUtil.writeFile(file, str);

        solutionMenuRefiner.refine(file);
		downloadMenuRefiner.refine(file);

    }

    public String replace(String str, String mark, String replacement){
        return str.replaceAll("<!--"+mark+"_Start-->[.\\s\\S]*<!--"+mark+"_End-->", replacement);
    }

}
