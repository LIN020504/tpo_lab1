package Main;

public class Tangent {
    private static double factorial(int n){
        int result = 1;
        for (int i = 1; i <= n ; i++){
            result *=i;
        }
        return result;
    }

    public static double taylorSinx(double x,int n){
        return Math.pow(-1,n - 1) * Math.pow(x, (2 * n - 1)) / factorial(2 * n - 1);
    }

    public static double taylorCosx(double x, int n){
        return Math.pow(-1, n - 1) * Math.pow(x, (2 * n - 2)) / factorial(2 * n - 2);
    }

    public static double tanX(double degree){
        if (Double.isInfinite(degree)||Double.isNaN(degree)){
            throw new IllegalArgumentException("Value invalid");
        }

        degree = degree % 180;

        if(degree < -90){
            degree += 180;
        }

        if(degree > 90){
            degree -= 180;
        }

        if(degree == 90|| degree == -90){
            throw new IllegalArgumentException("degree can not be 90/-90");
        }

        double x = Math.toRadians(degree);
        int n = 17;
        double result_sin = 0;
        double result_cos = 0;

        for (int i = 1; i <= n; i++){
            result_sin += taylorSinx(x,i);
            result_cos += taylorCosx(x,i);
        }
        double result_tg = result_sin/result_cos;
        System.out.println("tan(x): " + result_tg);
        return result_tg;
    }
}