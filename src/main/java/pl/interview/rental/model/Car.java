package pl.interview.rental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "car")
public class Car {
    @XmlAttribute(name = "id")
    private Long id;
    @XmlElement(name = "model")
    private String model;
    @XmlElement(name = "plateNo")
    private String plateNo;
    @XmlElement(name = "dailyRate")
    private double dailyRate;
    @XmlElement(name = "client")
    private User currentClient;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", plateNo='" + plateNo + '\'' +
                ", dailyRate=" + dailyRate +
                ", currentClient=" + currentClient +
                '}';
    }
}
