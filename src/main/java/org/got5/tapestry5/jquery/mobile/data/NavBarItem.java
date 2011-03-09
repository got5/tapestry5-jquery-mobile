package org.got5.tapestry5.jquery.mobile.data;

public class NavBarItem {
	
	String title;
	String pageName;
	String icon;
	String iconPos;
	String theme;
	
	public NavBarItem(String pageName) {
		super();
		this.pageName = pageName;
		this.title = pageName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconPos() {
		return iconPos;
	}

	public void setIconPos(String iconPos) {
		this.iconPos = iconPos;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
}
