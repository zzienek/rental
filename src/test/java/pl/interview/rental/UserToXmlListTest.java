package pl.interview.rental;

import org.junit.Before;
import org.junit.Test;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.Cars;
import pl.interview.rental.model.User;
import pl.interview.rental.model.Users;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserToXmlListTest {
    User user;
    User user1;
    User user2;
    User user3;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Before
    public void setUp() throws ParseException {
        long l = 10;
        Long longId = new Long(l);
        user = new User(longId,"Bartosz", "Zienkiewicz", dateFormat.parse("01-01-1990"));
        user1 = new User(longId,"Kamil", "Bednarek", dateFormat.parse("01-01-1999"));
        user2 = new User(longId,"Baltazar", "Gąbka", dateFormat.parse("01-01-1991"));
        user3 = new User(longId,"Fred", "Flintstone", dateFormat.parse("01-01-1900"));
    }

    @Test
    public void testObjectToXml() throws JAXBException, FileNotFoundException {
        Users users = new Users();
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(users, new File("clients.xml"));
        marshaller.marshal(users, System.out);
    }
}
