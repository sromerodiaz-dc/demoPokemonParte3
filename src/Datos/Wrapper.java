package Datos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class Wrapper {
    private List<DatosAdestrador> adestradores;

    @XmlElement(name = "adestrador")
    public List<DatosAdestrador> getAdestradores() {
        return adestradores;
    }

    public void setAdestradores(List<DatosAdestrador> adestradores) {
        this.adestradores = adestradores;
    }
}

