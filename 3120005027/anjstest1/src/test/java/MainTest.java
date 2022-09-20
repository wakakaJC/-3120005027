import org.junit.Test;

public class MainTest {

    @Test
    public void test1Method() {

        String text0 = TxtIOUtils.readTxt("orig\\orig.txt");
        String text1 = TxtIOUtils.readTxt("plagiarize\\orig_0.8_add.txt");

        MySimHash hash0 = new MySimHash(text0, 64);
        MySimHash hash1 = new MySimHash(text1, 64);

        double similarity = hash0.getSemblance(hash1);
        TxtIOUtils.writeTxt(similarity, "ans\\ans.txt");
    }

    @Test
    public void test2Method(){

        String text0 = TxtIOUtils.readTxt("orig\\orig.txt");
        String text2 = TxtIOUtils.readTxt("plagiarize\\orig_0.8_del.txt");

        MySimHash hash0 = new MySimHash(text0, 64);
        MySimHash hash2 = new MySimHash(text2, 64);

        double similarity = hash0.getSemblance(hash2);
        TxtIOUtils.writeTxt(similarity, "ans\\ans.txt");
    }

    @Test
    public void test3Method(){

        String text0 = TxtIOUtils.readTxt("orig\\orig.txt");
        String text3 = TxtIOUtils.readTxt("plagiarize\\orig_0.8_dis_1.txt");

        MySimHash hash0 = new MySimHash(text0, 64);
        MySimHash hash3 = new MySimHash(text3, 64);

        double similarity = hash0.getSemblance(hash3);
        TxtIOUtils.writeTxt(similarity, "ans\\ans.txt");
    }

    @Test
    public void test4Method(){

        String text0 = TxtIOUtils.readTxt("orig\\orig.txt");
        String text4 = TxtIOUtils.readTxt("plagiarize\\orig_0.8_dis_10.txt");

        MySimHash hash0 = new MySimHash(text0, 64);
        MySimHash hash4 = new MySimHash(text4, 64);

        double similarity = hash0.getSemblance(hash4);
        TxtIOUtils.writeTxt(similarity, "ans\\ans.txt");
    }

   @Test
    public void test5Method(){

        String text0 = TxtIOUtils.readTxt("orig\\orig.txt");
        String text5 = TxtIOUtils.readTxt("orig_0.8_dis_15.txt");

        MySimHash hash0 = new MySimHash(text0, 64);
        MySimHash hash5 = new MySimHash(text5, 64);

        double similarity = hash0.getSemblance(hash5);
        TxtIOUtils.writeTxt(similarity, "ans\\ans.txt");
    }

}
