package com.poc.demo.model

import spock.lang.Specification

class CustomerSpec extends Specification {
    Customer sampleCustomer = new Customer(customerId: "CUST123", firstName: "testFName", lastName: "testLName",
            address: "testAddress", zipCode: "62722", contactNumber: "4442229999")

    def "Customer: Setter/Getters methods"() {
        when:
        Customer customer = sampleCustomer

        then:
        customer.customerId == "CUST123"
        customer.firstName == "testFName"
        customer.lastName == "testLName"
        customer.address == "testAddress"
        customer.zipCode == "62722"
        customer.contactNumber == "4442229999"
    }

    def "Customer: HashCode"() {
        when:
        Customer customerClone = sampleCustomer

        then:
        sampleCustomer.hashCode() == customerClone.hashCode()
    }

    def "Customer: Equals"() {
        when:
        Customer customerClone = sampleCustomer

        then:
        sampleCustomer.equals(customerClone)
    }

    def "Customer: ToString"() {
        when:
        Customer customer = sampleCustomer

        then:
        customer.toString().length() > 0
    }

}
