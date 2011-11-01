package com.junshi.site.json;


public class Menu {

    private String menu;
    private String clazz;
    private String link;
    private MenuItem[] data;

    public Menu(String menu, String clazz, String link, MenuItem[] data) {
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
    public MenuItem[] getData() {
        return data;
    }
    public void setData(MenuItem[] data) {
        this.data = data;
    }




}
