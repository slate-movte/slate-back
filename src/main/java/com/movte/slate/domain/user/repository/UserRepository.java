package com.movte.slate.domain.user.repository;

import com.movte.slate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
