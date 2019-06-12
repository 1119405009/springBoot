package com.felix.redis;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * <p>
 * Redis测试
 * </p>
 */
@Slf4j
public class RedisTest extends SpringBootDemoCacheRedisApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Resource
    private  RedisTemplate<String,User> redisTemplate;
    @Autowired
    ObjectMapper objectMapper;

    /**
     * 测试 Redis 操作
     */
    @Test
    public void get() {
        // 测试线程安全，程序结束查看redis中count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.debug("【k1】= {}", k1);

        // 以下演示整合，具体Redis命令可以参考官方文档
        String key = "felix:user:1";
        redisCacheTemplate.opsForValue().set(key, new User(1L, "user1"));
        // 对应 String（字符串）
        User user = (User) redisCacheTemplate.opsForValue().get(key);
        log.debug("【user】= {}", user);
    }

    @Test
    public  void getRedisList(){
        ListOperations<String, Serializable> listAdd = redisCacheTemplate.opsForList();
        for(long i=0;i<10;i++){
            User user=new User(i, "user"+i);
            listAdd.leftPush("userList",user);
        }
        //查询list中指定范围的内容
        List<?> userList = listAdd.range("userList", 0, -1);
        for (Object object : userList) {
                User u=(User) object;
                System.out.println(u);
        }
        User user = new User(1L, "user1");
        listAdd.rightPush("userList",new User(250L, "250"));

    }

    @Test
    public  void getRedis(){
      /*  SetOperations<String, Serializable> set = redisCacheTemplate.opsForSet();
        set.add("userSet",new User(1L, "user1"),new User(2L,"user2"));*/

        HashOperations<String, Object, Object> hash = redisCacheTemplate.opsForHash();
        hash.put("hashValue","map1",new User(1L, "user1"));
        hash.put("hashValue","map2",new User(1L, "user1"));
        Map<Object,Object> map =  hash.entries("hashValue");
        System.out.println(map);
    }



}
