package com.ymzstudio.emba.repository.search;

import com.ymzstudio.emba.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
@Repository
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {
}
