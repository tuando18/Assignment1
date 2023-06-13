package com.dovantuan.assignment1;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class readWriteStudent {

    Context context;

    public readWriteStudent(Context context) {
        this.context = context;
    }

    public ArrayList<Listdsnv> getDataOld(Context context, String fileName) {
        ArrayList<Listdsnv> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<Listdsnv>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeStudent(Context context, String fileName, Listdsnv student, ArrayList<Listdsnv> list) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            list.add(student);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOneStudent(Context context, ArrayList<Listdsnv> list, int pos, String fileName) {
        list.remove(pos);
        try {
            FileOutputStream f = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(list);
            o.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Context context, ArrayList<Listdsnv> list, int pos, Listdsnv updatedStudent, String fileName) {
        list.remove(pos);
        list.add(pos, updatedStudent);
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Listdsnv> readAllStudents(String fileName) {
        ArrayList<Listdsnv> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<Listdsnv>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
