public class Sobrevivente extends Personagem {
    public Sobrevivente(String nome){
        super(nome,85,85,85,85,85);

    }
    public void meditar(){
        aumentarSanidade(5);
        aumentarEnergia(10);
        System.out.println("O sobrevivente " + this.getNome() + "meditou;");
        System.out.println("Foram recuparados 5 pontos de sanidade e 10 pontos de energia.");
    }

}
