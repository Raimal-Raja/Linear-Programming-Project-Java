import java.util.Arrays;

public class LinearProgrammingTest {
    public static void main(String[] args) {
        LinearProgramming original = new LinearProgramming(new double[]{3, 4},
                new double[][]{{1, 2, 3}}, new double[]{3});
        original.convertToStandardForm();
        check(Arrays.equals(original.getNewConstraintCoefficients()[0], new double[]{1, 2, 1}));
        LinearProgramming mixed = new LinearProgramming(new double[]{3, 4},
                new double[][]{{1, 2}, {2, 1}, {1, -1}}, new double[]{10, 8, 2},
                new String[]{"<=", ">=", "="});
        mixed.convertToStandardForm();
        check(Arrays.equals(mixed.getNewObjectiveCoefficients(), new double[]{3, 4, 0, 0}));
        check(Arrays.equals(mixed.getNewConstraintCoefficients()[0], new double[]{1, 2, 1, 0}));
        check(Arrays.equals(mixed.getNewConstraintCoefficients()[1], new double[]{2, 1, 0, -1}));
        check(Arrays.equals(mixed.getNewConstraintCoefficients()[2], new double[]{1, -1, 0, 0}));
        boolean rejected = false;
        try { new LinearProgramming(new double[]{3, 4}, new double[][]{{1}}, new double[]{1}); }
        catch (IllegalArgumentException expected) { rejected = true; }
        check(rejected);
        System.out.println("LP regression checks passed");
    }
    private static void check(boolean condition) {
        if (!condition) throw new AssertionError("Incorrect standard-form conversion");
    }
}
