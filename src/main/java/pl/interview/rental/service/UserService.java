package pl.interview.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.interview.rental.controllers.RentalController;
import pl.interview.rental.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class UserService {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public User createUser() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("First name: ");
        String firstName = br.readLine();
        System.out.println("Last name: ");
        String lastName = br.readLine();
        System.out.println("Birth date (dd-MM-yyyy): ");
        String s = br.readLine();

        return new User(firstName, lastName, dateFormat.parse(s));


    }

    public int chooseClient(List<User> clientList) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        printList(clientList);
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
