package com.yiranzhaojiu.starterdemo.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("hello/{key}")
    public String getName(@PathVariable("key") String key){

        RBucket<Object> bucket = redissonClient.getBucket(key);
        if(bucket.get()==null){
            bucket.set("1111");
        }

        return bucket.get().toString();
    }
}
