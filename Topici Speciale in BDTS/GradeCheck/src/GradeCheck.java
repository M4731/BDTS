public class GradeCheck {

    public static String evaluateStudent(int[] grades, int attendance, boolean bonus) {

        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }

        double average = sum / grades.length;

        if (average < 5 || attendance < 50) {
            return "PICAT";
        } else if (average >= 9.5 && attendance >= 90 && bonus) {
            return "BURSA";
        } else {
            return "TRECUT";
        }

    }

}
