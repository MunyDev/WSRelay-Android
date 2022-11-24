package org.muny.wsrelay;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ProxyConfig {
    private static Map<String, String> config = new HashMap<String, String>();
    private static final File rootDirectory = MainActivity.instance.getFilesDir();
    static {
        loadConfig();
    }
    @SuppressWarnings("unchecked")
    public static void loadConfig() {
        File propertiesFile = Paths.get(rootDirectory.getAbsolutePath(), "relayProperties.json").toFile();
//        propertiesFile.createNewFile();
        String fileContents = "";
        try {
            propertiesFile.createNewFile();

            FileInputStream fis = MainActivity.instance.openFileInput("relayProperties.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = br.readLine()) != null){
                stringBuilder.append(line).append('\n');

            }
            fileContents = stringBuilder.toString();
            System.out.println(fileContents);
            br.close();
            fis.close();
//            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config = (Map<String, String>) (new Gson().fromJson(fileContents, Map.class));
        if (config == null) {
            config = new HashMap<>();
        }
    }
    public static int getMaxClients() {
        return Integer.parseInt(config.getOrDefault("maxClients", "0"));
    }
    public static void setMaxClients(int maxClients){
        config.put("maxClients", String.valueOf(maxClients));
    }
    public static void set(String str, String value){
        config.put(str, value);
    }
    public static String get(String key) {
        return config.getOrDefault(key, "");
    }
    public static void writeConfig() {
//        File propertiesFile = Paths.get(rootDirectory.getAbsolutePath(), "relayProperties.json").toFile();

        String toWrite = new Gson().toJson(config);
        System.out.println(toWrite);
        try {
            FileOutputStream fos = MainActivity.instance.openFileOutput("relayProperties.json", Context.MODE_PRIVATE);
            fos.write(toWrite.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
