package home;

public class collection1 {

    private String url;
    private String placeid;
    private String cat_id;

    public collection1(){}


    public collection1(String url, String placeid, String cat_id) {
        this.url = url;
        this.placeid = placeid;
        this.cat_id = cat_id;
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

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }
}
