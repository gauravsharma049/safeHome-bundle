#include <WiFi.h>
#include <HTTPClient.h>

#define MQ2pin 32
#define threshold 3400
#define buzerPin 18
#define ledPin 5

const char* ssid = "LAPTOP-39F8KB72 9870";
const char* password = "5z19-1P8";
const char* serverUrl = "http://192.168.75.107:8080/send-notification";  // Replace with your server URL


float sensorValue;  //variable to store sensor value

void setup() {
  
  Serial.begin(9600);  // sets the serial port to 9600
  // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to Wi-Fi...");
  }
  Serial.println("Connected to Wi-Fi");


  pinMode(buzerPin, OUTPUT);
  pinMode(ledPin, OUTPUT);
  Serial.println("MQ2 warming up!");
  delay(20000);  // allow the MQ2 to warm up
}

void loop() {
  sensorValue = analogRead(MQ2pin);  // read analog input pin 0

  Serial.print("Sensor Value: ");
  Serial.println(sensorValue);
  if (sensorValue > threshold) {
    digitalWrite(buzerPin, HIGH);
    digitalWrite(ledPin, HIGH);
    Serial.println("sending request to server");
    // Create an HTTPClient object
    HTTPClient http;

    // Set the server URL
    http.begin(serverUrl);

    // Set the HTTP header
    http.addHeader("Content-Type", "application/json");

    String firstParam = "{\"value\":\"";
    String secondParam = "\"}";
    // Set the POST request data
    String postData = firstParam + sensorValue + secondParam;

    // Send the POST request
    int httpResponseCode = http.POST(postData);

    // Check the response code
    if (httpResponseCode > 0) {
      Serial.printf("HTTP Response code: %d\n", httpResponseCode);

      // Get the response body
      String responseBody = http.getString();
      Serial.println(responseBody);
    } else {
      Serial.printf("Error sending POST request: %s\n", http.errorToString(httpResponseCode).c_str());
    }

    // Clean up
    http.end();
    delay(5000);
  } else {
    digitalWrite(buzerPin, LOW);
    digitalWrite(ledPin, LOW);
  }
  delay(2000);  // wait 2s for next reading
}