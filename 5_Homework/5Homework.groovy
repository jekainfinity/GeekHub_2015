@groovy.transform.TypeChecked
class Duck{    
    StringBuilder str = new StringBuilder();
    def firstString = 'little ducks went out une day \n';
    def twoString = 'Over the hills and far away \n';
    def motherDuck = 'Mother Duck said \n';
    String[] quacks = [] ;
    
    def fourComeBack = 'But only 4 little ducks come back \n';
    
    def wentOut = 'little ducks went out one day \n';
    def oneWentOut = 'little duck went out one day \n';
    def comeBack = 'And all 5 of her little ducks come back \n';      
     
    def numbers(int number){
           def num = ''; 
           switch (number){
                case 1 : num = 'One '; break; 
                case 2 : num = 'Two..\n'; break;
                case 3 : num = 'Three..\n'; break;   
                case 4 : num = 'Four '; break
                case 5 : num = 'Five '; break;
            } 
        }
         
    def quack(){
        str << numbers(5) << firstString << twoString << motherDuck << quacks.join(' ')  << '\n';            
        str << (fourComeBack);            
        str << numbers(4) << wentOut << numbers(3) << numbers(2);            
        str << numbers(1) << oneWentOut << motherDuck << quacks.join(' ')  << '\n' << comeBack;    
        
        println str;        
    }
}

def duck = new Duck();

duck.metaClass.setAttribute(duck, 'quacks', ['quack', 'quack', 'quack', 'quack']);

duck.quack();

