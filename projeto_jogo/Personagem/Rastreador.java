package Personagem;

import Ambiente.Ambiente;

import java.util.Random;
public class Rastreador extends Personagem {
    Random random = new Random();
    public Rastreador(String nome) {
        super(nome, 90, 70, 70, 100, 80);

    }

    public void procurarRecursos(Ambiente ambiente){
        String [] recursos = ambiente.getRecursos();
        if(recursos.length == 0){
            System.out.println(this.getNome() + " procurou, mas não encontrou nenhum recurso.");
            return;
        }
        int index = random.nextInt(recursos.length);
        String recursoEncontrado = recursos[index];
        System.out.println(this.getNome() + " encontrou o recurso " + recursoEncontrado);

        }
    @Override
    public void acaoEspecial(){
        System.out.println("O rastreador  " + this.getNome() + " procurou, mas não encontrou nenhum recurso.");
    }


}
