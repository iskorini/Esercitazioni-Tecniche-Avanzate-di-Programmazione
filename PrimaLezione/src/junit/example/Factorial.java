package junit.example;

/**
 * Created by Federico on 12/10/2016.
 */
public class Factorial{
    public int compute(int i) {
        if (i < 0 ) {
            throw new IllegalArgumentException("negative input");
        }
        if (i==0) {
            return 1;
        }
        int res = 1;
        for (int x = 0; x<i; x++) {
            res = res*x;
        }
        return res;
    }
}
