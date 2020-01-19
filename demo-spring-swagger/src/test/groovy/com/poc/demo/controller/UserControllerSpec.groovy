package com.poc.demo.controller

import com.poc.demo.model.User
import com.poc.demo.service.UserService
import com.poc.demo.utils.TestUtils
import spock.lang.Specification

class UserControllerSpec extends Specification {

    UserService mockUserService = Mock(UserService)
    UserController userController = new UserController(userService: mockUserService)

    def "UserController:Create:: #scenario"() {
        when:
        def response = userController.create(TestUtils.sampleUser())

        then:
        if (isException) {
            1 * mockUserService.createUser(_ as User) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockUserService.createUser(_ as User) >> { TestUtils.sampleUser() }
        }

        /** Validate the response for each scenario.*/
        validateResponse(response, isException, statusReason)

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }


    def "UserController:Update:: #scenario"() {
        when:
        def response = userController.update(TestUtils.sampleUser())

        then:
        if (isException) {
            1 * mockUserService.updateUser(_ as User) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockUserService.updateUser(_ as User) >> { TestUtils.sampleUser() }
        }

        /** Validate the response for each scenario.*/
        validateResponse(response, isException, statusReason)

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }

    def "UserController:Delete:: #scenario"() {
        when:
        def response = userController.delete(TestUtils.sampleUser().userId)

        then:
        if (isException) {
            1 * mockUserService.deleteUser(_ as String) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockUserService.deleteUser(_ as String)
        }

        /** Validate the response for each scenario.*/
        response != null
        response.statusCode.reasonPhrase == statusReason

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }

    def "UserController:getUserById:: #scenario"() {
        when:
        def response = userController.getUserById(TestUtils.sampleUser().userId)

        then:
        if (isException) {
            1 * mockUserService.getUserById(_ as String) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockUserService.getUserById(_ as String) >> { TestUtils.sampleUser() }
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
        def response = userController.getAllUsers()

        then:
        if (isException) {
            1 * mockUserService.getAllUsers() >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockUserService.getAllUsers() >> { [TestUtils.sampleUser()] }
        }

        /** Validate the response for each scenario.*/
        validateAllUserResponse(response, isException, statusReason)

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
            User responseUser = ((User) response.body)
            assert responseUser.userId == "USR1234"
            assert responseUser.password == "testPwd"

        }
    }

    /**
     * Method to validate service API response.
     *
     * @param response
     * @param isException
     * @param statusReason
     */
    private void validateAllUserResponse(def response, def isException, def statusReason) {
        /** Validate the response for each scenario.*/
        assert response != null
        assert response.statusCode.reasonPhrase == statusReason

        if (!isException) {
            List<User> responseUsers = ((List<User>) response.body)
            responseUsers.size() == 1
            assert responseUsers[0].userId == "USR1234"
            assert responseUsers[0].password == "testPwd"
        }
    }

}
