import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.jtransforms.fft.DoubleFFT_1D;

public class FFTSolver1D {
    public Complex[] fft(Integer N, Integer M, Double a, Complex[] valuesComp){
        Double hx = 2 * a / (N);
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        org.apache.commons.math3.complex.Complex[] ff = new org.apache.commons.math3.complex.Complex[M];

        double[] values = transformToDouble(valuesComp);
        values = zeroAdding(M*2, values);
        values = swapParts(values);
        valuesComp = transformToComplex(values);
        for(int i = 0; i< M; i++) {
            ff[i] = new org.apache.commons.math3.complex.Complex(valuesComp[i].getReal(), valuesComp[i].getIm());
        }
        org.apache.commons.math3.complex.Complex[] res = fft.transform(ff, TransformType.FORWARD);
        for(int i = 0; i< M; i++) {
            valuesComp[i] = new Complex(res[i].getReal(), res[i].getImaginary());
        }
        values = transformToDouble(valuesComp);
        //DoubleFFT_1D fft_1D = new DoubleFFT_1D(M);
        //fft_1D.complexForward(values);
        for (int i = 0; i < M*2; i++) {
            values[i] = values[i] * hx * Math.sqrt(2);
        }
        values = swapParts(values);
        Complex[] result = transformToComplex(values);
        result = getCentralValues(result, N);
        return result;
    }

    private double[] transformToDouble(Complex[] valuesComp) {
        Integer n = valuesComp.length;
        double[] result = new double[2*n];
        for(int i = 0; i < n; i++){
            result[i*2] = valuesComp[i].getReal();
            result[i*2 +1] = valuesComp[i].getIm();
        }
        return result;
    }

    private Complex[] getCentralValues(Complex[] functionValues, Integer N) {
        Integer M = functionValues.length;
        Complex[] result = new Complex[N];
        int j = 0;
        for (int i = 0; i < (M - N) / 2; i++) {
            j++;
        }
        for (int i = 0; i < N; i++) {
            result[i] = functionValues[j];
            j++;
        }
        return result;
    }

    private Complex[] transformToComplex(double[] functionValues) {
        Complex[] result = new Complex[functionValues.length/2];
        for(int i = 0; i < functionValues.length/2; i++){
            result[i] = new Complex(functionValues[2*i], functionValues[2*i+1]);
        }
        return result;
    }

    private double[] swapParts(double[] functionValues) {
        double[] result = new double[functionValues.length];
        for (int i = 0; i < functionValues.length / 2; i++) {
            result[i] = functionValues[functionValues.length / 2 + i];
        }
        for (int i = 0; i < functionValues.length / 2; i++) {
            result[i + functionValues.length / 2] = functionValues[i];
        }
        return result;
    }

    private double[] zeroAdding(Integer M, double[] functionValues) {
        double[] result = new double[M];
        Integer N = functionValues.length;
        int j = 0;
        for (int i = 0; i < (M - N) / 2; i++) {
            result[j] = 0.0;
            j++;
        }
        for (int i = 0; i < N; i++) {
            result[j] = functionValues[i];
            j++;
        }
        for (int i = 0; i < (M - N) / 2; i++) {
            result[j] = 0.0;
            j++;
        }
        return result;
    }
}
