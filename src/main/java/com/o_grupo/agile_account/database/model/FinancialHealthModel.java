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
public class FinancialHealthModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private int percentage_status;

}
