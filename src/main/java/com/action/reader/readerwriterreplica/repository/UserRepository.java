package com.action.reader.readerwriterreplica.repository;

import com.action.reader.readerwriterreplica.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}