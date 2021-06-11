package com.medshare.repositories;

import com.medshare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  @Query(value = "SELECT * FROM user where username=?1", nativeQuery = true)
  User findByUsername(String username);
}
