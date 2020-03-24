package logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.play.poc.builders.Customer;
import com.play.poc.util.JsonUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class JsonLogClient {

    public static void main(String[] args){
        try{

            ObjectMapper mapper = new ObjectMapper();
            Customer customer = new Customer("CUSTID12344", "TestLogsss");
            String jsonValue = JsonUtils.serializeJson(customer);
            System.out.println("JsonVlaue for Logs :: "+jsonValue);

            Map<String, String> testMap = new HashMap<>();
            testMap.put("T1","Test1");
            testMap.put("T2","Test2");

            String mapValue2Log = JsonUtils.serializeJson(testMap);
            System.out.println("Map Value to Logs :: "+mapValue2Log);

            Instant instant = Instant.now();
            System.out.println("Current Time :: "+instant.toString());



//            log.info("request {}, message {}, timeMs {}, mdc {}, logMap {}",
//                    MDC.get(REQUEST_ID_MDC_KEY),
//                    message,
//                    Duration.between(start, Instant.now()).toMillis(),
//                    MDC.getCopyOfContextMap(),
//                    logMapToSring(req));
//
//            request 126, message hi, timeMs 30, mdc {"i":"te"} logMap {"i":"te"}
//
//            {"\"request":"123", "message":"TestMesage"}
//
//            "\"request"\"":"126",
//
//            LogInput logInput = new LogInput();
//            logInput.setRequest(MDC.get(REQUEST_ID_MDC_KEY));
//            String finalLogs = JsonUtils.serializeJson(logInput);
//            System.out.println(finalLogs);
//
//            Map<String, String> finalLogMap = new HashMap<>();
//            finalLogMap.

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
