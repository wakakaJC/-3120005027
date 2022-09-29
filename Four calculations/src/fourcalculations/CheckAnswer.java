package fourcalculations;

import java.util.HashMap;
import java.util.Stack;

public class CheckAnswer {
	  /**
     * ���ʽ�ӽ���������沨�����ʽ
     * @param formula Ϊ ʽ��
     * @return reversePolishNotation Ϊ ��ǰʽ�� �� ����������׺���ʽ && ��� && �ַ�����ʽ �� ����
     */
    public String[] checkout(String formula,int length){
        // ������ && ������ && �沨�����ʽ
        Stack<String> stackNumber = new Stack<>();
        Stack<String> stackOperator = new Stack<>();
        String[] reversePolishNotation = new String[length];
        // ��ϣ�� �����������ȼ�
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("(", 0);
        hashmap.put("��", 1);
        hashmap.put("��", 1);
        hashmap.put("��", 2);
        hashmap.put("��", 2);

        for (int i=0,j=0; i < formula.length();) {
            //StringBuffer���еķ�����Ҫƫ���ڶ����ַ����ı仯������׷�ӡ������ɾ���ȣ����Ҳ��StringBuffer��String�����Ҫ����
            StringBuilder digit = new StringBuilder();
            //�� ʽ�� �и�Ϊ c�ַ�
            char c = formula.charAt(i);
            //�� c�ַ� Ϊ10��������,�� c�ַ� ����digit�����Խ���λ��һ�𴢴�Ϊһ������
            while (Character.isDigit(c)||c=='/'||c=='\'') {
                digit.append(c);
                i++;
                c = formula.charAt(i);
            }

            if (digit.length() == 0){ //��ǰdigit�����Ѿ������֣�����ǰ�������
                switch (c) {
                    //����ǡ�(��ת��Ϊ�ַ���ѹ���ַ�ջ
                    case '(': {
                        stackOperator.push(String.valueOf(c));
                        break;
                    }
                    //������)���ˣ�����м��㣬��Ϊ��(�������ȼ����
                    case ')': {
                        //�� stackOperator ջ��Ԫ��ȡ�� operator
                        String operator = stackOperator.pop();
                        //��ǰ����ջ���滹�� �� �� �� ��ʱ��ȡ ������ �� ����
                        while (!stackOperator.isEmpty() && !operator.equals("(")) {
                            //ȡ������a,b
                            String a = stackNumber.pop();
                            String b = stackNumber.pop();
                            //��׺���ʽ����
                            reversePolishNotation[j++] = a;
                            reversePolishNotation[j++] = b;
                            reversePolishNotation[j++] = operator;
                            //����
                            String ansString = calculate(b, a, operator);
                            //��� ��� ������ Ҫ�� �� return -1����ʽ�Ӳ���������
                            if(ansString == null)
                                return  null;
                            //�����ѹ��ջ
                            stackNumber.push(ansString);
                            //����ָ����һ���������
                            operator = stackOperator.pop();
                        }
                        break;
                    }
                    //�����ˡ�=������������ս��
                    case '=': {
                        String operator;
                        //��ǰ����ջ���滹�� �� �� �� ��ʱ������û�����꣬ȡ ������ �� ����
                        while (!stackOperator.isEmpty()) {
                            //ȡֵ && ȡ������
                            operator = stackOperator.pop();
                            String a = stackNumber.pop();
                            String b = stackNumber.pop();
                            //��׺���ʽ����
                            reversePolishNotation[j++] = a;
                            reversePolishNotation[j++] = b;
                            reversePolishNotation[j++] = operator;
                            //����
                            String ansString = calculate(b, a, operator);
                            if(ansString == null)
                                return null;
                            stackNumber.push(ansString);
                        }
                        break;
                    }
                    //������֮ǰ���κ����
                    default: {
                        String operator;
                        //��ǰ����ջ���滹�� �� �� �� ��ʱ��ȡ ������ �� ����
                        while (!stackOperator.isEmpty()) {
                            //��ǰ����ջ��ջ��Ԫ��
                            operator = stackOperator.pop();
                            if (hashmap.get(operator) >= hashmap.get(String.valueOf(c))) { //�Ƚ����ȼ�
                                //ȡֵ
                                String a = stackNumber.pop();
                                String b = stackNumber.pop();
                                //��׺���ʽ����
                                reversePolishNotation[j++] = a;
                                reversePolishNotation[j++] = b;
                                reversePolishNotation[j++] = operator;
                                //����
                                String ansString =calculate(b, a, operator);
                                if(ansString == null)
                                    return  null;
                                stackNumber.push(ansString);
                            }
                            else {
                                stackOperator.push(operator);
                                break;
                            }

                        }
                        stackOperator.push(String.valueOf(c));  //������ѹ�����ջ
                        break;
                    }
                }
            }
            //�������֣�ֱ��ѹջ
            else {
                stackNumber.push(digit.toString());
                //reversePolishNotation[j++] = digit.toString();
                continue; //��������ѭ�����ص�for��������һ��ѭ��������ִ��i++(��Ϊ��ʱi�Ѿ�ָ�������)
            }
            i++;
        }
        //��ȡ ջ������ �� ��ʽ�����մ�
        reversePolishNotation[length-3] = "=";
        reversePolishNotation[length-2] = stackNumber.peek();
        reversePolishNotation[length-1] = formula;
        return reversePolishNotation;
    }

