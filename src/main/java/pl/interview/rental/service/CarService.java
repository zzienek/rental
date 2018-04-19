package pl.interview.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.interview.rental.controllers.RentalController;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.User;

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

    public void returnCar(List<Car> carList) throws IOException {

        System.out.println("RETURN CAR");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Car> toReturnList = loadTakenCarList(carList);
        if (!CollectionUtils.isEmpty(toReturnList)) {
            printList(toReturnList);
            System.out.print("Choose returned car...");
            int input = Integer.parseInt(br.readLine());
            Long returnedId = toReturnList.get(input - 1).getId();

            carList.stream().forEach(item -> {
                if (item.getId().equals(returnedId)) {
                    item.returnCar();
                    System.out.println("Car returned");
                }
            });
        } else {
            System.out.println("All cars are available");
        }
    }

    public void rentCar(List<Car> carList, List<User> clientList) throws IOException {
        System.out.println("RENT CAR");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Car> toRent = loadAvailibleCarList(carList);
        if (!CollectionUtils.isEmpty(toRent)) {
            printList(toRent);
            System.out.print("Choose car for rent...");
            int input = Integer.parseInt(br.readLine());
            Long rentId = toRent.get(input - 1).getId();
            int clientIndex = userService.chooseClient(clientList);
            carList.stream().forEach(item -> {
                if (item.getId().equals(rentId)) {
                    item.rentForClient(clientList.get(clientIndex));
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

    private List<Car> loadTakenCarList(List<Car> carList) {
        return carList.stream()
                .filter(item -> item.getCurrentClient() != null).collect(Collectors.toList());
    }

    private List<Car> loadAvailibleCarList(List<Car> carList) {
        return carList.stream()
                .filter(item -> item.getCurrentClient() == null).collect(Collectors.toList());
    }
}
