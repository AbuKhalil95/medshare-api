package com.medshare.repositories;

import com.medshare.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ItemsRepository extends JpaRepository<Items, Long> {

    @Query(value = "SELECT * FROM items",nativeQuery = true)
    List<Items> findAllItems();

    @Query(value = "SELECT * FROM items WHERE owner_id=?1", nativeQuery = true)
    Long findItemsByUsername(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE items SET available = (CASE available WHEN TRUE THEN FALSE ELSE TRUE END) WHERE id=?1", nativeQuery = true)
    void toggleItemAvailability(Long id);

}
