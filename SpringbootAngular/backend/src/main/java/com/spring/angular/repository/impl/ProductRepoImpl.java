package com.spring.angular.repository.impl;

import com.spring.angular.repository.ProductRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Object[]> getProduct() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select p.product_name,p.price,p.num_like,p.discount,f.url" +
                " from product p, file_info f" +
                " where p.product_id = f.product_id");
        Query query = entityManager.createNativeQuery(sqlBuilder.toString());
        return query.getResultList();
    }
}
