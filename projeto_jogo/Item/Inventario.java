package Item;

import java.util.TreeSet;
import java.util.Set;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Exception.InventarioCheioException;
import Personagem.Personagem;

public class Inventario {
    private Set<Item> itens;
    private double capacidadeMaxima;
    private double pesoAtual;

    public Inventario(double capacidadeMaxima) {
        this.itens = new TreeSet<>();
        this.capacidadeMaxima = capacidadeMaxima;
        this.pesoAtual = 0;
    }

    public void adicionarItem(Item item) throws InventarioCheioException {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }

        double novoPeso = pesoAtual + item.getPeso();

        if (novoPeso > capacidadeMaxima) {
            double excesso = novoPeso - capacidadeMaxima;
            throw new InventarioCheioException("Falha ao adicionar " + item.getNome() +
                    " - Excesso: " + String.format("%.2f", excesso) + "kg");
        }

        if (itens.add(item)) {
            pesoAtual = novoPeso;
            System.out.println(item.getNome() + " adicionado com sucesso!");
        } else {
            System.out.println(item.getNome() + " já está no inventário.");
        }
    }

    public void removerItem(Item item) {
        if (item != null && itens.remove(item)) {
            pesoAtual -= item.getPeso();
            if (pesoAtual < 0) {
                pesoAtual = 0;
            }
        }
    }

    public boolean removerItem(String nomeItem) {
        Iterator<Item> iterator = itens.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                pesoAtual -= item.getPeso();
                iterator.remove();
                System.out.println(nomeItem + " removido do inventário.");
                return true;
            }
        }
        System.out.println("Item " + nomeItem + " não encontrado.");
        return false;
    }
    //USAR ITEM ESTA IGUAL!
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
    //LISTAR ITENS ESTÁ IGUAL
    public void listarItens() {
        System.out.println("Capacidade: " + String.format("%.1f",pesoAtual) + "/" + String.format("%.1f",capacidadeMaxima));
        if (itens.isEmpty()) {
            System.out.println("Inventário está vazio.");
        } else {
            System.out.println("Itens no inventário:\n(ordenados alfabeticamente)");
            for (Item item : itens) {
                System.out.println("- " + item.getNome() + " | Peso: " + String.format("%.1f",item.getPeso()) + " | Durabilidade: " + item.getDurabilidade());
            }
        }
    }

    public double getPesoAtual() {
        return pesoAtual;
    }

    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public Set<Item> getItens() {
        return itens;
    }
}

