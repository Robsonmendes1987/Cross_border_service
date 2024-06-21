package com.example.cross_border_service.service;

import com.example.cross_border_service.model.dto.Transaction.RequestTransactionDTO;
import com.example.cross_border_service.model.dto.Transaction.ResponseTransactionDTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    List<ResponseTransactionDTO> listarTransacoesPorUsuario(Long usuarioId);

    String transferir(RequestTransactionDTO transaction);
}

