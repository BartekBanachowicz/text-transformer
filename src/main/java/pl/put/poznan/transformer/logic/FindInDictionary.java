package pl.put.poznan.transformer.logic;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FindInDictionary {
    private final BiMap <String, String> dictionary = HashBiMap.create();

    private void load(String dirPath){
        Path pathToFile = Paths.get(dirPath);
        String line;
        String[] elements;

        try(BufferedReader reader = Files.newBufferedReader(pathToFile)){
            line = reader.readLine();

            while(line != null){
                elements = line.split(",");
                dictionary.put(elements[0], elements[1]);
                line = reader.readLine();
            }

        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }


    public String findValueAndReplace(String key){
        String result = key;
        if(dictionary.containsKey(key)){
            result = dictionary.get(key);
        }
        return result;
    }

    public String findKeyAndReplace(String value){
        String result = value;
        if(dictionary.inverse().containsKey(value)){
            result = dictionary.inverse().get(value);
        }
        return result;
    }

    public boolean isKeyInDictionary(String key) {
        return dictionary.containsKey(key);
    }

    public String getValue(String key){
        return dictionary.get(key);
    }

    public String getKey(String value){
        return dictionary.inverse().get(value);
    }

    public String[] getListOfKeys(){
        return dictionary.keySet().toArray(new String[0]);
    }

    public String[] getListOfValues(){
        return dictionary.inverse().keySet().toArray(new String[0]);
    }

    public FindInDictionary(String dirPath){
        this.load(dirPath);
    }

}
