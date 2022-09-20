import org.junit.Test;

public class MySimHashTest {
    @Test
    public void MySimHashTest(){
        System.out.println();
        MySimHash hashTest1 = new MySimHash("芭芭拉:你了不起，你清高，你现在想改我了。好，真好，你了不起啊！" +
                "你当初设计的这个水环，你有没有想过帮我优化一下啊？我跟你讲，我现在不想改了！你知不知道我之前受什么样" +
                "的欺负？你知不知道我受什么样的欺负？就因为我帮角色上水，我控自己，我内鬼！这什么破水环破水环破水环啊？！\n" +
                "我就是要一步一步一步、一步一步一步地追到最高。我要做芭天后！我不要再让人家欺负我。我受不了了，我不要让别人" +
                "欺负我，我要做芭天后！我要做一个，做一个最高的芭天后，3.0绽放大C芭天后！", 64);
        MySimHash hashTest2 = new MySimHash("刻晴:你了不起，你清高，你现在还坚持用我大世界。好，真好，你真爱我！" +
                "你开服抽出我到现在，你有没有想过别人怎么看你的！我跟你讲，我忍不下去！你知不知道我心里有多么难受？" +
                "你知不知道我受什么样的欺负？就因为我雷系刮痧，我打若陀，我打不动！这什么破属性破属性破属性啊？！\n" +
                "我就是要一步一步一步、一步一步一步地追到最高。我要去须弥！我不要再让人家欺负我。我受不了了，我不要让别人" +
                "笑话你，我要做璃月雷神！我要做一个，做一个没人看不起，3.0激化大C璃月雷神！", 64);
        System.out.println(hashTest1.hammingDistance(hashTest2) );
        System.out.println(hashTest1.getSemblance(hashTest2));
    }
}
