package pl.interview.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.User;
import pl.interview.rental.service.RentalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CarController {
    @Autowired
    RentalService rentalService;
    @Autowired
    UserController userController;

    public void displayAllByModel() {
        System.out.println("ALL CARS");
        rentalService.getCarList().stream().sorted(Comparator.comparing(Car::getModel)).forEach(item -> System.out.println(item));

    }

    public void displayAvailibleByRateAsc() {
        System.out.println("AVAILABLE CARS");
        rentalService.getCarList().stream()
                .filter(item -> item.getCurrentClient() == null).sorted(Comparator.comparing(Car::getDailyRate)).forEach(item -> System.out.println(item));

    }

    public void displayAvailibleByRateDesc() {
        System.out.println("AVAILABLE CARS");
        rentalService.getCarList().stream()
                .filter(item -> item.getCurrentClient() == null).sorted(Comparator.comparing(Car::getDailyRate).reversed()).forEach(item -> System.out.println(item));

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
            rentalService.getCarList().stream().forEach(item -> {
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
            int clientIndex = userController.chooseClient();
            rentalService.getCarList().stream().forEach(item -> {
                if (item.getId().equals(rentId)) {
                    item.rentForClient(rentalService.getClientList().get(clientIndex));
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
        return rentalService.getCarList().stream()
                .filter(item -> item.getCurrentClient() != null).collect(Collectors.toList());
    }

    private List<Car> loadAvailibleCarList() {
        return rentalService.getCarList().stream()
                .filter(item -> item.getCurrentClient() == null).collect(Collectors.toList());
    }
}
