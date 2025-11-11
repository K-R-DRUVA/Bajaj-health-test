package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Component
public class AppRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        // 1️⃣ Generate Webhook + Token
        String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        JSONObject req = new JSONObject();
        req.put("name", "K R DRUVA");
        req.put("regNo", "PES2UG22CS245");
        req.put("email", "pes2ug22cs245@pesu.pes.edu");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(req.toString(), headers);

        String response = restTemplate.postForObject(generateUrl, entity, String.class);
        JSONObject json = new JSONObject(response);

        String webhookUrl = json.getString("webhook");
        String token = json.getString("accessToken");

        System.out.println("Webhook → " + webhookUrl);
        System.out.println("Access Token → " + token);

        // 2️⃣ Your Final SQL Answer
        String finalQuery = 
            "SELECT p.AMOUNT AS SALARY, CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, " +
            "TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, d.DEPARTMENT_NAME " +
            "FROM PAYMENTS p " +
            "JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID " +
            "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
            "WHERE DAY(p.PAYMENT_TIME) <> 1 " +
            "ORDER BY p.AMOUNT DESC LIMIT 1;";

        JSONObject answerBody = new JSONObject();
        answerBody.put("finalQuery", finalQuery);

        HttpHeaders answerHeaders = new HttpHeaders();
        answerHeaders.setContentType(MediaType.APPLICATION_JSON);
        answerHeaders.set("Authorization", token);

        HttpEntity<String> answerEntity = new HttpEntity<>(answerBody.toString(), answerHeaders);

        // 3️⃣ Submit Final Answer
        String finalResponse = restTemplate.postForObject(webhookUrl, answerEntity, String.class);

        System.out.println("✅ Submission Result → " + finalResponse);
    }
}
