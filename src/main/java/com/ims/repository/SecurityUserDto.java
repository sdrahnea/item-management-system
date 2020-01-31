package com.ims.repository;

import com.ims.model.security.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sdrahnea
 */
@Repository
public interface SecurityUserDto extends JpaRepository<SecurityUser,Long> {

    Optional<SecurityUser> findByUsername(String username);

}
