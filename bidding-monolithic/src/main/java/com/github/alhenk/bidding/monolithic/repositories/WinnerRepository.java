package com.github.alhenk.bidding.monolithic.repositories;

import com.github.alhenk.bidding.monolithic.domain.Winner;
import org.springframework.data.repository.CrudRepository;

public interface WinnerRepository extends CrudRepository<Winner, Integer> {
}
