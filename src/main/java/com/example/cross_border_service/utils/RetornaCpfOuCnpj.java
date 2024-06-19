package com.example.cross_border_service.utils;


public class RetornaCpfOuCnpj {
    public static String validarCpfOuCnpj(String numero) {
        String cleaned = numero.replaceAll("\\D", "");
        if (cleaned.length() == 11 && ValidarCpf.isValidCPF(cleaned)) {
            return "Física";
        } else if (cleaned.length() == 14 && ValidarCnpj.isValidCNPJ(cleaned)) {
            return "Jurídica";
        } else {
            return "Não Informado";
        }
    }
}
