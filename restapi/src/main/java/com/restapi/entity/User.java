package com.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Lob
    @Column(nullable = false)
    private String nickname;

    @Lob
    @Column(nullable = false)
    private String mail;

    @Lob
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private int sex;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date birth;
    
    @Lob
    @Column
    private String region;

    @Column(columnDefinition = "TEXT")
    private String pr;

    @Column(name = "del_flg", columnDefinition = "TINYINT", length = 1)
    private boolean delFlg;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    // @OneToMany(mappedBy="user")
    // private Collection<Board> boards;

    // @OneToMany
    // @JoinColumn(name="user_no")
    // private Collection<ProgressTime> progressTimes;

    // @OneToMany
    // @JoinColumn(name="entry_user_no")
    // private Collection<EntryProgress> entryProgress;

    @PrePersist
    protected void onSave() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
