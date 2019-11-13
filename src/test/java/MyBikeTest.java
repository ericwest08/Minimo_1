import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

    public class MyBikeTest {

        final static Logger log = Logger.getLogger(MyBikeTest.class.getName());

        private MyBike mb;

        @Before
        public void setUp() throws Exception {
            this.mb = MyBikeImpl.getInstance();
            this.mb.addUser("user1", "Juan");
            this.mb.addStation("Station1","description:: station1", 10, 3, 3);
            this.mb.addStation("Station2","description:: station2", 10, 3, 3);


            Assert.assertEquals(2, this.mb.numStations());
            Assert.assertEquals(0, this.mb.numBikes("Station1"));

            try {
                this.mb.addBike("bike101", "descripton", "Station1", 25.45);
                this.mb.addBike("bike102", "descripton", "Station1", 70.3);
                this.mb.addBike("bike103", "descripton", "Station1", 10.2);
            }
            catch (StationNotFoundException e){
                log.error("The station doesn't exist: " +e.getMessage());
            }
            Assert.assertEquals(3, this.mb.numBikes("Station1"));

            try {
                this.mb.addBike("bike201", "descripton", "Station2",1325.45);
                this.mb.addBike("bike202", "descripton", "Station2", 74430.3);
                this.mb.addBike("bike203", "descripton", "Station2",1320.2);
            }
            catch (StationNotFoundException e){
                log.error("The station doesn't exist: " +e.getMessage());
            }
            Assert.assertEquals(3, this.mb.numBikes("Station2"));
        }

        @After
        public void tearDown(){
            this.mb.clear();
        }

        @Test
        public void testAddUser() {
            this.mb.addUser("3334445ZX", "Juan");
            Assert.assertEquals(2, this.mb.numUsers());
        }


        @Test
        public void testAddStations() {
            this.mb.addStation("Station3","description", 10, 3, 3);
            Assert.assertEquals(3, this.mb.numStations());
        }

        @Test
        public void testAddBike() throws Exception {

            try {
                this.mb.addBike("bike500", "descripton", "Station1",43.3);
                this.mb.addBike("bike600", "descripton", "Station1",45.3);
            }
            catch (StationNotFoundException e){
                log.error("The station doesn't exist: " +e.getMessage());
            }

            Assert.assertEquals(5, this.mb.numBikes("Station1"));


        }

        @Test(expected = StationFullException.class)
        public void testAddBikesAndStationFull() throws Exception {
            this.mb.addStation("Station3","description", 2, 3, 3);
            Assert.assertEquals(3, this.mb.numStations());
            Assert.assertEquals(0, this.mb.numBikes("Station3"));

            this.mb.addBike("bike1", "descripton", "Station3",25.3);
            this.mb.addBike("bike2", "descripton", "Station3",70.2);
            this.mb.addBike("bike3", "descripton", "Station3",10.4);

        }


        @Test(expected = StationNotFoundException.class)
        public void testAddBikesAndStationNotFound() throws Exception {
            this.mb.addBike("bike1", "descripton", "StationXXXXX",55.4);
        }



        @Test
        public void testBikesByStation() throws Exception {

            try {
                List<Bike> bikes = this.mb.bikesByStationOrderByKms("Station1");
                Assert.assertEquals("bike103", bikes.get(0).getBikeid());
                Assert.assertEquals(10, bikes.get(0).getKms(),1);

                Assert.assertEquals("bike101", bikes.get(1).getBikeid());
                Assert.assertEquals(25, bikes.get(1).getKms(),1);

                Assert.assertEquals("bike102", bikes.get(2).getBikeid());
                Assert.assertEquals(70, bikes.get(2).getKms(),1);
            }
            catch(StationNotFoundException e){
                log.error("The station doesn't exist: " +e.getMessage());
            }
        }

        @Test
        public void testGetBikes() throws Exception {

            try {
                Bike b1 = this.mb.getBike("Station1", "user1");
                Assert.assertEquals("bike101", b1.getBikeid());
                Assert.assertEquals(2, this.mb.numBikes("Station1"));

                Bike b2 = this.mb.getBike("Station2", "user1");
                Assert.assertEquals("bike201", b2.getBikeid());
                Assert.assertEquals(2, this.mb.numBikes("Station1"));

                List<Bike> bikes = this.mb.bikesByUser("user1");

                Assert.assertEquals("bike101", bikes.get(0).getBikeid());
                Assert.assertEquals("bike201", bikes.get(1).getBikeid());
            }
            catch(UserNotFoundException e){
                log.error("The user doesn't exist: " +e.getMessage());
            }
            catch(StationNotFoundException e){
                log.error("The station doesn't exist: " +e.getMessage());
            }
        }
}

