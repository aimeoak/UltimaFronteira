package Item;

public class Materiais extends Item {
  private String tipo; 
  private int resistencia; 

  public Materiais (String nome, double peso, int durabilidade, String tipo, int resistencia){
    super(nome, peso, durabilidade);
    this.tipo = tipo; 
    this.resistencia = resistencia;
  }

  public String getTipo() {
    return tipo;
  }

  public int getResistencia() {
    return resistencia;
  }

  public void setTipo(String tipo){
    this.tipo = tipo;
  }

  public void setResistencia(int resistencia){
    this.resistencia = resistencia;
  }
  @Override
  public int usar() {
    return super.usar();
  }
  public void combinar (Materiais outroMaterial){
    String t1 = this.getTipo();
    String t2 = outroMaterial.getTipo();

    if (t1.equals("Madeira") && t2.equals("Pedra")){
      System.out.println("Você conseguiu fazer um machado.");
    }
    if(t1.equals("Pedra") && t2.equals("Pedra")){
      System.out.println("Você conseguiu fazer um isqueiro.");
    }
    if (t1.equals("Madeira") && t2.equals("Metal")){
      System.out.println("Você conseguiu fazer uma faca.");
    }
    else {
      System.out.println("Você não conseguiu fazer nada.");
    }
  }

  @Override
    public String toString() {
        return super.toString() + "\nTipo: " + tipo + "\nResistência: " + resistencia;
    }
}
