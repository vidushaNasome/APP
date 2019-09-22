package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PartyPlanMain extends AppCompatActivity {


    EditText userName, telNo, eventDate, eventTime, noOfGuests;
    Button showDetails, addEvent, updateEvent, deleteEvent;
    DatabaseReference dbRef;
    Party1 pty = new Party1();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_Item = database.getReference("partyplan");



    private void clearControls() {
        userName.setText("");
        telNo.setText("");
        eventDate.setText("");
        eventTime.setText("");
        noOfGuests.setText("");
    }

    //FirebaseDatabase database =FirebaseDatabase.getInstance();
    //final DatabaseReference table_Party1 =database.getReference("Party");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.partyplanmain);

        userName = findViewById(R.id.userName);
        telNo = findViewById(R.id.telNo);
        eventDate = findViewById(R.id.eventDate);
        eventTime = findViewById(R.id.eventTime);
        noOfGuests = findViewById(R.id.noOfGuests);

        showDetails = findViewById(R.id.showDetails);
        addEvent = findViewById(R.id.addEvent);
        updateEvent = findViewById(R.id.updateEvent);
        deleteEvent = findViewById(R.id.deleteEvent);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEvent();

            }
        });
        deleteEvent.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               delete();
            }
        });


        showDetails.setOnClickListener(new View.OnClickListener() {
          @Override
        public void onClick(View view) {
          show();
        }
        });

        // addEvent.setOnClickListener(new View.OnClickListener() {
        //   @Override
        // public void onClick(View view) {
        //   add();
        //}
        //});

        updateEvent.setOnClickListener(new View.OnClickListener() {
          @Override
        public void onClick(View view) {
          update();
        }
        });

        //deleteEvent.setOnClickListener(new View.OnClickListener() {
          //@Override
        //public void onClick(View view) {
          // delete();
        //}
        //});

        //public void add(){
        //  table_Party1.addValueEventListener(new ValueEventListener() {
        //    @Override
        //  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //    pty.setUserName(userName.getText().toString().trim());
        //  pty.setTelNo(telNo.getText().toString().trim());
        //pty.setNoOfGuests(Integer.parseInt(noOfGuests.getText().toString().trim()));
        //table_Party1.child(pty.getTelNo().setValue(pt))

        //@Override
        //public void onCancelled(@NonNull DatabaseError databaseError) {

        //}
        //})
        //}


    }

    private void setEvent() {

        dbRef = FirebaseDatabase.getInstance().getReference().child(telNo.getText().toString().trim());

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("partyplan");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    pty.setUserName(userName.getText().toString().trim());
                    pty.setTelNo(telNo.getText().toString().trim());
                    pty.setEventDate(eventDate.getText().toString().trim());
                    pty.setEventTime(eventTime.getText().toString().trim());
                    pty.setNoOfGuests(Integer.parseInt(noOfGuests.getText().toString().trim()));

                    dbRef = FirebaseDatabase.getInstance().getReference().child("partyplan").child(telNo.getText().toString());
                    dbRef.setValue(pty);

                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    clearControls();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid Telephone Number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void delete() {
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("partyplan");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChild(telNo.getText().toString().trim())) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("partyplan").child(telNo.getText().toString().trim());
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(), "Data Delete Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();

                }catch (Exception e){Toast.makeText(getApplicationContext(), "Please enter a phone number to delete the event", Toast.LENGTH_SHORT).show();}
            }

           @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void show(){


        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("partyplan").child(telNo.getText().toString());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChildren()) {
                        userName.setText(dataSnapshot.child("userName").getValue().toString());
                        telNo.setText(dataSnapshot.child("telNo").getValue().toString());
                        eventDate.setText(dataSnapshot.child("eventDate").getValue().toString());
                        eventTime.setText(dataSnapshot.child("eventTime").getValue().toString());
                        noOfGuests.setText(dataSnapshot.child("noOfGuests").getValue().toString());

                    } else {
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Please enter a phone number to show the event", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dbRef = FirebaseDatabase.getInstance().getReference().child(telNo.getText().toString().trim());
    }

    public void update() {
        dbRef = FirebaseDatabase.getInstance().getReference().child(telNo.getText().toString().trim());

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("partyplan");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    pty.setUserName(userName.getText().toString().trim());
                    pty.setTelNo(telNo.getText().toString().trim());
                    pty.setEventDate(eventDate.getText().toString().trim());
                    pty.setEventTime(eventTime.getText().toString().trim());
                    pty.setNoOfGuests(Integer.parseInt(noOfGuests.getText().toString().trim()));

                    dbRef = FirebaseDatabase.getInstance().getReference().child("partyplan").child(telNo.getText().toString());
                    dbRef.setValue(pty);

                    Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                    clearControls();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid Telephone Number", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dbRef = FirebaseDatabase.getInstance().getReference().child(telNo.getText().toString().trim());

        //try {
        ///   if (TextUtils.isEmpty(userName.getText().toString()))
        //        Toast.makeText(getApplicationContext(),"Please enter user name",Toast.LENGTH_SHORT).show();
        //      else if (TextUtils.isEmpty(telNo.getText().toString()))
        //         Toast.makeText(getApplicationContext(),"Please enter telephone number",Toast.LENGTH_SHORT).show();
        //     else if (TextUtils.isEmpty(eventDate.getText().toString()))
        //   Toast.makeText(getApplicationContext(),"Please enter event date",Toast.LENGTH_SHORT).show();
        //  else {
        //      pty.setUserName(userName.getText().toString().trim());
        //     pty.setTelNo(telNo.getText().toString().trim());
        //      pty.setEventDate(eventDate.getText().toString().trim());
        //     pty.setEventTime(eventTime.getText().toString().trim());
        //        pty.setNoOfGuests(Integer.parseInt(noOfGuests.getText().toString().trim()));
//
        //      dbRef.push().setValue(pty);

        //     Toast.makeText(getApplicationContext(),"Data update Successfully",Toast.LENGTH_SHORT).show();
        //      clearControls();
        // }
        // }
        //  catch (NumberFormatException e){
        //    Toast.makeText(getApplicationContext(),"Invalid Telephone Number",Toast.LENGTH_SHORT).show();
        //   }
    }

   // public void delete(){
     //   DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("partyPlan");
       // delRef.addListenerForSingleValueEvent(new ValueEventListener() {
         //   @Override
           // public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             //   if (dataSnapshot.hasChild("telNo")){
               //     dbRef =FirebaseDatabase.getInstance().getReference().child("partyPlan").child("telNo");
                 //   dbRef.removeValue();
                   // clearControls();

                   //Toast.makeText(getApplicationContext(),"Data Deleted Successfully",Toast.LENGTH_SHORT).show();


                //}
                //else
                  //  Toast.makeText(getApplicationContext(),"No Source to Delete",Toast.LENGTH_SHORT).show();
            //}

            //@Override
            //public void onCancelled(@NonNull DatabaseError databaseError) {

            //}
        //});
   // }




}
