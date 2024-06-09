package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Winner;

public interface WinnerService {
    Iterable<Winner> listAllWinners();
    Winner saveWinner(Winner winner);
    Winner getWinnerById(Integer id);
}
