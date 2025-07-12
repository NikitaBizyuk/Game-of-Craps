package Controller;

import Model.CrapsPlayer;
import View.CrapsView;

public class Driver {

    public static void main(String[] theArgs){
        DiceController controller = new DiceController(new CrapsView(),new CrapsPlayer());
    }

}
