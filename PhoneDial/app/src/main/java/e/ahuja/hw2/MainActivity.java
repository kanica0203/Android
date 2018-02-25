package e.ahuja.hw2;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import e.ahuja.hw2.R;
import e.ahuja.hw2.secondActivity;

public class MainActivity extends Activity {
    TextView textView1;
    Button button1;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=(TextView)findViewById(R.id.textView1);
        button1=(Button)findViewById(R.id.button1);
        button1.requestFocus();
        button1.setBackgroundColor(Color.GREEN);
        button2=(Button)findViewById(R.id.button2);
        button2.setEnabled(false);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(MainActivity.this,secondActivity.class);
                startActivityForResult(intent, 2);// Activity is started with requestCode 2
            }
        });
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            button1.clearFocus();
            button1.setBackgroundColor(Color.GRAY);
            button2.setEnabled(true);
            button2.setBackgroundColor(Color.GREEN);
            button2.requestFocus();
            if(resultCode==Activity.RESULT_OK){
                final String message=data.getStringExtra("RESULT_OK");
                button2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+message));
                        intent.setData(Uri.parse("tel:"+ message));
                        startActivity(intent);
                    }
                });

               button2.setOnLongClickListener(new View.OnLongClickListener() {
                   @Override
                   public boolean onLongClick(View view) {
                       Toast.makeText(getApplicationContext()," use short press", Toast.LENGTH_SHORT).show();

                       return true;
                   }
               });


            }

            else{
                final String message=data.getStringExtra("RESULT_CANCELED");
                button2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect format of number entered: "+message;
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });

            }

        }
    }
}

