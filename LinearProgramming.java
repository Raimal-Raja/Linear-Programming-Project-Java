import java.util.Arrays;

/** Converts constraints to equality form; does not solve the optimization problem. */
public class LinearProgramming {
    private final double[] objectiveCoefficients;
    private final double[][] constraintCoefficients;
    private final double[] constraintBounds;
    private final String[] relations;
    private double[] newObjectiveCoefficients;
    private double[][] newConstraintCoefficients;

    /** The original three-argument API treats every constraint as <=. */
    public LinearProgramming(double[] objective, double[][] coefficients, double[] bounds) {
        this(objective, coefficients, bounds, defaultRelations(bounds.length));
    }

    public LinearProgramming(double[] objective, double[][] coefficients,
                             double[] bounds, String[] relations) {
        if (objective.length == 0 || coefficients.length != bounds.length
                || relations.length != bounds.length) {
            throw new IllegalArgumentException("Inconsistent problem dimensions");
        }
        objectiveCoefficients = objective.clone();
        constraintBounds = bounds.clone();
        this.relations = relations.clone();
        constraintCoefficients = new double[coefficients.length][objective.length];
        for (int i = 0; i < coefficients.length; i++) {
            // Accept the original examples' augmented rows, as well as coefficient-only rows.
            if (coefficients[i].length != objective.length
                    && coefficients[i].length != objective.length + 1) {
                throw new IllegalArgumentException("Incorrect constraint width");
            }
            if (coefficients[i].length == objective.length + 1
                    && Double.compare(coefficients[i][objective.length], bounds[i]) != 0) {
                throw new IllegalArgumentException("Augmented row and explicit bound disagree");
            }
            if (!"<=".equals(relations[i]) && !">=".equals(relations[i])
                    && !"=".equals(relations[i])) {
                throw new IllegalArgumentException("Relations must be <=, >=, or =");
            }
            System.arraycopy(coefficients[i], 0, constraintCoefficients[i], 0, objective.length);
        }
    }

    private static String[] defaultRelations(int count) {
        String[] result = new String[count];
        Arrays.fill(result, "<=");
        return result;
    }

    public void convertToStandardForm() {
        int variables = objectiveCoefficients.length;
        int extraVariables = 0;
        for (String relation : relations) if (!"=".equals(relation)) extraVariables++;
        newObjectiveCoefficients = Arrays.copyOf(objectiveCoefficients, variables + extraVariables);
        newConstraintCoefficients = new double[relations.length][variables + extraVariables];
        int slackColumn = variables;
        for (int i = 0; i < relations.length; i++) {
            System.arraycopy(constraintCoefficients[i], 0, newConstraintCoefficients[i], 0, variables);
            if (!"=".equals(relations[i])) {
                newConstraintCoefficients[i][slackColumn++] = "<=".equals(relations[i]) ? 1 : -1;
            }
        }
    }

    public double[] getNewObjectiveCoefficients() { return newObjectiveCoefficients; }
    public double[][] getNewConstraintCoefficients() { return newConstraintCoefficients; }
    public double[] getConstraintBounds() { return constraintBounds.clone(); }

    public static void main(String[] args) {
        LinearProgramming lp = new LinearProgramming(new double[]{3, 4},
                new double[][]{{1, 2, 10}, {2, 1, 8}, {1, -1, 2}}, new double[]{10, 8, 2});
        lp.convertToStandardForm();
        System.out.println("New Objective Coefficients: " + Arrays.toString(lp.getNewObjectiveCoefficients()));
        System.out.println("New Constraint Coefficients:");
        for (double[] row : lp.getNewConstraintCoefficients()) System.out.println(Arrays.toString(row));
        System.out.println("Constraint Bounds: " + Arrays.toString(lp.getConstraintBounds()));
    }
}
