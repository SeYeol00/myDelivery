package com.sparta.mydelivery.repository;

import com.sparta.mydelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    @Query("select this from User this where this.username=:seyeal")
    List<User> getUsers(@Param("seyeal") String name);


}
