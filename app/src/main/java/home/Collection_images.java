package home;

public class Collection_images {

    private String url;
    private String placeid;

    public Collection_images(String url, String placeid) {
        this.url = url;
        this.placeid = placeid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }
}
