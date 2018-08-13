package cartlist;

public class Order_User {
 String id;
 String image;
 String name;
 String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String p_price) {
        this.price = p_price;
    }

    public Order_User() {

    }

    public Order_User(String id, String image, String name, String price) {

        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
    }
}
