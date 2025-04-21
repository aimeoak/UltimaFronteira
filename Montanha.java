import java.util.Random;

public class Montanha extends Ambiente {
    private static final String vegetacao = "Na montanha a vegetação é escassa, o que dificulta a busca por recursos."; 
    private static final String clima = "O clima na montanha é frio e instável. Nevascas e ventos fortes podem acontecer repentinamente";
    private static final String terreno = "O terreno na montanha é acidentado, o que exige maior gasto de energia para explorá-lo";

    public Montanha() {
        super("Montanha", vegetacao + " " + terreno, 4, new String[] {"Pedras preciosas e metais", "Refúgio em cavernas seguras", "Água de degelo"}, 0.6, clima);
    }

    @Override 
    public void gerarEvento(Personagem personagem) {
        String[] eventos = { "Nevasca repentina", "Deslizamento de pedras", "Descoberta de uma caverna segura"};
        Random rand = new Random();
        if (rand.nextDouble() < getProbEventos()) {
            int index = rand.nextInt(eventos.length);
            System.out.println("Um evento ocorreu: " + eventos[index]);
            if (eventos[index].equals("Nevasca repentina")) {
                System.out.println("A temperatura caiu drasticamente e você está congelando!");
                personagem.reduzirVida(10);
            }
            if (eventos[index].equals("Deslizamento de pedras")) {
                System.out.println("O deslizamento de pedras causou diversos ferimentos em você!");
                personagem.reduzirVida(20); 
            }
            if (eventos[index].equals("Descoberta de uma caverna segura")) {
                System.out.println("Você encontrou uma caverna segura para se abrigar!"); 
                personagem.aumentarVida(10);
                personagem.aumentarEnergia(10);
            }
        } else {
            System.out.println("Não houve evento");
        }
    }

    @Override
    public void explorar() {
        String[] recursos = getRecursos();
        Random rand = new Random();
        int index = rand.nextInt(recursos.length);
        System.out.println("Você encontrou " + recursos[index]);
    }
}
