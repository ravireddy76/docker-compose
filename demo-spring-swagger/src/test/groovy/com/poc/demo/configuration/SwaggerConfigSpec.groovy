package com.poc.demo.configuration

import spock.lang.Specification

class SwaggerConfigSpec extends Specification {

    SwaggerConfig swaggerConfig = new SwaggerConfig()

    def "SwaggerConfig: newsApi"() {
        when:
        def result = swaggerConfig.newsApi()

        then:
        result
        result.documentationType.name == "swagger"
        result.documentationType.version == "2.0"

        result.groupName == "Demo swagger POC"

        /** Validation for Tags of Swagger. */
        result.tags[0].name == "Demo swagger poc micro service"
        result.tags[0].description == "It provides sample end points for customer and user CRUD operations"
    }

}
