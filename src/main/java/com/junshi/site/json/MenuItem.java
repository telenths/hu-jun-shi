package com.junshi.site.json;

public class MenuItem {

    private String item;
    private String clazz;
    private String link;
	private String full;

	public MenuItem(String item, String clazz, String link, String full) {
        super();
        this.item = item;
        this.clazz = clazz;
        this.link = link;
		this.full = full;
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
	public String getFull() {
		return full;
	}
	public void setFull(String full) {
		this.full = full;
	}



}
