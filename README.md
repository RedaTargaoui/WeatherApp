# Weather App in Java

## Overview
This is a simple weather app written in Java that provides weather data for a given location. It uses the `api.open-meteo.com` API to fetch weather information. The app includes several classes and makes use of the Swing library for creating the graphical user interface (GUI).

## Classes

### 1. WeatherData
- Purpose: Get weather data for a given location name.
- Usage: This class is responsible for fetching weather data from the API based on the provided location name.

### 2. Location
- Purpose: Get data about a given location name.
- Usage: This class provides information about a location and utilized by other parts of the application.

### 3. HttpConnection
- Purpose: Response getter from a given URL.
- Usage: This class handles the HTTP connection and retrieves the response from a specified URL, such as the API endpoint.

### 4. GUI
- Purpose: Create the app GUI.
- Usage: This class is responsible for creating and displaying the graphical user interface of the weather app. It interacts with other classes to fetch and display weather data.

## Libraries

- **Swing**: Used for creating the graphical user interface.
- **json_simple**: Used for parsing JSON responses from the weather API.

## API

- **api.open-meteo.com**: This is the API used to fetch weather data. Ensure you have the necessary API key and follow their documentation for integration.
