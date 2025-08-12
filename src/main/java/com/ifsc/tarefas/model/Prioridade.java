package com.ifsc.tarefas.model;


// Um enum torna uma variável que só possa ter resultados delimitados. Exemplo: a variável só pode receber a string "BAIXA" ou "ALTA" ou "MÉDIA" e nada mais
public enum Prioridade {

    BAIXA,
    MEDIA,
    ALTA;

    private Prioridade() {

    }
    
}
