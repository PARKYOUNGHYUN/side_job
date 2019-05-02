package com.restapi.repository;

import com.restapi.entity.PaymentDetail;
import com.restapi.entity.PaymentDetailId;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, PaymentDetailId> {
}