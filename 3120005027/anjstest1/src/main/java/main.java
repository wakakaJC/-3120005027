import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String [] txtPath = new String[10];

        txtPath[0] = sc.next();
        txtPath[1] = sc.next();

        String txt1 = TxtIOUtils.readTxt(txtPath[0]);//orig\orig.txt
        String txt2 = TxtIOUtils.readTxt(txtPath[0]);//plagiarize\orig_0.8_add.txt

        MySimHash hash1 = new MySimHash(txt1, 64);
        MySimHash hash2 = new MySimHash(txt2, 64);

        double similarity1 = hash1.getSemblance(hash2);
        TxtIOUtils.writeTxt(similarity1, "ans\\ans.txt");




    }
}
