package org.example;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
    static final Logger log = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws IOException {
//        Post.storeOrder();
//        Get.storeOrder();
        log.info("Program started");
        Gson gson = new Gson();

        //POST
        Connection connectionPOST = new Connection();
        connectionPOST.init("https://petstore.swagger.io/v2/store/order");
        connectionPOST.setupMethod(Methods.POST);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = new Date();
        OrderDto orderCreate = new OrderDto(3,65849,1,formatter.format(date),"placed",true);
        connectionPOST.writeBody(gson.toJson(orderCreate));
        System.out.println(connectionPOST.getResponseCode());
        StringBuffer apiResponsePOSTorder = connectionPOST.processResponse();
        OrderDto orderDtoReceivedPOST = gson.fromJson(String.valueOf(apiResponsePOSTorder), OrderDto.class);
        System.out.println(apiResponsePOSTorder);
        connectionPOST.disconnect();

        //GET
        Connection connectionGET = new Connection();
        connectionGET.init("https://petstore.swagger.io/v2/store/order/3");
        connectionGET.setupMethod(Methods.GET);
        System.out.println(connectionGET.getResponseCode());
        StringBuffer response = connectionGET.processResponse();
        OrderDto orderDtoReceived = gson.fromJson(String.valueOf(response), OrderDto.class);
        System.out.println(response);
        connectionGET.disconnect();
    }
}
