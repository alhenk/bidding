package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.SecretValue;

public interface SecretValueService {
    Iterable<SecretValue> listAllSecretValues();
    SecretValue saveSecretValue(SecretValue secretValue);
    SecretValue getSecretValueById(Integer id);
}
