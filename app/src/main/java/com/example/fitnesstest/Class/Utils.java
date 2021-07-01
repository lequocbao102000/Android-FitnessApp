package com.example.fitnesstest.Class;

import android.content.Context;
import android.util.Log;

import com.example.fitnesstest.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Utils {
    Context context;
    static String filename="WorkoutDB.db";

    public Utils(Context context) {
        this.context = context;
    }
    public ArrayList<Workout> getDataWorkout(){
        ArrayList<Workout> tmp = new ArrayList<>();
        tmp.add(new Workout(R.drawable.stomach,"Stomach"));
        tmp.add(new Workout(R.drawable.chest,"Chest"));
        tmp.add(new Workout(R.drawable.leg,"Leg"));
        tmp.add(new Workout(R.drawable.shoulder,"Shoulder"));

        return tmp;
    }

    public  ArrayList<Exercise> getDataExercise(){
        ArrayList<Exercise> data=new ArrayList<>();
        data.add(new Exercise("ABDCRUNCHES", "abc", R.drawable.bung_abdcrunches,R.drawable.twostars, 0));
        data.add(new Exercise("BOAT HOLD", "abc", R.drawable.bung_boathold,R.drawable.twostars, 0));
        data.add(new Exercise("ELBOW TO KNEE CRUNCH RIGHT", "abc", R.drawable.bung_elbowtokneecrunchright,R.drawable.twostars, 0));
        data.add(new Exercise("RUSSIAN TWISTS", "abc", R.drawable.bung_russiantwists,R.drawable.twostars, 0));
        data.add(new Exercise("INCH WORM", "abc", R.drawable.bung_inchworm,R.drawable.threestars, 0));
        data.add(new Exercise("REVERSE CRUNCHES", "abc", R.drawable.bung_reversecrunches,R.drawable.threestars, 0));
        data.add(new Exercise("CRAB TOET TOUCHES", "abc", R.drawable.bung_crabtoetouches,R.drawable.fourstars, 0));

        data.add(new Exercise("KNEE PUSHUP", "abc", R.drawable.nguc_kneepushup,R.drawable.twostars, 1));
        data.add(new Exercise("PUSHUP", "abc", R.drawable.ngucvavai_pushup,R.drawable.twostars, 1));
        data.add(new Exercise("PLANK LOW TO HIGH", "abc", R.drawable.nguc_planklowtohigh,R.drawable.threestars, 1));

        data.add(new Exercise("CHAIR STAND", "abc", R.drawable.chan_chairstand ,R.drawable.onestar, 2));
        data.add(new Exercise("DOING SIT UP", "abc", R.drawable.chan_doingsitup,R.drawable.onestar, 2));
        data.add(new Exercise("HIGH STEPPING", "abc", R.drawable.chan_highstepping,R.drawable.onestar, 2));
        data.add(new Exercise("LUNGES", "abc", R.drawable.chan_lunges,R.drawable.twostars, 2));
        data.add(new Exercise("SQUATKICK", "abc", R.drawable.chan_squatkick,R.drawable.twostars, 2));
        data.add(new Exercise("TRIPCET DIPS", "abc", R.drawable.chan_tripcetdips,R.drawable.twostars, 2));
        data.add(new Exercise("WALL SITTING", "abc", R.drawable.chan_wallsitting,R.drawable.twostars, 2));
        data.add(new Exercise("JUMPING JACK", "abc", R.drawable.chanvavai_jumpingjack,R.drawable.twostars, 2));


        data.add(new Exercise("SIDE PLANK", "abc", R.drawable.vai_sideplank,R.drawable.twostars, 3));
        data.add(new Exercise("WIDE PUSHUP", "abc", R.drawable.vai_widepushup,R.drawable.twostars, 3));
        data.add(new Exercise("JUMPING JACK", "abc", R.drawable.chanvavai_jumpingjack,R.drawable.twostars, 3));
        data.add(new Exercise("PUSHUP", "abc", R.drawable.ngucvavai_pushup,R.drawable.twostars, 3));
        data.add(new Exercise("CROSSKNEE", "abc", R.drawable.vai_crossknee,R.drawable.threestars, 3));
        return data;
    }

    public void WriteToFile(ArrayList<Workout> arrayList){
        try {
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Workout> LoadToFile(){
        ArrayList<Workout> arrayList=new ArrayList<>();
        try {
            File file=new File(context.getFilesDir(),filename);
            FileInputStream fileInputStream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            arrayList= (ArrayList<Workout>) objectInputStream.readObject();
            Log.d("WorkoutApp",arrayList.size()+"");
            return arrayList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {e.printStackTrace();}
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
