package productdetails;

public class productitems {

    private String name;
    private String id;
    private String price;
    private String img_url;
    private String discount;
    private String final_price;
    private String grade;

    public productitems(String name, String id, String price,String img_url,String discount,String final_price,String grade) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.img_url=img_url;
        this.discount=discount;
        this.final_price=final_price;
        this.grade=grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
