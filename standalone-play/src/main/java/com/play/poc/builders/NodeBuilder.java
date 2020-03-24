///*
//package com.play.poc.builders;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class NodeBuilder {
//
//    private static final ObjectMapper MAPPER = new ObjectMapper();
//
//    public static void main(String[] args) {
//
//        try {
//
//
////            ObjectNode childNode1 = MAPPER.valueToTree();
////
////            JsonNode result = MAPPER.createObjectNode().set("dataClasses", )
//
//
////            List<Customer> e = buildCustomers(3);
////            ArrayNode array = MAPPER.valueToTree(e);
////            ObjectNode companyNode = MAPPER.valueToTree("");
////            companyNode.putArray().add();
////            companyNode.putArray("careCustomers").addAll(array);
////
////
//            List<Customer> customers = buildCustomers(3);
//            JsonNode rootNode = MAPPER.createObjectNode();
//
//            ObjectNode careCustomerNode = MAPPER.createObjectNode();
//            careCustomerNode.putArray("careCustomers").addAll(customers);
//
//            JsonNode companyJsonNode = MAPPER.
//            ObjectNode companyNode = MAPPER.createObjectNode();
//            companyNode.put("companyName", "cName");
//            companyNode.putArray("careCustomers").add()
//
//            ArrayNode arrayNode = MAPPER.createArrayNode();
//
//            JsonNode careCustomer = MAPPER.createObjectNode();
//            ((ObjectNode) careCustomer).put("careCustomer", e);
//
//
//
//
////            JSONObject jsonComplex = new JSONObject();
////            JSONObject notification = new JSONObject();
////            notification.put("message", "test");
////            notification.put("sound", "sounds_alarmsound.wav");
////
////            JSONObject targetJsonObject= new JSONObject();
////            JSONArray targetJsonArray= new JSONArray();
////            JSONObject appsJsonObject= new JSONObject();
////            appsJsonObject.put("id","app_id");
////
////            JSONArray platformArray = new JSONArray();
////            platformArray.add("ios");
////            appsJsonObject.put("platforms",platformArray);
////            targetJsonArray.add(appsJsonObject);
////            targetJsonObject.put("apps", targetJsonArray);
////            notification.put("target", targetJsonObject);
////            jsonComplex.put("notification", notification);
////            jsonComplex.put("access_token", "access_token");
////            System.out.println(jsonComplex);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    private static ArrayNode buildCustomers(int size) {
//        ArrayNode customers = MAPPER.createArrayNode();
//
//        for (int iter = 0; iter < size; iter++) {
//            JsonNode jsonNode = MAPPER.createObjectNode();
//            jsonNode.
//            Customer customer = new Customer(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(15));
//            customers.add(customer);
//        }
//
//        return customers;
//    }
//}
//*/
