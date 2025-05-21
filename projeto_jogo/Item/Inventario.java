package Item;
import Exception.InventarioCheioException;
import Personagem.Personagem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventario {
    private List<Item> itens;
    private double capacidadeMaxima;
    private double pesoAtual;

    public Inventario(double capacidadeMaxima) {
        this.itens = new ArrayList<>();
        this.capacidadeMaxima = capacidadeMaxima;
        this.pesoAtual = 0;
    }
    /*
    public boolean adicionarItem(Item item) {
        if ((pesoAtual + item.getPeso()) <= capacidadeMaxima) {
            itens.add(item);
            pesoAtual += item.getPeso();
            System.out.println(item.getNome() + " adicionado ao inventário.");
            return true;
        } else {
            System.out.println("Inventário cheio! Não é possível adicionar " + item.getNome());
            return false;
        }
    }

     */
    public void adicionarItem(Item item) throws InventarioCheioException {
        double novoPeso = pesoAtual + item.getPeso();

        if (novoPeso > capacidadeMaxima) {
            double excesso = novoPeso - capacidadeMaxima;
            throw new InventarioCheioException("Falha ao adicionar " + item.getNome() +
                    " - Excesso: " + String.format("%.2f", excesso) + "kg");
        }

        itens.add(item);
        pesoAtual = novoPeso;
        System.out.println(item.getNome() + " adicionado com sucesso!");
    }
    public void removerItem(Item item) {
        if (this.itens.remove(item)) {
            this.pesoAtual -= item.getPeso();
            if (this.pesoAtual < 0) {
                this.pesoAtual = 0;
            }
        }
    }



    public boolean removerItem(String nomeItem) {
        Iterator<Item> iterador = itens.iterator();
        while (iterador.hasNext()) {
            Item item = iterador.next();
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                pesoAtual -= item.getPeso();
                iterador.remove();
                System.out.println(nomeItem + " removido do inventário.");
                return true;
            }
        }
        System.out.println("Item " + nomeItem + " não encontrado.");
        return false;
    }

    public boolean usarItem(String nomeItem, Personagem personagem) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                if (item instanceof Alimento alimento) {
                    alimento.consumir(personagem);
                } else {
                    item.usar(); // para adicionar outros tipos no futuro
                }

                if (item.getDurabilidade() <= 0) {
                    removerItem(nomeItem);
                }
                return true;
            }
        }
        System.out.println("Item " + nomeItem + " não está no inventário.");
        return false;
    }

    public void listarItens() {
        System.out.println("Capacidade: " + pesoAtual + "/" + capacidadeMaxima);
        if (itens.isEmpty()) {
            System.out.println("Inventário está vazio.");
        } else {
            System.out.println("Itens no inventário:");
            for (Item item : itens) {
                System.out.println("- " + item.getNome() + " | Peso: " + item.getPeso() + " | Durabilidade: " + item.getDurabilidade());
            }
        }
    }

    public double getPesoAtual() {
        return pesoAtual;
    }

    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public List<Item> getItens() {
        return itens;
    }
}
