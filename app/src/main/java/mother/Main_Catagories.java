package mother;

public class Main_Catagories {

    private String id;
    private String name;
    //private ArrayList<Main_Catagories> childcatagories;
    private String pos;

    public Main_Catagories(String id, String name,String pos) {
        this.id = id;
        this.name = name;
        this.pos=pos;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ArrayList<Main_Catagories> getChildcatagories() {
//        return this.childcatagories;
//    }
//
//    public void setChildcatagories(ArrayList<Main_Catagories> childcatagories) {
//       // this.childcatagories=(ArrayList<Main_Catagories>)childcatagories.clone();
//        this.childcatagories = childcatagories;
//    }
}
