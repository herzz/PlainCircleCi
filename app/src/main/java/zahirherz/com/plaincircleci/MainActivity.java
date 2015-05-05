package zahirherz.com.plaincircleci;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import zahirherz.com.plaincircleci.model.Flower;
import zahirherz.com.plaincircleci.network.api;


public class MainActivity extends ListActivity {
    List<Flower> flowerList;


    @InjectView(R.id.button_main_1)
    Button button_main_1;
    @InjectView(R.id.editText_main)
    EditText editText_main;
    @InjectView(R.id.radioButton_1)
    RadioButton radioButton_1;
    @InjectView(R.id.radioButton_2)
    RadioButton radioButton_2;
    @InjectView(R.id.checkBox_main)
    CheckBox checkBox_main;
    @InjectView(R.id.seekBar_main)
    SeekBar seekBar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://services.hanselandpetal.com").build();

        api flowerapi = restAdapter.create(api.class);

        flowerapi.getData(new Callback<List<Flower>>() {
            @Override
            public void success(List<Flower> flowers, Response response) {
                flowerList = flowers;
                adapter adapt = new adapter(getApplicationContext(), R.layout.item_file, flowerList);
                setListAdapter(adapt);
            }

            @Override
            public void failure(RetrofitError error) {
                Crouton.makeText(MainActivity.this, "FAILED " + error, Style.CONFIRM).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnCheckedChanged(R.id.radioButton_1)
    public void onChecked1(boolean checked) {
        if (checked) {
            Crouton.makeText(MainActivity.this, "Radio 1 Chekced", Style.CONFIRM).show();
        } else {
            Crouton.makeText(MainActivity.this, "Radio 2 Checked", Style.ALERT).show();
        }
    }



    @OnClick(R.id.button_main_1)
    public void sayHi(Button button) {
        //button.setText("Hello!");
        Crouton.makeText(MainActivity.this, "Button Was Pressed!", Style.ALERT).show();
    }
}
