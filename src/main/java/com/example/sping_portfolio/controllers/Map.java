package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.database.*;


import com.fasterxml.jackson.core.type.TypeReference;



@Controller
public class Map {

    
   // FileInputStream serviceAccount = new FileInputStream("serviceAccount.json");
    InputStream is = TypeReference.class.getResourceAsStream("/json/serviceAccount.json");


    FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(is))
            .setDatabaseUrl("https://nolan-4b453.firebaseio.com/")
            .build();

    ArrayList<String> result = new ArrayList<String>();
    int numIssues = 0;
    int numCompleted = 0;
    int numReports = 0;

    public Map() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    @GetMapping("/map")
    public String getMap() {
        return "map";
    }

    // Fetches GeoJSON data from Firebase, returns in a String. Adds to an ArrayList
    // locally.
    @GetMapping("/fetchFromFirebase")
    @ResponseBody
    public String fetchFromFirebase(@RequestParam(name = "index", required = false, defaultValue = "0") int index)
            throws IOException, InterruptedException {
        String[] elementResult = { "" };
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("map/" + index);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    elementResult[0] = "empty";
                } else {
                    result.add(dataSnapshot.getValue(String.class));
                    elementResult[0] = (dataSnapshot.getValue(String.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Failed to read value: " + error.toException());
            }
        });
        Thread.sleep(1500);
        if (elementResult[0].isEmpty()) {
            elementResult[0] = "empty";
            return "empty";
        }
        return elementResult[0];
    }

    @GetMapping("/fetchNumIssues")
    @ResponseBody
    public int fetchNumIssues() throws IOException, InterruptedException {
        numIssues = 0;
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("map/numIssues");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numIssues = dataSnapshot.getValue(Integer.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Failed to read value: " + error.toException());
            }
        });
        Thread.sleep(1500);
        return numIssues;
    }

    @GetMapping("/fetchNumCompleted")
    @ResponseBody
    public int fetchNumCompleted() throws IOException, InterruptedException {
        numCompleted = 0;
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("map/numCompleted");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numCompleted = dataSnapshot.getValue(Integer.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Failed to read value: " + error.toException());
            }
        });
        Thread.sleep(1500);
        return numCompleted;
    }

    @GetMapping("/fetchNumReports")
    @ResponseBody
    public int fetchNumReports() throws IOException, InterruptedException {
        numReports = 0;
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("map/numReports");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numReports = dataSnapshot.getValue(Integer.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Failed to read value: " + error.toException());
            }
        });
        Thread.sleep(1500);
        return numReports;
    }

    @GetMapping("/addIssue")
    @ResponseBody
    public String setToFirebase(@RequestParam(name = "title", required = false, defaultValue = "null") String title,
            @RequestParam(name = "description", required = false, defaultValue = "null") String description,
            @RequestParam(name = "contact", required = false, defaultValue = "null") String contact,
            @RequestParam(name = "address", required = false, defaultValue = "null") String address,
            @RequestParam(name = "latLong", required = false, defaultValue = "null") String latLong)
            throws IOException, InterruptedException {

        String[] latLongArr = latLong.split("--", 2);

        String newString = latLongArr[1] + "," + latLongArr[0] + "--" + title + "--" + description + "--" + contact
                + "--" + address + "--" + 0;

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("map/numIssues");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numIssues = dataSnapshot.getValue(Integer.class);

                DatabaseReference ref3 = FirebaseDatabase.getInstance()
                        .getReference("map/numReports");

                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        numReports = dataSnapshot.getValue(Integer.class);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        System.out.println("Failed to read value: " + error.toException());
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Failed to read value: " + error.toException());
            }
        });

        //

        DatabaseReference refSet1 = FirebaseDatabase.getInstance()
                .getReference("map/" + numIssues);
        refSet1.setValueAsync(newString);

        DatabaseReference ref2 = FirebaseDatabase.getInstance()
                .getReference("map/numIssues");
        ref2.setValueAsync(numIssues + 1);

        DatabaseReference ref4 = FirebaseDatabase.getInstance()
                .getReference("map/numReports");
        ref4.setValueAsync(numReports + 1);

        return "";

    }

    @GetMapping("/completeIssue")
    @ResponseBody
    public String markComplete(@RequestParam(name = "title", required = false, defaultValue = "null") String title,
    @RequestParam(name = "numCompleted", required = false, defaultValue = "null") int numCompleted,
            @RequestParam(name = "numIssues", required = false, defaultValue = "null") int numIssues) {
        String[] newString = { "" };
        String[] index = {};
        for (int i = 0; i < numIssues; i++) {

            String mapI = "map/" + i;
            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("map/" + i);

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String snapshot = dataSnapshot.getValue(String.class);

                    String[] arr = snapshot.split("--", 20);

                    String foundTitle = arr[1];
                    if (foundTitle.equals(title)) {

                        // Found Corresponding Issue
                        // --> Determine if # of reports is >= to 1, if so remove from db
                        // --> If # of reports is 0, add 1.
                        DatabaseReference newRef = FirebaseDatabase.getInstance().getReference();

                        int numReports = Integer.valueOf(arr[5]);
                        if (numReports >= 1) {
                            HashMap<String, Object> updates = new HashMap<>();
                            updates.put(mapI, null);
                            newRef.updateChildrenAsync(updates);
                            newString[0] = null;


                            HashMap<String, Object> updates2 = new HashMap<>();
                            updates2.put("map/numIssues", numIssues - 1);
                            newRef.updateChildrenAsync(updates2);

                            HashMap<String, Object> updates3 = new HashMap<>();
                            updates3.put("map/numCompleted", numCompleted +1);
                            newRef.updateChildrenAsync(updates3);

                        } else {
                            String newNumReports = Integer.toString(++numReports);
                            newString[0] = arr[0] + "--" + arr[1] + "--" + arr[2] + "--" + arr[3] + "--" + arr[4] + "--"
                                    + newNumReports;

                            HashMap<String, Object> updates = new HashMap<>();
                            updates.put(mapI, newString[0]);
                            newRef.updateChildrenAsync(updates);

                        }

                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.out.println("Failed to read value: " + error.toException());
                }
            });


        }
        return newString[0];
    }
}