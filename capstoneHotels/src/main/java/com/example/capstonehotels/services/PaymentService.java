package com.example.capstonehotels.services;

import com.example.capstonehotels.dtos.request.PaymentRequest;
import com.example.capstonehotels.dtos.response.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

@Slf4j
@Service
public class PaymentService {

        private final String SECRET_KEY = System.getenv("PAY_STACK_SECRET_KEY");

        public PaymentResponse payment(PaymentRequest paymentRequest) throws IOException {

            OkHttpClient okHttpClient = new OkHttpClient();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name",paymentRequest.getName());
                jsonObject.put("description",paymentRequest.getDescription());
                jsonObject.put( "amount",paymentRequest.getAmount());
            }catch (JSONException ex){
                log.info(ex.getMessage());
            }
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType,jsonObject.toString());
            Request request = new Request.Builder()
                    .url("https://api.paystack.co/page")
                    .post(requestBody)
                    .addHeader("Authorization", "Bearer "+SECRET_KEY)
                    .addHeader("Content-Type", "application/json")
                    .build();
            try(ResponseBody response = okHttpClient.newCall(request).execute().body()) {
                Gson gson = new Gson();
                return gson.fromJson(response.string(),PaymentResponse.class);
            }

        }

    }
