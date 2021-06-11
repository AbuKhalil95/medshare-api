package com.medshare.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @ElementCollection
    private List<String> itemPictures;
    @Column(length = 200000)
    private String description;
    private Boolean available;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    private User owner;

    @OneToMany
    private List<Comments> comments;

    public Items(String title, List<String> itemPictures, String description, Boolean available, Date createdAt, User owner) {
        this.title = title;
        this.itemPictures = itemPictures;
        this.description = description;
        this.available = available;
        this.createdAt = createdAt;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getItemPictures() {
        return itemPictures;
    }

    public void setItemPictures(List<String> itemPictures) {
        this.itemPictures = itemPictures;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", itemPictures=" + itemPictures +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", createdAt=" + createdAt +
                ", owner=" + owner +
                ", comments=" + comments +
                '}';
    }
}
