import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;


public class Bike {

    String idStation;
    String idbike;
    String namebike;
    double kms;

    public Bike(String ids, String idb, String name,double kms){
        this.idStation=ids;
        this.idbike = idb;
        this.namebike = name;
        this.kms = kms;
    }

    public void setidStation(String ids){
        this.idStation=ids;
    }

    public String getIdStation(){
        return this.idStation;
    }

    public void setIdbike(String idb){
        this.idbike=idb;
    }

    public String getIdbike(){
        return this.idbike;
    }

    public void setNameBike(String n){
        this.namebike=n;
    }

    public String getNameBike(){
        return this.namebike;
    }

    public void setKms(double kms){
        this.kms = kms;
    }

    public double getKms(){
        return this.kms;
    }

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getBikeid(){
        return this.idbike;
    }







}
