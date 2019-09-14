package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CardDelivery extends AppCompatActivity {
    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;
    CreditCard card;

    DatabaseReference dbRef;
    DatabaseReference upRef;
    DatabaseReference deleteRef;
    DatabaseReference shRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_delivery);

        cardForm=findViewById(R.id.card_form);
        buy=findViewById(R.id.btnBuy);

        card = new CreditCard();

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(CardDelivery.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardForm.isValid())
                {
                    alertBuilder=new AlertDialog.Builder(CardDelivery.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card number:"+cardForm.getCardNumber()+"\n"+
                            "Card expiry date:"+cardForm.getExpirationDateEditText().getText().toString()+"\n"+
                            "Card CVV:"+cardForm.getCvv()+"\n"+
                            "Postal Code"+cardForm.getPostalCode()+"\n"+
                            "Phone Number"+cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(CardDelivery.this,"Thank you for purchase",Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog=alertBuilder.create();
                    alertDialog.show();
                }else{
                    Toast.makeText(CardDelivery.this,"Please complete the form",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

        private void addCard(){
            dbRef = FirebaseDatabase.getInstance().getReference().child("CreditCard");

            try {
                 if (TextUtils.isEmpty(cardForm.getCardNumber()))
                     Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                 else if (TextUtils.isEmpty(cardForm.getExpirationDateEditText().getText().toString()))
                     Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                 else if (TextUtils.isEmpty(cardForm.getCvv()))
                     Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                 else if (TextUtils.isEmpty(cardForm.getPostalCode()))
                     Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                 else if (TextUtils.isEmpty(cardForm.getCountryCode()))
                     Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                 else if (TextUtils.isEmpty(cardForm.getMobileNumber()))
                     Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                 else {
                     card.setForm1(cardForm.getCardNumber().trim());
                     card.setForm1(cardForm.getCountryCodeEditText().toString().trim());
                     card.setForm1(cardForm.getCvv().trim());
                     card.setForm1(cardForm.getPostalCode().trim());
                     card.setForm1(cardForm.getPostalCode().trim());

                     dbRef.push().setValue(card);

                 }
                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException ex) {
                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();

            }

        }
    }

