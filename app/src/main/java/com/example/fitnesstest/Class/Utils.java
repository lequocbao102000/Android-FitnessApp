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
        data.add(new Exercise("ABDCRUNCHES", "Repeat for 15 repetitions with perfect form for each rep.", R.drawable.bung_abdcrunches,R.drawable.twostars, 0));
        data.add(new Exercise("BOAT HOLD", "1.Sitting position with your knees bent and feet.\n" +
                "2.Sit up high, lean back slightly, lift your feet off the ground.\n" +
                "3.Engage your abs.\n" +
                "4.Straighten your legs out and bring your hands towards your feet.", R.drawable.bung_boathold,R.drawable.twostars, 0));
        data.add(new Exercise("ELBOW TO KNEE CRUNCH RIGHT", "Repeat for 10 repetitions with perfect form for each rep.", R.drawable.bung_elbowtokneecrunchright,R.drawable.twostars, 0));
        data.add(new Exercise("RUSSIAN TWISTS", "Repeat for 10 repetitions with perfect form for each rep.", R.drawable.bung_russiantwists,R.drawable.twostars, 0));
        data.add(new Exercise("INCH WORM", "Repeat for 8 repetitions with perfect form for each rep.", R.drawable.bung_inchworm,R.drawable.threestars, 0));
        data.add(new Exercise("REVERSE CRUNCHES", "Repeat for 10 repetitions with perfect form for each rep.", R.drawable.bung_reversecrunches,R.drawable.threestars, 0));
        data.add(new Exercise("CRAB TOE TOUCHES", "Repeat for 12 repetitions with perfect form for each rep.", R.drawable.bung_crabtoetouches,R.drawable.fourstars, 0));

        data.add(new Exercise("KNEE PUSH UP", "Repeat for 15 repetitions with perfect form for each rep", R.drawable.nguc_kneepushup,R.drawable.twostars, 1));
        data.add(new Exercise("PUSH UP", "Repeat for 15 repetitions with perfect form for each rep", R.drawable.ngucvavai_pushup,R.drawable.twostars, 1));
        data.add(new Exercise("PLANK LOW TO HIGH", "Repeat for 10 repetitions with perfect form for each rep", R.drawable.nguc_planklowtohigh,R.drawable.threestars, 1));

        data.add(new Exercise("CHAIR STAND", "Repeat for 24 repetitions with perfect form for each rep", R.drawable.chan_chairstand ,R.drawable.onestar, 2));
        data.add(new Exercise("DOING SIT UP", "Repeat for 15 repetitions with perfect form for each rep", R.drawable.chan_doingsitup,R.drawable.onestar, 2));
        data.add(new Exercise("HIGH STEPPING", "Repeat for 24 repetitions with perfect form for each rep", R.drawable.chan_highstepping,R.drawable.onestar, 2));
        data.add(new Exercise("LUNGES", "Repeat for 20 repetitions with perfect form for each rep", R.drawable.chan_lunges,R.drawable.twostars, 2));
        data.add(new Exercise("SQUAT KICK", "Repeat for 18 repetitions with perfect form for each rep", R.drawable.chan_squatkick,R.drawable.twostars, 2));
        data.add(new Exercise("TRICEP DIPS", "Repeat for 12 repetitions with perfect form for each rep", R.drawable.chan_tripcetdips,R.drawable.twostars, 2));
        data.add(new Exercise("WALL SITTING", "1.Squeeze your stomach, push your back flat and \nraise high enough for your hands to slide along \nyour thighs to touch the tops of your knees.\n" +
                "2.Don’t pull with you neck or head and keep your \nlower back on the floor.\n" +
                "3.Then return to the starting position.", R.drawable.chan_wallsitting,R.drawable.twostars, 2));
        data.add(new Exercise("JUMPING JACK", "Repeat for 24 repetitions with perfect form for each rep", R.drawable.chanvavai_jumpingjack,R.drawable.twostars, 2));


        data.add(new Exercise("SIDE PLANK", "1.Hold this position for the duration of the exercise.\n" +
                "2.Depending on your fitness level, aim for between \n20 to 25 seconds.\n" +
                "3.Repeat on your left side.", R.drawable.vai_sideplank,R.drawable.twostars, 3));
        data.add(new Exercise("WIDE PUSH UP", "Repeat for 12 repetitions with perfect form for each rep.", R.drawable.vai_widepushup,R.drawable.twostars, 3));
        data.add(new Exercise("JUMPING JACK", "Repeat for 24 repetitions with perfect form for each rep.", R.drawable.chanvavai_jumpingjack,R.drawable.twostars, 3));
        data.add(new Exercise("PUSH UP", "Repeat for 12 repetitions with perfect form for each rep.", R.drawable.ngucvavai_pushup,R.drawable.twostars, 3));
        data.add(new Exercise("CROSS KNEE", "Repeat for 12 repetitions with perfect form for each rep.", R.drawable.vai_crossknee,R.drawable.threestars, 3));
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
