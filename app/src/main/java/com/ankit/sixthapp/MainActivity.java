package com.ankit.sixthapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.ankit.sixthapp.R.id.quantity;

public class MainActivity extends AppCompatActivity {

    int quanti = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll = (LinearLayout) findViewById(R.id.email);
        ll.setVisibility(View.GONE);
        TextView t1 = (TextView) findViewById(R.id.summary);
        t1.setVisibility(View.GONE);
        TextView t2 = (TextView) findViewById(R.id.ordersummary);
        t2.setVisibility(View.GONE);
    }

    public void reset(View view) {
        //Naming Field
        EditText et = (EditText) findViewById(R.id.naming);
        et.setText(" ");
        //Check boxes
        CheckBox whippedcream = (CheckBox) findViewById(R.id.whippedcream);
        whippedcream.setChecked(false);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        chocolate.setChecked(false);
        //quantity
        quanti = 2;
        display(quanti);
        //Summary
//        TextView textprice = (TextView) findViewById(R.id.ordersummary);
//        textprice.setText("0 $");
        //mail address
        EditText mailid = (EditText) findViewById(R.id.mail);
        mailid.setText(" ");
        //visibility
        LinearLayout ll = (LinearLayout) findViewById(R.id.email);
        ll.setVisibility(View.GONE);
        TextView t1 = (TextView) findViewById(R.id.summary);
        t1.setVisibility(View.GONE);
        TextView t2 = (TextView) findViewById(R.id.ordersummary);
        t2.setVisibility(View.GONE);

    }

    public void mail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //address portion
        EditText mailid = (EditText) findViewById(R.id.mail);
        String mid = mailid.getText().toString();
        intent.putExtra(Intent.EXTRA_EMAIL, mid);
        //Subject portion
        EditText nam = (EditText) findViewById(R.id.naming);
        String name = nam.getText().toString();
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order summary for " + name);
        //Body portion
        //    intent.putExtra()
        //check for mail app
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void submitorder(View view) {
        int cost = 5;

        EditText nam = (EditText) findViewById(R.id.naming);
        String name = nam.getText().toString();

        //check boxex
        CheckBox whippedcream = (CheckBox) findViewById(R.id.whippedcream);
        boolean whipcrm = whippedcream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean choco = chocolate.isChecked();

        //Summary Message
        String message = createsummarymessage(name, quanti, cost, whipcrm, choco);
        displaymessage(message);

        //visibility
        LinearLayout ll = (LinearLayout) findViewById(R.id.email);
        ll.setVisibility(View.VISIBLE);
        TextView t1 = (TextView) findViewById(R.id.summary);
        t1.setVisibility(View.VISIBLE);
        TextView t2 = (TextView) findViewById(R.id.ordersummary);
        t2.setVisibility(View.VISIBLE);


        //Log.v("MainActivity","Has whipped cream :" + whipcrm);
    }

    public String createsummarymessage(String name, int quanti, int cost, boolean whipcrm, boolean choco) {
        int extra = 0;
        if (whipcrm)
            extra += 1;
        if (choco)
            extra += 2;
        cost = cost * quanti;
        if (extra != 0)
            cost = cost * extra;
        String message;
        message = "Name:" + name;
        message += "\nAddOns:";
        message += "\n\tWhipped Cream : " + whipcrm;
        message += "\n\tChocolate     : " + choco;
        message += "\nQuantity = " + quanti;
        message += "\n Total = " + cost + " $";
        message += "\n Thankyou";
        return message;
    }

    public void displaymessage(String message) {
        TextView textprice = (TextView) findViewById(R.id.ordersummary);
        textprice.setText(message);
    }

    public void display(int num) {
        TextView tex = (TextView) findViewById(quantity);
        tex.setText("" + num);
    }

    public void increment(View view) {
        if (quanti < 100) {
            quanti = quanti + 1;
        } else
            Toast.makeText(this, "You Cannot have more than 99 coffee", Toast.LENGTH_SHORT).show();
        display(quanti);
    }

    public void decrement(View view) {
        if (quanti > 1) {
            quanti = quanti - 1;
        } else
            Toast.makeText(this, "You Cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();

        display(quanti);
    }

}
