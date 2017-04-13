
package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
int quantity = 2;
    String summarymessage;
    boolean whippedcream_check,chocolatecream_check;
    String namefororder;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void submitOrder(View view) {

        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.whippedcream);
        whippedcream_check = hasWhippedCream.isChecked();
        CheckBox hasChocolateCream = (CheckBox) findViewById(R.id.choclatecream);
        chocolatecream_check = hasChocolateCream.isChecked();
        EditText name = (EditText) findViewById(R.id.nameorder);
        namefororder = name.getText().toString();
        Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show();
        String mail_from = "prabhjyotgambhir1994@gmail.com";
        String subject = "Order Summary for : " + namefororder;
        int price = calculatePrice();
        createSummaryOrder(price);
        Intent send_email = new Intent(Intent.ACTION_SENDTO);
        send_email.setData(Uri.parse("mailto:"));
        send_email.putExtra(Intent.EXTRA_EMAIL,mail_from);
        send_email.putExtra(Intent.EXTRA_SUBJECT,subject);
        send_email.putExtra(Intent.EXTRA_TEXT,summarymessage);
        if(send_email.resolveActivity(getPackageManager()) !=null)
        {
            startActivity(send_email);
        }


    }
    public void increment(View view) {

        if(quantity==100)
        {
            Toast.makeText(this, "You cannot place more than 100 cups!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);

    }
    public void decrement(View view){
        if(quantity==1)
        {
            Toast.makeText(this, "You cannot place less then 1 cup!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);

    }
    public int calculatePrice()
    {
        int price = quantity * 5;
        if(whippedcream_check)
        {
            price = price + quantity * 1;
        }
        if(chocolatecream_check)
        {
            price = price + quantity * 2;
        }
        return price;
    }
    public String createSummaryOrder(int price)
    {
         summarymessage = " Name: " + namefororder + "\n Quantity " + quantity  + "\n Want Whipped cream? " + whippedcream_check + "\n Want Chocolate cream? " + chocolatecream_check + "\n Total : $" + price + "\n Thankyou!";
        return summarymessage;
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}