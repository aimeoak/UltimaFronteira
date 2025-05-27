package Personagem;

public class Sobrevivente extends Personagem {
    public Sobrevivente(String nome){
        super(nome,85,85,85,85,85);

    }
    @Override
    public void acaoEspecial(){
        aumentarSanidade(10);
        aumentarEnergia(10);
        System.out.println("O sobrevivente " + this.getNome() + " meditou;");
        System.out.println("Foram recuparados 10 pontos de sanidade e 10 pontos de energia.");
    }

}
