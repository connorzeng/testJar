
package com.ws.chelloword;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="in0" type="{http://helloworld.ws.com}People"/&gt;
 *         &lt;element name="in1" type="{http://helloworld.ws.com}ArrayOfHouse"/&gt;
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
    "in0",
    "in1"
})
@XmlRootElement(name = "getUser")
public class GetUser {

    @XmlElement(required = true, nillable = true)
    protected People in0;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfHouse in1;

    /**
     * 获取in0属性的值。
     * 
     * @return
     *     possible object is
     *     {@link People }
     *     
     */
    public People getIn0() {
        return in0;
    }

    /**
     * 设置in0属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link People }
     *     
     */
    public void setIn0(People value) {
        this.in0 = value;
    }

    /**
     * 获取in1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfHouse }
     *     
     */
    public ArrayOfHouse getIn1() {
        return in1;
    }

    /**
     * 设置in1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfHouse }
     *     
     */
    public void setIn1(ArrayOfHouse value) {
        this.in1 = value;
    }

}
