package com.example.cross_border_service.model.repository;


import com.example.cross_border_service.model.entity.CotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CotacaoRepository extends JpaRepository<CotacaoEntity, Long> {

    Optional<CotacaoEntity> findByDataCotacao(LocalDate dataCotacao);

}
