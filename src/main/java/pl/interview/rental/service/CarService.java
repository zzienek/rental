package pl.interview.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.interview.rental.controllers.RentalController;
import pl.interview.rental.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CarService {
    UserService userService;

    @Autowired
    public CarService( UserService userService) {
        this.userService = userService;
    }

    public void returnCar() throws IOException {

        System.out.println("RETURN CAR");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Car> toReturnList = loadTakenCarList();
        if (!CollectionUtils.isEmpty(toReturnList)) {
            printList(toReturnList);
            System.out.print("Choose returned car...");
            int input = Integer.parseInt(br.readLine());
            Long returnedId = toReturnList.get(input - 1).getId();

            rentalController.getCarList().stream().forEach(item -> {
                if (item.getId().equals(returnedId)) {
                    item.returnCar();
                    System.out.println("Car returned");
                }
            });
        } else {
            System.out.println("All cars are available");
        }
    }

    public void rentCar() throws IOException {
        System.out.println("RENT CAR");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Car> toRent = loadAvailibleCarList();
        if (!CollectionUtils.isEmpty(toRent)) {
            printList(toRent);
            System.out.print("Choose car for rent...");
            int input = Integer.parseInt(br.readLine());
            Long rentId = toRent.get(input - 1).getId();
            int clientIndex = userService.chooseClient(rentalController.getClientList());
            rentalController.getCarList().stream().forEach(item -> {
                if (item.getId().equals(rentId)) {
                    item.rentForClient(rentalController.getClientList().get(clientIndex));
                    System.out.println("Car successfully rent!");
                }
            });
        } else {
            System.out.println("No available cars");
        }
    }

    private void printList(List<Car> list) {
        IntStream.range(0, list.size())
                .forEach(idx ->
                        System.out.println(idx + 1 + ". " + list.get(idx))
                );
    }

    private List<Car> loadTakenCarList() {
        return rentalController.getCarList().stream()
                .filter(item -> item.getCurrentClient() != null).collect(Collectors.toList());
    }

    private List<Car> loadAvailibleCarList() {
        return rentalController.getCarList().stream()
                .filter(item -> item.getCurrentClient() == null).collect(Collectors.toList());
    }
}
