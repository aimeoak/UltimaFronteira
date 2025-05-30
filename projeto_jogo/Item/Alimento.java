package Item;
import Interfaces.ComportamentoDeItem;
import Personagem.Personagem;

public class Alimento extends Item implements ComportamentoDeItem {
    private int valorNutricional; 
    private String tipoAlimento; //nossos tipos de alimentos serão carne, frutas, legumes, peixe, cogumelos (Carne = intoxicação alimentar, Cougumelo = restaura vida)
    private int validade; 

    public Alimento (String nome, double peso, int durabilidade, int valorNutricional, String tipoAliment, int validade){
        super(nome, peso, durabilidade);
        this.valorNutricional = valorNutricional;
        this.tipoAlimento = tipoAliment.toLowerCase(); 
        this.validade = validade;
    }  
    public void consumir(Personagem personagem){
        if (tipoAlimento.equals("carne")){
            personagem.aumentarFome(valorNutricional);
            personagem.aumentarEnergia(valorNutricional);



        }
        else if(tipoAlimento.equals("cogumelo")){
            System.out.println("Vida extra");
            personagem.aumentarVida(10);
            personagem.aumentarFome(valorNutricional);
            personagem.aumentarEnergia(valorNutricional);



        }
        else{
            personagem.aumentarFome(valorNutricional);
            personagem.aumentarEnergia(valorNutricional);
        }
         //Os itens com uma durabilidade == 0 precisam sumir 
        setDurabilidade(getDurabilidade() - 1);
        if(getDurabilidade() <= 0){
            System.out.println("O alimento " + getNome() + " acabou!!");
        }

    }

    //temos que definir um método após toda a passagem de tempo, dimunui um em validade 

    public void dimValidade(){
        if (validade > 0){
        this.validade--;
        }
    }

    public int getValorNutricional(){
        return valorNutricional;
    }

    public String getTipoAlimento(){
        return tipoAlimento;
    }

    public int getValidade(){
        return validade;
    }

}
