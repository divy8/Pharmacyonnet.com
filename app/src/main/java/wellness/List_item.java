package wellness;

public class List_item
{
    private String head;

    private String id;

    public List_item(String head, String id) {
        this.head = head;
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
