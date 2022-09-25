import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Calculator {
    public static void main(String[] args) throws IOException {


        Random random = new Random();
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();


        List<String> exercisedata = new ArrayList<>();    //使用链表储存习题以及答案数据
        List<String> answerdata = new ArrayList<>();


        File Exercisefile = new File("Exercises.txt");
        File Answerfile = new File("Answer.txt");
        Writer ExerciseWrite =  new FileWriter(Exercisefile);
        Writer AnwerWrite = new FileWriter(Answerfile);
        BufferedWriter Exercise = new BufferedWriter(ExerciseWrite);
        BufferedWriter Answer = new BufferedWriter(AnwerWrite);


        for (int i = 0 ; i < n ; i ++ ) {
            int num1 = random.nextInt(100);    //生成一个个随机数
            if(num1==0){
                i--;
                continue;
            }
            int num2 = 0;
            int answer = 0;
            String data = null;
            int Opertornum  = random.nextInt(2);
            int Judgenum  = random.nextInt(2);   //随机生成一个 不是 0 就是 1 的数，用于判断加减
            num2 = Judge(num1,Judgenum);
            if( Judgenum == 0 ){     // 0 为加法 1 为减法
                data = num1+"+"+num2;
                answer = num1 + num2;
            }
            if( Judgenum == 1 ){     // 0 为加法 1 为减法
                answer = num1 - num2;
                data = num1+"-"+num2;
            }
            if (Opertornum == 1){
                int Judgenum2  = random.nextInt(2);
                int num3 = Judge(answer,Judgenum2);
                if ( Judgenum2 == 0 ){
                    data = data+"+"+num3;
                    answer = answer + num3;
                }
                if ( Judgenum2 == 1 ){
                    data = data+"-"+num3;
                    answer = answer - num3;
                }
            }


            if (exercisedata.contains(data)){
                i--;
                continue;
            }
            exercisedata.add( data );
            answerdata.add(Integer.toString(answer));
        }
        for (String s : exercisedata) {    //增强for遍历链表数据，并通过bufferedwrite写入文件
            Exercise.write(s);
            Exercise.newLine();
            Exercise.flush();
            System.out.println(s);
        }
        for (String s : answerdata) {
            Answer.write(s);
            Answer.newLine();
            Answer.flush();
            System.out.println(s);
        }


    }
    public static int Judge (int num1 ,int Judgennum){    //控制生成的第二随机数符合要求
        int num2 = 0 ;
        Random random = new Random();
        if(Judgennum == 0){
            num2 = random.nextInt(100-num1);   //做加法时两数之和不大于100
        }
        else if(Judgennum == 1){
            num2 = random.nextInt(num1);     //做减法时不出现负数和，num2 的最大值为 num1 - 1
        }
        return num2;
    }
}
