package pl.interview.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.interview.rental.FileAdapter;
import pl.interview.rental.controllers.CarController;
import pl.interview.rental.controllers.FileController;
import pl.interview.rental.controllers.UserController;
import pl.interview.rental.model.Car;
import pl.interview.rental.repository.CarRepository;
import pl.interview.rental.model.User;
import pl.interview.rental.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Component
public class RentalService {
    List<User> clientList;
    List<Car> carList;

    @Autowired
    UserController userController;
    @Autowired
    CarController carController;
    @Autowired
    FileController fileController;

    @PostConstruct
    public void init() throws JAXBException {
        setClientListFromFile();
        setCarListFromFile();
    }

    private void setClientListFromFile() throws JAXBException {
        clientList = fileController.loadClientListFromFile();
    }

    private void setCarListFromFile() throws JAXBException {
        carList = fileController.loadCarListFromFile();
    }

    public void saveFiles() {
       fileController.saveFiles();
    }

    public void createUser() {
        try {
            userController.createUser();
        } catch (IOException e) {
            System.out.println("Problem with input.");
        } catch (ParseException e) {
            System.out.println("Problem with date format.");
        } catch (Exception e) {
            System.out.println("Unexpected error.");
        }
    }

    public boolean containsClient(final User client) {
        return clientList.stream().filter(o -> isUserDuplicate(client, o)).findFirst().isPresent();
    }

    private boolean isUserDuplicate(User client, User o) {
        return o.getFirstName().equals(client.getFirstName())
                || o.getLastName().equals(client.getLastName())
                || o.getDateOfBirth().equals(client.getDateOfBirth());
    }

    public void returnCar() {
        try {
            carController.returnCar();
        } catch (IOException e) {
            System.out.println("Problem with your input");
        } catch (Exception ex) {
            System.out.println("Unexpected error");
        }

    }

    public void rentCar() {
        try {
            carController.rentCar();

        } catch (IOException e) {
            System.out.println("Problem with your input");
        } catch (Exception ex) {
            System.out.println("Unexpected error");
        }
    }

    public void displayAllByModel() throws IOException {
        carController.displayAllByModel();
    }


    public void listAvailibleAsc() throws IOException {
        carController.displayAvailibleByRateAsc();
    }

    public void listAvailibleDesc() throws IOException {
        carController.displayAvailibleByRateDesc();
    }

    public List<User> getClientList() {
        return clientList;
    }

    public List<Car> getCarList() {
        return carList;
    }
}
