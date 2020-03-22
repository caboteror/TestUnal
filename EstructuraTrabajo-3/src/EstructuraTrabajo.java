import java.util.*;

public class EstructuraTrabajo {

    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);
        int maximo, minimo;
        System.out.print("Máximo de fantasmas: ");
        maximo = num.nextInt();
        System.out.print("Mínimo de fantasmas: ");
        minimo = num.nextInt();
        new Snake(maximo, minimo).setVisible(true);
        
        //Hola
    }

}
