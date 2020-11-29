package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText inputid,inputname,inputaddress;
    Button insert,update,delete,view;
    dbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputid=findViewById(R.id.inputid);
        inputname=findViewById(R.id.inputname);
        inputaddress=findViewById(R.id.inputaddress);

        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        view=findViewById(R.id.view);
        DB=new dbHelper(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentID=inputid.getText().toString();
                String Name=inputname.getText().toString();
                String Ad=inputaddress.getText().toString();

                Boolean checkinsertdata=DB.insertuserdata(studentID,Name,Ad);
                if (checkinsertdata==true)
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();



            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentID=inputid.getText().toString();
                String Name=inputname.getText().toString();
                String Ad=inputaddress.getText().toString();

                Boolean checkupdatedata=DB.updateuserdata(studentID,Name,Ad);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_SHORT).show();



            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentID=inputid.getText().toString();

                Boolean checkudeletedata=DB.deletedata(studentID);
                if (checkudeletedata==true)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();



            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Data Exists",Toast.LENGTH_SHORT).show();
                    return;
                }



                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){

                    buffer.append("Student ID: "+res.getString(0)+"\n");
                    buffer.append("Name: "+res.getString(1)+"\n");
                    buffer.append("Address: "+res.getString(2)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Information");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });






    }
}