public class DataObject {
    private String name, house_number, street_name, city, state, zipcode, phone;
    public DataObject(String name, String house_number, String street_name,
                      String city, String state, String zipcode,String phone){
        this.name = name;
        this.house_number = house_number;
        this.street_name = street_name;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.phone = phone;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setHouseNumber(String house_number){
        this.house_number = house_number;
    }
    public String getHouseNumber(){
        return this.house_number;
    }

    public void setStreetName(String street_name){
        this.street_name = street_name;
    }
    public String getStreetName(){
        return this.street_name;
    }

    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }

    public void setState(String state){
        this.state= state;
    }
    public String getState(){
        return this.state;
    }

    public void setZipcode(String zipcode){
        this.zipcode= zipcode;
    }
    public String getZipcode(){
        return this.zipcode;
    }

    public void setPhone(String phone){
        this.phone= phone;
    }
    public String getPhone(){
        return this.phone;
    }
}