package com.kaishu.servicebase.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

    public static String getMessage(Exception e){
        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            //将出错的栈信息，输出到printwriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        }finally {
            try {
                sw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (pw != null){
                pw.close();
            }
        }
        return sw.toString();
    }
}
