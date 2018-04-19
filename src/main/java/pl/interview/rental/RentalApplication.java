package pl.interview.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import pl.interview.rental.service.MenuService;

@SpringBootApplication
public class RentalApplication implements CommandLineRunner {
    @Autowired
    public RentalApplication(MenuService menuService) {
        this.menuService = menuService;
    }

    MenuService menuService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(RentalApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        SpringApplication.run(RentalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menuService.init();
    }


}
