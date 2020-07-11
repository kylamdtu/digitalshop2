package com.dtucdio3.digitalshop2.service;

import com.dtucdio3.digitalshop2.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> nameContainKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
        };
    }
    public static Specification<Product> descriptionContainKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.like(root.get("description"), "%" + keyword + "%");
        };
    }

    public static Specification<Product> hasCategory(Integer categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.join("category").get("id"), categoryId);
        };
    }
}
