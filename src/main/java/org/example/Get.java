package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Get {
    public static void storeOrder() throws IOException {
        URL url = new URL("https://petstore.swagger.io/v2/store/order/3");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);
        BufferedReader in;
        if ( responseCode == HttpURLConnection.HTTP_OK ) {
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
        } else {
            in = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream()));
        }
        String inputLine;
        StringBuffer apiResponseGETOrder = new StringBuffer();

        while ((inputLine = in.readLine()) !=null){
            apiResponseGETOrder.append(inputLine);
        }
        Gson gson = new Gson();
        OrderDto orderDtoReceived = gson.fromJson(String.valueOf(apiResponseGETOrder), OrderDto.class);
        System.out.println(apiResponseGETOrder);

        in.close();
        connection.disconnect();
    }
}
