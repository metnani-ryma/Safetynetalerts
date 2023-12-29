package com.safetynet.alert.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.safetynet.alert.model.SafetyNet;

public final class JsonDataExtractor {

    private JsonDataExtractor() {}

    public static SafetyNet extractDataFromJson() {
        Gson gson = new Gson();
        SafetyNet safetyNet = null;

        try (
            // Utilisation de try-with-resources pour s'assurer que les ressources sont correctement fermées
            InputStreamReader inputStreamReader = new InputStreamReader(
                    new FileInputStream("src/main/resources/firestations.json"),
                    StandardCharsets.UTF_8
            );
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            // Lecture des données JSON et conversion en objet SafetyNet
            safetyNet = gson.fromJson(bufferedReader, SafetyNet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return safetyNet;
    }
}
