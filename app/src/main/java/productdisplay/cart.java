package productdisplay;

public class cart {

    private String name;
    private String id;
    private String price;
    private String image;

    public cart(String name, String id, String price, String image) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.image = image;
    }
    public cart(){}

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
