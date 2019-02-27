
import java.util.*;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.simple.JSONObject;
public class Program {

    public static void main(String[] args) throws Exception
    {
        APIClient client = new APIClient("https://bgch.testrail.net/");
        client.setUser("");
        client.setPassword("");

        JSONObject c = (JSONObject) client.sendGet("get_case/442021");
        System.out.println(c);
        System.out.println(c.get("title"));
        //Gson gson = new Gson();
        Map<String,Object> testcaseDetails =new HashMap<String,Object>();
        Iterator<String> keysItr = c.keySet().iterator();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = c.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            testcaseDetails.put(key, value);
        }
        Map data = new HashMap();
        data.put("status_id", new Integer(1));
        data.put("comment", "This test worked fine!");
        JSONObject r = (JSONObject) client.sendPost("add_result/442021", data);

    }
}
