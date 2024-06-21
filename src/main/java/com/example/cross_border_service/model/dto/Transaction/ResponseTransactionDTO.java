package com.example.cross_border_service.model.dto.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTransactionDTO {

    private Long transactionId;
    private String emissor;
    private String receptor;
    private LocalDateTime dataTransaction;
    private Double saldoAnterior;
    private Double credito;
    private Double debito;
    private double saldoAtual;

}