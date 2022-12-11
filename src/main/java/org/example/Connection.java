package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Connection {
    static final Logger log = LoggerFactory.getLogger(App.class);
    private URL url;
    private HttpURLConnection connection;

    public void init(String s) throws IOException {
        if (connection == null) {
            url = new URL(s);
            connection = (HttpURLConnection) url.openConnection();
            log.info("Connection established");
        } else {
            log.warn("Connection already exists");
        }
    }
        public void setupMethod(Enum method) throws ProtocolException {
            connection.setRequestMethod(method.name());

            if(method.name().equals(Methods.POST.name())){
                connection.setDoOutput(true);
            }
            connection.setRequestProperty("Content-Type", "application/json");
        }
        public void writeBody(String body) throws IOException {
            OutputStream os = connection.getOutputStream();
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
    }
        public int getResponseCode() throws IOException {
        return connection.getResponseCode();
        }
        public StringBuffer processResponse() throws IOException {
            BufferedReader in;
            int responseCode= connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK ) {
                in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuffer apiResponse = new StringBuffer();

            while ((inputLine = in.readLine()) !=null){
                apiResponse.append(inputLine);
            }
            in.close();
            return apiResponse;

    }
         public void disconnect(){
         connection.disconnect();
    }

}
