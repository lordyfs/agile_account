package com.o_grupo.agile_account.database.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aaclient")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AAClientModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;

    //com isso na table de AAClient poderá haver na coluna relacionada à finance várias despesas
    /*@OneToMany
    @JoinColumn(name = "id_expenses")//Foreing Key or chave estrangeira
    private ExpensesModel expenses;*/

    //com isso na table de AAClient poderá haver na coluna relacionada à finance vários salários
    /*@OneToMany
    @JoinColumn(name="wage_id")//Foreing Key or chave estrangeira
    private List<WageModel> wage;*/
}