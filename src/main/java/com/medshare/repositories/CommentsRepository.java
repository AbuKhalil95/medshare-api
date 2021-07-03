package com.medshare.repositories;

import com.medshare.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query(value = "SELECT * FROM  comments WHERE item_id=?1", nativeQuery = true)
    public List<Comments> findComments(Long itemId);
}
