package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.SecretValue;
import com.github.alhenk.bidding.monolithic.repositories.SecretValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretValueServiceImpl implements SecretValueService{
    private SecretValueRepository secretValueRepository;

    @Autowired
    public void setSecretValueRepository(SecretValueRepository secretValueRepository) {
        this.secretValueRepository = secretValueRepository;
    }

    @Override
    public Iterable<SecretValue> listAllSecretValues() {
        return secretValueRepository.findAll();
    }

    @Override
    public SecretValue saveSecretValue(SecretValue secretValue) {
        return secretValueRepository.save(secretValue);
    }

    @Override
    public SecretValue getSecretValueById(Integer id) {
        return secretValueRepository.findById(id).orElse(null);
    }
}