    /**
     * ����ʽ��������
     * @param m Ϊ ������
     * @param n Ϊ ������
     * @param operator Ϊ �����
     * @return ansFormula Ϊ ��ǰʽ�� �� ����������ansFormulaΪnull���򲻷�������
     */
    private String calculate(String m,String n,String operator) {
        String ansFormula = null;//������
        char op = operator.charAt(0);//����
        int[] indexFraction = {m.indexOf('\''), m.indexOf('/'), n.indexOf('\''), n.indexOf('/')};//���� ������ �и�λ��

        //���� ������ �� ����
        if (indexFraction[1] > 0 || indexFraction[3] > 0) {
            int[] denominator = new int[3];
            int[] molecule = new int[3];
            int[] integralPart = new int[3];

            //�и����
            if (indexFraction[1] > 0) {
                for (int i = 0; i < m.length(); i++) {
                    if (i < indexFraction[0]) {
                        integralPart[0] = Integer.parseInt(integralPart[0] + String.valueOf(m.charAt(i) - '0'));
                    } else if (i > indexFraction[0] && i < indexFraction[1]) {
                        molecule[0] = Integer.parseInt(molecule[0] + String.valueOf(m.charAt(i) - '0'));
                    } else if (i > indexFraction[1]) {
                        denominator[0] = Integer.parseInt(denominator[0] + String.valueOf(m.charAt(i) - '0'));
                    }
                }
            } else {
                integralPart[0] = Integer.parseInt(m);
                denominator[0] = 1;
                molecule[0] = 0;
            }

            if (indexFraction[3] > 0) {
                for (int i = 0; i < n.length(); i++) {
                    if (i < indexFraction[2]) {
                        integralPart[1] = Integer.parseInt(integralPart[1] + String.valueOf(n.charAt(i) - '0'));
                    } else if (i > indexFraction[2] && i < indexFraction[3]) {
                        molecule[1] = Integer.parseInt(molecule[1] + String.valueOf(n.charAt(i) - '0'));
                    } else if (i > indexFraction[3]) {
                        denominator[1] = denominator[1] + n.charAt(i) - '0';
                    }
                }
            } else {
                integralPart[1] = Integer.parseInt(n);
                denominator[1] = 1;
                molecule[1] = 0;
            }

            //��������
            switch (op) {
                case '��': {
                    denominator[2] = denominator[0] * denominator[1];
                    molecule[2] = integralPart[0] * denominator[2] + molecule[0] * denominator[1]
                            + integralPart[1] * denominator[2] + molecule[1] * denominator[0];
                    break;
        }
                case '��': {
                    denominator[2] = denominator[0] * denominator[1];
                    molecule[2] = integralPart[0] * denominator[2] + molecule[0] * denominator[1]
                            - integralPart[1] * denominator[2] - molecule[1] * denominator[0];
                    break;
                }
                default:
                    return null;
            }

            //��ȡ��������
            if (molecule[2] >= denominator[2] && molecule[2]>0) {
                integralPart[2] = molecule[2] / denominator[2];
                molecule[2] = Math.abs(molecule[2] % denominator[2]);
            } else if (molecule[2]<0) {
                return null;
            }

            //�������
            if (molecule[2] != 0) {
                ansFormula = greatFraction(integralPart[2],molecule[2],denominator[2]);
            } else ansFormula = String.valueOf(integralPart[2]);

        } else { //������������
            int a = Integer.parseInt(m);
            int b = Integer.parseInt(n);

            switch (op) {
                case '��': {
                    ansFormula = String.valueOf(a + b);
                    break;
                }
                case '��': {
                    if (a - b >= 0)
                        ansFormula = String.valueOf(a - b);
                    else
                        return null;
                    break;
                }
                case '��': {
                    ansFormula = String.valueOf(a * b);
                    break;
                }
                case '��': {
                    if (b == 0) {
                        return null;
                    } else if (a % b != 0) {
                        ansFormula = a % b + "/" + b;
                        if (a / b > 0) ansFormula = a / b + "'" + ansFormula;
                    } else
                        ansFormula = String.valueOf(a / b);
                    break;
                }
            }
        }
        return ansFormula;
    }

    /**
     * �������
     * @param integralPart Ϊ ��������������
     * @param molecule Ϊ �����ķ��Ӳ���
     * @param denominator Ϊ �����ķ�ĸ����
     * @return ansFormula Ϊ ��ǰʽ�� �� �����������ʽ
     */
    private String greatFraction (int integralPart,int molecule,int denominator) {
        String ansFormula;
        int commonFactor = 1;

        //�����Լ��
        Create create = new Create();
        commonFactor = create.commonFactor(denominator,molecule);

        //�������
        denominator /= commonFactor;
        molecule /= commonFactor;

        //������ ��ʾ
        if (integralPart == 0 && molecule > 0) {
            ansFormula = String.valueOf(molecule) + '/' + String.valueOf(denominator);
        } else if (molecule == 0)
            ansFormula = String.valueOf(integralPart);
        else {
            ansFormula = String.valueOf(integralPart) + "'" + String.valueOf(molecule) + '/' + String.valueOf(denominator);
        }

        //����������
        return ansFormula;
    }

}
