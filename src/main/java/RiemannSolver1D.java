public class RiemannSolver1D {

    public RiemannSolver1D() {
    }

    public Complex[] solve(double b, double a, Complex[] input) {
        Integer n = input.length;
        Complex[] result = new Complex[n];
        Double ksih = 2 * b / (n);
        Double xh = 2 * a / (n);
        Double ksi;
        for (int j = 0; j < n; j++) {
            ksi = -b + j * ksih;
            Complex localResult = new Complex(0.0, 0.0);
            Double x;
            for (int i = 0; i < n; i++) {
                x = -a + i * xh;
                localResult = localResult.add(input[i].mult(new Complex(xh, 0.0)).mult(furie(ksi * x)));
            }
            result[j] = localResult;
        }
        return result;
    }

    private Complex furie(double arg) {
        return new Complex(Math.cos(Math.PI * 2 * arg), -Math.sin(Math.PI * 2 * arg));
    }


}
