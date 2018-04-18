package pl.interview.rental.service;

import org.springframework.stereotype.Component;
import pl.interview.rental.FileUnmarshaller;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.Cars;
import pl.interview.rental.model.User;
import pl.interview.rental.model.Users;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Component
public class RentalService {
    List<User> clientList;
    List<Car> carList;

    @PostConstruct
    public void init() throws JAXBException {
        setClientListFromFile();
        setCarListFromFile();
    }

    private void setClientListFromFile() throws JAXBException {
        FileUnmarshaller<Users, User> clientsFileUnmarshaller = new FileUnmarshaller<>(new Users());
        clientList = clientsFileUnmarshaller.getListFromFile("clients.xml");
    }

    private void setCarListFromFile() throws JAXBException {
        FileUnmarshaller<Cars, Car> carsFileUnmarshaller = new FileUnmarshaller<>(new Cars());
        carList = carsFileUnmarshaller.getListFromFile("cars.xml");
    }
    public void helloClients(){
        for(User user :clientList) {
            System.out.println(user.toString());
        }
    }
}
