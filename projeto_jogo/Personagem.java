public class Personagem {
    private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;

    public Personagem(String nome, int vida, int fome, int sede, int energia, int sanidade){
        this.nome = nome;
        this.vida = vida;
        this.fome = fome;
        this.sede = sede;
        this.energia = energia;
        this.sanidade = sanidade;
    }

    String getNome(){
        return this.nome;
    }

    int getVida(){
        return this.vida;
    }
    int getFome(){
        return this.fome;
    }
    int getSede(){
        return this.sede;
    }
    int getEnergia(){
        return this.energia;
    }
    int getSanidade(){
        return this.sanidade;
    }

    void setVida(int vida){
        this.vida = vida;
    }
    void setFome(int fome){
        this.fome = fome;
    }
    void setSede(int sede){
        this.sede = sede;
    }
    void setEnergia(int energia){
        this.energia = energia;
    }
    void setSanidade(int sanidade){
        this.sanidade = sanidade;
    }

    //Metodos redução
    public void reduzirVida(int quantidade){
        vida -= quantidade;
        if (vida<0){
            vida = 0;
        }
    }
    public void reduzirFome(int quantidade){
        fome -= quantidade;
        if (fome<0){
            fome = 0;
        }
    }
    public void reduzirSede(int quantidade){
        sede -= quantidade;
        if (sede<0){
            sede = 0;
        }
    }
    public void reduzirEnergia(int quantidade){
        energia -= quantidade;
        if (energia<0){
            energia = 0;
        }
    }
    public void reduzirSanidade(int quantidade){
        sanidade -= quantidade;
        if (sanidade<0){
            sanidade = 0;
        }
    }
    //Metodos aumentar
    public void aumentarVida(int quantidade){
        vida += quantidade;
        if (vida>100){
            vida = 100;
        }
    }
    public void aumentarFome(int quantidade){
        fome += quantidade;
        if (fome>100){
            fome = 100;
        }
    }
    public void aumentarSede(int quantidade){
        sede += quantidade;
        if (sede>100){
            sede = 100;
        }
    }
    public void aumentarEnergia(int quantidade){
        energia += quantidade;
        if (energia>100){
            energia = 100;
        }
    }
    public void aumentarSanidade(int quantidade){
        sanidade += quantidade;
        if (sanidade>100){
            sanidade = 100;
        }
    }


    @Override
    public String toString(){
        return "NOME: " + this.nome + "\nVIDA: " + this.vida + "\nFOME: " +this.fome + "\nSEDE: " + this.sede + "\nENERGIA: " + this.energia + "\nSANIDADE: " + this.sanidade;

    }



}
