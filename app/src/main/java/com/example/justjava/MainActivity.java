
package com.example.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.justjava.R.id.order_summary;

public class MainActivity extends AppCompatActivity {
int quantity = 2;
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
        int price = calculatePrice();
        String summary = createSummaryOrder(price);
        displayMessage(summary);
        Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show();


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
        return " Name: " + namefororder + "\n Quantity " + quantity  + "\n Want Whipped cream? " + whippedcream_check + "\n Want Chocolate cream? " + chocolatecream_check + "\n Total : $" + price + "\n Thankyou!";
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayMessage(String message) {
        TextView ordersummary = (TextView) findViewById(order_summary);
        ordersummary.setText(message);
    }
}