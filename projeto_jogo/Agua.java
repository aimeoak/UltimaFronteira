public class Agua extends Item{
  private int volume; 
  private boolean potavel; // true = potável, false = contaminada;

  public Agua(String nome, double peso, int durabilidade, int volume, boolean potavel){
    super(nome, peso, durabilidade); 
    this.volume = volume;
    this.potavel = potavel;
  }

  public int getVolume(){
    return volume;
  }

  public void setVolume(int volume){
    this.volume = volume;  
  }

  public boolean isPotavel(){
    return potavel;  
  }
  public void setPotavel(boolean potavel){
    this.potavel = potavel;
  }

  @Override
  public void usar() {
      // Nada acontece
  }
  public void beber(Personagem personagem){
    if (potavel){
      System.out.println("A água é potável.");
      personagem.aumentarSede(volume);
    }
    else {
      personagem.reduzirVida(10);
      System.out.println("A água está contaminada, você se envenenou.");
    }

    setDurabilidade(getDurabilidade() - 1);

    if (getDurabilidade() <= 0) {
      System.out.println("A água acabou!");
    }
  }
   @Override
    public String toString() {
        return super.toString() + "\nVolume: " + volume + "\nPotável: " + (potavel ? "Sim" : "Não");
    }
}
