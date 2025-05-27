package Item;

import Personagem.Personagem;

public class Remedios extends Item {
    private String tipo; // Analgésico, antibiótico, bandagem
  private String efeito; 

  public Remedios(String nome, double peso, int durabilidade, String tipo, String efeito){
    super(nome, peso, durabilidade);
    this.tipo = tipo;
    this.efeito = efeito;
  }

  public String getTipo(){
    return tipo;
  }
  public void setTipo(String tipo){
    this.tipo = tipo;
  }

  public String getEfeito(){
    return efeito;
  }
  public void setEfeito(String efeito){
    this.efeito = efeito;  
  }

  @Override
  public int usar() {
      return super.usar();
  }


  public void usar(Personagem jogador) {
      System.out.println("Usando o remédio " + getNome() + " de tipo " + tipo + " com efeito " + efeito);

      if (tipo.equals("Analgesico")) {
          jogador.aumentarVida(20);
          System.out.println("+20 de vida recuperada!");
      }
      else if (tipo.equals("Antibiotico")) {
          jogador.aumentarVida(15);
          System.out.println("+15 de vida recuperada!");
      }
      else if (tipo.equals("Bandagem")){
          jogador.aumentarVida(10);
          System.out.println("+10 de vida recuperada!");
      }
      else {
          System.out.println("Você não conseguiu fazer nada.");
      }
      setDurabilidade(getDurabilidade() - 1);
  }

  @Override
  public String toString() {
      return super.toString() + "\nTipo: " + tipo + "\nEfeito: " + efeito;
  }
}
