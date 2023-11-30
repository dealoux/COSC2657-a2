package ducle.greenapp.restful;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {
    public static String getRequest(String urlStr){
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            String line="";
            while ((line = reader.readLine())!=null){
                builder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();

    }

    public static String postRequest(String urlStr, String name){
        String status="";
        try {
            //Step 1 - prepare the connection
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Step 2 - prepare the JSON object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            //Step 3 - Writing data to webservice
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonObject.toString());
            os.flush();
            os.close();
            status = conn.getResponseCode() + ": " + conn.getResponseMessage();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return status;
    }
}
