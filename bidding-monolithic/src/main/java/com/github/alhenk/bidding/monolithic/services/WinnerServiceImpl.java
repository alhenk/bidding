package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Winner;
import com.github.alhenk.bidding.monolithic.repositories.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinnerServiceImpl implements  WinnerService{
    private WinnerRepository winnerRepository;

    @Autowired
    public void setWinnerRepository(WinnerRepository winnerRepository) {
        this.winnerRepository = winnerRepository;
    }

    @Override
    public Iterable<Winner> listAllWinners() {
        return winnerRepository.findAll();
    }

    @Override
    public Winner saveWinner(Winner winner) {
        return winnerRepository.save(winner);
    }

    @Override
    public Winner getWinnerById(Integer id) {
        return winnerRepository.findById(id).orElse(null);
    }
}
