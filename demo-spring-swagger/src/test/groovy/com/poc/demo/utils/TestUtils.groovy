package com.poc.demo.utils

import com.poc.demo.model.Customer
import com.poc.demo.model.User

class TestUtils {

    static User sampleUser() {
        User user = new User(userId: "USR1234", password: "testPwd")
        user
    }

    static Customer sampleCustomer() {
        Customer customer = new Customer(customerId: "CUST123", firstName: "testFName", lastName: "testLName",
                address: "testAddress", zipCode: "62722", contactNumber: "4442229999")

        customer
    }

}
