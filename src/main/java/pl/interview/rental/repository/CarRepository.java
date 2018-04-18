package pl.interview.rental.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.interview.rental.ListGetter;
import pl.interview.rental.model.Car;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name = "cars")
public class CarRepository extends ListGetter<Car> {


    private List<Car> cars;

    public void add(Car car) {
        if (this.cars == null) {
            this.cars = new ArrayList<Car>();
        }
        this.cars.add(car);

    }
    @XmlElement(name = "car")
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public List<Car> getList() {
        return cars;
    }
}
