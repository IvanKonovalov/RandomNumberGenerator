package structData.ProjectWork.randomGenerator;

public class Matrix {
    public static double[][] modn (double[][] A, double[][] B, int n) throws Exception {
        if (A.length != B.length || A[0].length != B[0].length )
            throw new Exception();
        int height = A[0].length, width = B.length;
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++)
            for (int j=0; j < width; j++)
                result[i][j] = (A[i][j]+B[i][j])%n;
        return result;
    }


    public static double[][] mnoj (double[][] A, double[][] B, int n) throws Exception {
        if (A.length != B[0].length)
            throw new Exception();
        int height = A[0].length, width = B.length;
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                result[i][j] = x_y(A[i],getColumn(B,j))%n;
            }
        return result;
    }

    public static int getMin(double[] x) {
        int imin = -1;
        double min = 9999999;
        for (int i = 0; i < x.length; i ++) {
            if (min > x[i] && x[i] != 0){
                min = x[i];
                imin = i;
            }
        }
        return imin;
    }

    private static double x_y(double[] x, double[] y) {
        double result = 0;
        for (int i =0; i < x.length; i++)
            result += x[i]*y[i];
        return result;
    }


    public static double[] getColumn(double B[][], int j) {
        double[] column = new double[B[j].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = B[j][i];
        }
        return column;
    }
    public static int[] getColumn(int B[][], int j) {
        int[] column = new int[B[j].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = B[j][i];
        }
        return column;
    }

    public static void matrixCopy (double A[][], double B[][]) {
//        try {
//            if (A.length != B.length)
//                throw new Exception("Different size");
//            for (int i = 0; i < A.length; i ++)
//                if (A[i].length != B[i].length)
//                    throw new Exception("Different size");
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//        }

        for (int i = 0; i < A.length; i ++)
            for (int j = 0; j < B[0].length; j ++)
                A[i][j]=B[i][j];

    }
    public static void matrixCopy (int A[][], int B[][]) {
        for (int i = 0; i < A.length; i ++)
            for (int j = 0; j < B[0].length; j ++)
                A[i][j]=B[i][j];
    }

    public static boolean equals (int A[], int B[]) {
        for (int i = 0; i < A.length; i ++)
                if(A[i]!=B[i])
                    return false;
        return true;
    }
    public static int [] vectorMatrix (int []A, int [][]B) {
        int [] result = new int[B.length];
        for (int i = 0; i < B.length; i++)
            for (int j = 0; j < A.length; j++)
                result[i] = (x_y(A,B[i])%2);
        return result;
    }
    private static int x_y(int[] x, int[] y) {
        int result = 0;
        for (int i =0; i < x.length; i++)
            result += x[i]*y[i];
        return result;
    }
}
