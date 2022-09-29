package fourcalculations;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckRepeat {
	private ArrayList<String> returnList = new ArrayList<>();
    private ArrayList<String> txtList = new ArrayList<>();
    private ArrayList<String> ansList = new ArrayList<>();
    private ArrayList<String[]> ansFoList = new ArrayList<>();

    /**
     * �����ݴ��⼯���𰸼�
     * @param n Ϊ ��Ҫ��ʽ������
     * @param r Ϊ ʽ���в������ķ�Χ
     * @return returnList Ϊ �⼯&�𰸼�
     */
    public ArrayList generate(int n,int r) {
        Create create = new Create();
        //����n�����ظ���ʽ��
        for(int i=0;i<n;){
            //�������ʽ��
            String[] ansFormula = create.createFormula(r);
            //�ж����ɵ�ʽ���Ƿ��ظ�-n 10000 -r 1
            if (ansFormula!=null) {
                //System.out.println("generFormula:"+ansFormula[ansFormula.length-1]);
                if (!ifRepeat(ansFormula)) {
                    System.out.println((i+1)+":"+ansFormula[ansFormula.length-1]);
                    i++;
                }
            }
        }

        //��ʽ�Ӽ���������ӵ�returnList
        for (int i =0; i<2*n;i++) {
            if(i<n) {
                returnList.add(txtList.get(i));
            } else {
                returnList.add(ansList.get(i - n));
            }
        }
        return returnList;
    }

    /**
     * �ж�ʽ���Ƿ��ظ�
     * @param ansFormula Ϊ ��׺���ʽ����������ʽ�� �� ����
     * @return ifRepeat ��ʾ��ǰʽ���Ƿ��ظ�
     */
    private boolean ifRepeat(String[] ansFormula) {
        String formula = ansFormula[ansFormula.length-1];
        String[] rPNotation = new String[ansFormula.length-1];
        System.arraycopy(ansFormula, 0, rPNotation, 0, ansFormula.length-1);
        boolean ifRepeat = false;

        for (String[] ansFo: ansFoList) {
            if (Arrays.equals(ansFo,rPNotation)) { //ֱ��һһ��Ӧ�Ƚ�
                ifRepeat = true;
                //System.out.println("ansFo==rPNotation:"+Arrays.toString(ansFo)+"=="+Arrays.toString(rPNotation));
            } else if (ansFo.length == rPNotation.length && ansFo[ansFo.length-1].equals(rPNotation[rPNotation.length-1])){//��������������һ�£���ʽ�ӿ����ظ�����һ���Ƚ�
                int j=0;
                for (j=0;j<rPNotation.length-2;) {
                    //System.out.println(Arrays.toString(ansFo)+":"+Arrays.toString(rPNotation));
                    boolean opRight = ansFo[j+2].equals("��")||ansFo[j+2].equals("��");
                    boolean exRight = ansFo[j].equals(rPNotation[j + 1]) && ansFo[j + 1].equals(rPNotation[j]) && ansFo[j + 2].equals(rPNotation[j + 2]);
                    boolean copRight = ansFo[j].equals(rPNotation[j]) && ansFo[j + 1].equals(rPNotation[j + 1]) && ansFo[j + 2].equals(rPNotation[j + 2]);
                    //�����ǰ�����������������Ƚ�
                    if (exRight&&opRight) {
                        j = j + 3;
                    } else if (copRight) {
                        j = j + 3;
                    } else {
                        //System.out.println(formula+":"+opRight+","+exRight+","+"false ;j:"+j+"<---->ifRepeat:false");
                        break;
                    }
                    //System.out.print(formula+":"+opRight+","+exRight+","+copRight+"  ;");
                }
                if (j == rPNotation.length-2) {
                    ifRepeat = true;
                    break;
                }
                //System.out.println("j:"+j+",rPNotation.length-5:"+(rPNotation.length-5)+"<---->ifRepeat:" + ifRepeat);
            }
        }

        if (!ifRepeat) {
            this.txtList.add(formula);
            this.ansList.add(rPNotation[rPNotation.length-1]);
            this.ansFoList.add(rPNotation);
        }
        return ifRepeat;
    }

}
