package com.lorens.daria.target;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TARGET_1 = "target1";
    public static final String TARGET_2 = "target2";
    public static final String INSPIRING_TARGET = "InspiringTarget";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveTarget (View view){
        EditText oneView = (EditText) findViewById(R.id.oneText);
        EditText twoView = (EditText) findViewById(R.id.twoText);
        String oneText = oneView.getText().toString();
        String twoText = twoView.getText().toString();

        if (oneText.isEmpty() || twoText.isEmpty()){return;}
        Map<String, Object> day = new HashMap<>();
        day.put(TARGET_1, oneText);
        day.put(TARGET_2, twoText);
        db.collection("day")
                .add(day)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(INSPIRING_TARGET, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(INSPIRING_TARGET, "Error adding document", e);
                    }
                });
    }
}
