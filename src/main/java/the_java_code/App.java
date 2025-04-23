package the_java_code;

public class App {

    int maxNumber = 100;
    int attendOfMember = 10;

    public boolean validateOfMember(){
        if(maxNumber == 0) {
            return false;
        }
        if(attendOfMember > maxNumber) {
            return false;
        }
        return true;
    }
}
