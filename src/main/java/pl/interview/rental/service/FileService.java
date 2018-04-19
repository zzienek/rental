package pl.interview.rental.service;

import org.springframework.stereotype.Service;
import pl.interview.rental.adapters.FileAdapter;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.User;
import pl.interview.rental.repository.CarRepository;
import pl.interview.rental.repository.UserRepository;

import javax.xml.bind.JAXBException;
import java.util.List;

@Service
public class FileService {


    public List<User> loadClientListFromFile() throws JAXBException {
        FileAdapter<UserRepository, User> clientsFileAdapter = new FileAdapter<>(new UserRepository());
        return clientsFileAdapter.loadListFromFile("clients.xml");
    }

    public List<Car> loadCarListFromFile() throws JAXBException {
        FileAdapter<CarRepository, Car> carsFileAdapter = new FileAdapter<>(new CarRepository());
        return carsFileAdapter.loadListFromFile("cars.xml");
    }

    public void saveFiles(List<Car> carList, List<User> clientList) {
        FileAdapter<CarRepository, Car> carsFileAdapter = new FileAdapter<>(new CarRepository());
        FileAdapter<UserRepository, User> clientsFileAdapter = new FileAdapter<>(new UserRepository());
        try {
            carsFileAdapter.saveListToFile("cars.xml", new CarRepository(carList));
            clientsFileAdapter.saveListToFile("clients.xml", new UserRepository(clientList));
        } catch (Exception e) {
            System.out.println("Unexpected error.");
        }
        System.out.println("Save successful");
    }
}
