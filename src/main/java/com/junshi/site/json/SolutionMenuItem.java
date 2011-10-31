package com.junshi.site.json;

public class SolutionMenuItem {

    private String item;
    private String clazz;
    private String link;

    public SolutionMenuItem(String item, String clazz, String link) {
        super();
        this.item = item;
        this.clazz = clazz;
        this.link = link;
    }

    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
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




}
