
package clientews.servicio;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PersonaServiceWS", targetNamespace = "http://servicio.sga.rlr.com.mx/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PersonaServiceWS {


    /**
     * 
     * @return
     *     returns java.util.List<clientews.servicio.Persona>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listarPersonas", targetNamespace = "http://servicio.sga.rlr.com.mx/", className = "clientews.servicio.ListarPersonas")
    @ResponseWrapper(localName = "listarPersonasResponse", targetNamespace = "http://servicio.sga.rlr.com.mx/", className = "clientews.servicio.ListarPersonasResponse")
    @Action(input = "http://servicio.sga.rlr.com.mx/PersonaServiceWS/listarPersonasRequest", output = "http://servicio.sga.rlr.com.mx/PersonaServiceWS/listarPersonasResponse")
    public List<Persona> listarPersonas();

}
