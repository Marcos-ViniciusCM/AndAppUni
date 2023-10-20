package com.example.anjosgaar;

public class Cachorro {
    private Integer id;
    private String nome;
    private String descricao;
    private String Sexo;


    public Cachorro() {
    }

    public Cachorro(Integer id, String nome, String descricao, String sexo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        Sexo = sexo;
    }

    public Cachorro(String nome, String sexo,String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        Sexo = sexo;
    }

    @Override
    public String toString() {
        return "Cachorro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", Sexo='" + Sexo + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }
}
