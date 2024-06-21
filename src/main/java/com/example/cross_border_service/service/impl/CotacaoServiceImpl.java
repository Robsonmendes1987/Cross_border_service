package com.example.cross_border_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.cross_border_service.model.entity.CotacaoEntity;
import com.example.cross_border_service.model.repository.CotacaoRepository;
import com.example.cross_border_service.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CotacaoServiceImpl implements CotacaoService {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CotacaoServiceImpl() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public CotacaoEntity getCotacao(LocalDate date) {
        LocalDate consultaDate = ajustarParaUltimoDiaUtil(date);

        return buscarCotacaoRecursivamente(consultaDate);
    }

    private CotacaoEntity buscarCotacaoRecursivamente(LocalDate date) {
        Optional<CotacaoEntity> cotacaoOptional = cotacaoRepository.findByDataCotacao(date);
        if (cotacaoOptional.isPresent()) {
            return cotacaoOptional.get();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        String url = String.format("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='%s'&$top=100&$format=json&$select=cotacaoCompra,cotacaoVenda",
                date.format(formatter));

        String response = restTemplate.getForObject(url, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            if (jsonNode.has("value") && jsonNode.get("value").isArray() && jsonNode.get("value").size() > 0) {
                JsonNode cotacaoData = jsonNode.get("value").get(0);

                Double cotacaoCompra = cotacaoData.get("cotacaoCompra").asDouble();
                Double cotacaoVenda = cotacaoData.get("cotacaoVenda").asDouble();

                CotacaoEntity cotacao = new CotacaoEntity();
                cotacao.setDataCotacao(date);
                cotacao.setCotacaoCompra(cotacaoCompra);
                cotacao.setCotacaoVenda(cotacaoVenda);
                cotacaoRepository.save(cotacao);

                return cotacao;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDate previousDate = ajustarParaUltimoDiaUtil(date.minusDays(1));
        return buscarCotacaoRecursivamente(previousDate);
    }

    LocalDate ajustarParaUltimoDiaUtil(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return date.minusDays(1);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            return date.minusDays(2);
        }
        return date;
    }
}