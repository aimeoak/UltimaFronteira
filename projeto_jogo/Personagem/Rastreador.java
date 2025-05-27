package Personagem;
import Exception.InventarioCheioException;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import Item.*;
import Ambiente.Ambiente;

import java.util.Random;
public class Rastreador extends Personagem {
    Random random = new Random();
    public Rastreador(String nome) {
        super(nome, 90, 70, 70, 100, 80);

    }

        // Lista fixa de recursos especiais que só o Rastreador pode encontrar
        private static final List<String> recursosRastreador = Arrays.asList(
                "Agua",
                "Lagartixa",
                "Coelho",
                "Antibiotico",
                "Bandagem",
                "Filtro de Água"
        );


    @Override
    public void acaoEspecial() {
        String recursoEncontrado = recursosRastreador.get(random.nextInt(recursosRastreador.size()));

        if (recursoEncontrado == null) {
            System.out.println(this.getNome() + " procurou cuidadosamente, mas não encontrou nada útil desta vez.");
            return;
        }

        System.out.println(this.getNome() + " usou suas habilidades e encontrou: " + recursoEncontrado);

        // Lógica para adicionar o item ao inventário
        try {
            Item item = criarItemEspecial(recursoEncontrado);
            this.getInventario().adicionarItem(item);
        } catch (InventarioCheioException e) {
            System.out.println("Não foi possível recolher " + recursoEncontrado + ": " + e.getMessage());
        }
    }

        private Item criarItemEspecial(String nomeRecurso) {
            switch(nomeRecurso) {
                case "Agua":
                    return new Agua("Agua", 1.5, 3, 10, true);
                case "Lagartixa":
                    return new Alimento("Lagartixa", 0.8, 2, 5, "carne", 15);
                case "Coelho":
                    return new Alimento("Coelho", 2.0, 3, 15,"carne",15);
                case "Antibiotico":
                    return new Remedios("Antibiotico",2,2,"Antibiotico","Alívio imediato da dor");
                case "Bandagem":
                    return new Remedios("Bandagem",0.5,3,"Bandagem","Estancamento de ferimentos");

                default:
                    return new Alimento("Casca de arvore", 1.0, 3,1,"casca de arvore",10);
            }
        }
    }



