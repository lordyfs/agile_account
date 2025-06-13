package com.o_grupo.agile_account.service;

import org.springframework.stereotype.Service;

@Service
public class WageService {

    private double salario = 0.0;

    public String defWage(double valor) {
        this.salario = valor;
        return "Salário definido como R$" + salario;
    }

    public double getWage() {
        return this.salario;
    }
}
