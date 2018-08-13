package helpers;

public class NavigationItems {


    private int icon;
    private String name;

    public NavigationItems(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public NavigationItems() {
        super();
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}