import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class CityMaxTemperature {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String cityName = sc.nextLine();

        String apiKey = "11bfdafe8c9bfabd5a33f6b713ae299f";

        try {
            String apiUrl =
                "https://api.openweathermap.org/data/2.5/weather?q="
                + cityName + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(apiUrl);

            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader =
                new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
                );

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject mainObject = jsonObject.getJSONObject("main");

            double maxTemp = mainObject.getDouble("temp_max");

            System.out.println("City: " + cityName);
            System.out.println("Maximum Temperature: " + maxTemp + " °C");

        } catch (Exception e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }

        sc.close();
    }
}
