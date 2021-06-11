package com.medshare.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long itemId;
    private String commenterName;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;
    private String body;

    public Comments() {
    }

    public Comments(Long userId,String commenterName ,Date createdAt, String body, Long itemId) {
        this.userId = userId;
        this.itemId = itemId;
        this.commenterName =  commenterName;
        this.createdAt = createdAt;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", commenterName='" + commenterName + '\'' +
                ", createdAt=" + createdAt +
                ", body='" + body + '\'' +
                '}';
    }
}
