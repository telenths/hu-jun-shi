package com.junshi.site.gen;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.junshi.site.json.Menu;
import com.junshi.site.json.MenuItem;
import com.junshi.util.FileUtil;

public class InjectRefiner {

	protected final String menuStart, menuEnd, jsonFile, pathCheck;

	protected final String lineBreak = System.getProperty("line.separator");

	protected final String pageSideMenuReplacement = lineBreak
            + " <a style='width:100%' href='/html/about.html'><div class='menu_link'>联系方式</div></a>       ".trim() + lineBreak
            + " <table border='0' cellspacing='0' cellpadding='0' class='salse_section'>    ".trim() + lineBreak
            + "   <tr><td>联系人:</td><td>蔡&nbsp;亮</td></tr>                               ".trim() + lineBreak
            + "   <tr><td>电话:</td><td>13764193800</td></tr>                               ".trim() + lineBreak
            + "   <tr><td>QQ:</td><td>1272489503</td></tr>                                  ".trim() + lineBreak
            + "   <tr><td>Email:</td><td>bkcailiang@163.com</td></tr>                       ".trim() + lineBreak
            + " </table>                                                                    ".trim() + lineBreak
            + " <div style='height:1em; line-height:30px;'> </div>                          ".trim() + lineBreak
            ;

    public InjectRefiner(String menuStart, String menuEnd, String jsonFile, String pathCheck) {
		super();
		this.menuStart = menuStart;
		this.menuEnd = menuEnd;
		this.jsonFile = jsonFile;
		this.pathCheck = pathCheck;
	}

	public void refine(File file) throws IOException{
        if(!isTarget(file))
            return;

		System.out.println("  +---- inserting menu for " + menuStart);

        String str = FileUtil.readFile(file);
        StringBuffer buf = new StringBuffer(str);
        Menu[] menus = getSideMenu();

		injectText(menuStart, menuEnd, buf, getMenuString(file, menus));

		String pageTitleString = getPageTitleString(file, menus);
        System.out.println("  +---- Page Title - " + pageTitleString);
        injectText("<title>", "</title>", buf, pageTitleString);

        String contentTitleString = getContentTitleString(file, menus);
        System.out.println("  +---- Content Title - " + contentTitleString);
		injectText("<p class='content_title'>", "</p>", buf, contentTitleString);

        FileUtil.writeFile(file, buf.toString());
    }

    protected void injectText(String startMark, String endMark, StringBuffer buf, String injectString) throws IOException {
		if (injectString == null || injectString.trim().length() <= 0)
			return;

        int startPoint = buf.indexOf(startMark);
        if(startPoint < 0)
            return;
		int endPoint = buf.indexOf(endMark, startPoint + startMark.length());
        buf.replace(startPoint + startMark.length(), endPoint, injectString);
    }

    protected String getContentTitleString(File file, Menu[] menus){
        String fileName =  file.getParentFile().getName() + "/" + file.getName();
        outer: for(Menu sideMenu : menus){
            String link = sideMenu.getLink();
            if(link.indexOf(fileName) >= 0){
                break outer;
            }
            for(MenuItem menuItem : sideMenu.getData() ){
                String itemLink = menuItem.getLink();
				String itemName = menuItem.getItem();
				String fullName = menuItem.getFull();
                if(itemLink.indexOf(fileName) >= 0){
					return fullName == null ? itemName : fullName;
                }
            }
        }

		return null;

    }

    protected String getPageTitleString(File file, Menu[] menus){
        String fileName =  file.getParentFile().getName() + "/" + file.getName();
	    String subTitle = "";
        outer: for(Menu sideMenu : menus){
            String link = sideMenu.getLink();
            String menu = sideMenu.getMenu();
            if(link.indexOf(fileName) >= 0){
                subTitle = menu;
                break outer;
            }
            for(MenuItem menuItem : sideMenu.getData() ){
                String itemLink = menuItem.getLink();
                String itemName = menuItem.getItem();
                if(itemLink.indexOf(fileName) >= 0){
                    subTitle = menu + " - " + itemName;
                    break outer;
                }
            }
        }
	    return subTitle + " - 上海安科瑞能源管理有限公司";
	}

    protected String getMenuString(File file, Menu[] menus) {
        String fileName =  file.getParentFile().getName() + "/" + file.getName();
        StringBuffer buf = new StringBuffer("\n");
		buf.append("<table border='0' cellspacing='0' cellpadding='0' id='table_left_menu'>\n");
		buf.append("<tr><td>\n");
        for(Menu sideMenu : menus){
        	String link = sideMenu.getLink();
        	String clazz = sideMenu.getClazz();
        	String menu = sideMenu.getMenu();

        	String html = "<div class='"+clazz+"'>" + menu + "</div> \n";
        	if(link != null && link.trim().length() > 0){
        	  html = "<a style='width:100%' href='" + link + "'><div class='"+clazz+"'>" + menu + "</div></a> \n";
        	}
        	buf.append(html);


        	for(MenuItem menuItem : sideMenu.getData() ){
        		String itemLink = menuItem.getLink();
        		String itemName = menuItem.getItem();
        		String itemClazz = menuItem.getClazz();

        		String itemHtml = "";
        		if(itemLink.indexOf(fileName) >= 0){
        			itemHtml = "<div class='"+itemClazz+"_current'>" + itemName + "</div> \n";
        		}else{
        			itemHtml = "<a style='width:100%' href='" + itemLink + "'><div class='"+itemClazz+"'>" + itemName + "</div></a> \n";
        		}
      		  buf.append(itemHtml);
        	}
        }
		buf.append(pageSideMenuReplacement);
		buf.append("</td></tr></table>\n");
		// buf.append("<!--Page_Side_Menu_End-->");
        return buf.toString();
    }

    protected boolean isTarget(File file){
		return file.getAbsolutePath().indexOf(pathCheck) > 0;
    }

    protected Menu[] getSideMenu() throws IOException{
		String jsonFileText = FileUtil.readFile(new File(jsonFile));
        Gson gson = new Gson();
        Menu[] sideMenu = gson.fromJson(jsonFileText, Menu[].class);
        return sideMenu;
    }
}
