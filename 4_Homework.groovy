class Car{
   Door[] doors = [];    
   Engine engine = new Engine();
   Color color = new Color();
   Wheel[] wheels = [];
   Frame frame;
   
   String toString(){
         'Car: doors:' + doors + ' engine: ' + engine + ' color :' + color.color+ ' wheels: ' + wheels + ', frame' + frame ;
    }  
}

class Engine{
    def volume;
    int cylinder;
}

class Color{
    String color;
}

class Door{
}

class Wheel{
    def diameter;
}

class Frame{
    String type;
    int length;
}

class Conveyor{
    Car car;   
    def addFrame = {prop -> 
        println 'Add Frame'
        car.setFrame(new Frame(prop));
    }
    
    def addDoors = {prop -> 
        println 'Add Doors'
        Door[] doors = [new Door(), new Door()];
        car.setDoors(doors);
    }
    
    def addColor = {prop -> 
        println 'Add color'
        car.setColor(new Color(prop));
    }
    
    def addEngine = {prop -> 
        println 'Add Engine'
        car.setEngine(new Engine(prop));
    }
    
    def addWheels = {prop -> 
        println 'Add wheels'
        Wheel[] wheels = [new Wheel(prop), new Wheel(prop), new Wheel(prop), new Wheel(prop)]
        car.setWheels(wheels);
    }
    
    def stage = {elem, propMap -> 
            switch (elem) {
                case 'Frame': addFrame(propMap); break;
                case 'Doors': addDoors(propMap); break;
                case 'Color': addColor(propMap); break;
                case 'Engine': addEngine(propMap); break;
                case 'Wheel': addWheels(propMap); break;
            }
        } 
            
    def startConnect(def configurationMap){
        car = new Car();    
       
        configurationMap.each{key,value ->
            stage(key,value);
            
        }
        println "${car.toString()}";
    }      
}

Conveyor conveyor = new Conveyor();
def configurationMap = [:];

def file = new File('/home/jekainfinity/CarConfig.txt');
if(!file.exists()) {
    println "File does not exist !!!"; 
}

// Get string of configuration and add element of configuration (for example "Wheels") as Key
// and put Map of Property as Value of this element 

file.eachLine(){ str ->
    def propMap = [:];
    String elemOfCar;
    
    str.split().each {param ->
        def nameAndValue = param.split("=");
        if (nameAndValue[0].equals(nameAndValue[1])){
            elemOfCar = nameAndValue[0];
        }else {
            propMap[nameAndValue[0]] = nameAndValue[1];
        }
    }
    
    configurationMap[elemOfCar]=propMap;
}

conveyor.startConnect(configurationMap);

