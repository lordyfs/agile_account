package com.o_grupo.agile_account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExpensesService {

    private List<Double> expenses = new ArrayList<>();

    public String addExpenses(Double valor) {
        expenses.add(valor);
        double total = expenses.stream().mapToDouble(Double::doubleValue).sum();
        return "Despesa de R$" + valor + " adicionada com sucesso. Total atual: R$" + total;
    }

    public List<Double> listarexpenses() {
        return expenses;
    }

    public double obterTotalexpenses() {
        return expenses.stream().mapToDouble(Double::doubleValue).sum();
    }

    public String expenses(String string) {
        throw new UnsupportedOperationException("Unimplemented method 'expenses'");
    }

    public double getTotalExpanses() {
        return expenses.stream().mapToDouble(Double::doubleValue).sum();
    }
}
