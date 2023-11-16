/**
 * Get weather data for a given location name
 * @author Reda TARGAOUI
 */


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WeatherData {
    // Attributes :
    private double temperature;
    private long weatherCode;
    private long humidity;
    private double windSpeed;

    /**
     * Get weather data for a location
     * @param locationName location name
     */
    public WeatherData(String locationName) {
        // Get location latitude and longitude :
        Location location = new Location(locationName);

        // Set url to get weather data :
        String url = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude() +
                "&hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&timezone=America%2FLos_Angeles";

        try {
            // Call api and get response :
            HttpURLConnection connection = HttpConnection.getResponseFromURL(url);

            // Verify response code :
            // 200 means the connection was successful
            if (connection.getResponseCode() == 200) {
                // To store results :
                StringBuilder result = new StringBuilder();
                Scanner scanner = new Scanner(connection.getInputStream());

                // To read and store connection results into result StringBuilder :
                while (scanner.hasNext()) {
                    result.append(scanner.nextLine());
                }

                // Close scanner and connection :
                scanner.close();
                connection.disconnect();

                // Convert result string into JSON obj :
                JSONParser jsonParser = new JSONParser();
                JSONObject resultJsonObject = (JSONObject) jsonParser.parse(String.valueOf(result));

                // Get hourly data
                JSONObject hourlyData = (JSONObject) resultJsonObject.get("hourly");

                // We want to get the current time's data, so we have to get the index of the current time in time array:
                JSONArray currentTime = (JSONArray) hourlyData.get("time");
                int index = findCurrentTimeIndex(currentTime);

                // Get the temperature :
                JSONArray temperatureData = (JSONArray) hourlyData.get("temperature_2m");
                this.temperature = (double) temperatureData.get(index);

                // Get weather code :
                JSONArray weatherCodeData = (JSONArray) hourlyData.get("weathercode");
                this.weatherCode = (long) weatherCodeData.get(index);

                // Get humidity :
                JSONArray humidityData = (JSONArray) hourlyData.get("relativehumidity_2m");
                this.humidity = (long) humidityData.get(index);

                // Get wind speed :
                JSONArray windSpeedData = (JSONArray) hourlyData.get("windspeed_10m");
                this.windSpeed = (double) windSpeedData.get(index);
            }
            else {
                System.out.println("Error while connecting to API!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get our current time from time list
     * @param timeList time list
     * @return our current time index in time list
     */
    private int findCurrentTimeIndex(JSONArray timeList) {
        // Get current date and time :
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Convert format to API format :
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'"));

        // Iterate the list until we find current time :
        for (int i = 0; i < timeList.size(); i++) {
            if (((String) timeList.get(i)).equalsIgnoreCase(formattedDateTime)) {
                return i;
            }
        }

        return 0;
    }

    /**
     * Get temperature
     * @return temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Get weather code
     * @return weather code
     */
    public long getWeatherCode() {
        return weatherCode;
    }

    /**
     * Get humidity
     * @return humidity
     */
    public long getHumidity() {
        return humidity;
    }

    /**
     * Get wind speed
     * @return wind speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }
}
