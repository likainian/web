package com.flyer.chat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Integer deleteUserByUdid(String udid);
    User getUserByUdid(String udid);
}
