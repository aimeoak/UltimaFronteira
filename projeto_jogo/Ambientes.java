import java.util.Random; 
public abstract class Ambiente{
  private String nome; //BIOMA
  private String descricao;
  private int dificuldadeExplo; 
  private String []  recursos; 
  private double probEventos; 
  private String condClima;

  public Ambiente (String nome, String descricao, int dificuldadeExplo, String[] recursos, double probEventos, String condClima){
    this.nome = nome; 
    this.descricao = descricao; 
    this.dificuldadeExplo = dificuldadeExplo;
    this.recursos = recursos;
    this.probEventos = probEventos;
    this.condClima = condClima;
  }
  public String getNome(){
    return nome;
  }

  public String getDescricao(){
    return descricao;
  }

  public int getDificuldadeExplo(){
    return dificuldadeExplo;
  }
  public String[] getRecursos(){
    return recursos;
  }
  public double getProbEventos(){
    return probEventos;
  }

  public String getCondClima(){
    return condClima;
  }

  public abstract void explorar (); 
  
  public void gerarEvento(){
    String [] eventos = {"tempestade", "Incêndio", "neve"}; 
    Random rand = new Random(); 
    if (rand.nextDouble() < probEventos){
      int index = rand.nextInt(eventos.length);
      System.out.println("Um evento ocorreu " + eventos[index]);
    }
    else{
      System.out.println("Não houve evento");
    }
  }
  
  public void modificarClima(){
    String [] climas = {"Ensolarado", "Nublado", "Chuvoso"};
    Random rand = new Random(); 
    condClima = climas[rand.nextInt(climas.length)];
    System.out.println("O clima agora é " + condClima);
    }
}
