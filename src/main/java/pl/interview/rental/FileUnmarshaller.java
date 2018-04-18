package pl.interview.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.interview.rental.model.Cars;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
@AllArgsConstructor
@Getter@Setter
public class FileUnmarshaller<V extends ListGetter, T>{
    private V object;


    public List<T> getListFromFile(String fileName) throws JAXBException {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        V unmarshalCars = (V) unmarshaller.unmarshal(file);
        return unmarshalCars.getList();
    }
}
