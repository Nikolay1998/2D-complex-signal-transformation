import org.jtransforms.fft.DoubleFFT_1D;

public class Main {
    public static void main(String[] args) {
        Double a = 5.0;
        Integer n = 1000;
        Integer  m  = 2 << 13;
        Double b = n * n / (4 * a * m);
        InputSignal inputSignal = new InputSignal() {
            protected Complex inputSignal(double x, double y) {
                return new Complex(Math.exp(-x * x) * Math.exp(-y * y), 0.0);
/*
                Complex xpart;
                if (x < -1.0 || x > 3.0) {
                    xpart = new Complex(0.0, 0.0);
                } else if (x == -1.0 || x == 3.0) {
                    xpart =  new Complex(0.5, 0.0);
                } else xpart = new Complex(1.0, 0.0);

                Complex ypart;
                if (y < -1.0 || y > 3.0) {
                    ypart = new Complex(0.0, 0.0);
                } else if (y == -1.0 || y == 3.0) {
                    ypart =  new Complex(0.5, 0.0);
                } else ypart = new Complex(1.0, 0.0);

                return xpart.mult(ypart);



 */





            }
        };
        //Complex[][] analytic = Analytic.getSolution(n, b);
        //draw(analytic, "analytic");
        Complex[][] input = inputSignal.getInput(a, n);
        draw(input, "input");
        FFTSolver1D fftSolver1D = new FFTSolver1D();
        //RiemannSolver1D riemannSolver1D = new RiemannSolver1D();
        Complex[][] intermediateFFT = new Complex[n][n];
        //Complex[][] intermediateRiemann = new Complex[n][n];
        for (int i = 0; i < n; i++) {
            intermediateFFT[i] = fftSolver1D.fft(n, m, a, input[i]);
            //intermediateRiemann[i] = riemannSolver1D.solve(b, a, input[i]);
        }
        Complex[][] intermediateTransFFT = transponation(intermediateFFT);
        //Complex[][] intermediateTransRiemann = transponation(intermediateRiemann);
        Complex[][] resultFFT = new Complex[n][n];
        //Complex[][] resultRiemann = new Complex[n][n];
        for (int i = 0; i < n; i++) {
            resultFFT[i] = fftSolver1D.fft(n, m, a, intermediateTransFFT[i]);
            //resultRiemann[i] = riemannSolver1D.solve(b, a, intermediateTransRiemann[i]);
        }
        draw(resultFFT, "fft");
        //draw(resultRiemann, "riemann");
    }

    private static void draw(Complex[][] values, String name) {
        Painter painter = new Painter();
        int n = values.length;
        double[][] ampl = new double[n][n];
        double[][] phase = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ampl[i][j] = values[i][j].abs();
                phase[i][j] = values[i][j].phase();
            }
        }
        painter.draw(ampl, name+" amplitude");
        painter.draw(phase, name+" phase");
    }

    private static Complex[][] transponation(Complex[][] a) {
        int n = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                Complex temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }
        return a;
    }
}
