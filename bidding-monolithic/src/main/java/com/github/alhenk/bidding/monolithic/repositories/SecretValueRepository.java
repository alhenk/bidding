package com.github.alhenk.bidding.monolithic.repositories;

import com.github.alhenk.bidding.monolithic.domain.SecretValue;
import org.springframework.data.repository.CrudRepository;

public interface SecretValueRepository extends CrudRepository<SecretValue, Integer> {
}
