/**
 * Response getter from a given url
 * @author Reda TARGAOUI
 */

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    /**
     * Get the response from url
     * @param url url
     * @return HttpURLConnection
     */
    public static HttpURLConnection getResponseFromURL(String url) {
        try {
            // Create new connection :
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            // Set GET as a request method :
            connection.setRequestMethod("GET");

            // Connect to API :
            connection.connect();

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
