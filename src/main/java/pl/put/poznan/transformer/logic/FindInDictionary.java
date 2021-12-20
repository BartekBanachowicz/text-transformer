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

    private void load(){
        String fileName = "src/main/resources/dictionary.csv";
        Path pathToFile = Paths.get(fileName);
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

    public String findWordAndReplace(String shortcut){
        String result = shortcut;
        if(dictionary.containsKey(shortcut)){
            result = dictionary.get(shortcut);
        }
        return result;
    }

    public String findShortcutAndReplace(String word){
        String result = word;
        if(dictionary.inverse().containsKey(word)){
            result = dictionary.inverse().get(word);
        }
        return result;
    }

    public String getWord(String shortcut){
        return dictionary.get(shortcut);
    }

    public String getShortcut(String word){
        return dictionary.inverse().get(word);
    }

    public String[] getListOfShortcuts(){
        return dictionary.keySet().toArray(new String[0]);
    }

    public String[] getListOfWords(){
        return dictionary.inverse().keySet().toArray(new String[0]);
    }

    public FindInDictionary(){
        this.load();
    }

}
