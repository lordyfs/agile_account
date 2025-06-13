package com.o_grupo.agile_account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.o_grupo.agile_account.database.model.AAClientModel;
import com.o_grupo.agile_account.repository.AAClientRepository;

@Service
public class AAClientService {
    @Autowired
    private AAClientRepository aaRepository;

    public AAClientModel login(AAClientModel aaModel) {
        Optional<AAClientModel> client = this.aaRepository.findByEmailAndSenha(aaModel.getEmail(), aaModel.getSenha());
        if(client.isPresent())
            return client.get();
        return new AAClientModel();
    }
}
