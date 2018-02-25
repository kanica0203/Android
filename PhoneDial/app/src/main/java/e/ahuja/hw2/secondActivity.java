package e.ahuja.hw2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class secondActivity extends Activity {
    EditText editText1;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText1.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override public boolean onEditorAction(TextView textView, int id, KeyEvent event){
                if (id == EditorInfo.IME_ACTION_DONE || id==EditorInfo.IME_ACTION_GO)
                {
                    match();
                }
                return false;
            }});
    }

    public void match(){

        editText1=(EditText)findViewById(R.id.editText1);
        String message=editText1.getText().toString();
        Pattern p = Pattern.compile("\\s*\\(\\d{3}\\)\\s\\d{3}-\\d{4}\\s*");
        Matcher m = p.matcher(message);
        Intent intent= new Intent();
        if(m.matches())
        {
            intent.putExtra("RESULT_OK",message);
            setResult(Activity.RESULT_OK,intent);
        }
        else{
            intent.putExtra("RESULT_CANCELED",message);
            setResult(Activity.RESULT_CANCELED,intent);
        }
        finish();
    }
}