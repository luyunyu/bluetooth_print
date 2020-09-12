package android.src.main.java.com.example.bluetooth_print;

import com.gprinter.command.LabelCommand;

class LabelNewCommand extends LabelCommand{

    public void addText(int x, int y, String font, ROTATION rotation, int xscal, int yscal,
                        String text) {
        String str = "TEXT " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation.getValue() + "," +
                xscal + "," + yscal + "," + "\"" + text + "\"" + "\r\n";
        addStrToCommand(str);
    }

}