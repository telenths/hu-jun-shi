package com.junshi.site.json;


public class SolutionMenu {

    private String menu;
    private String clazz;
    private String link;
    private SolutionMenuItem[] data;

    public SolutionMenu(String menu, String clazz, String link, SolutionMenuItem[] data) {
        super();
        this.menu = menu;
        this.clazz = clazz;
        this.link = link;
        this.data = data;
    }
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public String getClazz() {
        return clazz;
    }
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public SolutionMenuItem[] getData() {
        return data;
    }
    public void setData(SolutionMenuItem[] data) {
        this.data = data;
    }




}
