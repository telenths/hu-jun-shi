package com.junshi.site.util;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.junshi.site.json.SolutionMenu;
import com.junshi.site.json.SolutionMenuItem;
import com.junshi.util.FileUtil;

public class MenuRefiner {

	private String menuStart, menuEnd, jsonFile, pathCheck;

    public MenuRefiner(String menuStart, String menuEnd, String jsonFile, String pathCheck) {
		super();
		this.menuStart = menuStart;
		this.menuEnd = menuEnd;
		this.jsonFile = jsonFile;
		this.pathCheck = pathCheck;
	}

	public void replaceSolutionMenu(File file) throws IOException{
        if(!isParsable(file))
            return;

		System.out.println("  +---- inserting menu for " + menuStart);

        String str = FileUtil.readFile(file);
		String menuId = menuStart;
        int startPoint = str.indexOf(menuId);
        if(startPoint < 0)
            return;

		int endPoint = str.indexOf(menuEnd, startPoint + menuId.length());
        
        StringBuffer buf = new StringBuffer(str);
//        buf.insert(startPoint + menuId.length(), "\n" + getMenuString(file.getName()));
        buf.replace(startPoint + menuId.length(), endPoint, "\n" + getMenuString(file.getName()));

        FileUtil.writeFile(file, buf.toString());
    }

    private String getMenuString(String fileName) throws IOException {

        SolutionMenu[] solutionMenus = getSolutionMenu();
        StringBuffer buf = new StringBuffer();

        for(SolutionMenu solutionMenu : solutionMenus){
        	
        	String link = solutionMenu.getLink();
        	String clazz = solutionMenu.getClazz();
        	String menu = solutionMenu.getMenu();
        	
        	String html = "<a style='width:100%' href='" + link + "'><div class='"+clazz+"'>" + menu + "</div></a> \n";
        	buf.append(html);
        	
        	for(SolutionMenuItem solutionMenuItem : solutionMenu.getData() ){
        		String itemLink = solutionMenuItem.getLink();
        		String itemName = solutionMenuItem.getItem();
        		String itemClazz = solutionMenuItem.getClazz();
        		
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
    
    private boolean isParsable(File file){
		return file.getAbsolutePath().indexOf(pathCheck) > 0;
    }

    private SolutionMenu[] getSolutionMenu() throws IOException{
		String jsonFileText = FileUtil.readFile(new File(jsonFile));
        Gson gson = new Gson();
        SolutionMenu[] solutionMenu = gson.fromJson(jsonFileText, SolutionMenu[].class);
        return solutionMenu;
    }
}
