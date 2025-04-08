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

        //simulação loop de turno
        for(int turno = 1; turno <= 3; turno++ ){
            System.out.println("\n\nTURNO: " + turno);
            floresta.modificarClima();
            System.out.println("O que você deseja fazer? (1) Comer (2) Explorar  (3) Curar");
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
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }


        }



        scanner.close();
    }
}
