package com.store.cosystore.repos;

import com.store.cosystore.domain.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class MyValueRepoImpl implements MyValueRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Integer> findProductsByValues(List<Value> values) {
        List<Integer> ids = new ArrayList<>(), tmpIds;
        for(int i = 0; i < values.size(); i++){
            String request = String.format("select productId from Value where propertyId=%d and value='%s'", values.get(i).getPropertyId(), values.get(i).getValue());

            tmpIds = entityManager.createQuery(request, Integer.class).getResultList();

            if(i == 0) ids.addAll(tmpIds);
            else ids.retainAll(tmpIds);
        }
        return ids;
    }
}
