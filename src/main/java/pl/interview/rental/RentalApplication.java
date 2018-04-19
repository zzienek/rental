package pl.interview.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RentalApplication implements CommandLineRunner {
    @Autowired
    public RentalApplication(Menu menu) {
        this.menu = menu;
    }

    Menu menu;

    public static void main(String[] args) {
        new SpringApplicationBuilder(RentalApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        SpringApplication.run(RentalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menu.init();
    }


}
