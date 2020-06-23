package structData.ProjectWork.randomGenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RandomGenerator {

    public static void main (String [] args) {
        RandomGenerator lol = new RandomGenerator(new Polynom(15, "197 152235G"));
        //RandomGenerator lol = new RandomGenerator(new Polynom(6, "1 103F"));

        int [] ss = new int[lol.degree];

        int counter =0;
        for (int i : lol.getMState())
            counter++;
        System.out.println(counter);
//        for (int i =0; i <ss.length; i++)
//            ss[i] = 1;
        //lol.setS0(ss);
       // System.out.println(lol.getLisOfS());
    }


    private int degree  = 0; //степень полинома
    private int theoreticT = 0;
    private int experimentalT = 0;
    private List<String> listOfS = null;
    private Polynom polynom;
    private int[][] Fx = null; // матрица F(x) изначальная
    private int[] S0 = null;
    private int[] M = null; // массив М последовательностей
    private List<Integer> MState;

    public List<Integer> getMState() {
        return MState;
    }

//сканер для подсчета строк


    RandomGenerator () {};
    public RandomGenerator (Polynom polynom) {
        this.polynom = polynom;
        this.degree = polynom.getDegree();
        listOfS = new LinkedList<String>();

        formationFx();
        S0 = new int[degree];
        for (int i = 0; i < degree; i++) {
            //S0[i] = Fx[0][i];
           if(i%2==0)
            S0[i] = 1;
        }


        System.out.println(getTheoreticT());
        try {
            solveExpetimentalT();
        } catch (Exception e) {
            System.out.println("Експериментальный превысил теоретический");
            e.printStackTrace();
        }
    }

    public String getName() {
        return polynom.fullForm;
    }


    public void setDegree (int degree) {
        this.degree = degree;
    }
    public void setS0 (int [] S0) {

        S0 = new int [degree];
        for (int i = 0; i < degree; i++)
            this.S0[i]=S0[i];

        try {
            solveExpetimentalT();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(getLisOfS());
    }

    //возвращает список полиномов, получив его из файла "degree.txt"
    public String getPolynomList () {
        FileReader file = null;
        try {
            file = new FileReader(degree+".txt"); //открываем файл "degree.txt"
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(file);
        // сбор строки
        StringBuilder listOfPolynom = new StringBuilder();
        while (scanner.hasNextLine()) {
            listOfPolynom.append(scanner.nextLine() +'\n');
        }
        //closing file
        scanner.close();
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfPolynom.toString();
    }

    public String getAllMState () {
        StringBuilder str = new StringBuilder();
        for (int i: MState)
            str.append(i+" ");

        String Mstates;
        Mstates = str.toString();

        Mstates = Mstates.replace("1", "-1");
        Mstates = Mstates.replace('0','1');

        return Mstates;
    }

    public int [][] getFx() {
        return Fx;
    }

    //получение теоретического периода
    public int getTheoreticT() {
        theoreticT = ((int)(((Math.pow(2,(double)(degree))-1))/gcd((int)(((Math.pow(2,(double)(degree))-1))),polynom.getJ())));
        return theoreticT;
    }
    //получение экспериментального периода
    public int getExperimentalT() {
        return experimentalT;
    }

    public boolean isValid() {
        return experimentalT==theoreticT;
    }

    //наибольший общий делитель
    public static int gcd(int a, int b) {
        if (b == 0)
            return Math.abs(a);
        return gcd(b, a % b);
    }

    //получает количество полиномов степени degree *не нужен
    private int getListSize() {
        FileReader file = null;
        try {
            file = new FileReader(degree+".txt"); //открываем файл "degree.txt"
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int listSize = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) { //считает количество строк (= количеству полиномов)
            listSize++;
            scanner.nextLine();
        }
        //closing file
        scanner.close();
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listSize;
    }

    //формирование F(x)
    private void formationFx() {

        //создания массива
        Fx = new int[degree][];
        for (int i = 0; i < degree; i++)
            Fx[i] = new int [degree];

        M = new int[getTheoreticT()];

        long value;
        value = Long.parseLong(Long.toString(polynom.getNumber()),8);
        value = transform(value,2);


        //заполнение первой строки
        for(int i = Fx.length-1; i > -1; i--) {
            Fx[0][i] = (int) (value % 10);
            value /= 10;
        }
        //1 0 0 1 0 1 0 0 1 0 1 0 1 0 1 0 1 1
        //добавление 1 под главной диагональю
        for (int i = 1; i < Fx.length; i++)
            Fx[i][i-1]=1;

        for (int i = 0 ; i < Fx.length; i++) {
            for (int j = 0; j < Fx[i].length; j++)
                System.out.print(Fx[i][j]);
            System.out.println();
        }

    }
    //переводит число в numsysРичную систему исчисления
    private long transform (long number, int numsys) {
        long result = number%numsys;
        if (number/numsys == 0 && number%numsys == 0)
            return result;
        result += (10*transform(number/numsys, numsys));
        return result;
    }
    private void solveExpetimentalT() throws Exception {
        listOfS = new ArrayList<String>();
        MState = new ArrayList<Integer>();
        experimentalT=0;
        //создаем матрицу для хранения промежуточных состояний
        M = new int[getTheoreticT()];
        int [] Fxx = new int[degree];
        //Fxx = vector(S0,Fx);
        Fxx = S0;
        int [] En = new int[degree];
        //En = vector(S0,Fx);
        En = S0;
        String stemp;
        do {
            stemp="";
            //запись "сдвинутого и выкинутого" значения
            MState.add(Fxx[Fxx.length - 1]);
            for (int i = 0;i < Fxx.length; i++){
                stemp += Integer.toString(Fxx[i]);
            }
            listOfS.add(stemp);//запись состояния в массив состояний
            Fxx = vector(Fxx,Fx); // умножение матрицы на изначальное состояние
            experimentalT++; //подсчет периода
            if (experimentalT > theoreticT)
                throw new Exception();

        }while(!Matrix.equals(Fxx,En)); //пока она не будет равна En

        for (int i = 0; i < Fxx.length; i++)
            System.out.print(Fxx[i]);
        System.out.println();
        System.out.println(theoreticT + " " +experimentalT);
    }

    public String getListOfS() {
        StringBuilder str= new StringBuilder();

        for (int i =0; i < listOfS.size();i++){
            str.append(listOfS.get(i) +'\n');
        }

        return str.toString();
    }

    public String getM () {
        StringBuilder str = new StringBuilder();
        for (int i: MState)
            str.append(i+" ");

        return str.toString();
    }

    public int wt() {
        int counter = 0;
        for (int i: MState)
            if (i == 1)
                counter++;

        return counter;
    }

    private int [] vector (int [] A, int [][] B){
        int [] result = new int [A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++)
                result[i] = (result[i] + (A[j]*B[i][j]))%2;
        }

        return result;
    }


}
