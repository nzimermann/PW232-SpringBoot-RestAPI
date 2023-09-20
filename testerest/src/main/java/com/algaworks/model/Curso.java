package com.algaworks.model;
 
public class Curso {
 
  private Integer id;
  private String nome;
  private String duracao;
   
  public Curso(Integer id, String nome, String duracao) {
    this.id = id;
    this.nome = nome;
    this.duracao = duracao;
  }
   
  public Integer getId () {
    return this.id;
  }

  public String getNome () {
    return this.nome;
  }

  public String getDuracao () {    
    return this.duracao;
  }

  private void setId (Integer id) {
    this.id = id;
  }

  private void setNome (String nome) {
    this.nome = nome;
  }

  private void setId (String nome) {
    this.nome = nome;
  }
}