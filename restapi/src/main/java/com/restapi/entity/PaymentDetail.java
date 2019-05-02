package com.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RDBレコードのマッピング用クラス
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_details")
public class PaymentDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PaymentDetailId pdId;

    @Column(name = "billing_amount", nullable = false)
    private Long billingAmount;
}
