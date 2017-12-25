import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nitendra.thakur on 10/17/17.
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello");
        //JSONObject map = new JSONObject();
        Map map = new HashMap();
        map.put("0", Integer.MAX_VALUE + 10);
        map.put("0L", Integer.MAX_VALUE + 10);
        System.out.println("0 is Integer ? " + (map.get("0") instanceof Integer));
        System.out.println("0 is Long ? " + (map.get("0") instanceof Long));
        System.out.println("0L is Integer ? " + (map.get("0L") instanceof Integer));
        System.out.println("0L is Long ? " + (map.get("0L") instanceof Long));
    }
}
