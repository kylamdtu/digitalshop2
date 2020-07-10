package com.dtucdio3.digitalshop2.service;

import com.dtucdio3.digitalshop2.entity.PromotionDetail;
import com.dtucdio3.digitalshop2.repository.PromotionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionDetailService {
    PromotionDetailRepository promotionDetailRepository;

    @Autowired
    public void setPromotionDetailRepository(PromotionDetailRepository promotionDetailRepository) {
        this.promotionDetailRepository = promotionDetailRepository;
    }

    public List<PromotionDetail> findAll() {
        return promotionDetailRepository.findAll();
    }

    public PromotionDetail findById(Integer id) {
        return promotionDetailRepository.findById(id).orElse(null);
    }

    public void save(PromotionDetail promotionDetail) {
        promotionDetailRepository.save(promotionDetail);
    }

    public void deleteById(int id) {
        promotionDetailRepository.deleteById(id);
    }
}
