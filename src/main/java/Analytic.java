public class Analytic {
    public static Complex[][] getSolution(int n, Double b) {
        Complex[][] solution = new Complex[n][n];
        double v;
        double h = 2 * b / n;
        for (int i = 0; i < n; i++) {
            v = -b + h * i;
            double u;
            if (v == 0) {
                solution[i] = solution[i - 1];
            } else {
                for (int j = 0; j < n; j++) {
                    u = -b + h * j;
                    if (u == 0) {
                        solution[i][j] = solution[i][j - 1];
                    } else {
                        solution[i][j] = getValue(v, u);
                    }
                }
            }
        }
        return solution;
    }

    private static Complex getValue(double v, double u) {
        Double den1 = Math.PI * 2 * u;
        Complex xPart = new Complex(
                //(Math.PI * ksi * Math.cos(2 * Math.PI * ksi) + Math.sin(2 * Math.PI * ksi) + Math.PI * ksi * Math.cos(6 * Math.PI * ksi) + Math.sin(6 * Math.PI * ksi)) / den,
                //-(Math.cos(2 * Math.PI * ksi) - Math.PI * ksi * Math.sin(2 * Math.PI * ksi) - Math.cos(6 * Math.PI * ksi) + Math.PI * ksi * Math.sin(6 * Math.PI * ksi)) / den
                (Math.sin(2 * Math.PI * u) + Math.sin(6 * Math.PI * u)) / den1,
                -(Math.cos(2 * Math.PI * u) - Math.cos(6 * Math.PI * u)) / den1);
        Double den2 = Math.PI * 2 * v;
        Complex yPart = new Complex(
                //(Math.PI * ksi * Math.cos(2 * Math.PI * ksi) + Math.sin(2 * Math.PI * ksi) + Math.PI * ksi * Math.cos(6 * Math.PI * ksi) + Math.sin(6 * Math.PI * ksi)) / den,
                //-(Math.cos(2 * Math.PI * ksi) - Math.PI * ksi * Math.sin(2 * Math.PI * ksi) - Math.cos(6 * Math.PI * ksi) + Math.PI * ksi * Math.sin(6 * Math.PI * ksi)) / den
                (Math.sin(2 * Math.PI * v) + Math.sin(6 * Math.PI * v)) / den2,
                -(Math.cos(2 * Math.PI * v) - Math.cos(6 * Math.PI * v)) / den2);
        return xPart.mult(yPart);
    }

    ;
}
