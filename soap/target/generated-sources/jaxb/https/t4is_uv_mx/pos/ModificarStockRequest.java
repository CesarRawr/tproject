//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.06.08 a las 07:31:38 AM CDT 
//


package https.t4is_uv_mx.pos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tortillas" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="totopos" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nickname",
    "tortillas",
    "totopos"
})
@XmlRootElement(name = "ModificarStockRequest")
public class ModificarStockRequest {

    @XmlElement(required = true)
    protected String nickname;
    protected int tortillas;
    protected int totopos;

    /**
     * Obtiene el valor de la propiedad nickname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Define el valor de la propiedad nickname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Obtiene el valor de la propiedad tortillas.
     * 
     */
    public int getTortillas() {
        return tortillas;
    }

    /**
     * Define el valor de la propiedad tortillas.
     * 
     */
    public void setTortillas(int value) {
        this.tortillas = value;
    }

    /**
     * Obtiene el valor de la propiedad totopos.
     * 
     */
    public int getTotopos() {
        return totopos;
    }

    /**
     * Define el valor de la propiedad totopos.
     * 
     */
    public void setTotopos(int value) {
        this.totopos = value;
    }

}
