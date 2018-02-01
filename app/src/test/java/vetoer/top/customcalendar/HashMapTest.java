package vetoer.top.customcalendar;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tg on 18-2-1.
 */

public class HashMapTest {
    @Test
    public void testmap(){
        Map<Integer,Integer> map = new HashMap();
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map.put(4,5);
        map.put(5,6);
        map.put(6,7);
        int value = map.get(2);
        System.out.println("value is:"+value);
        int value2 = map.get(7);
        System.out.println("value is:"+value2);
    }
}
