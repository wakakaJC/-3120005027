package fourcalculations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class problemSet {
	 /**
     * ���ɲ����Exercises.txt��Answer.txt
     * @param n Ϊ ��Ҫ��ʽ������
     * @param r Ϊ ʽ���в������ķ�Χ
     */
    public void createProblemSet(int n,int r){
        CheckRepeat temporarySet = new CheckRepeat();
        ArrayList returnList = temporarySet.generate(n,r);
        ArrayList<String> txtList = new ArrayList<>();
        ArrayList<String> ansList = new ArrayList<>();

        //��ȡ�⼯���𰸼�
        for (int i =0;i<2*n;i++) {
            if(i<n) txtList.add(returnList.get(i).toString());
            else ansList.add(returnList.get(i).toString());
        }
        //����⼯�����𰸼�
        createEXEFile(txtList);
        createAnsFile(ansList);
    }

    /**
     * ���ɲ����Exercises.txt
     * @param txtList Ϊ �����⼯�� ʽ���ַ���
     */
    private void createEXEFile(ArrayList txtList){
        try{
            File exTXT = new File("../Exercises.txt");

            //����ļ��Ѵ��ڣ���ɾ���ļ�
            if (exTXT.exists()) {
                exTXT.delete();
            }
            //�����ļ��ɹ�����
            if(exTXT.createNewFile()){
                System.out.println("����Exercises.txt:");
                FileOutputStream txtFile = new FileOutputStream(exTXT);
                PrintStream q = new PrintStream(exTXT);
                q.println("ѧ�ţ�3216005168    ������Lyuthia    �ɼ���\n");

                for(int i=0;i<txtList.size();i++){
                    System.out.print(">");
                    q.println(i+1 + ". " +txtList.get(i));
                    //System.out.println(i+1 + ". " +txtList.get(i));
                }

                txtFile.close();
                q.close();
                System.out.println("Exercises.txt �����ɹ���");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * ���ɲ����Answer.txt
     * @param ansList Ϊ ���ô𰸼��� ���ַ���
     */
    private void createAnsFile(ArrayList ansList){
        try{
            File ansTXT = new File("../Answer.txt");

            //����ļ��Ѵ��ڣ���ɾ���ļ�
            if (ansTXT.exists()) {
                ansTXT.delete();
            }
            //�����ļ��ɹ�����
            if(ansTXT.createNewFile()){
                System.out.print("����Answer.txt:");
                FileOutputStream ansFile = new FileOutputStream(ansTXT);
                PrintStream p = new PrintStream(ansTXT);
                p.println("�𰸣�\n");

                for(int i=0;i<ansList.size();i++){//��������
                //for(int i=0;i<ansList.size()+1;i++){//���Դ��븲����
                    System.out.print(">");
                    p.println(i+1 + ". " +ansList.get(i));
                }
                ansFile.close();
                p.close();
                System.out.println("Answer.txt �����ɹ���");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * ���ɲ����Grade.txt
     * -e F:\5168-homework\theFourArithmeticGenerator\Answer.txt -a F:\5168-homework\theFourArithmeticGenerator\submit.txt
     * @param submitPath Ϊ �����ļ� �� ·��
     * @param answersPath Ϊ ���ļ� �� ·��
     */
    public void createGradeFile(String submitPath, String answersPath) {
        try {
            /* **** ��ȡָ���ļ������� **** */
            ArrayList<String> submitList = obtainAnswer(submitPath);
            ArrayList<String> answersList = obtainAnswer(answersPath);

            /* **** ���гɼ� **** */
            ArrayList<String> trueQuesNum = new ArrayList<>();
            ArrayList<String> falseQuesNum = new ArrayList<>();

            for (int i = 0; i < submitList.size(); i++) {
                if (submitList.get(i).equals(answersList.get(i)))
                    trueQuesNum.add(String.valueOf(i+1));
                else
                    falseQuesNum.add(String.valueOf(i+1));
            }

            /* **** ���ɲ�����ļ� **** */
            File gradeTXT = new File("../Grade.txt");

            //����ļ��Ѵ��ڣ���ɾ���ļ�
            if (gradeTXT.exists()) {
                gradeTXT.delete();
            }
            //�����ļ��ɹ�����
            if (gradeTXT.createNewFile()) {
                System.out.print("����Grade.txt:");
                FileOutputStream gradeFile = new FileOutputStream(gradeTXT);
                PrintStream p = new PrintStream(gradeTXT);
                p.println("�ɼ���\n");

                p.print("Correct:");
                output(p, trueQuesNum);
                p.print("Wrong:");
                output(p, falseQuesNum);

                gradeFile.close();
                p.close();
                System.out.println("Grade.txt �����ɹ���");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * ��� �ɼ�
     * @param quesNum Ϊ Correct/Wrong�� ��Ŀ��ż�
     */
    private void output(PrintStream p,ArrayList quesNum) {
        p.print(quesNum.size() +"(");
        for(int i=0;i<quesNum.size();i++){//��������
        //for(int i=0;i<quesNum.size()+1;i++){//���Դ��븲����
            System.out.print(">");
            if (i<quesNum.size()-1)
                p.print(" " + quesNum.get(i) + "��");
            else
                p.print(" " + quesNum.get(i));
        }
        p.println(" )\n");
    }

    /**
     * ��ȡ��Ӧ�ļ��� ��ȷ�� �� �����
     * @param path Ϊ �ļ� ·��
     */
    private ArrayList<String> obtainAnswer(String path) throws IOException {
        ArrayList<String> answerList = new ArrayList<>();
        BufferedReader answerFile = new BufferedReader(new FileReader(path));
        String answerLine = null;

        while ((answerLine = answerFile.readLine()) != null) {
            answerLine = answerLine.replace(" ", "");
            //int index = answerLine.indexOf('=') > answerLine.indexOf('.') ? answerLine.indexOf('=') : answerLine.indexOf('.');
            if (answerLine.indexOf('.') >= 0) {//index >= 0
                if (answerLine.length() > 2)
                    answerList.add(answerLine);
            }
        }
        return answerList;
    }

}
