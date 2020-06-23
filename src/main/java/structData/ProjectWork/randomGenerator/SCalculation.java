package structData.ProjectWork.randomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SCalculation {
    List<Integer> SList;
    private int Ts;

    public SCalculation(RandomGenerator[] list) throws Exception {

        SList = new ArrayList<>();
        if (list.length == 2) {

            if (RandomGenerator.gcd(list[0].getTheoreticT(), list[1].getTheoreticT()) != 1)
                throw new Exception ();

            Ts = list[0].getTheoreticT() * list[1].getTheoreticT();

            String [] str = list[0].getM().split(" ");
            String [] str2 = list[1].getM().split(" ");

            int [] intstr = new int[str.length];
            int [] intstr2 = new int[str2.length];

            for(int i = 0; i < str.length; i++)
                intstr[i] = Integer.parseInt(str[i]);

            for(int i = 0; i < str2.length; i++)
                intstr2[i] = Integer.parseInt(str2[i]);

            for (int i = 0; i < Ts; i++)
                SList.add((intstr[i % intstr.length] + intstr2[i % intstr2.length])%2);

        }

        if (list.length == 3) {

            RandomGenerator[] rtemp = new RandomGenerator[2];
            rtemp[0] = list[0];
            rtemp[1] = list[1];

            SCalculation temp = new SCalculation(rtemp);
            if (RandomGenerator.gcd(list[1].getTheoreticT(), list[2].getTheoreticT()) != 1)
                throw new Exception ();
            if (RandomGenerator.gcd(list[0].getTheoreticT(), list[1].getTheoreticT()) != 1)
                throw new Exception ();
            if (RandomGenerator.gcd(list[0].getTheoreticT(), list[2].getTheoreticT()) != 1)
                throw new Exception ();

            Ts = temp.Ts * list[2].getTheoreticT();

            String [] str = temp.getSList().split(" ");
            String [] str2 = list[2].getM().split(" ");

            int [] intstr = new int[str.length];
            int [] intstr2 = new int[str2.length];

            for(int i = 0; i < str.length; i++)
                intstr[i] = Integer.parseInt(str[i]);

            for(int i = 0; i < str2.length; i++)
                intstr2[i] = Integer.parseInt(str2[i]);

            for (int i = 0; i < Ts; i++)
                SList.add((intstr[i % intstr.length] + intstr2[i % intstr2.length])%2);
        }

    }

    public String getSList() {
        StringBuilder str = new StringBuilder();
        for (Integer i : SList)
            str.append(i+" ");

        return str.toString();
    }

    public int getTs() {
        return Ts;
    }

    public String akf () {
        StringBuilder result = new StringBuilder();
        int [] MArr;
        int index;
        double [] sum;
        String temp = getSList();
        temp = temp.replaceAll("1", "-1");
        temp = temp.replaceAll("0","1" );

        String [] str= temp.split(" ");

        sum = new double[Ts];
        MArr = new int[Ts];

        for(int i =0 ; i <Ts; i++)
            MArr[i] = Integer.parseInt(str[i]);

        for(int x = 0;  x < Ts; x++) {
            sum[x] = 0;
            for(int i = 0; i < Ts; i++) {
                index = ( (i+x)%(Ts) );
                sum[x] += MArr[i] * MArr[index];
            }
            sum[x] = sum[x]/Ts;
        }

        for( double i : sum)
            result.append(i +" ");

        return result.toString();
    }
}
