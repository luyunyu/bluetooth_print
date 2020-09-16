package com.example.bluetooth_print;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import com.gprinter.command.EscCommand.ENABLE;
import com.gprinter.command.GpUtils;
import com.gprinter.utils.ZLibUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LabelCommand {
    private static final String DEBUG_TAG = "LabelCommand";
    Vector<Byte> Command = null;

    public LabelCommand() {
        this.Command = new Vector();
    }

    public LabelCommand(int width, int height, int gap) {
        this.Command = new Vector(4096, 1024);
        this.addSize(width, height);
        this.addGap(gap);
    }

    public void clrCommand() {
        this.Command.clear();
    }

    private void addStrToCommand(String str) {
        byte[] bs = null;
        if (!str.equals("")) {
            try {
                bs = str.getBytes("GB18030");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
            }

            for(int i = 0; i < bs.length; ++i) {
                this.Command.add(bs[i]);
            }
        }

    }

    private void addStrToUnicodeCommand(String str, String textType) {
        byte[] bs = null;
        if (!str.equals("")) {
            try {
                bs = str.getBytes(textType);
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
            }

            for(int i = 0; i < bs.length; ++i) {
                this.Command.add(bs[i]);
            }
        }

    }

    public void addGap(int gap) {
        String str = "GAP " + gap + " mm," + 0 + " mm" + "\r\n";
        this.addStrToCommand(str);
    }

    public void addSize(int width, int height) {
        String str = "SIZE " + width + " mm," + height + " mm" + "\r\n";
        this.addStrToCommand(str);
    }

    public void addCashdrwer(com.gprinter.command.LabelCommand.FOOT m, int t1, int t2) {
        String str = "CASHDRAWER " + m.getValue() + "," + t1 + "," + t2 + "\r\n";
        this.addStrToCommand(str);
    }

    public void addOffset(int offset) {
        String str = "OFFSET " + offset + " mm" + "\r\n";
        this.addStrToCommand(str);
    }

    public void addSpeed(com.gprinter.command.LabelCommand.SPEED speed) {
        String str = "SPEED " + speed.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addDensity(com.gprinter.command.LabelCommand.DENSITY density) {
        String str = "DENSITY " + density.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addDirection(com.gprinter.command.LabelCommand.DIRECTION direction, com.gprinter.command.LabelCommand.MIRROR mirror) {
        String str = "DIRECTION " + direction.getValue() + ',' + mirror.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addReference(int x, int y) {
        String str = "REFERENCE " + x + "," + y + "\r\n";
        this.addStrToCommand(str);
    }

    public void addShif(int shift) {
        String str = "SHIFT " + shift + "\r\n";
        this.addStrToCommand(str);
    }

    public void addCls() {
        String str = "CLS\r\n";
        this.addStrToCommand(str);
    }

    public void addFeed(int dot) {
        String str = "FEED " + dot + "\r\n";
        this.addStrToCommand(str);
    }

    public void addBackFeed(int dot) {
        String str = "BACKFEED " + dot + "\r\n";
        this.addStrToCommand(str);
    }

    public void addFormFeed() {
        String str = "FORMFEED\r\n";
        this.addStrToCommand(str);
    }

    public void addHome() {
        String str = "HOME\r\n";
        this.addStrToCommand(str);
    }

    public void addPrint(int m, int n) {
        String str = "PRINT " + m + "," + n + "\r\n";
        this.addStrToCommand(str);
    }

    public void addPrint(int m) {
        String str = "PRINT " + m + "\r\n";
        this.addStrToCommand(str);
    }

    public void addCodePage(com.gprinter.command.LabelCommand.CODEPAGE page) {
        String str = "CODEPAGE " + page.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addSound(int level, int interval) {
        String str = "SOUND " + level + "," + interval + "\r\n";
        this.addStrToCommand(str);
    }

    public void addLimitFeed(int n) {
        String str = "LIMITFEED " + n + "\r\n";
        this.addStrToCommand(str);
    }

    public void addSelfTest() {
        String str = "SELFTEST\r\n";
        this.addStrToCommand(str);
    }

    public void addBar(int x, int y, int width, int height) {
        String str = "BAR " + x + "," + y + "," + width + "," + height + "\r\n";
        this.addStrToCommand(str);
    }

    public void addText(int x, int y, FONTTYPE font, ROTATION rotation, FONTMUL Xscal, FONTMUL Yscal, String text) {
        String str = "TEXT " + x + "," + y + "," + "\"" + font.getValue() + "\"" + "," + rotation.getValue() + "," + Xscal.getValue() + "," + Yscal.getValue() + "," + "\"" + text + "\"" + "\r\n";
        this.addStrToCommand(str);
    }

    public void addText(int x, int y, String font, ROTATION rotation, int Xscal, int Yscal,
                        String text) {
        String str = "TEXT " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation.getValue() + "," + Xscal + "," + Yscal + "," + "\"" + text + "\"" + "\r\n";
        this.addStrToCommand(str);
    }

    public void addUnicodeText(int x, int y, com.gprinter.command.LabelCommand.FONTTYPE font, com.gprinter.command.LabelCommand.ROTATION rotation, com.gprinter.command.LabelCommand.FONTMUL Xscal, com.gprinter.command.LabelCommand.FONTMUL Yscal, String text, String textType) {
        String str = "TEXT " + x + "," + y + "," + "\"" + font.getValue() + "\"" + "," + rotation.getValue() + "," + Xscal.getValue() + "," + Yscal.getValue() + "," + "\"" + text + "\"" + "\r\n";
        this.addStrToUnicodeCommand(str, textType);
    }

    public void add1DBarcode(int x, int y, com.gprinter.command.LabelCommand.BARCODETYPE type, int height, com.gprinter.command.LabelCommand.READABEL readable, com.gprinter.command.LabelCommand.ROTATION rotation, String content) {
        int narrow = 2;
        int width = 2;
        String str = "BARCODE " + x + "," + y + "," + "\"" + type.getValue() + "\"" + "," + height + "," + readable.getValue() + "," + rotation.getValue() + "," + narrow + "," + width + "," + "\"" + content + "\"" + "\r\n";
        this.addStrToCommand(str);
    }

    public void add1DBarcode(int x, int y, com.gprinter.command.LabelCommand.BARCODETYPE type, int height, com.gprinter.command.LabelCommand.READABEL readable, com.gprinter.command.LabelCommand.ROTATION rotation, int narrow, int width, String content) {
        String str = "BARCODE " + x + "," + y + "," + "\"" + type.getValue() + "\"" + "," + height + "," + readable.getValue() + "," + rotation.getValue() + "," + narrow + "," + width + "," + "\"" + content + "\"" + "\r\n";
        this.addStrToCommand(str);
    }

    public void addBox(int x, int y, int xend, int yend, int thickness) {
        String str = "BOX " + x + "," + y + "," + xend + "," + yend + "," + thickness + "\r\n";
        this.addStrToCommand(str);
    }

    public void addBitmap(int x, int y, com.gprinter.command.LabelCommand.BITMAP_MODE mode, int nWidth, Bitmap b) {
        if (b != null) {
            int width = (nWidth + 7) / 8 * 8;
            int height = b.getHeight() * width / b.getWidth();
            Log.d("BMP", "bmp.getWidth() " + b.getWidth());
            Bitmap grayBitmap = GpUtils.toGrayscale(b);
            Bitmap rszBitmap = GpUtils.resizeImage(grayBitmap, width, height);
            byte[] src = GpUtils.bitmapToBWPix(rszBitmap);
            height = src.length / width;
            width /= 8;
            String str = "BITMAP " + x + "," + y + "," + width + "," + height + "," + mode.getValue() + ",";
            this.addStrToCommand(str);
            byte[] codecontent = GpUtils.pixToLabelCmd(src);

            for(int k = 0; k < codecontent.length; ++k) {
                this.Command.add(codecontent[k]);
            }

            Log.d("LabelCommand", "codecontent" + codecontent);
        }

    }

    public void addBitmapByMethod(int x, int y, com.gprinter.command.LabelCommand.BITMAP_MODE mode, int nWidth, Bitmap b) {
        if (b != null) {
            int width = (nWidth + 7) / 8 * 8;
            int height = b.getHeight() * width / b.getWidth();
            Log.d("BMP", "bmp.getWidth() " + b.getWidth());
            Bitmap rszBitmap = GpUtils.resizeImage(b, width, height);
            Bitmap grayBitmap = GpUtils.filter(rszBitmap, width, height);
            byte[] src = GpUtils.bitmapToBWPix(grayBitmap);
            height = src.length / width;
            width /= 8;
            String str = "BITMAP " + x + "," + y + "," + width + "," + height + "," + mode.getValue() + ",";
            this.addStrToCommand(str);
            byte[] codecontent = GpUtils.pixToLabelCmd(src);

            for(int k = 0; k < codecontent.length; ++k) {
                this.Command.add(codecontent[k]);
            }

            Log.d("LabelCommand", "codecontent" + codecontent);
        }

    }

    public void addBitmap(int x, int y, int nWidth, Bitmap bmp) {
        if (bmp != null) {
            int width = (nWidth + 7) / 8 * 8;
            int height = bmp.getHeight() * width / bmp.getWidth();
            Log.d("BMP", "bmp.getWidth() " + bmp.getWidth());
            Bitmap rszBitmap = GpUtils.resizeImage(bmp, width, height);
            byte[] bytes = GpUtils.printTscDraw(x, y, com.gprinter.command.LabelCommand.BITMAP_MODE.OVERWRITE, rszBitmap);

            for(int i = 0; i < bytes.length; ++i) {
                this.Command.add(bytes[i]);
            }

            this.addStrToCommand("\r\n");
        }

    }

    public void addZLibBitmap(int x, int y, int nWidth, Bitmap bitmap) {
        if (bitmap != null) {
            int width = (nWidth + 7) / 8 * 8;
            int height = bitmap.getHeight() * width / bitmap.getWidth();
            Bitmap grayBitmap = GpUtils.toGrayscale(bitmap);
            Bitmap rszBitmap = GpUtils.resizeImage(grayBitmap, width, height);
            byte[] src = GpUtils.bitmapToBWPix(rszBitmap);
            height = src.length / width;
            width /= 8;
            byte[] codeContent = GpUtils.ZlibpixToLabelCmd(src);
            byte[] compressByte = new byte[0];

            try {
                compressByte = ZLibUtils.zLib(codeContent);
            } catch (IOException var14) {
                var14.printStackTrace();
            }

            String str = "BITMAP " + x + "," + y + "," + width + "," + height + "," + "3" + "," + compressByte.length + ",";
            this.addStrToCommand(str);

            for(int k = 0; k < compressByte.length; ++k) {
                this.Command.add(compressByte[k]);
            }

            Log.d("LabelCommand", "codecontent" + codeContent);
        }

    }

    public void addNewBitmap(int x, int y, int nWidth, Bitmap b, int level) {
        if (b != null) {
            int width = (nWidth + 7) / 8 * 8;
            int height = b.getHeight() * width / b.getWidth();
            Log.d("BMP", "bmp.getWidth() " + b.getWidth());
            Bitmap grayBitmap = GpUtils.toGrayscale(b);
            Bitmap rszBitmap = GpUtils.resizeImage(grayBitmap, width, height);
            byte[] src = GpUtils.bitmapToBWPix(rszBitmap);
            height = src.length / width;
            width /= 8;
            byte[] codecontent = GpUtils.ZlibpixToLabelCmd(src);
            byte[] compressByte = new byte[0];

            try {
                compressByte = ZLibUtils.zLib(codecontent, level);
            } catch (IOException var15) {
                var15.printStackTrace();
            }

            String str = "BITMAP " + x + "," + y + "," + width + "," + height + "," + "3" + "," + compressByte.length + ",";
            this.addStrToCommand(str);

            for(int k = 0; k < compressByte.length; ++k) {
                this.Command.add(compressByte[k]);
            }

            Log.d("LabelCommand", "codecontent" + codecontent);
        }

    }

    public void addZLibNoTrembleBitmapheight(int x, int y, int nWidth, Bitmap bitmap) {
        if (bitmap != null) {
            int photoheight = 100;
            if (photoheight > 0) {
                List<Bitmap> mlist = ImageCrop(bitmap, photoheight);

                for(int i = 0; i < mlist.size(); ++i) {
                    int width = (nWidth + 7) / 8 * 8;
                    int height = ((Bitmap)mlist.get(i)).getHeight() * width / ((Bitmap)mlist.get(i)).getWidth();
                    this.addSize(nWidth / 8, height / 8);
                    this.addGap(0);
                    this.addDirection(com.gprinter.command.LabelCommand.DIRECTION.FORWARD, com.gprinter.command.LabelCommand.MIRROR.NORMAL);
                    this.addCls();
                    this.addNewBitmap(x, y, nWidth, (Bitmap)mlist.get(i), 9);
                    this.addPrint(1);
                }
            }
        }

    }

    public static Bitmap cutBitmap(Bitmap bitmap, int y, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Log.e(String.valueOf(w), String.valueOf(h));
        return Bitmap.createBitmap(bitmap, 0, y, w, height, (Matrix)null, false);
    }

    public static List<Bitmap> ImageCrop(Bitmap bitmap, int photoheight) {
        List<Bitmap> list = new ArrayList();
        int last = bitmap.getHeight() % photoheight;

        for(int i = 0; i < bitmap.getHeight() - bitmap.getHeight() % photoheight; i += photoheight) {
            list.add(cutBitmap(bitmap, i, photoheight));
        }

        if (last > 0) {
            Bitmap cp = cutBitmap(bitmap, bitmap.getHeight() - bitmap.getHeight() % photoheight, bitmap.getHeight() % photoheight);
            list.add(cp);
        }

        return list;
    }

    public void addZLibNoTrembleBitmap(int x, int y, int nWidth, Bitmap bitmap) {
        if (bitmap != null) {
            byte[] data = this.getZLibData(8, nWidth, bitmap);
            int width = (nWidth + 7) / 8 * 8;
            int height = bitmap.getHeight() * width / bitmap.getWidth();
            String str = "BITMAP " + x + "," + y + "," + width / 8 + "," + height + "," + "3" + "," + data.length + ",";
            this.addStrToCommand(str);

            for(int k = 0; k < data.length; ++k) {
                this.Command.add(data[k]);
            }
        }

    }

    public byte[] getZLibData(int w, int nWidth, Bitmap bitmap) {
        try {
            if (bitmap != null) {
                int e = (nWidth + (w - 1)) / w * w;
                int height = bitmap.getHeight() * e / bitmap.getWidth();
                Bitmap grayBitmap = GpUtils.toGrayscale(bitmap);
                Bitmap rszBitmap = GpUtils.resizeImage(grayBitmap, e, height);
                byte[] src = GpUtils.printZlibTscDraw(rszBitmap);
                byte[] compressByte = ZLibUtils.zLib(src);
                return compressByte;
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        return null;
    }

    public void addErase(int x, int y, int xwidth, int yheight) {
        String str = "ERASE " + x + "," + y + "," + xwidth + "," + yheight + "\r\n";
        this.addStrToCommand(str);
    }

    public void addReverse(int x, int y, int xwidth, int yheight) {
        String str = "REVERSE " + x + "," + y + "," + xwidth + "," + yheight + "\r\n";
        this.addStrToCommand(str);
    }

    public void addQRCode(int x, int y, com.gprinter.command.LabelCommand.EEC level, int cellwidth, com.gprinter.command.LabelCommand.ROTATION rotation, String data) {
        String str = "QRCODE " + x + "," + y + "," + level.getValue() + "," + cellwidth + "," + 'A' + "," + rotation.getValue() + "," + "\"" + data + "\"" + "\r\n";
        this.addStrToCommand(str);
    }

    public Vector<Byte> getCommand() {
        return this.Command;
    }

    public void addQueryPrinterType() {
        new String();
        String str = "~!T\r\n";
        this.addStrToCommand(str);
    }

    public void addQueryPrinterStatus() {
        this.Command.add((byte)27);
        this.Command.add((byte)33);
        this.Command.add((byte)63);
    }

    public void addResetPrinter() {
        this.Command.add((byte)27);
        this.Command.add((byte)33);
        this.Command.add((byte)82);
    }

    public void addQueryPrinterLife() {
        String str = "~!@\r\n";
        this.addStrToCommand(str);
    }

    public void addQueryPrinterMemory() {
        String str = "~!A\r\n";
        this.addStrToCommand(str);
    }

    public void addQueryPrinterFile() {
        String str = "~!F\r\n";
        this.addStrToCommand(str);
    }

    public void addQueryPrinterCodePage() {
        String str = "~!I\r\n";
        this.addStrToCommand(str);
    }

    public void addPeel(ENABLE enable) {
        if (enable.getValue() == 0) {
            String str = "SET PEEL " + enable.getValue() + "\r\n";
            this.addStrToCommand(str);
        }

    }

    public void addTear(ENABLE enable) {
        String str = "SET TEAR " + enable.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addCutter(ENABLE enable) {
        String str = "SET CUTTER " + enable.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addCutterBatch() {
        String str = "SET CUTTER BATCH\r\n";
        this.addStrToCommand(str);
    }

    public void addCutterPieces(short number) {
        String str = "SET CUTTER " + number + "\r\n";
        this.addStrToCommand(str);
    }

    public void addReprint(ENABLE enable) {
        String str = "SET REPRINT " + enable.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addPrintKey(ENABLE enable) {
        String str = "SET PRINTKEY " + enable.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addPrintKey(int m) {
        String str = "SET PRINTKEY " + m + "\r\n";
        this.addStrToCommand(str);
    }

    public void addPartialCutter(ENABLE enable) {
        String str = "SET PARTIAL_CUTTER " + enable.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addQueryPrinterStatus(com.gprinter.command.LabelCommand.RESPONSE_MODE mode) {
        String str = "SET RESPONSE " + mode.getValue() + "\r\n";
        this.addStrToCommand(str);
    }

    public void addDMATRIX(int x, int y, int width, int height, String content) {
        String str = "DMATRIX " + x + "," + y + "," + width + "," + height + ",\"" + content + "\"\r\n";
        this.addStrToCommand(str);
    }

    public void addDMATRIX(int x, int y, int width, int height, com.gprinter.command.LabelCommand.ROTATION rotation, String content) {
        String str = "DMATRIX " + x + "," + y + "," + width + "," + height + ",r" + rotation.getValue() + ",\"" + content + "\"\r\n";
        this.addStrToCommand(str);
    }

    public void addDMATRIX(int x, int y, int width, int height, int xZoom, String content) {
        String str = "DMATRIX " + x + "," + y + "," + width + "," + height + ",x" + xZoom + ",\"" + content + "\"\r\n";
        this.addStrToCommand(str);
    }

    public void addDMATRIX(int x, int y, int width, int height, int c, int xZoom, String content) {
        String str = "DMATRIX " + x + "," + y + "," + width + "," + height + ",c" + c + ",x" + xZoom + ",\"" + content + "\"\r\n";
        this.addStrToCommand(str);
    }

    public void addDMATRIX(int x, int y, int width, int height, int xZoom, int row, int col, String content) {
        String str = "DMATRIX " + x + "," + y + "," + width + "," + height + ",x" + xZoom + "," + row + "," + col + ",\"" + content + "\"\r\n";
        this.addStrToCommand(str);
    }

    public void addDMATRIX(int x, int y, int width, int height, int c, int xZoom, com.gprinter.command.LabelCommand.ROTATION rotation, com.gprinter.command.LabelCommand.Shape shape, int row, int col, String content) {
        String str = "DMATRIX " + x + "," + y + "," + width + "," + height + ",c" + c + ",x" + xZoom + ",r" + rotation.getValue() + ",a" + shape.getValue() + "," + row + "," + col + ",\"" + content + "\"\r\n";
        this.addStrToCommand(str);
    }

    public void addUserCommand(String command) {
        this.addStrToCommand(command);
    }

    public static enum BARCODETYPE {
        CODE128("128"),
        CODE128M("128M"),
        EAN128("EAN128"),
        ITF25("25"),
        ITF25C("25C"),
        CODE39("39"),
        CODE39C("39C"),
        CODE39S("39S"),
        CODE93("93"),
        EAN13("EAN13"),
        EAN13_2("EAN13+2"),
        EAN13_5("EAN13+5"),
        EAN8("EAN8"),
        EAN8_2("EAN8+2"),
        EAN8_5("EAN8+5"),
        CODABAR("CODA"),
        POST("POST"),
        UPCA("UPCA"),
        UPCA_2("UPCA+2"),
        UPCA_5("UPCA+5"),
        UPCE("UPCE13"),
        UPCE_2("UPCE13+2"),
        UPCE_5("UPCE13+5"),
        CPOST("CPOST"),
        MSI("MSI"),
        MSIC("MSIC"),
        PLESSEY("PLESSEY"),
        ITF14("ITF14"),
        EAN14("EAN14");

        private final String value;

        private BARCODETYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum BITMAP_MODE {
        OVERWRITE(0),
        OR(1),
        XOR(2);

        private final int value;

        private BITMAP_MODE(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum CODEPAGE {
        PC437(437),
        PC850(850),
        PC852(852),
        PC860(860),
        PC863(863),
        PC865(865),
        WPC1250(1250),
        WPC1252(1252),
        WPC1253(1253),
        WPC1254(1254);

        private final int value;

        private CODEPAGE(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum DENSITY {
        DNESITY0(0),
        DNESITY1(1),
        DNESITY2(2),
        DNESITY3(3),
        DNESITY4(4),
        DNESITY5(5),
        DNESITY6(6),
        DNESITY7(7),
        DNESITY8(8),
        DNESITY9(9),
        DNESITY10(10),
        DNESITY11(11),
        DNESITY12(12),
        DNESITY13(13),
        DNESITY14(14),
        DNESITY15(15);

        private final int value;

        private DENSITY(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum DIRECTION {
        FORWARD(0),
        BACKWARD(1);

        private final int value;

        private DIRECTION(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum EEC {
        LEVEL_L("L"),
        LEVEL_M("M"),
        LEVEL_Q("Q"),
        LEVEL_H("H");

        private final String value;

        private EEC(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum FONTMUL {
        MUL_1(1),
        MUL_2(2),
        MUL_3(3),
        MUL_4(4),
        MUL_5(5),
        MUL_6(6),
        MUL_7(7),
        MUL_8(8),
        MUL_9(9),
        MUL_10(10);

        private final int value;

        private FONTMUL(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum FONTTYPE {
        FONT_1("1"),
        FONT_2("2"),
        FONT_3("3"),
        FONT_4("4"),
        FONT_5("5"),
        FONT_6("6"),
        FONT_7("7"),
        FONT_8("8"),
        FONT_9("9"),
        FONT_10("10"),
        SIMPLIFIED_CHINESE("TSS24.BF2"),
        TRADITIONAL_CHINESE("TST24.BF2"),
        KOREAN("K");

        private final String value;

        private FONTTYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum FOOT {
        F2(0),
        F5(1);

        private final int value;

        private FOOT(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum MIRROR {
        NORMAL(0),
        MIRROR(1);

        private final int value;

        private MIRROR(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum READABEL {
        DISABLE(0),
        EANBEL(1);

        private final int value;

        private READABEL(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum RESPONSE_MODE {
        ON("ON"),
        OFF("OFF"),
        BATCH("BATCH");

        private final String value;

        private RESPONSE_MODE(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum ROTATION {
        ROTATION_0(0),
        ROTATION_90(90),
        ROTATION_180(180),
        ROTATION_270(270);

        private final int value;

        private ROTATION(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum SPEED {
        SPEED1DIV5(1.5F),
        SPEED2(2.0F),
        SPEED3(3.0F),
        SPEED4(4.0F);

        private final float value;

        private SPEED(float value) {
            this.value = value;
        }

        public float getValue() {
            return this.value;
        }
    }

    public static enum Shape {
        Square("0"),
        Rectangle("1");

        private final String value;

        private Shape(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
