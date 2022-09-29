package fourcalculations;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        while(true) {
       
            int n = 10;
            int r = 10;
            String submitPath = null;
            String answersPath = null;

            try {
                // 输入题目个数、数字范围
                System.out.println("Please enter the command?");
                Scanner command = new Scanner(System.in);
                String arr[] = command.nextLine().split("\\s");

                //获取指令相应参数
                if (arr.length > 1) {
                    for (int i = 0; i < arr.length; i = i + 2) {
                        switch (arr[i]) {
                            case "-n":
                                n = Integer.parseInt(arr[i + 1]);
                                if (n > 10000 || n < 1) {
                                    System.out.println("对不起，只允许输入1-10000的数字！");
                                    return; 
                                }
                                break;
                            case "-r":
                                r = Integer.parseInt(arr[i + 1]);
                                if (r < 1) {
                                    System.out.println("对不起，只允许大于等于1的自然数！");
                                    return;
                                }
                                break;
                            case "-e":
                                submitPath = arr[i + 1];
                                if (submitPath == null) {
                                    System.out.println("对不起，没有输入相应文件路径，请重新输入");
                                    return; 
                                }
                                break;
                            case "-a":
                                answersPath = arr[i + 1];
                                if (answersPath == null) {
                                    System.out.println("对不起，没有输入相应文件路径，请重新输入");
                                    return; 
                                }
                                break;
                            default:
                                System.out.println("指令输入错误!");
                                break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("您输入的指令有误，请重新输入");
            }

            /* ****执行函数 **** */
            System.out.println("n: " + n + ", r: " + r);
            problemSet makefile = new problemSet();
            if (submitPath != null && answersPath != null)
                makefile.createGradeFile(submitPath,answersPath);
            else
                makefile.createProblemSet(n,r);
        }
    }
}
