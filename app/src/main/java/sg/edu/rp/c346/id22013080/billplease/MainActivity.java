package sg.edu.rp.c346.id22013080.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    EditText dis;
    RadioGroup payment;
    Button splitBtn;
    Button resetBtn;

    TextView totalAmt;
    TextView splitAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.AmountInput);
        pax = findViewById(R.id.NumOfPaxInput);
        dis = findViewById(R.id.DiscountInput);
        svs = findViewById(R.id.svsToggle);
        gst = findViewById(R.id.gstToggle);
        totalAmt = findViewById(R.id.totalAmt);
        splitAmt = findViewById(R.id.splitAmt);
        payment = findViewById(R.id.paymentOption);
        splitBtn = findViewById(R.id.splitBtn);
        resetBtn = findViewById(R.id.resetBtn);




        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalAmount = 0;
                double splitAmount = 0;
                double discount = 0;

                String amountConvert = amount.getText().toString();

                String paxConvert = pax.getText().toString();

                String disConvert = dis.getText().toString();

                int paymentMethod = payment.getCheckedRadioButtonId();
                if(amount.getText().toString().length()==0 && pax.getText().toString().length()==0) {
                    amount.setError("Cannot be left empty");
                    pax.setError("Cannot be left empty");
                }
                else if (amount.getText().toString().length()!=0 && pax.getText().toString().length()==0) {
                    pax.setError("Cannot be left empty");
                }
                else if(amount.getText().toString().length()==0 && pax.getText().toString().length()!=0) {
                    amount.setError("Cannot be left empty");
                }
                else{
                    double AmtToPay = Double.parseDouble(amountConvert);
                    int paxTotal = Integer.parseInt(paxConvert);
                    if(svs.isChecked() && gst.isChecked()) {
                        totalAmount = AmtToPay * 1.1;
                        totalAmount *= 1.08;

                        if (disConvert.length() != 0){
                            discount = Double.parseDouble(disConvert);
                            totalAmount *= (1 - (discount/100));
                            splitAmount = totalAmount/paxTotal;
                        }
                        else{
                            splitAmount = totalAmount/paxTotal;
                        }
                        if(paymentMethod == R.id.cashOption){
                            totalAmt.setText(String.format("Total Amount: $%.2f",totalAmount));
                            splitAmt.setText(String.format("Each Pays: $%.2f", splitAmount));
                        }
                        else{
                            totalAmt.setText(String.format("Total Amount: $%.2f",totalAmount));
                            splitAmt.setText(String.format("Each Pays: $%.2f via PayNow to 912345678", splitAmount));
                        }


                    }
                    else if (svs.isChecked() && !gst.isChecked()) {
                        totalAmount = AmtToPay * 1.1;

                        if (disConvert.length() != 0){
                            discount = Double.parseDouble(disConvert);
                            discount /= 100;
                            totalAmount *= (1 - (discount/100));
                            splitAmount = totalAmount/paxTotal;
                        }
                        else{
                            splitAmount = totalAmount/paxTotal;
                        }
                        if(paymentMethod == R.id.cashOption){
                            totalAmt.setText(String.format("Total Amount: $%.2f",totalAmount));
                            splitAmt.setText(String.format("Each Pays: $%.2f", splitAmount));
                        }
                        else{
                            totalAmt.setText(String.format("Total Amount: $%.2f",totalAmount));
                            splitAmt.setText(String.format("Each Pays: $%.2f via PayNow to 912345678", splitAmount));
                        }

                    }
                    else if (gst.isChecked() && !svs.isChecked()) {
                        totalAmount = AmtToPay * 1.08;

                        if (disConvert.length() != 0){
                            discount = Double.parseDouble(disConvert);
                            totalAmount *= (1 - (discount/100));
                            splitAmount = totalAmount/paxTotal;
                        }
                        else{
                            splitAmount = totalAmount/paxTotal;
                        }
                        if(paymentMethod == R.id.cashOption){
                            totalAmt.setText(String.format("Total Amount: $%.2f",totalAmount));
                            splitAmt.setText(String.format("Each Pays: $%.2f", splitAmount));
                        }
                        else{
                            totalAmt.setText(String.format("Total Amount: $%.2f",totalAmount));
                            splitAmt.setText(String.format("Each Pays: $%.2f via PayNow to 912345678", splitAmount));
                        }
                    }
                    else{
                        totalAmount = AmtToPay;
                        if (disConvert.length() != 0){
                            discount = Double.parseDouble(disConvert);
                            totalAmount *= (1 - (discount/100));
                            splitAmount = totalAmount/paxTotal;
                        }
                        else{
                            splitAmount = totalAmount/paxTotal;
                        }
                        if(paymentMethod == R.id.cashOption){
                            totalAmt.setText(String.format("Total Amount: $%.2f",totalAmount));
                            splitAmt.setText(String.format("Each Pays: $%.2f", splitAmount));
                        }
                        else{
                            totalAmt.setText(String.format("Total Amount: $%.2f",totalAmount));
                            splitAmt.setText(String.format("Each Pays: $%.2f via PayNow to 912345678", splitAmount));
                        }
                }


                }
            }

        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                payment.setEnabled(false);
                dis.setText("");
                totalAmt.setText("");
                splitAmt.setText("");

            }
        });


    }
}