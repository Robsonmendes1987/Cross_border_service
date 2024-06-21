package com.example.cross_border_service.model;

public enum TipoDeMoeda {

    DOLAR("USD", "Dólar"),
    REAL("BRL", "Real");

    private final String codigo;
    private final String descricao;

    TipoDeMoeda(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
