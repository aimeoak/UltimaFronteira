package Item;
import Interfaces.ComportamentoDeItem;
import java.lang.Comparable;
public class Item implements ComportamentoDeItem, Comparable<Item>  {
    private String nome; 
    private double peso; 
    private int durabilidade; //cada "usar" diminui um inteiro de durabilidade até zerar, no caso de alimentos a durabilidade pode ser 1

    public Item(String nome, double peso, int durabilidade){
        this.nome = nome; 
        this.peso = peso; 
        this.durabilidade = durabilidade;
    }

    //USAR() geral 
    public int usar(){ 
        if (durabilidade>0){
            durabilidade --;
        }
        return durabilidade;
    }


    public String getNome(){ 
        return this.nome;
    }

    public double getPeso(){
        return this.peso;
    }

    public int getDurabilidade(){
        return this.durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    @Override
    public String toString(){ 
        return "Nome: " + this.nome+ "\nPeso: " + this.peso + "\nDurabilidade: " + this.durabilidade;

    }
    @Override
    public int compareTo(Item outro){
        return Integer.compare(this.durabilidade,outro.durabilidade);
    }
}
