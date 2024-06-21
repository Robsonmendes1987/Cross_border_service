package com.example.cross_border_service.service;

import com.example.cross_border_service.model.entity.CotacaoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface CotacaoService {
    CotacaoEntity getCotacao(LocalDate date);
}
