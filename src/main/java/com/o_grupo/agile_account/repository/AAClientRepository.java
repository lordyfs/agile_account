package com.o_grupo.agile_account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.o_grupo.agile_account.database.model.AAClientModel;

@Repository
public interface AAClientRepository extends JpaRepository<AAClientModel, Long> {

    // --- Métodos Padrão (herdados de JpaRepository) ---
    // save(), findById(), findAll(), deleteById(), etc.

    // --- Métodos Derivados (Query Methods) ---

    // Busca por nome exato
    Optional<AAClientModel> findByNome(String nome);

    // Busca por nome contendo (case-insensitive)
    List<AAClientModel> findByNomeContainingIgnoreCase(String termo);

    // Busca por CPF
    Optional<AAClientModel> findByCpf(String cpf);

    // Busca por email
    Optional<AAClientModel> findByEmail(String email);

    Optional<AAClientModel> findByEmailAndSenha(String email, String senha);


}