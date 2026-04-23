import java.util.Scanner;

public class Main {

    public static boolean shouldContinue(char response) {
        return response == 'y' || response == 'Y';
    }

    static void main() {

        Scanner scanner = new Scanner(System.in);
        char response;

        do {
            // INPUT
            System.out.print("Introdu numarul de note: ");
            int n = scanner.nextInt();

            int[] grades = new int[n];

            System.out.println("Introdu notele:");
            for (int i = 0; i < n; i++) {
                grades[i] = scanner.nextInt();
            }

            System.out.print("Introdu procentul de prezenta (0-100): ");
            int attendance = scanner.nextInt();

            System.out.print("Are bonus activitate? (true/false): ");
            boolean bonus = scanner.nextBoolean();

            // PROCESARE
            String result = GradeCheck.evaluateStudent(grades, attendance, bonus);

            // OUTPUT
            System.out.println("Rezultat: " + result);

            // CONTINUARE
            System.out.print("Continui? (y/n): ");
            response = scanner.next().charAt(0);

        } while (shouldContinue(response));

        scanner.close();
    }

}
