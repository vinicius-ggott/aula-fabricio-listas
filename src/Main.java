public class Main {
    public static void main(String[] args) {
        UniBHList<Integer> list = new UniBHList<>();

        // Teste: isEmpty
        System.out.println("Está vazia? " + list.isEmpty());

        // Inserção
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        System.out.println("Após inserções: " + list);

        // Tamanho
        System.out.println("Tamanho: " + list.size());

        // contains
        System.out.println("Contém 20? " + list.contains(20));
        System.out.println("Contém 40? " + list.contains(40));

        // indexOf
        try {
            System.out.println("Índice do 30: " + list.indexOf(30));
            System.out.println("Índice do 100: " + list.indexOf(100)); // Vai gerar erro
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Inserir após o 1º item (posição 1)
        try {
            list.insertAfter(1, 25); // entre 20 e 30
            System.out.println("Após inserir 25 após índice 1: " + list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Remover pelo valor
        try {
            list.removeByValue(10);
            System.out.println("Após remover valor 10: " + list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Remover pelo índice
        try {
            list.removeAt(1); // Remove 25
            System.out.println("Após remover índice 1: " + list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Modificar valor
        try {
            list.set(0, 99); // Altera valor na posição 0 (de 20 pra 99)
            System.out.println("Após alterar índice 0 para 99: " + list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Ordenação e verificação
        list.insertAtEnd(15);
        System.out.println("Antes de ordenar: " + list);
        System.out.println("Está ordenada? " + list.isSorted());
        list.organize();
        System.out.println("Depois de ordenar: " + list);
        System.out.println("Está ordenada? " + list.isSorted());

        // Esvaziando
        while (!list.isEmpty()) {
            list.removeAtEnd();
            System.out.println("Removido um do final: " + list);
        }

        System.out.println("Lista final vazia? " + list.isEmpty());
    }
}
