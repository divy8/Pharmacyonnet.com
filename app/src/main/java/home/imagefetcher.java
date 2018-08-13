package home;

public class imagefetcher {

    private String url;
    private String page_id;

    public imagefetcher(String url, String page_id) {
        this.url = url;
        this.page_id = page_id;
    }

    public imagefetcher()
    {

    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public String getUrl() {
        return url;
    }

    public String getPage_id() {
        return page_id;
    }
}
