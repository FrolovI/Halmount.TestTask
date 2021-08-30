package ru.halmount.test.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTTPService {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.findAndRegisterModules();
    }

    public static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static <T> T get(String url, TypeReference<T> typeReference) {
        try {

            HttpGet request = new HttpGet(url);

            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                    return OBJECT_MAPPER.readValue(result, typeReference);
                }

            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T post(String url, Object object, TypeReference<T> typeReference) {
        try {

            HttpPost request = new HttpPost(url);
            if (object != null) {
                request.setEntity(new StringEntity(OBJECT_MAPPER.writeValueAsString(object),
                        ContentType.APPLICATION_JSON));
            }
            CloseableHttpResponse response = httpClient.execute(request);

            try {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                    if (typeReference != null) {
                        return OBJECT_MAPPER.readValue(result, typeReference);
                    } else {
                        return null;
                    }
                }

            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String put(String url, Object object) {

        try {

            HttpPut request = new HttpPut(url);
            if (object != null) {
                request.setEntity(new StringEntity(OBJECT_MAPPER.writeValueAsString(object),
                        ContentType.APPLICATION_JSON));
            }

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(String url) {
        try {

            HttpDelete request = new HttpDelete(url);

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
