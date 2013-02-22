#include "Arduino.h"
class Sensor {
  
  private:
  int sPin;
  int value;
  String sType;
  String sId;
  
  public:
  Sensor(int pin, String type, String id);
  int getValue();
  String getID();
  String getType();
  String getJson();
};
