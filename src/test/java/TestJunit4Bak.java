import org.junit.*;

/**
 *
 */
public class TestJunit4Bak {

    @Ignore("暂时疲敝这个TestJunit2...Ignore只作用与@Test标签")
    @Test
    public void test1(){
        System.out.println("TestJunit4Bak-test1");
    }


    @After
    public void after1(){
        System.out.println("TestJunit4Bak-after1");
    }

    @After
    public void after2(){
        System.out.println("TestJunit4Bak-after2");
    }


    @Before
    public void before1(){
        System.out.println("TestJunit4Bak-before1");
    }
}




