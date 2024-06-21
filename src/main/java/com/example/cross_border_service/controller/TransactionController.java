package com.example.cross_border_service.controller;

import com.example.cross_border_service.model.dto.Transaction.RequestTransactionDTO;
import com.example.cross_border_service.model.dto.Transaction.ResponseTransactionDTO;
import com.example.cross_border_service.service.CotacaoService;
import com.example.cross_border_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CotacaoService cotacaoService;

    @PostMapping("/transferir")
    public String transferir(@RequestBody RequestTransactionDTO transaction) {
        return transactionService.transferir(transaction);
    }

    @GetMapping("/listar/{usuarioId}")
    public ResponseEntity<List<ResponseTransactionDTO>> listarTransacoesPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok().body(transactionService.listarTransacoesPorUsuario(usuarioId));
    }
}
