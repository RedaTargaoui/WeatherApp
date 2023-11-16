/**
 * Create app GUI
 * @author Reda TARGAOUI
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    // Attributes :

    /**
     * Create GUI
     */
    public GUI() {
        // create GUI and set a title :
        super("Weather App");

        // To configure GUI to stop the program's process and the end :
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the size :
        setSize(450, 650);

        // Display at the center of the screen :
        setLocationRelativeTo(null);

        setLayout(null);
        setResizable(false);

        setComponents();
    }

    /**
     * Set GUI components
     */
    private void setComponents() {
        // Search field :
        JTextField searchField = new JTextField();

        // Set location :
        searchField.setBounds(15, 15, 351, 45);

        // Set style and size :
        searchField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchField);

        // Weather image :
        JLabel weatherConditionImage = new JLabel(getImage("./assets/cloudy.png"));
        weatherConditionImage.setBounds(0, 125, 450, 217);
        add(weatherConditionImage);

        // Temperature label :
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
        // To position the text at the center :
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // Weather description label :
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // Humidity image :
        JLabel humidityImage = new JLabel(getImage("./assets/humidity.png"));
        humidityImage.setBounds(15, 500, 74, 66);
        add(humidityImage);

        // Humidity label :
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        // Wind speed image :
        JLabel windSpeedImage = new JLabel(getImage("./assets/windspeed.png"));
        windSpeedImage.setBounds(220, 500, 74, 66);
        add(windSpeedImage);

        // Wind speed label :
        JLabel windSpeedText = new JLabel("<html><b>Wind speed</b> 15km/h</html>");
        windSpeedText.setBounds(310, 500, 90, 55);
        windSpeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windSpeedText);

        // Search Button :
        JButton searchButton = new JButton(getImage("./assets/search.png"));

        // Change to hand cursor :
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);

        // Add action listener to button :
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input :
                String userInput = searchField.getText();

                // Verify input :
                if (!userInput.replaceAll("\\s", "").isEmpty()) {
                    // Get weather data :
                    WeatherData weatherData = new WeatherData(userInput);

                    // Set temperature :
                    temperatureText.setText(weatherData.getTemperature() + " C");

                    // Set humidity :
                    humidityText.setText("<html><b>Humidity</b> " + weatherData.getHumidity() + "%</html>");

                    // Set wind speed :
                    windSpeedText.setText("<html><b>Windspeed</b> " + weatherData.getWindSpeed() + "km/h</html>");

                    // Get weather code :
                    long weatherCode = weatherData.getWeatherCode();

                    // Change weather image based on weather code :
                    if (weatherCode == 0L) {// Clear weather
                        weatherConditionDesc.setText("Clear");
                        weatherConditionImage.setIcon(getImage("./assets/clear.png"));
                        add(weatherConditionImage);
                    }
                    else if (weatherCode > 0L && weatherCode < 3L) {// Cloudy weather
                        weatherConditionDesc.setText("Cloudy");
                        weatherConditionImage.setIcon(getImage("./assets/cloudy.png"));
                        add(weatherConditionImage);
                    }
                    else if ((weatherCode >= 51L && weatherCode <= 67L) || (weatherCode >= 80L && weatherCode <= 99L)) {// Rainy weather
                        weatherConditionDesc.setText("Rainy");
                        weatherConditionImage.setIcon(getImage("./assets/rain.png"));
                        add(weatherConditionImage);
                    }
                    else if (weatherCode >= 71L && weatherCode <= 77L) {// Snowy weather
                        weatherConditionDesc.setText("Snowy");
                        weatherConditionImage.setIcon(getImage("./assets/snow.png"));
                        add(weatherConditionImage);
                    }
                }
            }
        });

        add(searchButton);
    }

    /**
     * Get image
     * @param pathToImage path to image
     * @return ImageIcon if image exist, null otherwise
     */
    private ImageIcon getImage(String pathToImage) {
        try{
            // Read image file :
            BufferedImage image = ImageIO.read(new File(pathToImage));

            // Return image as imageIcon so it can be rendered :
            return new ImageIcon(image);
        } catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Image not found!!");
        return null;
    }
}
