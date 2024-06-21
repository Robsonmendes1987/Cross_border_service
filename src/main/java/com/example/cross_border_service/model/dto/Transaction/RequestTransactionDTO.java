package com.example.cross_border_service.model.dto.Transaction;

import com.example.cross_border_service.model.TipoDeMoeda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransactionDTO {

    private Long usuario;
    private Long destinatario;
    private TipoDeMoeda moeda;
    private Double valor;

}
