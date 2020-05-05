/*
We have the class PrintStackTraceDemo with four methods: a, m, t, x.
One of the methods of PrintStackTraceDemo throws IOException, 
the other one catches the exception and prints a stack trace.

We have this part of stack trace that was generated in PrintStackTraceDemo class:

java.io.IOException
     PrintStackTraceDemo.t(__tester__.java:19)
     PrintStackTraceDemo.m(__tester__.java:27)
     PrintStackTraceDemo.a(__tester__.java:23)
     PrintStackTraceDemo.x(__tester__.java:8)

Please, fill in the gaps in the code of the PrintStackTraceDemo class so that the stack trace will remain the same.
*/

import java.io.IOException;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class PrintStackTraceDemo{
    public static void x(){
        try{
            a();
        }
        catch (IOException ioe){//this way of stack trace output is workaround for moodle
           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           PrintStream ps = new PrintStream(byteArrayOutputStream);
           ioe.printStackTrace(ps);
           System.out.println(byteArrayOutputStream);
        }
    }
   
    public static void t() throws IOException{
       throw new IOException();
    }

   static void a() throws IOException   {
      m();
   }

   static void m() throws IOException   {
      t();
   }
}
