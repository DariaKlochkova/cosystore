package com.store.cosystore.repos;

import com.store.cosystore.domain.Order;
import com.store.cosystore.domain.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderRepo extends CrudRepository<Order, Integer> {
    Order findById(int id);
    LinkedHashSet<Order> findAllByOrderByDateDesc();
    LinkedHashSet<Order> findByUserIdOrderByDateDesc(int userId);
    LinkedHashSet<Order> findByStatusOrderByDateDesc(Status status);
    Set<Order> findByStatus(Status aNew);

    @Query("select sum(cost) from Order where date = ?1 and status in ?2")
    Integer getDayStatistics(LocalDate date, Set<Status> statuses);
}
