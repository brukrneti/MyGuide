package hr.foi.myguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Jura on 4.2.2018..
 */

public class AddFilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filter);

        final Button btnOK = (Button) findViewById(R.id.button2);
        final RadioGroup radioGrp = (RadioGroup) findViewById(R.id.RGroup);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGrp.getCheckedRadioButtonId();

                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                String selection = (String) radioButton.getText();
                if(selection.contains("Čak")){
                    Intent intent=new Intent();
                    intent.putExtra("RESULT_STRING", "Čakovec");
                    setResult(RESULT_OK, intent);
                    finish();

                };

            }
        });


    }
}