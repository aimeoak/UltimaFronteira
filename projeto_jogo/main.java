public class main {
    public static void main(String[] args) {
        System.out.println("ÙLTIMA FRONTEIRA\n\nEstas são as informações do seu personagem: \n");
        Personagem personagem = new Personagem("João", 100, 100, 100, 100, 100);
        System.out.println(personagem);

        Floresta floresta = new Floresta(); 
        System.out.println("\nAMBIENTE: \n");
        System.out.print(floresta);

        System.out.println("\n\n PRIMEIRO TURNO DO JOGO\n\n");

        floresta.modificarClima();

        Alimento alimento = new Alimento("Carne", 2.0, 1, 10, "Carne", 3);
        alimento.consumir(personagem); 

        System.out.println("\n\nINFORMAÇÕES DO PERSONAGEM APÓS O CONSUMO DO ALIMENTO\n");

        System.out.println(personagem);

    }
}
