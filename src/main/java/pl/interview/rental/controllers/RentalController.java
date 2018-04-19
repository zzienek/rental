package pl.interview.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.User;
import pl.interview.rental.service.CarService;
import pl.interview.rental.service.FileService;
import pl.interview.rental.service.UserService;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

@Controller
public class RentalController {
    List<User> clientList;
    List<Car> carList;

    private final UserService userService;
    private final CarService carService;
    private final FileService fileService;

    @Autowired
    public RentalController(UserService userService, CarService carService, FileService fileService) {
        this.userService = userService;
        this.carService = carService;
        this.fileService = fileService;
    }


    @PostConstruct
    public void init() throws JAXBException {
        loadClientListFromFile();
        loadCarListFromFile();
    }

    private void loadClientListFromFile() throws JAXBException {
        clientList = fileService.loadClientListFromFile();
    }

    private void loadCarListFromFile() throws JAXBException {
        carList = fileService.loadCarListFromFile();
    }

    public void saveFiles() {
        fileService.saveFiles(carList, clientList);
    }

    public void createUser() {
        try {
            User client = userService.createUser();
            System.out.println();
            if (containsClient(client)) {
                System.out.println("Client already exists");
            } else {
                clientList.add(client);
                System.out.println("Client added");
            }
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
            carService.returnCar(carList);
        } catch (IOException e) {
            System.out.println("Problem with your input");
        } catch (Exception ex) {
            System.out.println("Unexpected error");
        }

    }

    public void rentCar() {
        try {

            carService.rentCar(carList, clientList);

        } catch (IOException e) {
            System.out.println("Problem with your input");
        } catch (Exception ex) {
            System.out.println("Unexpected error");
        }
    }

    public void displayAllByModel() throws IOException {
        System.out.println("ALL CARS");
        carList.stream().sorted(Comparator.comparing(Car::getModel)).forEach(item -> System.out.println(item));
    }


    public void listAvailibleAsc() throws IOException {
        System.out.println("AVAILABLE CARS");
        carList.stream()
                .filter(item -> item.getCurrentClient() == null).sorted(Comparator.comparing(Car::getDailyRate)).forEach(item -> System.out.println(item));

    }

    public void listAvailibleDesc() throws IOException {
        System.out.println("AVAILABLE CARS");
        carList.stream()
                .filter(item -> item.getCurrentClient() == null).sorted(Comparator.comparing(Car::getDailyRate).reversed()).forEach(item -> System.out.println(item));

    }


}
