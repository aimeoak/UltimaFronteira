package Personagem;

import Item.Inventario;

public class Personagem {
    private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private boolean envenenado; //veneno, fratura, infeccao...
    private int tempoEnvenenamento;
    private Inventario inventario;

    public Personagem(String nome, int vida, int fome, int sede, int energia, int sanidade){
        this.nome = nome;
        this.vida = vida;
        this.fome = fome;
        this.sede = sede;
        this.energia = energia;
        this.sanidade = sanidade;
        this.envenenado = false;
        this.tempoEnvenenamento =0;
        this.inventario = new Inventario(10);
    }

    public String getNome(){
        return this.nome;
    }

    public int getVida(){
        return this.vida;
    }
    public int getFome(){
        return this.fome;
    }
    public int getSede(){
        return this.sede;
    }
    public int getEnergia(){
        return this.energia;
    }
    public int getSanidade(){
        return this.sanidade;
    }
    public int getTempoEnvenenamento(){return this.tempoEnvenenamento;}
    public Inventario getInventario(){return inventario;}


    public void setVida(int vida){
        this.vida = vida;
    }
    public void setFome(int fome){
        this.fome = fome;
    }
    public void setSede(int sede){
        this.sede = sede;
    }
    public void setEnergia(int energia){
        this.energia = energia;
    }
    public void setSanidade(int sanidade){
        this.sanidade = sanidade;
    }
    public void setEnvenenado(boolean envenenado){this.envenenado = envenenado;}
    public void setTempoInfluenciaNegativa(){this.tempoEnvenenamento = tempoEnvenenamento;}
    public void setInventario(){this.inventario=inventario;}

    //Metodos redução
    public void reduzirVida(int quantidade){
        if (quantidade > 0 && vida > 0) {
            vida -= quantidade;
            if (vida < 0) {
                vida = 0;
            }
        }
    }
    public void reduzirFome(int quantidade){
        if (quantidade > 0 && fome > 0) {
            fome -= quantidade;
            if (fome < 0) {
                fome = 0;
            }
        }
    }
    public void reduzirSede(int quantidade){
        if (quantidade > 0 && sede > 0) {
            sede -= quantidade;
            if (sede < 0) {
                sede = 0;
            }
        }
    }
    public void reduzirEnergia(int quantidade){
        if (quantidade > 0 && energia > 0) {
            energia -= quantidade;
            if (energia < 0) {
                energia = 0;
            }
        }
    }
    public void reduzirSanidade(int quantidade){
        if (quantidade > 0 && sanidade > 0) {
            sanidade -= quantidade;
            if (sanidade < 0) {
                sanidade = 0;
            }
        }
    }
    //Metodos aumentar
    public void aumentarVida(int quantidade){
        if (quantidade > 0 && vida < 100) {
            vida += quantidade;
            if (vida > 100) {
                vida = 100;
            }
        }
    }
    public void aumentarFome(int quantidade){
        if (quantidade > 0 && fome < 100) {
            fome += quantidade;
            if (fome > 100) {
                fome = 100;
            }
        }
    }
    public void aumentarSede(int quantidade){
        if (quantidade > 0 && sede < 100) {
            sede += quantidade;
            if (sede > 100) {
                sede = 100;
            }
        }
    }
    public void aumentarEnergia(int quantidade){
        if (quantidade > 0 && energia < 100) {
            energia += quantidade;
            if (energia > 100) {
                energia = 100;
            }
        }
    }
    public void aumentarSanidade(int quantidade){
        if (quantidade > 0 && sanidade < 100) {
            sanidade += quantidade;
            if (sanidade > 100) {
                sanidade = 100;
            }
        }
    }
    //Métodos de veneno
    public void envenenar() {
        this.envenenado = true;
        this.tempoEnvenenamento = 5;  // Define que o veneno vai durar 5 turnos (ajuste conforme necessário)
    }

    public void curarEnvenenamento() {
        this.envenenado = false;
        this.tempoEnvenenamento = 0;
    }

    // Método para aplicar o efeito do veneno a cada turno
    public void aplicarEfeitoVeneno() {
        if (envenenado && tempoEnvenenamento > 0) {
            reduzirVida(2);  // O jogador perde 2 de vida a cada turno (ajuste conforme necessário)
            tempoEnvenenamento--;
        } else {
            envenenado = false;  // Se o veneno acabou, o jogador já não está mais envenenado
        }
    }

    @Override
    public String toString(){
        return "NOME: " + this.nome + "\nVIDA: " + this.vida + "\nFOME: " +this.fome + "\nSEDE: " + this.sede + "\nENERGIA: " + this.energia + "\nSANIDADE: " + this.sanidade;

    }



}
