/**
 * Get data about a given location name
 * @author Reda TARGAOUI
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class Location {
    // Attributes :
    private double latitude;
    private double longitude;

    /**
     * Get latitude and longitude of the given location name
     * @param locationName location name
     */
    public Location(String locationName) {
        // Replace all the spaces in locationName with '+' :
        String editedLocationName = locationName.replaceAll(" ", "+");

        // Set the url by adding the edited locationName :
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + editedLocationName + "&count=10&language=en&format=json";

        try {
            // Call API and get result :
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

                // Get the results from the generated API response :
                JSONArray locationData = (JSONArray) resultJsonObject.get("results");

                // Set attributes :
                JSONObject location = (JSONObject) locationData.get(0);
                this.latitude = (double) location.get("latitude");
                this.longitude = (double) location.get("longitude");
            }
            else {
                System.out.println("Error while connecting to API!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }
}
