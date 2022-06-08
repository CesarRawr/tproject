//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.06.08 a las 07:31:38 AM CDT 
//


package https.t4is_uv_mx.pos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the https.t4is_uv_mx.pos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BuscarProductosRequest_QNAME = new QName("https://t4is.uv.mx/pos", "BuscarProductosRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.t4is_uv_mx.pos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuscarProductosResponse }
     * 
     */
    public BuscarProductosResponse createBuscarProductosResponse() {
        return new BuscarProductosResponse();
    }

    /**
     * Create an instance of {@link VentaRequest }
     * 
     */
    public VentaRequest createVentaRequest() {
        return new VentaRequest();
    }

    /**
     * Create an instance of {@link VentaResponse }
     * 
     */
    public VentaResponse createVentaResponse() {
        return new VentaResponse();
    }

    /**
     * Create an instance of {@link ModificarStockRequest }
     * 
     */
    public ModificarStockRequest createModificarStockRequest() {
        return new ModificarStockRequest();
    }

    /**
     * Create an instance of {@link ModificarStockResponse }
     * 
     */
    public ModificarStockResponse createModificarStockResponse() {
        return new ModificarStockResponse();
    }

    /**
     * Create an instance of {@link AgregarProductoRequest }
     * 
     */
    public AgregarProductoRequest createAgregarProductoRequest() {
        return new AgregarProductoRequest();
    }

    /**
     * Create an instance of {@link AgregarProductoResponse }
     * 
     */
    public AgregarProductoResponse createAgregarProductoResponse() {
        return new AgregarProductoResponse();
    }

    /**
     * Create an instance of {@link BuscarProductosResponse.Inventario }
     * 
     */
    public BuscarProductosResponse.Inventario createBuscarProductosResponseInventario() {
        return new BuscarProductosResponse.Inventario();
    }

    /**
     * Create an instance of {@link ModificarInventarioRequest }
     * 
     */
    public ModificarInventarioRequest createModificarInventarioRequest() {
        return new ModificarInventarioRequest();
    }

    /**
     * Create an instance of {@link ModificarInventarioResponse }
     * 
     */
    public ModificarInventarioResponse createModificarInventarioResponse() {
        return new ModificarInventarioResponse();
    }

    /**
     * Create an instance of {@link BorrarProductoRequest }
     * 
     */
    public BorrarProductoRequest createBorrarProductoRequest() {
        return new BorrarProductoRequest();
    }

    /**
     * Create an instance of {@link BorrarProductoResponse }
     * 
     */
    public BorrarProductoResponse createBorrarProductoResponse() {
        return new BorrarProductoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "https://t4is.uv.mx/pos", name = "BuscarProductosRequest")
    public JAXBElement<Object> createBuscarProductosRequest(Object value) {
        return new JAXBElement<Object>(_BuscarProductosRequest_QNAME, Object.class, null, value);
    }

}
