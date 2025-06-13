package com.o_grupo.agile_account.database.finance;

import com.o_grupo.agile_account.database.model.AAClientModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expenses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpensesModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private double value;
    private String description;
    
    @ManyToOne
    @JoinColumn(name= "id_client")
    private AAClientModel client;
    
}