package Personagem;

public class Medico extends Personagem {
    public Medico(String nome){
        super(nome,70,80,80,80,100);
}
    @Override
    public void acaoEspecial(){
        aumentarVida(10);
        System.out.println("O médico " + this.getNome() + " se curou.");
        System.out.println("Foram recuperados 10 pontos de vida.\n");
    }
}
