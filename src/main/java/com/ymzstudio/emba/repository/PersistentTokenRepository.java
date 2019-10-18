package com.ymzstudio.emba.repository;

import com.ymzstudio.emba.domain.PersistentToken;
import com.ymzstudio.emba.domain.User;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link PersistentToken} entity.
 */
@Repository
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {

    List<PersistentToken> findByUser(User user);

    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);

}
