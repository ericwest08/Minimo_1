import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class User {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    LinkedList<Bike> bikesofUser;
    String iduser;
    String name;

    public User(){

    }

    public User(String iduser, String name){
        this.iduser = iduser;
        this.name = name;
        this.bikesofUser = new LinkedList();
    }

    public void setIduser(String id){
        this.iduser = id;
    }

    public String getIduser(){
        return this.iduser;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addBike(Bike b)
    {
        this.bikesofUser.add(b);
    }

    public void setBikesofUser(LinkedList<Bike> bk){
        this.bikesofUser = bk;
    }

    public LinkedList<Bike> getBikesofUser(){
        return this.bikesofUser;
    }
}
