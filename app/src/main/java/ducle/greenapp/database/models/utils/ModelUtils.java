package ducle.greenapp.database.models.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ducle.greenapp.database.models.MyEntity;

public class ModelUtils {
    public static String prefixId(String id, String prefix){
        if(!id.startsWith(prefix)){
            return prefix + "_" + String.format("%04d", id);
        }
        return id;
    }

    /**
     * This function reads and loads the given file
     * */
    public static void readFile(ArrayList<String> lines, String path, String fileName){
        File directory = new File(path);
        File file = new File(directory, fileName);
        FileInputStream inputStream = null;
        try{
            lines.clear();
            inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null){
                lines.add(line);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This function saves the given text to the given file
     * */
    public static void saveFile(String text, String path, String fileName){
        try{
            File directory = new File(path);
            File file = new File(directory, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This function split and trim the given line
     * */
    public static String[] splitTrimLine(String line){
        String result[] = line.trim().split("\\s*;\\s*");
        for(String s : result){
            Log.d("splitTrimLine", s);
        }
        return result;
    }

//    /**
//     * This function converts a map of T key value pair to a list of T instances and returns it
//     * @param map map of T key value pair
//     * */
//    public static <T extends MyEntity> List<T> toList(Map<String, T> map){
//        List<T> result = new ArrayList<>(map.values());
//        Collections.sort(result);
//        return result;
//    }
}
