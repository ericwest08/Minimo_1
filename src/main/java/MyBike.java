import java.util.LinkedList;
import java.util.List;

public interface MyBike {

    int S = 10;
    void addUser(String iduser, String name);
    void addStation(String id, String n, double lat, double lon, int maxbikes);
    void addBike(String ids, String idb, String name,double kms)  throws StationFullException, StationNotFoundException;
    LinkedList<Bike> bikesByStationOrderByKms(String idstation) throws StationNotFoundException;
    Bike getBike (String idstation, String idBike) throws UserNotFoundException, StationNotFoundException;
    LinkedList<Bike> bikesByUser(String userid) throws UserNotFoundException;
    int numUsers();
    int numStations();
    int numBikes(String idStation) throws StationNotFoundException;
    void clear();


}
