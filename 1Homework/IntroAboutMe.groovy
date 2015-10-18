class OutText{
    Integer age;
    String name;
    def experience;
    boolean isShortVersion;
         
    def out(){
        String text = """Hi, my name is ${name} I am ${age} years old. 
            As for programming i create program for near ${experience} years""";
        String appendText = " My hobby is a travel, especialy mountain travel. In mountain i feel myself free and relaxed."
        
        isShortVersion? text.toString(): (text+appendText).toString();
    }
}
outText = new OutText(age:20, name:"Evgenii",experience:1, isShortVersion: true);
outText.out();
