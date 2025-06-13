package com.o_grupo.agile_account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidualBalanceService {
    @Autowired
    private WageService wageService;

    @Autowired
    private ExpensesService expansesService;

        public String analisarSaldo() {
        double wage = wageService.getWage();
        double totalExpanses = expansesService.getTotalExpanses();
        double saldoRestante = wage - totalExpanses;

        StringBuilder resultado = new StringBuilder();

        double percentualGasto = (totalExpanses / wage) * 100;

        // Verificação de alerta
        if (percentualGasto > 50) {
            resultado.append("⚠️ MUITO CUIDADO, VOCÊ ESTÁ GASTANDO MAIS DO QUE O RECOMENDADO!\n")
                    .append("BUSQUE REDUZIR O VALOR DAS SUAS expanses PARA SE ENQUADRAR A 50% DO SEU SALÁRIO!\n")
                    .append("CASO NÃO SEJA POSSÍVEL, COMPENSE ESSE EXCEDENTE COM O VALOR QUE SERIA DIRECIONADO AOS LUXOS E LAZERES.\n\n");
        }

        if (percentualGasto <= 50) {
            resultado.append("Valor restante após expanses: R$").append(String.format("%.2f", saldoRestante)).append("\n");

            if (wage > 3000) {
                double investimento = saldoRestante * 0.4;
                double emergencias = saldoRestante * 0.4;
                double lazer = saldoRestante * 0.2;

                resultado.append("Deste valor sobrando de R$")
                        .append(String.format("%.2f", saldoRestante))
                        .append(", a recomendação é que você:\n")
                        .append("- Invista 40%, ou seja, R$").append(String.format("%.2f", investimento)).append("\n")
                        .append("- Guarde o mesmo valor para gastos emergenciais: R$").append(String.format("%.2f", emergencias)).append("\n")
                        .append("- E com o restante de R$").append(String.format("%.2f", lazer))
                        .append(" você pode integrar às duas operações anteriores ou usar com luxos e lazeres.\n");
            } else {
                double investimento = saldoRestante * 0.5;
                double emergencias = saldoRestante * 0.3;
                double lazer = saldoRestante * 0.2;

                resultado.append("Deste valor sobrando de R$")
                        .append(String.format("%.2f", saldoRestante))
                        .append(", a recomendação é que você:\n")
                        .append("- Invista 50%, ou seja, R$").append(String.format("%.2f", investimento)).append("\n")
                        .append("- Guarde 30% para gastos emergenciais: R$").append(String.format("%.2f", emergencias)).append("\n")
                        .append("- E com o restante de R$").append(String.format("%.2f", lazer))
                        .append(" você pode integrar às duas operações anteriores ou usar com luxos e lazeres.\n");
            }
        }

        return resultado.toString();
    }
}
