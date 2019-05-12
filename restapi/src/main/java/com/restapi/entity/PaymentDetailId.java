package com.restapi.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RDBレコードのマッピング用クラス
 */
@Data
@Embeddable
public class PaymentDetailId implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "board_no")
  private long boardNo;

  private long payer;
}