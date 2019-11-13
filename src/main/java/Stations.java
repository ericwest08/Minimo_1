import java.util.LinkedList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

public class Stations {

    LinkedList<Bike> stationsbikes;
    String idstation;
    String name;
    double lat;
    double lon;
    int maxbikes;

    public Stations(String id, String n, double lat, double lon, int maxbikes){
        this.idstation = id;
        this.name = n;
        this.lat = lat;
        this.lon = lon;
        this.maxbikes = maxbikes;
        this.stationsbikes = new LinkedList();
    }

    public void setIdstation(String id){
        this.idstation = id;
    }

    public String getIdstation(){
        return this.idstation;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat(){
        return this.lat;
    }

    public void setLon(double lat){
        this.lon = lon;
    }

    public double getLon(){
        return this.lon;
    }

    public void setMaxbikes(int max){
        this.maxbikes = max;
    }

    public int getMaxbikes(){
        return this.maxbikes;
    }

    public void setStationsbikes(LinkedList<Bike> b){
        this.stationsbikes = b;
    }
    public LinkedList<Bike> getStationsbikes(){
        return this.stationsbikes;
    }

    public void addBike(Bike b){
        this.stationsbikes.add(b);
    }

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public int getNumofBikes(){
        return this.stationsbikes.size();
    }

}
