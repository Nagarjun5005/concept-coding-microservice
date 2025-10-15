package com.order.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FeignCustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String message=extractBody(response);

        switch (response.status()){
            case 400:
                return new ProductServiceException("Bad Request from product service "+message,400);
            case 404:
                return new ProductServiceException("Product not found in product service ",400);
            case 500:
                return new ProductServiceException("Internal server error in product service ",500);
            default:
                return new ProductServiceException("Unexpected error: "+message,response.status());
        }
    }


    private String extractBody(Response response) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().asInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return "No response body";
        }
    }
}
