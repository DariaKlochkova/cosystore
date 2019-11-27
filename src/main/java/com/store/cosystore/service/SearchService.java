package com.store.cosystore.service;

import com.store.cosystore.domain.ProductVersion;
import com.store.cosystore.repos.ProductRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductVersionRepo productVersionRepo;

    public Set<ProductVersion> findProducts(String query){
        if (query.matches("\\d{3}[.]\\d{3}[.]\\d{2}"))
            return Collections.singleton(productVersionRepo.findByArticle(query));

        return productRepo.findByNameOrGeneralInfIgnoreCaseStartingWith(query.toLowerCase(), query)
                .stream()
                .flatMap(p -> p.getProductVersions().stream())
                .collect(Collectors.toSet());
    }
}
