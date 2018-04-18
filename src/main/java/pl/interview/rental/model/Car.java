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
        String s = "Model:'" + model + '\'' +
                ", Plate Number:'" + plateNo + '\'' +
                ", Daily Rate=" + dailyRate;
        return currentClient != null ? s + ", Current Client:" + currentClient :         s;
    }

    public Long getId() {
        return id;
    }


    public String getModel() {
        return model;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public User getCurrentClient() {
        return currentClient;
    }

    public void returnCar() {
        this.currentClient = null;
    }

    public void rentForClient(User user) {
        this.currentClient = user;
    }
}
