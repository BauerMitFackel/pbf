#include <SPI.h>
#include <Ethernet.h>
#include "Sensor.h"


//********************** Ethernet Stuff *************************
// Enter a MAC address and IP address for the controller.
byte mac[] = {'90', 'A2', 'DA', '0D', '94', 'D3'}; // written on the backsite of the board
IPAddress ip(192, 168, 178, 59); // specific IP in the LAN
byte ipByte[] = {192, 168, 178, 59};
byte subnet[] = {255, 255, 255, 0}; 
byte gateway[] = {192, 168, 178, 1};
int port = 80;
// Initialize the Ethernet server library
EthernetServer server(port);

//********************** Declare Sensors *************************
const String moistureSensorType = "moisture_sensor";
const String temperatureSensorType = "temperature_sensor";
const String lightSensorType = "light_sensor";
const int numberOfSensors = 4;


Sensor s2(2, moistureSensorType, "S2");
Sensor s3(3, lightSensorType, "S3");
Sensor s4(4, temperatureSensorType, "S4");
Sensor s5(5, moistureSensorType, "S5");

Sensor sensors[numberOfSensors] = {s2, s3, s4, s5 };


void setup() {

  Serial.begin(9600);
  startEthernetConnection();
}


void loop() {
  
   // listen for incoming clients
  EthernetClient client = server.available();
  if (client) {
    Serial.println("new client");
    // an http request ends with a blank line
    boolean currentLineIsBlank = true;
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        Serial.write(c);
        // if you've gotten to the end of the line (received a newline
        // character) and the line is blank, the http request has ended,
        // so you can send a reply
        if (c == '\n' && currentLineIsBlank) {
          // send a standard http response header
          
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connnection: close");
          client.println();
          client.println("<!DOCTYPE HTML>");
          client.println("<html>");
                    // add a meta refresh tag, so the browser pulls again every 5 seconds:
          client.println("<meta http-equiv=\"refresh\" content=\"5\">");
          
          client.print("<body>");
          
          client.print("[");
          for (int i = 0; i < numberOfSensors ; i++) {
            Sensor sensor = sensors[i];
            client.print(sensor.getJson());
            if(i != numberOfSensors-1){
             client.print(",");
            }
     
          }
           client.println("]");
          
          client.println("</body></html>");
          break;
        }
        if (c == '\n') {
          // you're starting a new line
          currentLineIsBlank = true;
        } 
        else if (c != '\r') {
          // you've gotten a character on the current line
          currentLineIsBlank = false;
        }
      }
    }
    // give the web browser time to receive the data
    delay(1);
    // close the connection:
    client.stop();
    Serial.println("client disonnected");
  }
}



// start the Ethernet connection and the server:
void startEthernetConnection(){

  //Ethernet.begin(mac, ip, gateway, gateway, subnet);
  Ethernet.begin(mac, ip);
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());

}

