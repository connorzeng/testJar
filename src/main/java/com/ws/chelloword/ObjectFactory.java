
package com.ws.chelloword;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ws.chelloword package. 
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

    private final static QName _CUserDepartment_QNAME = new QName("http://entity.connor.com", "department");
    private final static QName _CUserEmail_QNAME = new QName("http://entity.connor.com", "email");
    private final static QName _CUserId_QNAME = new QName("http://entity.connor.com", "id");
    private final static QName _CUserSalary_QNAME = new QName("http://entity.connor.com", "salary");
    private final static QName _CUserSex_QNAME = new QName("http://entity.connor.com", "sex");
    private final static QName _CUserWorkername_QNAME = new QName("http://entity.connor.com", "workername");
    private final static QName _HouseAddress_QNAME = new QName("http://helloworld.ws.com", "address");
    private final static QName _HouseHouseNo_QNAME = new QName("http://helloworld.ws.com", "houseNo");
    private final static QName _PeopleName_QNAME = new QName("http://helloworld.ws.com", "name");
    private final static QName _PeopleNickName_QNAME = new QName("http://helloworld.ws.com", "nickName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ws.chelloword
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link People }
     * 
     */
    public People createPeople() {
        return new People();
    }

    /**
     * Create an instance of {@link ArrayOfHouse }
     * 
     */
    public ArrayOfHouse createArrayOfHouse() {
        return new ArrayOfHouse();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link ArrayOfCUser }
     * 
     */
    public ArrayOfCUser createArrayOfCUser() {
        return new ArrayOfCUser();
    }

    /**
     * Create an instance of {@link House }
     * 
     */
    public House createHouse() {
        return new House();
    }

    /**
     * Create an instance of {@link CUser }
     * 
     */
    public CUser createCUser() {
        return new CUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.connor.com", name = "department", scope = CUser.class)
    public JAXBElement<String> createCUserDepartment(String value) {
        return new JAXBElement<String>(_CUserDepartment_QNAME, String.class, CUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.connor.com", name = "email", scope = CUser.class)
    public JAXBElement<String> createCUserEmail(String value) {
        return new JAXBElement<String>(_CUserEmail_QNAME, String.class, CUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.connor.com", name = "id", scope = CUser.class)
    public JAXBElement<Integer> createCUserId(Integer value) {
        return new JAXBElement<Integer>(_CUserId_QNAME, Integer.class, CUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.connor.com", name = "salary", scope = CUser.class)
    public JAXBElement<Integer> createCUserSalary(Integer value) {
        return new JAXBElement<Integer>(_CUserSalary_QNAME, Integer.class, CUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.connor.com", name = "sex", scope = CUser.class)
    public JAXBElement<String> createCUserSex(String value) {
        return new JAXBElement<String>(_CUserSex_QNAME, String.class, CUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.connor.com", name = "workername", scope = CUser.class)
    public JAXBElement<String> createCUserWorkername(String value) {
        return new JAXBElement<String>(_CUserWorkername_QNAME, String.class, CUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.ws.com", name = "address", scope = House.class)
    public JAXBElement<String> createHouseAddress(String value) {
        return new JAXBElement<String>(_HouseAddress_QNAME, String.class, House.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.ws.com", name = "houseNo", scope = House.class)
    public JAXBElement<String> createHouseHouseNo(String value) {
        return new JAXBElement<String>(_HouseHouseNo_QNAME, String.class, House.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.ws.com", name = "name", scope = People.class)
    public JAXBElement<String> createPeopleName(String value) {
        return new JAXBElement<String>(_PeopleName_QNAME, String.class, People.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.ws.com", name = "nickName", scope = People.class)
    public JAXBElement<String> createPeopleNickName(String value) {
        return new JAXBElement<String>(_PeopleNickName_QNAME, String.class, People.class, value);
    }

}
