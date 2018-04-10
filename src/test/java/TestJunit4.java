import org.junit.*;

/**
 *
 */
public class TestJunit4 {

    @Test
    public void test1(){
        //TODO 使用LOG进行输出
        System.out.println("TestJunit4-test1-begin");
        try {
            int i = 1/0;
        } catch (ArithmeticException e) {
            //is non-null, the test will halt and be ignored. 测试用将被停止并忽略
            //Tests run: 1, Failures: 0, Errors: 0, Skipped: 1
            Assume.assumeNoException(e);
        }
        System.out.println("TestJunit4-test1-end");

    }

    @Test
    public void test2(){
        System.out.println("TestJunit4-test2-begin");
        //Assert断言,条件不满足,throw Exception.
        //此处会在Maven中无法通过
        //Assert.assertNotNull(null);
    }

    @Ignore
    @Test
    public void test3(){
        System.out.println("TestJunit4-test3-begin");
    }


    @Before
    public void before1(){
        System.out.println("TestJunit4-before1");
    }

    @Before
    public void before2(){
        System.out.println("TestJunit4-before2");
    }

    @Before
    public void before3(){
        System.out.println("TestJunit4-before3");
    }

    @After
    public void after1(){
        System.out.println("TestJunit4-after1");
    }

}
