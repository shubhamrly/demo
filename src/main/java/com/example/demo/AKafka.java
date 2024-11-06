package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AKafka {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final PizzaRepo pizzarepo;
    public AKafka(@Autowired KafkaTemplate<String, String> kafkaTemplate, @Autowired ObjectMapper objectMapper, PizzaRepo pizzarepo) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.pizzarepo = pizzarepo;
    }
    public void send() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/sample1.json");
        List<Pizza> modelList = objectMapper.readValue(inputStream, new TypeReference<List<Pizza>>() {});

        // Send each object to Kafka
        for (Pizza data : modelList) {
            String message = objectMapper.writeValueAsString(data); // Convert to JSON string
            kafkaTemplate.send("my-topic", message); // Send to Kafka
        }

        System.out.println("Data sent to Kafka successfully!");
    }


    @KafkaListener(topics="my-topic",groupId = "my-consumer-group")
    public void consumer(String msg) throws JsonProcessingException {
        Pizza pizza = new ObjectMapper().readValue(msg, Pizza.class);
        pizzarepo.save(pizza);
        System.out.println(pizza.toString());
    }

    public String  findbytype(String a)
    {
        return pizzarepo.findByType(a).toString();
    }

    public List<String>  findbySize( String b)
    {
        ArrayList<String>s = (ArrayList<String>) pizzarepo.findBySize(b);
        return pizzarepo.findBySize(b);
    }

    public List<String>  findbyToppings(String c)
    {
        return pizzarepo.findByToppings(c);
    }
}
