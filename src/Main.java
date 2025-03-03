import java.util.Random;

public class Main {
    public static void main(String[] args) {
        UniBHList<Integer> list = new UniBHList<>();
        Random random = new Random();
        
        // Inserir 10 números aleatórios entre 1 e 44 em ordem crescente gradualmente
        for (int i = 1; i <= 10; i++) {
            int randomNumber = random.nextInt(44) + 1;
            list.insertAtEnd(randomNumber);
            System.out.println(list);
            if (!list.isSorted()) {
                list.organize();
                System.out.println(list);
            }
        }
        
        // Remover elementos do final gradualmente
        for (int i = 0; i < 10; i++) {
            list.removeAtEnd();
            System.out.println(list);
        }
    }
}