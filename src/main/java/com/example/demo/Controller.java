package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    private final AKafka kafkaProducerService;

    @Autowired
    public Controller(AKafka kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public String sendData() throws IOException {
        kafkaProducerService.send();
        return "Data sent to Kafka!";
    }
    @PostMapping("/size/{Size}")
    public List<String> findSize(@PathVariable String Size) throws IOException {
        return kafkaProducerService.findbySize(Size);

    }
    @PostMapping("/type/{type}")
    public String findtype(@PathVariable String type) throws IOException {
        return kafkaProducerService.findbytype(type);

    }
    @PostMapping("/toppings/{type}")
    public List<String> findtoppings(@PathVariable String type) throws IOException {
        return kafkaProducerService.findbyToppings(type);

    }
}