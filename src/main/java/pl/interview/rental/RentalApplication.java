package pl.interview.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.interview.rental.service.RentalService;

@SpringBootApplication
public class RentalApplication implements CommandLineRunner {
    @Autowired
    RentalService rentalService;

    public static void main(String[] args) {
        SpringApplication.run(RentalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
rentalService.helloClients();
    }
}
