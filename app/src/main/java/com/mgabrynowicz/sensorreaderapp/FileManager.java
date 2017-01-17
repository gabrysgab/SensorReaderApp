package com.mgabrynowicz.sensorreaderapp;

import android.content.Context;
import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

/**
 * Created by RENT on 2017-01-17.
 */

public class FileManager {
    public static final FileManager instance = new FileManager();
    private static OutputStreamWriter outputStreamWriter;


    private FileManager() {

    }

    public void save(Context context, float[] values) {

        saveToFile(values);


    }

    public void openOutputStreamWriter() {
        String filename = "accelerometer_readings" + String.valueOf(System.currentTimeMillis());
        try {
            // FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

            FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/accelerometer/" + filename);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void saveToFile(float[] values) {

        byte[] byteArray = FloatArray2ByteArray(values);
        try {
            outputStreamWriter.write(new String(byteArray).toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void closeOutputStreamWriter() {

        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static byte[] FloatArray2ByteArray(float[] values) {
        ByteBuffer buffer = ByteBuffer.allocate(4 * values.length);

        for (float value : values) {
            buffer.putFloat(value);
        }

        return buffer.array();
    }


}
