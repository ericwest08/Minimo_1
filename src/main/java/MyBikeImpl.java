import org.apache.log4j.Logger;
import java.util.*;


public class MyBikeImpl implements MyBike{

    final static Logger log = Logger.getLogger(MyBikeImpl.class.getName());
    private static MyBike instance;
    private int numstations;
    private Stations arrayStations[];
    private LinkedList<Bike> bikesStation;
    private LinkedList<Bike> bikesUser;
    //Initialize the hashmap(key: string; value: User) of users
    private HashMap<String, User> users;

    private MyBikeImpl(){
        numstations = 0;
        arrayStations = new Stations[S];
        bikesStation = new LinkedList();
        bikesUser = new LinkedList();
        users = new HashMap();
    }

    public static MyBike getInstance(){
        if(instance==null){
            instance = new MyBikeImpl();
        }
        return instance;
    }

    public void clear(){
        instance = null;
        arrayStations = null;
        arrayStations = new Stations[S];
        bikesStation.clear();
        bikesUser.clear();
        users.clear();
    }

    //Get the number of users
    public int numUsers(){
        log.info("Number of users: " +this.users.size());
        return this.users.size();
    }

    //Get the number of stations
    public int numStations(){
        log.info("Number of Stations: " +this.numstations);
        return this.numstations;
    }

    //Get the number of bikes in a station
    public int numBikes(String idStation) throws StationNotFoundException{
        //We get the Station that has this idStation
        Stations station = null;
        int numBikes;
        for(int i = 0; i<this.numstations; i++) {
            if(idStation.equals(this.arrayStations[i].idstation)){
                station = this.arrayStations[i];
            }
        }

        log.info("idStation of this Station: " +station.idstation);

        if (station != null){
            numBikes = station.getNumofBikes();
            log.info("Number of bikes of this station: " +numBikes);
        }
        else{
            log.error("The station doesn't exist");
            throw new StationNotFoundException();
        }

        return numBikes;
    }

    //Get the bikes used by the user
    public LinkedList<Bike> bikesByUser(String userId) throws UserNotFoundException{
        LinkedList<Bike> bikes;

        //We want to find the given user
        User theUser = this.users.get(userId);

        if(theUser!=null){
            log.info("User: " +theUser);
            bikes = theUser.getBikesofUser();
        }
        else{
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
        log.info("Bikes used by the user: " +bikes);
        return bikes;
    }

    //Get the first bike of the station
    public Bike getBike (String stationId, String userId) throws UserNotFoundException, StationNotFoundException{
        Stations station = null;
        Bike firstBike;
        //We want to know if the user exists or not
        User theUser = this.users.get(userId);
        log.info("User: " +theUser);

        //We want to know if the station exists or not
        for(int i = 0; i<this.numstations; i++) {
            if(stationId.equals(this.arrayStations[i].idstation)){
                station = this.arrayStations[i];
            }
        }

        if(theUser != null) {
            if (station != null) {
                firstBike = station.stationsbikes.removeFirst();
                theUser.addBike(firstBike);
            }
            else {
                log.error("The station doesn't exist");
                throw new StationNotFoundException();
            }
        }
        else{
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
        log.info("First bike of the station: " +firstBike);
        return firstBike;
    }

    //Get the bikes of a station ordered by kilometers
    public LinkedList<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException{
        Stations station = null;
        LinkedList<Bike> ret;
        for(int i = 0; i<this.numstations; i++) {
            if(idStation.equals(this.arrayStations[i].idstation)){
                station = this.arrayStations[i];
            }
        }

        if(station != null) {
            //We create a copy of the list
            log.info("List before changes: " + station.getStationsbikes());
            ret = station.getStationsbikes();

            //We have to tell to the sort method, which criteria we want to apply
            Collections.sort(ret, new Comparator<Bike>() {

                public int compare(Bike o1, Bike o2) {
                    return (int) (o1.getKms() - o2.getKms());
                }
            });
        }
        else{
            log.error("The station doesn't exist");
            throw new StationNotFoundException();
        }
        log.info("List after changes: " + ret);
        return ret;
    }

    //Add a new Bike into a Station
    public void addBike(String idstation, String idbike, String name, double kms) throws StationFullException, StationNotFoundException{
        Stations station = null;
        Bike bike;
        for(int i = 0; i<this.numstations; i++) {
            if(idstation.equals(this.arrayStations[i].idstation)){
                station = this.arrayStations[i];
            }
        }

        log.info("Station: " +station);

        if(station != null){
            LinkedList<Bike> bikes = station.getStationsbikes();
            int maxbikes = station.maxbikes;
            if(bikes.size() < maxbikes){
                station.addBike(new Bike(idstation, idbike,name,kms));
            }
            else{
                log.error("The station that you want to add a new bike is FULL");
                throw new StationFullException();
            }
        }
        else{
            log.error("The station doesn't exist");
            throw new StationNotFoundException();
        }
    }

    //Add a new Station
    public void addStation(String idStation, String description, double lat, double lon, int max){
        this.arrayStations[this.numstations] = new Stations(idStation, description, lat, lon, max);
        this.numstations++;
    }

    //Add a new User
    public void addUser(String idUser, String name){
        this.users.put(idUser, new User(idUser, name));

}
}
