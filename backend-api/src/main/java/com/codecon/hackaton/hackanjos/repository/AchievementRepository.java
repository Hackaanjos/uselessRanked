package com.codecon.hackaton.hackanjos.repository;

import com.codecon.hackaton.hackanjos.model.Achievement;
import com.codecon.hackaton.hackanjos.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUserAndName(User user, String name);
    List<Achievement> findByUser(User user);
}
