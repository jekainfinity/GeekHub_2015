class JsonParser{
    def jsonString;
    def bufferString = StringBuilder.newInstance();
    
    def serializes(def objectCollection){   
            def objects = objectCollection;  

            objects.each{propList -> 
               char doubQuot= (char) 34;
               def bufStr = [] as ArrayList;

               propList.each{key, value -> 
                   def doubQuoteValue = "${doubQuot}$value${doubQuot}";
                   bufStr.add("${doubQuot}$key${doubQuot}:" + "${(value instanceof java.lang.Number) ? value : doubQuoteValue}\n");                                                                      
               }    

               bufferString << "{${bufStr.join(',')}}\n";
             }
             
             jsonString = "backpacks:[\n $bufferString]"; 
             println jsonString;  
    }
    
}

class Finder{
      def objects;  

      def findByVolume(Integer volume){
          objects.each{elem->
              elem.each{key,value->
                  if ((key.equals('volume')) && (value == volume)){
                      println "ByVolume $elem"
                  }
              }
          }
      }
      
      def findByPrice(Integer price){
          objects.each{elem->
              elem.each{key,value->
                  if ((key.equals('price')) && (value == price)){
                      println "ByPrice $elem"
                  }
              }
          }
      }
      
      def findByProducerAndPrice(String producer, Integer lowPrice, Integer highPrice){
          String mapProducer;
          Integer mapPrice;

          objects.each{elem->

              mapProducer = "";
              mapPrice = 0;   
              elem.each{key,value->
                  if (key.equals('producer')){
                      mapProducer = value;
                  }
                  if (key.equals('price')){
                      mapPrice = value;
                  }                
              }
                             
              if ((mapPrice >= lowPrice) && (mapPrice <= highPrice) && (mapProducer.equals(producer))){
                  println "ByProducerAndPrice $elem";
              }
          }
      }
}


    JsonParser jp = new JsonParser();
    def backpacks = [[producer: 'RedPoint',model: 'Daypack 30', volume: 30, colour: 'red', price: 170],
        [producer: 'RedPoint',model:'Daypack 45', volume:45 ,colour:'blue', price: 190],
        [producer: 'Deuter', model: 'Futura', volume: 70, colour: 'grey', price: 270],
        [producer: 'Deuter', model: 'Futura Pro', volume: 35, colour: 'green', price: 190],
        [producer: 'Osprey', model: 'SityPack', volume: 15, colour: 'black', price: 123],
        [producer: 'Osprey', model: 'Fly' ,volume: 60, colour: 'red', price: 250]]

    Finder finder = new Finder(objects: backpacks);
    
    jp.serializes(backpacks);
    
    finder.findByVolume(15);
    finder.findByPrice(190);
    finder.findByProducerAndPrice('RedPoint',180,200);
   

   