package fourcalculations;

import java.util.Random;

public class Create {
	 /**
     * ʽ��������
     * totalOperator Ϊ ��ǰʽ�� �� ����� ����
     * formula Ϊ ��ǰʽ�� �� �ַ�����ʽ
     * totalFraction Ϊ ��ǰʽ�� �� ������ ����
     * @param r Ϊ ������ �� ��Χ
     * @return ansFormula Ϊ ��ǰʽ�� �� ����������׺���ʽ && ��� && �ַ�����ʽ �� ����
     */
    public String[] createFormula(int r){
        Random random = new Random();
        String[] operator = {"��","��","��","��","��"};

        //����� && ������ && ʽ��
        String[] totalOperator = new String[1 + random.nextInt(3)];
        String[] totalFraction = new String[totalOperator.length+1];
        String formula = new String();
        //�Ƿ��������
        Boolean hasFraction = false;

        //������� ��������
        for (int i=0;i<totalFraction.length;i++) {

            // ���ȷ������ ���� �� ����
            int fractionOrNot = random.nextInt(2);
            //����
            if (fractionOrNot == 0) { //��������
                int integralPart = random.nextInt(r+1);
                totalFraction[i] = String.valueOf(integralPart);
            } else { //���ɷ���
                int denominator = 1+random.nextInt(r);
                int molecule = random.nextInt(denominator);
                int integralPart = random.nextInt(r+1);

                //�������
                if (molecule!=0) {
                    int commonFactor = commonFactor(denominator, molecule);
                    denominator /= commonFactor;
                    molecule /= commonFactor;
                }

                //���������
                if (integralPart == 0 && molecule > 0) {
                    totalFraction[i] = molecule + "/" + denominator;
                    hasFraction = true;
                }
                else if (molecule == 0)
                    totalFraction[i] = String.valueOf(integralPart);
                else {
                    totalFraction[i] = integralPart + "'" + molecule + "/" + denominator;
                    hasFraction = true;
                }
            }
        }

        //������� �������
        for (int i=0;i < totalOperator.length;i++) {
            if (hasFraction)
                totalOperator[i] = operator[random.nextInt(2)];
            else
                totalOperator[i] = operator[random.nextInt(4)];
        }

        //���ѡ��ʽ��������ʼλ�ã���ʽ������ a+b= ʱ����������
        int choose = totalFraction.length;
        if (totalFraction.length != 2 )
            choose = random.nextInt(totalFraction.length);

        //�ϳ�ʽ�� formula
        for (int i=0;i<totalFraction.length;i++) {
            if (i == choose && choose<totalOperator.length) {
                formula = formula + "(" + totalFraction[i] + totalOperator[i] ;
            } else if (i == totalFraction.length - 1 && i == choose+1 && choose<totalOperator.length) {
                formula = formula + totalFraction[i] + ")" + "=";
            } else if (i == choose+1 && choose<totalOperator.length) {
                formula = formula + totalFraction[i] + ")" + totalOperator[i];
            } else if (i == totalFraction.length - 1) {
                formula = formula + totalFraction[i] + "=";
            } else {
                formula = formula + totalFraction[i] + totalOperator[i];
            }
        }

        //���������
        CheckAnswer checkAns = new CheckAnswer();
        String[] ansFormula = checkAns.checkout(formula,3*totalOperator.length+2+1);//2*totalOperator.length+3+1

        //System.out.println("ansFormula��"+Arrays.toString(ansFormula));
        if (ansFormula!=null)
            return ansFormula;
        return null;
    }

    /**
     * ������������Ի������
     * @param x Ϊ ������ �� ��ĸ
     * @param y Ϊ ������ �� ����
     * @return y Ϊ �������
     */
    public int commonFactor(int x,int y) {
        while(true)
        {
            if(x%y==0)return y;
            int temp=y;
            y=x%y;
            x=temp;
        }
    }
}
