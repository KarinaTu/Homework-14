package org.example;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.in;
public class Post {
    public static void storeOrder() throws IOException {
        URL urlPost = new URL("https://petstore.swagger.io/v2/store/order");
        HttpURLConnection connection1 = (HttpURLConnection) urlPost.openConnection();
        connection1.setDoOutput(true);
        connection1.setRequestMethod("POST");
        connection1.setRequestProperty("Content-Type", "application/json");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = new Date();

        OrderDto orderCreate = new OrderDto(3,65849,1,formatter.format(date),"placed",true);
        Gson gson = new Gson();
        String json = gson.toJson(orderCreate);
        try(OutputStream os = connection1.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader inPost;
        int responseCode = connection1.getResponseCode();
        System.out.println(responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            inPost = new BufferedReader(
                    new InputStreamReader(connection1.getInputStream()));
        } else {
            inPost = new BufferedReader(
                    new InputStreamReader(connection1.getErrorStream()));
        }

        String inputLinePOST;
        StringBuffer apiResponsePOSTorder = new StringBuffer();

        while ( ( inputLinePOST = inPost.readLine() ) != null){
            apiResponsePOSTorder.append( inputLinePOST );
        }

        System.out.println(apiResponsePOSTorder);

        inPost.close();
        connection1.disconnect();
    }
}
