package pl.interview.rental.adapters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.interview.rental.ListGetter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
@AllArgsConstructor
@Getter@Setter
public class FileAdapter<V extends ListGetter, T>{
    private V object;


    public List<T> loadListFromFile(String fileName) throws JAXBException {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        V unmarshallCars = (V) unmarshaller.unmarshal(file);
        return unmarshallCars.getList();
    }

    public void saveListToFile(String fileName, V repository) throws JAXBException {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(repository, file);
    }
}
