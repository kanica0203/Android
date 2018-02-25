package e.ahuja.songsplaylist;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    String []Titles={"Closer","We Don't Talk Anymore","Perfect","Meant To Be","Nazm Nazm"};
    String []Artist={"ChainSmoker", "Charlie Puth","Ed Sheeren","Bebe Rehxa","Ayushman"};
    String[] uris={"https://youtu.be/PT2_F-1esPk","https://youtu.be/3AtDnEC4zak","https://youtu.be/2Vv-BfVoq4g",
            "https://youtu.be/zDo0H8Fm7d0","https://youtu.be/DK_UsATwoxI"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= (ListView)findViewById(R.id.list);

        CustomAdapter customAdapter =new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long it) {

                Intent aIntent = new Intent(Intent.ACTION_VIEW);
                aIntent.setData(Uri.parse(uris[position])) ;
                aIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
                startActivity(aIntent) ;
            }
        }) ;

    }


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Titles.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=getLayoutInflater().inflate(R.layout.list_item,null);
            TextView title = (TextView)view.findViewById(R.id.title);
            TextView artist =(TextView)view.findViewById(R.id.artist);

            title.setText(Titles[i]);
            artist.setText(Artist[i]);
            return view;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.add:
                Toast.makeText(this,"Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove:
                Toast.makeText(this,"Remove",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onPause() {
        super.onPause() ;
        setResult(RESULT_OK) ;
    }

}
