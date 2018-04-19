package pl.interview.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.interview.rental.adapters.FileAdapter;
import pl.interview.rental.controllers.RentalController;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.User;
import pl.interview.rental.repository.CarRepository;
import pl.interview.rental.repository.UserRepository;

import javax.xml.bind.JAXBException;
import java.util.List;

@Service
public class FileService {
    RentalController rentalController;

    @Autowired
    public FileService(RentalController rentalController) {
        this.rentalController = rentalController;
    }

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
            carsFileAdapter.saveListToFile("cars.xml", new CarRepository(rentalController.getCarList()));
            clientsFileAdapter.saveListToFile("clients.xml", new UserRepository(rentalController.getClientList()));
        } catch (Exception e) {
            System.out.println("Unexpected error.");
        }
        System.out.println("Save successful");
    }
}
