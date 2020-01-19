package com.poc.demo.controller

import com.poc.demo.model.Customer
import com.poc.demo.service.CustomerService
import com.poc.demo.utils.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class CustomerControllerSpec extends Specification {

    CustomerService mockCustomerService = Mock(CustomerService)
    CustomerController customerController = new CustomerController(customerService: mockCustomerService)

    def "CustomerController:Create:: #scenario"() {
        when:
        def response = customerController.create(TestUtils.sampleCustomer())

        then:
        if (isException) {
            1 * mockCustomerService.createCustomer(_ as Customer) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockCustomerService.createCustomer(_ as Customer) >> { TestUtils.sampleCustomer() }
        }

        /** Validate the response for each scenario.*/
        validateResponse(response, isException, statusReason)

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }


    def "CustomerController:Update:: #scenario"() {
        when:
        def response = customerController.update(TestUtils.sampleCustomer())

        then:
        if (isException) {
            1 * mockCustomerService.updateCustomer(_ as Customer) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockCustomerService.updateCustomer(_ as Customer) >> { TestUtils.sampleCustomer() }
        }

        /** Validate the response for each scenario.*/
        validateResponse(response, isException, statusReason)

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }

    def "CustomerController:Delete:: #scenario"() {
        when:
        def response = customerController.delete(TestUtils.sampleCustomer().customerId)

        then:
        if (isException) {
            1 * mockCustomerService.deleteCustomer(_ as String) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockCustomerService.deleteCustomer(_ as String)
        }

        /** Validate the response for each scenario.*/
        response != null
        response.statusCode.reasonPhrase == statusReason

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }

    def "CustomerController:getCustomerById:: #scenario"() {
        when:
        def response = customerController.getCustomerById(TestUtils.sampleCustomer().customerId)

        then:
        if (isException) {
            1 * mockCustomerService.getCustomerById(_ as String) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockCustomerService.getCustomerById(_ as String) >> { TestUtils.sampleCustomer() }
        }

        /** Validate the response for each scenario.*/
        validateResponse(response, isException, statusReason)

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }

    def "CustomerController:getAllCustomers:: #scenario"() {
        when:
        def response = customerController.getAllCustomers()

        then:
        if (isException) {
            1 * mockCustomerService.getAllCustomers() >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockCustomerService.getAllCustomers() >> { [TestUtils.sampleCustomer()] }
        }

        /** Validate the response for each scenario.*/
        validateAllCustomerResponse(response, isException, statusReason)

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }

    /**
     * Method to validate service API response.
     *
     * @param response
     * @param isException
     * @param statusReason
     */
    private void validateResponse(def response, def isException, def statusReason) {
        /** Validate the response for each scenario.*/
        assert response != null
        assert response.statusCode.reasonPhrase == statusReason

        if (!isException) {
            Customer responseCustomer = ((Customer) response.body)

            assert responseCustomer.customerId == "CUST123"
            assert responseCustomer.firstName == "testFName"
            assert responseCustomer.lastName == "testLName"
            assert responseCustomer.address == "testAddress"
            assert responseCustomer.zipCode == "62722"
            assert responseCustomer.contactNumber == "4442229999"
        }
    }

    /**
     * Method to validate service API response.
     *
     * @param response
     * @param isException
     * @param statusReason
     */
    private void validateAllCustomerResponse(def response, def isException, def statusReason) {
        /** Validate the response for each scenario.*/
        assert response != null
        assert response.statusCode.reasonPhrase == statusReason

        if (!isException) {
            List<Customer> responseCustomers = ((List<Customer>) response.body)
            responseCustomers.size() == 1

            assert responseCustomers[0].customerId == "CUST123"
            assert responseCustomers[0].firstName == "testFName"
            assert responseCustomers[0].lastName == "testLName"
            assert responseCustomers[0].address == "testAddress"
            assert responseCustomers[0].zipCode == "62722"
            assert responseCustomers[0].contactNumber == "4442229999"
        }
    }

}
