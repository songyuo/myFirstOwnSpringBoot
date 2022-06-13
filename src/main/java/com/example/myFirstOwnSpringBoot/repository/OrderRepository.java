package com.example.myFirstOwnSpringBoot.repository;

import com.example.myFirstOwnSpringBoot.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByDeliveryZip(String deliveryZip);

    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String delivery, Date startDate, Date endDate);

//    @Query(" Order by where deliveryCity in 'Seattle'")
//    List<Order> readOrdersByDeliveryCityInSeattle();
}
