package com.example.myFirstOwnSpringBoot.service.Implement;

import com.example.myFirstOwnSpringBoot.entity.Order;
import com.example.myFirstOwnSpringBoot.entity.Taco;
import com.example.myFirstOwnSpringBoot.service.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class JdbcOrderRepository implements OrderRepository {

    private final SimpleJdbcInsert orderInsert;
    private final SimpleJdbcInsert orderTacoInsert;
    private final ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc){
        this.orderInsert = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
        this.orderTacoInsert = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {

        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.getTacos().forEach(taco -> saveTacoToOrder(taco, orderId));
        return order;
    }

    private long saveOrderDetails(Order order){
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        long orderId = orderInsert.executeAndReturnKey(values).longValue();
        log.info(orderInsert.getInsertString());
        return orderId;
    }

    private void saveTacoToOrder(Taco taco, long orderId){
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInsert.execute(values);
    }
}
