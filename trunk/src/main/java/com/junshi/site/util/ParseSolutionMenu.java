package com.junshi.site.util;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.junshi.site.json.SolutionMenu;
import com.junshi.util.FileUtil;

public class ParseSolutionMenu {

    public static void main(String[] args) throws IOException {
        new ParseSolutionMenu().getSolutionMenu();
    }

    public void replaceSolutionMenu(File file) throws IOException{
        if(!isSolutionFile(file))
            return;
        String str = FileUtil.readFile(file);
        String menuId = "id=\"menu_solution\">";
        int startPoint = str.indexOf(menuId);
        if(startPoint < 0)
            return;

        StringBuffer buf = new StringBuffer(str);
        buf.insert(startPoint + menuId.length(), getMenuString(file.getName()));

        FileUtil.writeFile(file, buf.toString());
    }

    private Object getMenuString(String fileName) throws IOException {

        SolutionMenu[] solutionMenus = getSolutionMenu();

        for(SolutionMenu solutionMenu : solutionMenus){

        }

        return null;
    }

    private boolean isSolutionFile(File file){
        return file.getAbsolutePath().indexOf("/html/solutions/solution_") > 0;
    }

    private SolutionMenu[] getSolutionMenu() throws IOException{
        String jsonFile = FileUtil.readFile(new File("./src/main/webapp/json/menu_solutions.json"));
        Gson gson = new Gson();
        SolutionMenu[] solutionMenu = gson.fromJson(jsonFile, SolutionMenu[].class);
        return solutionMenu;
    }
}
