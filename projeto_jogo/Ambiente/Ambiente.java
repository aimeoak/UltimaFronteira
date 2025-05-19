package Ambiente;


import Evento.Evento;
import Personagem.Personagem;



import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;

public abstract class Ambiente {
  private String nome; //BIOMA
  private String descricao;
  private int dificuldadeExplo; 
  private String []  recursos; 
  private double probEventos; 
  private String condClima;

  private List<Evento> eventos = new ArrayList<>(); //criar uma lista com os eventos

  public Ambiente(String nome, String descricao, int dificuldadeExplo, String[] recursos, double probEventos, String condClima){
    this.nome = nome; 
    this.descricao = descricao; 
    this.dificuldadeExplo = dificuldadeExplo;
    this.recursos = recursos;
    this.probEventos = probEventos;
    this.condClima = condClima;
  }
  public String getNome(){
    return nome;
  }

  public String getDescricao(){
    return descricao;
  }

  public int getDificuldadeExplo(){
    return dificuldadeExplo;
  }
  public String[] getRecursos(){
    return recursos;
  }
  public double getProbEventos(){
    return probEventos;
  }

  public String getCondClima(){
    return condClima;
  }

  public void adicionarEvento(Evento evento){
    this.eventos.add(evento);
  }

  public List<Evento> getEventos(){
    return Collections.unmodifiableList(eventos);
  }

  public abstract void explorar (Personagem jogador);

  @Override
  public String toString(){
    return "NOME: " + this.nome + "\nDESCRIÇÃO: " + this.descricao + "\nDIFICULDADE: " +this.dificuldadeExplo + "\nRECURSOS: " + Arrays.toString(this.recursos) + "\nPROBABILIDADE DE EVENTOS: " + this.probEventos + "\nCONDIÇÃO DO CLIMA: " + this.condClima;
  }
  
  public abstract void gerarEvento();
  
  public void modificarClima(){
    String [] climas = {"Ensolarado", "Nublado", "Chuvoso"};
    Random rand = new Random(); 
    condClima = climas[rand.nextInt(climas.length)];
    System.out.println("O clima agora é " + condClima);
    }
}
