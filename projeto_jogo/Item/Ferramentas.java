package Item;

public class Ferramentas extends Item {
    private String tipo; //Machado, faca, isqueiro, lanterna
    private int eficiencia; 

   public Ferramentas(String nome, double peso, int durabilidade, String tipo, int eficiencia){
     super(nome, peso, durabilidade);
     this.tipo = tipo;
     this.eficiencia = eficiencia;
   }  

  public String getTipo(){
     return tipo;
   }

   public int getEficiencia(){
     return eficiencia;

   }
   public void setTipo(String tipo){
     this.tipo = tipo;

   }
   public void setEficiencia(int eficiencia){
     this.eficiencia = eficiencia;
   }

    @Override
    public int usar() {
        System.out.println("Usando a ferramenta " + getNome() + " de tipo " + tipo + " com eficiência " + eficiencia);

        if (getNome().equals("Machado")){
            System.out.println("Você cortou a madeira.");
        } else if (getNome().equals("Faca")){
            System.out.println("Você cortou o alimento.");
        } else if (getNome().equals("Isqueiro")){
            System.out.println("Você acendeu o fogo.");
        } else if (getNome().equals("Lanterna")){
            System.out.println("Você iluminou o ambiente.");
        } else {
            System.out.println("Você não conseguiu fazer nada.");
        }

        setDurabilidade(getDurabilidade() - 1);
        return getDurabilidade();
    }
    @Override
    public String toString(){
        return super.toString() + ", Tipo: " + tipo + ", Eficiência: " + eficiencia;
    }

}
