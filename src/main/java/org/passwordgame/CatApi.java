package org.passwordgame;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;


public class CatApi {
    private static final String CAT_API_URL = "https://api.thecatapi.com/v1/images/search";

    public void loadImage() {
        try {
            URL url = new URL(CAT_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            JSONArray jsonArray = new JSONArray(content.toString());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String imageUrl = jsonObject.getString("url");

            URL imageDownloadUrl = new URL(imageUrl);
            HttpURLConnection imageConnection = (HttpURLConnection) imageDownloadUrl.openConnection();
            imageConnection.setRequestMethod("GET");
            InputStream imageInputStream = imageConnection.getInputStream();


            File outputFile = new File("cat_image.jpg");
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = imageInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            imageInputStream.close();
            imageConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

