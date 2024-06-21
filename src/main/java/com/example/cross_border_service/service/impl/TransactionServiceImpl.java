package com.example.cross_border_service.service.impl;

import com.example.cross_border_service.exception.NotFound;
import com.example.cross_border_service.model.dto.Transaction.ResponseTransactionDTO;
import com.example.cross_border_service.model.dto.Transaction.RequestTransactionDTO;
import com.example.cross_border_service.model.entity.CotacaoEntity;
import com.example.cross_border_service.model.entity.TransactionEntity;
import com.example.cross_border_service.model.entity.UserEntity;
import com.example.cross_border_service.model.repository.TransactionRepository;
import com.example.cross_border_service.model.repository.UserRepository;
import com.example.cross_border_service.service.CotacaoService;
import com.example.cross_border_service.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transacaoRepository;

    @Autowired
    private CotacaoService cotacaoService;

    @Transactional
    public String transferir(RequestTransactionDTO transaction) {

        CotacaoEntity cotacao = cotacaoService.getCotacao(LocalDate.now());

        UserEntity usuario = userRepository.findById(transaction.getUsuario()).orElseThrow(() -> new NotFound("Usuário não encontrado"));
        UserEntity destinatario = userRepository.findById(transaction.getDestinatario()).orElseThrow(() -> new NotFound("Destinatário não encontrado"));

        double valorConvertido;

        if (transaction.getMoeda().getCodigo().equals("USD")) {
            valorConvertido = transaction.getValor() * cotacao.getCotacaoCompra();
        } else {
            valorConvertido = transaction.getValor();
        }

        if (usuario.getSaldoReal() < valorConvertido) {
            return "Saldo insuficiente, seu saldo altual é: R$" + usuario.getSaldoReal();
        }


        TransactionEntity transacao = new TransactionEntity();
        transacao.setUsuario(usuario);
        transacao.setDestinatario(destinatario);
        transacao.setValor(valorConvertido);
        transacao.setData(LocalDateTime.now());
        transacao.setSaldoAnterior(usuario.getSaldoReal());
        transacaoRepository.save(transacao);

        usuario.setSaldoReal(usuario.getSaldoReal() - valorConvertido);
        destinatario.setSaldoReal(destinatario.getSaldoReal() + valorConvertido);
        usuario.setSaldoDolar(usuario.getSaldoReal() / cotacao.getCotacaoCompra());
        destinatario.setSaldoDolar(destinatario.getSaldoReal() / cotacao.getCotacaoCompra());

        userRepository.save(usuario);
        userRepository.save(destinatario);

        return "Transferência realizada com sucesso";
    }

    public List<ResponseTransactionDTO> listarTransacoesPorUsuario(Long usuarioId) {
        List<TransactionEntity> lista = transacaoRepository.findByUsuarioIdOrDestinatarioIdOrderByDataAsc(usuarioId, usuarioId);

        List<ResponseTransactionDTO> result = new ArrayList<>();

        UserEntity usuario = userRepository.findById(usuarioId).orElseThrow(() -> new NotFound("Usuário não encontrado"));
        double saldoAtual = usuario.getSaldoReal();

        for (TransactionEntity x : lista) {
            ResponseTransactionDTO aux = new ResponseTransactionDTO();
            aux.setTransactionId(x.getId());
            aux.setEmissor(x.getUsuario().getNome());
            aux.setReceptor(x.getDestinatario().getNome());
            aux.setDataTransaction(x.getData());

            aux.setSaldoAnterior(saldoAtual);

            if (x.getUsuario().getId().equals(usuarioId)) {
                aux.setDebito(x.getValor());
                aux.setCredito(0.0);
                saldoAtual -= x.getValor();
            } else if (x.getDestinatario().getId().equals(usuarioId)) {
                aux.setDebito(0.0);
                aux.setCredito(x.getValor());
                saldoAtual += x.getValor();
            }

            aux.setSaldoAtual(saldoAtual);
            result.add(aux);
        }

        return result;
    }


}