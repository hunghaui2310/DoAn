package com.spring.angular.repository.impl;

import com.spring.angular.helper.SearchRequest;
import com.spring.angular.repository.ProductRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Object[]> getProduct() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select p.product_id,p.product_name,p.price,p.num_like,p.discount,f.url" +
                " from product p, file_info f,category c" +
                " where p.product_id = f.product_id");
        Query query = entityManager.createNativeQuery(sqlBuilder.toString());
        return query.getResultList();
    }

    @Override
    public List<Object[]> searchProduct(SearchRequest searchRequest) {
        StringBuilder sqlBuilder = new StringBuilder();
        HashMap hashMap = new HashMap();
        sqlBuilder.append("select p.product_id,p.product_name,p.price,p.num_like,p.discount,f.url" +
                " from product p, file_info f, category c");
        sqlBuilder.append(sqlSearch(searchRequest,hashMap));
        Query query = entityManager.createNativeQuery(sqlBuilder.toString());
        hashMap.forEach((k,v)->{
            query.setParameter(k.toString(),v);
        });;
        return query.getResultList();
    }

    private StringBuilder sqlSearch(SearchRequest searchRequest, HashMap hashMap){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" where 1 = 1");
        stringBuilder.append(" and p.product_id = f.product_id");
        if(searchRequest.getProductName() != null){
            stringBuilder.append(" and p.product_name like '"+ searchRequest.getProductName() +"_%'");
        }
        if(searchRequest.getCategoryId() != null){
            stringBuilder.append(" and c.category_id = p.category_id");
            stringBuilder.append(" and c.category_id = :categoryId");
            hashMap.put("categoryId", searchRequest.getCategoryId());
        }
        if (searchRequest.getPrice() != 0){
            if(searchRequest.getPrice() == 1){
                stringBuilder.append(" and p.price BETWEEN 0 and 10");
            }else if(searchRequest.getPrice() == 2){
                stringBuilder.append( " and p.price BETWEEN 10 and 15");
            }else if(searchRequest.getPrice() == 3){
                stringBuilder.append(" and p.price BETWEEN 15 and 20");
            }else if(searchRequest.getPrice() == 4){
                stringBuilder.append(" and p.price BETWEEN 20 and 1000");
            }else
                stringBuilder.append(" and p.price BETWEEN 0 and 1000");
        }
        return stringBuilder;
    }
}
