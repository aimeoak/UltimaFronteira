public class Armas extends Item{
    private String tipo; // corpo a corpo ou à distância
    private int dano;
    private int alcance; //distancia efetiva da arma

  public Armas(String nome, double peso, int durabilidade, String tipo, int dano, int alcance){
    super(nome, peso, durabilidade);
    this.tipo = tipo;
    this.dano = dano;
    this.alcance = alcance;
  }


  public String getTipo(){
    return tipo;
  }
  public String setTipo(String tipo){
    return this.tipo = tipo;
  }

  public int getDano(){
    return dano;
  }
  public int setDano(int dano){
    return this.dano = dano;
  }

  public int getAlcance(){
    return alcance;
  }
  public int setAlcance(int alcance){
    return this.alcance = alcance;
  }

  @Override
  public void usar(){
    //NADA
  }

  public void atacar(Personagem personagem){ //personagem é o alvo
    personagem.reduzirVida(dano);
    System.out.println("Você atacou " + personagem.getNome() + " com " + getNome() + " causando " + dano + " de dano.");
    setDurabilidade(getDurabilidade() - 1);
  }

  @Override
  public String toString(){
    return super.toString() + "\nTipo: " + tipo + "\nDano: " + dano + "\nAlcance: " + alcance;
  }
}
