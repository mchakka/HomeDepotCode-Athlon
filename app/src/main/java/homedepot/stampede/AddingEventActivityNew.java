package homedepot.stampede;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;


public class AddingEventActivityNew extends AppCompatActivity {
    private final String USER_AGENT = "Chrome/68.0.3440.106";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_event_new);

        Button SubmitButton = (Button) findViewById(R.id.button);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit = (EditText)findViewById(R.id.editText1);
                final String result1 = edit.getText().toString();

                EditText edit2 = (EditText)findViewById(R.id.editText2);
                final String result2 = edit2.getText().toString();

                EditText edit3 = (EditText)findViewById(R.id.editText3);
                final String result3 = edit3.getText().toString();

                EditText edit4 = (EditText)findViewById(R.id.editText4);
                final String result4 = edit4.getText().toString();

                EditText edit6 = (EditText)findViewById(R.id.editText6);
                final String result6 = edit6.getText().toString();

                EditText edit7 = (EditText)findViewById(R.id.editText7);
                final String result7 = edit7.getText().toString();



                class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
                    protected Boolean doInBackground(Void... params) {
                        // TODO: attempt authentication against a network service.

                        try {
                            // Simulate network access.
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            return false;
                        }

                        String mUrl = String.format("http://stampede-codeathlon.herokuapp.com/addEvent/?name='"+result1+"'&location='"+result2+"'&organization='"+result3+"'&day='"+result4+"'&time='"+result6+"'&description='"+result7+"'");

                        int mKey = sendHttpRequest(mUrl);

                        // TODO: register the new account here.
                        return true;
                    }
                }

                System.out.print("COOOOOO");
            }
        });
    }



    private int sendHttpRequest(String url) {
        int returnVal = 0;
        try {
           // System.out.println("URL [" + url + "] - email [" + email + "]");

            HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestMethod("GET");
            con.connect();

            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONObject object = new JSONObject(jsonText);
                returnVal = (Integer) object.get("ID");
            } finally {
                is.close();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return returnVal;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
