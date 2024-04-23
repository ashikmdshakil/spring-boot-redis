package com.ashik.redis_pubsub.controller;

import com.ashik.redis_pubsub.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageBrokerController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Topic topic;

    @PostMapping(value = "publishMessage")
    public ResponseEntity<?> publishMessage(@RequestBody Product product) {
        redisTemplate.convertAndSend(topic.getTopic(), product.toString());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
