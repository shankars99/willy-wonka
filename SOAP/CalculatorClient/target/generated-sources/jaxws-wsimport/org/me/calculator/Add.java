
package org.me.calculator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for add complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="add">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="i" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="j" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "add", propOrder = {
    "i",
    "j"
})
public class Add {

    protected double i;
    protected double j;

    /**
     * Gets the value of the i property.
     * 
     */
    public double getI() {
        return i;
    }

    /**
     * Sets the value of the i property.
     * 
     */
    public void setI(double value) {
        this.i = value;
    }

    /**
     * Gets the value of the j property.
     * 
     */
    public double getJ() {
        return j;
    }

    /**
     * Sets the value of the j property.
     * 
     */
    public void setJ(double value) {
        this.j = value;
    }

}
