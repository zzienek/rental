package pl.interview.rental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.interview.rental.ListGetter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "clients")
public class Users extends ListGetter<User> {

    private List<User> users;

    public void add(User user) {
        if (this.users == null) {
            this.users = new ArrayList<User>();
        }
        this.users.add(user);

    }
    @XmlElement(name = "client")
    public void setUsers(List<User> users) {
        this.users = users;
    }


    @Override
    public List<User> getList() {
        return users;
    }
}
