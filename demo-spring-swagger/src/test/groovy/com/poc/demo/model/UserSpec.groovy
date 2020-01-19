package com.poc.demo.model

import spock.lang.Specification

class UserSpec extends Specification {
    User sampleUser = new User(userId: "USR1234", password: "testPwd")

    def "User: Setter/Getters methods"() {
        when:
        User user = sampleUser

        then:
        user.userId == "USR1234"
        user.password == "testPwd"
    }

    def "User: HashCode"() {
        when:
        User userClone = sampleUser

        then:
        sampleUser.hashCode() == userClone.hashCode()
    }

    def "User: Equals"() {
        when:
        User userClone = sampleUser

        then:
        sampleUser.equals(userClone)
    }

    def "Customer: ToString"() {
        when:
        User user = sampleUser

        then:
        user.toString().length() > 0
    }

}
