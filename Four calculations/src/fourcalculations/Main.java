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
                // ������Ŀ���������ַ�Χ
                System.out.println("Please enter the command?");
                Scanner command = new Scanner(System.in);
                String arr[] = command.nextLine().split("\\s");

                //��ȡָ����Ӧ����
                if (arr.length > 1) {
                    for (int i = 0; i < arr.length; i = i + 2) {
                        switch (arr[i]) {
                            case "-n":
                                n = Integer.parseInt(arr[i + 1]);
                                if (n > 10000 || n < 1) {
                                    System.out.println("�Բ���ֻ��������1-10000�����֣�");
                                    return; 
                                }
                                break;
                            case "-r":
                                r = Integer.parseInt(arr[i + 1]);
                                if (r < 1) {
                                    System.out.println("�Բ���ֻ������ڵ���1����Ȼ����");
                                    return;
                                }
                                break;
                            case "-e":
                                submitPath = arr[i + 1];
                                if (submitPath == null) {
                                    System.out.println("�Բ���û��������Ӧ�ļ�·��������������");
                                    return; 
                                }
                                break;
                            case "-a":
                                answersPath = arr[i + 1];
                                if (answersPath == null) {
                                    System.out.println("�Բ���û��������Ӧ�ļ�·��������������");
                                    return; 
                                }
                                break;
                            default:
                                System.out.println("ָ���������!");
                                break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("�������ָ����������������");
            }

            /* ****ִ�к��� **** */
            System.out.println("n: " + n + ", r: " + r);
            problemSet makefile = new problemSet();
            if (submitPath != null && answersPath != null)
                makefile.createGradeFile(submitPath,answersPath);
            else
                makefile.createProblemSet(n,r);
        }
    }
}
