package com.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * RDBレコードのマッピング用クラス
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Lob
    @Column(nullable=false)
    private String nickname;

    @Lob
    @Column(nullable=false)
    private String mail;

    @Lob
    @Column(nullable=false)
    private String password;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private int sex;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birth;
    
    @Lob
    @Column
    private String region;

    @Column(columnDefinition = "TEXT")
    private String pr;

    @Column(name = "del_flg", nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean delFlg;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @PrePersist
    protected void onSave() {
        createdAt = new Date();
        updatedAt = new Date();
        System.out.println("createdAt1 = " + createdAt);
        System.out.println("updatedAt2 = " + updatedAt);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
        System.out.println("updatedAt2 = " + updatedAt);
    }
}
