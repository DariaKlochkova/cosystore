package com.store.cosystore.service;

import com.store.cosystore.domain.OrderPosition;
import com.store.cosystore.domain.Status;
import com.store.cosystore.repos.OrderPositionRepo;
import com.store.cosystore.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class StatisticsService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderPositionRepo orderPositionRepo;

    public String getSumStatistics(){
        Set<Status> delivered = Collections.singleton(Status.DELIVERED),
        canceled = new HashSet<>(Arrays.asList(Status.DENIED, Status.CANCELED)),
        completing = new HashSet<>(Arrays.asList(Status.NEW, Status.COMPLETING, Status.TRANSIT));

        int[][] table = new int[30][4];
        LocalDate date = LocalDate.now().minusDays(30);
        for (int i = 0; i < 30; i++){
            table[i][0] = date.getDayOfMonth();
            table[i][1] = orderRepo.getDayStatistics(date, delivered) == null ? 0 : orderRepo.getDayStatistics(date, delivered);
            table[i][2] = orderRepo.getDayStatistics(date, canceled) == null ? 0 : orderRepo.getDayStatistics(date, canceled);
            table[i][3] = orderRepo.getDayStatistics(date, completing) == null ? 0 : orderRepo.getDayStatistics(date, completing);
            date = date.plusDays(1);
        }
        return statisticsToString(table);
    }

    public String getCategoryStatistics() {
        Iterable<OrderPosition> orderPositions = orderPositionRepo.findAll();
        Map<String, Integer> map = new HashMap<>();
        for (OrderPosition op : orderPositions){
            String category = op.getProductVersion().getProduct().getCategory().getName();
            Integer count = map.get(category);
            if(count == null)
                map.put(category, 0);
            else
                map.put(category, count + 1);
        }
        StringBuilder res = new StringBuilder();
        for(Map.Entry entry : map.entrySet())
            res.append("['" + entry.getKey() + "'," + entry.getValue() + "],");

        return res.toString();
    }

    public String statisticsToString(int[][] table){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 30; i++){
            res.append("[");
            for (int j = 0; j < 4; j++){
                res.append(table[i][j]);
                res.append(",");
            }
            res.append("],");
        }
        return res.toString();
    }
}
