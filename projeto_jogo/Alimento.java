public class Alimento extends Item{
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
            System.out.println("Intoxicação alimentar!!");
            personagem.setVida(personagem.getVida()-1);
            personagem.setFome(personagem.getFome()+ valorNutricional);



        }
        else if(tipoAlimento.equals("cogumelo")){
            System.out.println("Vida extra");
            personagem.setVida(personagem.getVida()+ 1);
            personagem.setFome(personagem.getFome()+ valorNutricional);



        }
        else{
            personagem.setFome(personagem.getFome()+ valorNutricional);
            //deveria adicionar energia também??
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
