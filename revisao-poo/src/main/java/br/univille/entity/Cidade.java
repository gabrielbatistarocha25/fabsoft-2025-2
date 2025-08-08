package br.univille.entity;

public class Cidade {

    private String nome;

    //construtor, serve para inicializar variáveis e também obrigar a passar um valor
    //parametro é uma variável local que "vive" apenas enquanto o construtor existir
    public Cidade(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }
}
