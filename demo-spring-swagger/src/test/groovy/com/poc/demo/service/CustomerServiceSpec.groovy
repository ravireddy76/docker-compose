package com.poc.demo.service

import com.poc.demo.dao.CustomerDao
import com.poc.demo.model.Customer
import com.poc.demo.utils.TestUtils
import spock.lang.Specification

class CustomerServiceSpec extends Specification {
    CustomerDao mockCustomerDao = Mock(CustomerDao)

    CustomerService customerService = new CustomerService(customerDao: mockCustomerDao)

    def "customerService: createCustomer"() {
        when:
        def result = customerService.createCustomer(TestUtils.sampleCustomer())

        then:
        1 * mockCustomerDao.saveCustomer(_ as Customer)

        /** Validate the result data.*/
        result.customerId == "CUST123"
        result.firstName == "testFName"
        result.lastName == "testLName"
        result.address == "testAddress"
        result.zipCode == "62722"
        result.contactNumber == "4442229999"
    }

    def "customerService: updateCustomer"() {
        when:
        def result = customerService.updateCustomer(TestUtils.sampleCustomer())

        then:
        1 * mockCustomerDao.updateCustomer(_ as Customer, _ as String)

        /** Validate the result data.*/
        result.customerId == "CUST123"
        result.firstName == "testFName"
        result.lastName == "testLName"
        result.address == "testAddress"
        result.zipCode == "62722"
        result.contactNumber == "4442229999"
    }

    def "customerService: deleteCustomer"() {
        when:
        customerService.deleteCustomer(TestUtils.sampleCustomer().customerId)

        then:
        1 * mockCustomerDao.deleteCustomer(_ as String)
    }

    def "customerService: getAllCustomers"() {
        given:
        def expectedUsers = [TestUtils.sampleCustomer()]

        when:
        def result = customerService.getAllCustomers()

        then:
        1 * mockCustomerDao.findAllCustomers() >> { expectedUsers }

        /** Validate the result data.*/
        result.size() == 1
        result[0].customerId == "CUST123"
        result[0].firstName == "testFName"
        result[0].lastName == "testLName"
        result[0].address == "testAddress"
        result[0].zipCode == "62722"
        result[0].contactNumber == "4442229999"
    }

    def "customerService: getCustomerById"() {
        given:
        def sampleCustomerId = TestUtils.sampleCustomer().customerId

        when:
        def result = customerService.getCustomerById(sampleCustomerId)

        then:
        1 * mockCustomerDao.findCustomerById(_ as String) >> { TestUtils.sampleCustomer() }

        /** Validate the result data.*/
        result.customerId == "CUST123"
        result.firstName == "testFName"
        result.lastName == "testLName"
        result.address == "testAddress"
        result.zipCode == "62722"
        result.contactNumber == "4442229999"
    }
    
}
