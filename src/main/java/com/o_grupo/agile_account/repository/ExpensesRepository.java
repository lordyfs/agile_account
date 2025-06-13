package com.o_grupo.agile_account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.o_grupo.agile_account.database.finance.ExpensesModel;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesModel, Long> {

    // Métodos herdados automaticamente:
    // save(), findById(), findAll(), deleteById(), etc.

    // Busca por valor exato
    List<ExpensesModel> findByValue(double value);

    // Busca por descrição (case-insensitive e contendo o termo)
    List<ExpensesModel> findByDescriptionContainingIgnoreCase(String term);

    // Busca por valor maior que
    List<ExpensesModel> findByValueGreaterThan(double minValue);

    // Busca por valor entre
    List<ExpensesModel> findByValueBetween(double minValue, double maxValue);

    // Consulta personalizada com JPQL para buscar despesas de um cliente específico
    @Query("SELECT e FROM ExpensesModel e JOIN e.client c WHERE c.id = :clientId")
    List<ExpensesModel> findByClientId(@Param("clientId") Long clientId);

    // Consulta nativa para somar todos os valores das despesas
    @Query(value = "SELECT SUM(value) FROM expenses", nativeQuery = true)
    Double sumAllExpenses();

    // Consulta nativa para média de valores
    @Query(value = "SELECT AVG(value) FROM expenses", nativeQuery = true)
    Double averageExpenseValue();
}