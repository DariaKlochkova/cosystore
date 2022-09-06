package com.store.cosystore.repos;

import com.store.cosystore.domain.Value;

import java.util.List;

public interface MyValueRepo {
    List<Integer> findProductsByValues(List<Value> values);
}
