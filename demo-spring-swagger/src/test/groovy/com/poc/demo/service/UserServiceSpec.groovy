package com.poc.demo.service

import com.poc.demo.dao.UserDao
import com.poc.demo.model.User
import com.poc.demo.utils.TestUtils
import spock.lang.Specification

class UserServiceSpec extends Specification {
    UserDao mockUserDao = Mock(UserDao)
    UserService userService = new UserService(userDao: mockUserDao)

    def "UserService: createUser"() {
        when:
        def result = userService.createUser(TestUtils.sampleUser())

        then:
        1 * mockUserDao.saveUser(_ as User)

        /** Validate the result data.*/
        result.userId == "USR1234"
        result.password == "testPwd"
    }

    def "UserService: updateUser"() {
        when:
        def result = userService.updateUser(TestUtils.sampleUser())

        then:
        1 * mockUserDao.updateUser(_ as User, _ as String)

        /** Validate the result data.*/
        result.userId == "USR1234"
        result.password == "testPwd"
    }

    def "UserService: deleteUser"() {
        when:
        userService.deleteUser(TestUtils.sampleUser().userId)

        then:
        1 * mockUserDao.deleteUser(_ as String)
    }

    def "UserService: getAllUsers"() {
        given:
        def expectedUsers = [TestUtils.sampleUser()]

        when:
        def result = userService.getAllUsers()

        then:
        1 * mockUserDao.findAllUsers() >> { expectedUsers }

        /** Validate the result data.*/
        result.size() == 1
        result[0].userId == "USR1234"
        result[0].password == "testPwd"
    }

    def "UserService: getUserById"() {
        given:
        def sampleUserId = TestUtils.sampleUser().userId

        when:
        def result = userService.getUserById(sampleUserId)

        then:
        1 * mockUserDao.findUserById(_ as String) >> { TestUtils.sampleUser() }

        /** Validate the result data.*/
        result.userId == "USR1234"
        result.password == "testPwd"
    }
}
