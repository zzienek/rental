package pl.interview.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.interview.rental.controllers.RentalController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class MenuService {

    public void init() {
        int input = 0;

        do {


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//            try {
//                showMenu();
//                input = Integer.parseInt(br.readLine());
//                switch (input) {
//                    case 1:
//                        this.rentalController.createUser();
//                        waitForEnterKey();
//                        break;
//                    case 2:
//                        rentalController.listAvailibleAsc();
//                        waitForEnterKey();
//                        break;
//                    case 3:
//                        rentalController.listAvailibleDesc();
//                        waitForEnterKey();
//                        break;
//                    case 4:
//                        rentalController.displayAllByModel();
//                        waitForEnterKey();
//                        break;
//                    case 5:
//                        rentalController.rentCar();
//                        waitForEnterKey();
//                        break;
//                    case 6:
//                        rentalController.returnCar();
//                        waitForEnterKey();
//                        break;
//                    case 7:
//                        rentalController.saveFiles();
//                        waitForEnterKey();
//                        break;
//                    case 8:
//                        System.out.println("You have quit the program\r\n");
//                        System.exit(1);
//                        break;
//                    default:
//                        clearScreen();
//                        System.out.println("You have entered an invalid selection, please try again");
//                        System.in.read();
//                }
//            } catch (IOException ioe) {
//                System.out.println("IO error trying to read your input!");
//                System.exit(1);
//            } catch (NumberFormatException ex) {
//                System.out.println();
//            }


        } while (input != 8);
    }

    private void waitForEnterKey() throws IOException {
        System.out.print("Press ENTER...");
        System.in.read();
    }

    private void showMenu() throws IOException {
        clearScreen();
        System.out.println("Car Rental");
        System.out.println();
        System.out.println("Menu Options:");
        System.out.println("1. Add Client");
        System.out.println("2. List available rate (ASC)");
        System.out.println("3. List available rate (DESC)");
        System.out.println("4. All cars");
        System.out.println("5. Rent car");
        System.out.println("6. Return car");
        System.out.println("7. Save all");
        System.out.println("8. Exit the program");
        System.out.println();
    }

    private void clearScreen() {
        for (int clear = 0; clear < 10; clear++) {
            System.out.println();
        }
    }
}
