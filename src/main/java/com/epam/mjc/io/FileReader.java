package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {
//    String directory = System.getProperty("user.home");
//    String filename = "Profile.txt";
//    String absolutePath = directory + File.separator + filename;

    public Profile getDataFromFile(File file) {
        String fileData = readFile(file);

        String name = extractValueForKey("Name", fileData);
        int age = Integer.parseInt(extractValueForKey("Age", fileData));
        String email = extractValueForKey("Email", fileData);
        Long phone = Long.valueOf(extractValueForKey("Phone", fileData));

        return new Profile(name, age, email, phone);
    }



    public String readFile(File file){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            int ch;
            while ((ch = fileInputStream.read()) != -1){
                stringBuilder.append((char) ch);
            }
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractValueForKey(String key, String fileData) {
        String keyPattern = key + ":\\s*(.*)";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(keyPattern);
        java.util.regex.Matcher matcher = pattern.matcher(fileData);

        if (matcher.find()) {
            System.out.println(matcher.group(1).trim());
            return matcher.group(1).trim();
        } else {
            return null; // Handle the case when key is not found
        }
    }
}
