package com.ymzstudio.emba.repository;

import com.ymzstudio.emba.domain.HibernateTestBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HibernateTestRepository extends JpaRepository<HibernateTestBase, Long> {
}
