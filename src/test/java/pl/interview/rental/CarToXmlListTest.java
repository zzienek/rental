package pl.interview.rental;

import org.junit.Before;
import org.junit.Test;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.Cars;
import pl.interview.rental.model.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CarToXmlListTest {
    Car car;
    Car car1;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Before
    public void setUp() throws ParseException {
        long l = 10;
        Long longId = new Long(l);
        User user = new User(longId,"Bartosz", "Zienkiewicz", dateFormat.parse("01-01-1990"));
        car = new Car(longId, "Fiat", "AWDGB", new Double(64), user);
        car1 = new Car(longId, "Ford", "AAAA", new Double(61), null);
    }

    @Test
    public void testObjectToXml() throws JAXBException, FileNotFoundException {
        Cars cars = new Cars();
        cars.add(car);
        cars.add(car1);

        JAXBContext jaxbContext = JAXBContext.newInstance(Cars.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(cars, new File("cars.xml"));
        marshaller.marshal(cars, System.out);
    }
}
