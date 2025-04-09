import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ÙLTIMA FRONTEIRA\n\nEstas são as informações do seu personagem: \n");
        Medico jogador = new Medico("João");
        Alimento alimento = new Alimento("Carne", 2.0, 1, 10, "Carne", 3);
        System.out.println(jogador);

        Floresta floresta = new Floresta(); 
        System.out.println("\nAMBIENTE: \n");
        System.out.print(floresta);

        int turno = 1;
        while (true){
            System.out.println("\n\n===TURNO: " + turno + "===" );
            //desgaste natural de cada turno e atualização de clima
            floresta.modificarClima();
            jogador.reduzirFome(5);
            jogador.reduzirSede(5);
            jogador.aumentarEnergia(3);
            jogador.aumentarSanidade(2);

            System.out.println("O que você deseja fazer? (1) Comer (2) Explorar  (3) Curar (4) Status");
            int escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        alimento.consumir(jogador);
                        System.out.println("\n\nINFORMAÇÕES DO PERSONAGEM APÓS O CONSUMO DO ALIMENTO\n");
                        System.out.println(jogador);
                        break;
                    case 2:
                        floresta.explorar();
                        break;
                    case 3:
                        jogador.curar();
                        System.out.println(jogador);
                        break;
                    case 4:
                        System.out.println(jogador);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
                if(jogador.getVida() <= 0|| jogador.getFome() <=0 || jogador.getSede() <=0 || jogador.getEnergia() <=0|| jogador.getSanidade() <=0){
                    System.out.println("\nVocê não resistiu... Fim de jogo.");
                    System.out.println("Você sobreviveu durante " + turno + " dias.");
                    break;
                }
                turno++;


        }

        scanner.close();
    }
}
