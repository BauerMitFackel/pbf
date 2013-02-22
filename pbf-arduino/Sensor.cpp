#include "Arduino.h"
#include "Sensor.h"

Sensor::Sensor(int pin, String type, String id) {
  sPin = pin;
  sType = type;
  sId = id;
}

int Sensor::getValue() {
  value = analogRead(sPin);
  if(sType == "temperature_sensor") {
     value = value * 0.48828;
  }
  return value;
}

String Sensor::getID(){
  return sId;
}

String Sensor::getType(){
  return sType;
}

String Sensor::getJson(){
  
      String json;      
      json = json + "{";
      json = json + ("\"type\":\"");
      json = json + sType;
      json = json + ("\",\"id\":\"");
      json = json + sId;
      json = json + ("\",\"value\":"); 
      json = json + getValue();
      json = json + ("}");      
      return json;
}
