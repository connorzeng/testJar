import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scala.actors.threadpool.Arrays;

import java.util.List;

/**
 * 一般不使用suit组件和Parameterized组件
 */
@RunWith(Parameterized.class)
public class TestJunit4Two {

    private Integer a;
    private Integer b;
    private Integer c;

    public TestJunit4Two(Integer a, Integer b, Integer c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameterized.Parameters
    public static List<Integer[]> getParams(){

        Integer[][] b = {{1,3,4},{2,3,5}};
        return Arrays.asList(b);
    }


    @Test
    public void sum(){
        long a = this.a;
        long b = this.b;
        long c = this.c;
        Assert.assertEquals(c,a+b);
    }

}
