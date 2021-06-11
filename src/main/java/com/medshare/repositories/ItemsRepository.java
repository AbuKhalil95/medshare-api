package com.medshare.repositories;

import com.medshare.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ItemsRepository extends JpaRepository<Items, Long> {

    @Query(value = "select * from items",nativeQuery = true)
    List<Items> findAllItems();

    @Query(value = "SELECT * FROM items where owner=?1", nativeQuery = true)
    Long findItemsByUsername(Long id);

    @Modifying
    @Query(value = "update items i SET i.available = CASE i.available WHEN TRUE THEN FALSE WHEN FALSE THEN TRUE ELSE i.available END WHERE i.id=?1", nativeQuery = true)
    void toggleItemAvailability(Long id);

}
