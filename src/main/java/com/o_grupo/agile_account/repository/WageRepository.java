package com.o_grupo.agile_account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.o_grupo.agile_account.database.finance.WageModel;

@Repository
public interface WageRepository extends JpaRepository<WageModel, Long> {

    // --- Métodos Padrão (herdados de JpaRepository) ---
    // save(), findById(), findAll(), deleteById(), etc.

    // --- Métodos Derivados (Query Methods) ---
    
    // Busca por valor exato
    List<WageModel> findByValue(double value);
    
    // Busca por descrição (case-insensitive e contendo o termo)
    List<WageModel> findByDescriptionContainingIgnoreCase(String term);
    
    // Busca por valor maior que um mínimo
    List<WageModel> findByValueGreaterThan(double minValue);
    
    // Busca por valor entre um intervalo
    List<WageModel> findByValueBetween(double minValue, double maxValue);

    // --- Consultas Personalizadas ---

    // Busca salários associados a um cliente específico (JPQL com JOIN)
    @Query("SELECT w FROM WageModel w JOIN w.client c WHERE c.id = :clientId")
    List<WageModel> findByClientId(@Param("clientId") Long clientId);

    // Soma todos os salários (consulta nativa)
    @Query(value = "SELECT SUM(value) FROM wage", nativeQuery = true)
    Double sumAllWages();

    // Média dos valores de salário (consulta nativa)
    @Query(value = "SELECT AVG(value) FROM wage", nativeQuery = true)
    Double averageWageValue();

    // Busca o maior salário (consulta nativa)
    @Query(value = "SELECT MAX(value) FROM wage", nativeQuery = true)
    Optional<Double> findMaxWage();

    // Busca o menor salário (consulta nativa)
    @Query(value = "SELECT MIN(value) FROM wage", nativeQuery = true)
    Optional<Double> findMinWage();
}