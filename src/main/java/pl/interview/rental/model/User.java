package pl.interview.rental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "client")
public class User {
    @XmlElement(name = "firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "dateOfBirth")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dateOfBirth;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return
                "First Name:'" + firstName + '\'' +
                "Last Name:'" + lastName + '\'' +
                "Date Of Birth:" + dateFormat.format(dateOfBirth) ;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
