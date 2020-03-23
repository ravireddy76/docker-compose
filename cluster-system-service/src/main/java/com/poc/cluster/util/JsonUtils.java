package com.poc.cluster.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * JsonUtil class used to handle serialize and de-serialize the JSON.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
public class JsonUtils {
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper objectMapper = createObjectMapper();

    /**
     * Serializes an object to a JSON string
     *
     * @param object The object to be serialized
     * @return
     * @throws IOException
     */
    public static <T> String serializeJson(T object)
            throws IOException {
        String jsonInString = null;

        if (object != null) {
            jsonInString = objectMapper.writeValueAsString(object);
        }

        return jsonInString;
    }


    /**
     * DeSerializes the json string to the object type specified in clazz
     *
     * @param clazz
     * @param json
     * @return
     * @throws IOException
     */
    public static <T> T deserializeJson(Class<T> clazz, String json)
            throws IOException {
        T deSerializedValue = null;

        if (StringUtils.isNotBlank(json)) {
            deSerializedValue = objectMapper.readValue(json, clazz);
        }

        return deSerializedValue;
    }


    /**
     * Method to de serialize the input json to collection of objects for the given object type.
     *
     * @param clazz
     * @param inputJson
     * @return List<?>
     * @throws IOException
     */
    public static List<?> deserializeObjectsCollection(Class<?> clazz, String inputJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<?> objectsCollection = mapper.readValue(inputJson,
                TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));

        return objectsCollection;
    }


    /**
     * Create an {@link com.fasterxml.jackson.databind.ObjectMapper} that ignores unknown properties
     *
     * @return ObjectMapper
     */
    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return objectMapper;
    }

}
