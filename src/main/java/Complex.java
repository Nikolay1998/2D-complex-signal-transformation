public class Complex {
    private Double real;
    private Double im;

    public Complex(Double real, Double im) {
        this.real = real;
        this.im = im;
    }

    public Complex add(Complex c) {
        return new Complex(this.real + c.real, this.im + c.im);
    }

    public Complex mult(Complex c) {
        return new Complex(this.real * c.real - this.im * c.im, this.real * c.im + this.im * c.real);
    }

    public Double abs() {
        return Math.sqrt(this.im * this.im + this.real * this.real);
    }

    public Double phase() {
        return Math.atan2(this.im, this.real);
    }

    public Double getReal() {
        return real;
    }

    public Double getIm() {
        return im;
    }
}
