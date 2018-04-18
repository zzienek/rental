package pl.interview.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.interview.rental.FileAdapter;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.User;
import pl.interview.rental.repository.CarRepository;
import pl.interview.rental.repository.UserRepository;
import pl.interview.rental.service.RentalService;

import javax.xml.bind.JAXBException;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    RentalService rentalService;

    public List<User> loadClientListFromFile() throws JAXBException {
        FileAdapter<UserRepository, User> clientsFileAdapter = new FileAdapter<>(new UserRepository());
        return clientsFileAdapter.loadListFromFile("clients.xml");
    }

    public List<Car> loadCarListFromFile() throws JAXBException {
        FileAdapter<CarRepository, Car> carsFileAdapter = new FileAdapter<>(new CarRepository());
        return carsFileAdapter.loadListFromFile("cars.xml");
    }

    public void saveFiles() {
        FileAdapter<CarRepository, Car> carsFileAdapter = new FileAdapter<>(new CarRepository());
        FileAdapter<UserRepository, User> clientsFileAdapter = new FileAdapter<>(new UserRepository());
        try {
            carsFileAdapter.saveListToFile("cars.xml", new CarRepository(rentalService.getCarList()));
            clientsFileAdapter.saveListToFile("clients.xml", new UserRepository(rentalService.getClientList()));
        } catch (Exception e) {
            System.out.println("Unexpected error.");
        }
        System.out.println("Save successful");
    }
}
