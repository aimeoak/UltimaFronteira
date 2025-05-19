package Evento;

import Ambiente.Ambiente;
import Personagem.Personagem;

public abstract class Evento {
    private String nome;
    private String descricao;
    private double probabilidadeOcorrencia;
    private int impacto;
    private String condicaoAtivacao;//Ambiente.Floresta...

    public Evento(String nome, String descricao, double probabilidadeOcorrencia, int impacto, String condicaoAtivacao){
        this.nome = nome;
        this.descricao = descricao;
        this.probabilidadeOcorrencia = probabilidadeOcorrencia;
        this.impacto = impacto;
        this.condicaoAtivacao=condicaoAtivacao;
    }
    public String getNome() {
        return nome;
    }
    public String getCondicaoAtivacao() {
        return condicaoAtivacao;
    }
    public double getProbabilidadeOcorrencia() {
        return probabilidadeOcorrencia;
    }

    public int getImpacto() {
        return impacto;
    }

    public void setCondicaoAtivacao(String condicaoAtivacao) {
        this.condicaoAtivacao = condicaoAtivacao;
    }


    public abstract void executar(Personagem jogador, Ambiente local);
    public abstract String getDescricao();
}
