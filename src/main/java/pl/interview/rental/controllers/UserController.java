package pl.interview.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.interview.rental.model.Car;
import pl.interview.rental.model.User;
import pl.interview.rental.service.RentalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class UserController {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Autowired
    RentalService rentalService;

    public void createUser() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("First name: ");
        String firstName = br.readLine();
        System.out.println("Last name: ");
        String lastName = br.readLine();
        System.out.println("Birth date (dd-MM-yyyy): ");
        String s = br.readLine();

        User client = new User(firstName, lastName, dateFormat.parse(s));
        System.out.println();

        if (rentalService.containsClient(client)) {
            System.out.println("Client already exists");
        } else {
            rentalService.getClientList().add(client);
            System.out.println("Client added");
        }
    }

    public int chooseClient() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        printList(rentalService.getClientList());
        System.out.print("Choose client...");
        int input = Integer.parseInt(br.readLine());
        return input - 1;
    }

    private void printList(List<User> toRent) {
        IntStream.range(0, toRent.size())
                .forEach(idx ->
                        System.out.println(idx + 1 + ". " + toRent.get(idx))
                );
    }

}
