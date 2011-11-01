package com.junshi.site.util;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.junshi.site.json.Menu;
import com.junshi.site.json.MenuItem;
import com.junshi.util.FileUtil;

public class InjectRefiner {

	private final String menuStart, menuEnd, jsonFile, pathCheck;

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

		injectSideMenu(menuStart, menuEnd, buf, getMenuString(file.getName(), menus));

        String titleStart = "<title>胡君实（上海）";
        String titleEnd = "</title>";
        injectSideMenu(titleStart, titleEnd, buf, getTitleString(file.getName(), menus));

        FileUtil.writeFile(file, buf.toString());
    }

    private void injectSideMenu(String startMark, String endMark, StringBuffer buf, String injectString) throws IOException {
        int startPoint = buf.indexOf(startMark);
        if(startPoint < 0)
            return;
		int endPoint = buf.indexOf(endMark, startPoint + startMark.length());
        buf.replace(startPoint + startMark.length(), endPoint, injectString);
    }

	private String getTitleString(String fileName, Menu[] menus){
	    String subTitle = "";
        outer: for(Menu sideMenu : menus){
            String link = sideMenu.getLink();
            String menu = sideMenu.getMenu();
            if(link.indexOf(fileName) >= 0){
                subTitle = " - " + menu;
                break outer;
            }
            for(MenuItem menuItem : sideMenu.getData() ){
                String itemLink = menuItem.getLink();
                String itemName = menuItem.getItem();
                if(itemLink.indexOf(fileName) >= 0){
                    subTitle = " - " + menu + " - " + itemName;
                    break outer;
                }
            }
        }
	    return subTitle;
	}

    private String getMenuString(String fileName, Menu[] menus) {
        StringBuffer buf = new StringBuffer("\n");
        for(Menu sideMenu : menus){
        	String link = sideMenu.getLink();
        	String clazz = sideMenu.getClazz();
        	String menu = sideMenu.getMenu();

        	String html = "<a style='width:100%' href='" + link + "'><div class='"+clazz+"'>" + menu + "</div></a> \n";
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
        return buf.toString();
    }

    private boolean isTarget(File file){
		return file.getAbsolutePath().indexOf(pathCheck) > 0;
    }

    private Menu[] getSideMenu() throws IOException{
		String jsonFileText = FileUtil.readFile(new File(jsonFile));
        Gson gson = new Gson();
        Menu[] sideMenu = gson.fromJson(jsonFileText, Menu[].class);
        return sideMenu;
    }
}
