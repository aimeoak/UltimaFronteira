public class Remedios extends Item{
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
  public void usar() {
      System.out.println("Esse remédio precisa de um personagem para ser usado corretamente.");
  }


  public void usar(Personagem personagem) {
      System.out.println("Usando o remédio " + getNome() + " de tipo " + tipo + " com efeito " + efeito);

      if (tipo.equals("Analgésico")) {
          personagem.aumentarVida(20);
      }
      if (tipo.equals("Antibiótico")) {
          personagem.aumentarVida(30);
      }
      if (tipo.equals("Bandagem")){
          personagem.aumentarVida(10);
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
