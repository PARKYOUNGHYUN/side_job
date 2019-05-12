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
public class FavoriteTagId implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "user_no")
  private long userNo;

  @Column(name = "hashtag_no")
  private long hashtagNo;
}