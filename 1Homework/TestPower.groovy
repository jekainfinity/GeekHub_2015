import java.lang.*;
class TestPower{
    int a = 44;
    int b = 3;
    BigInteger result;

    def byLog(){
       println "By Log";
       result = (BigInteger) Math.round(Math.exp(b * Math.log(a)));
    }
    
    def byMath(){
       println "By Java Math";
       result = Math.pow(a,b);
    } 
    
    def testNumb(){
        assert (result == 85184)     
    }
}

testPower = new TestPower();
testPower.byLog()
testPower.testNumb();

testPower.byMath()
testPower.testNumb()