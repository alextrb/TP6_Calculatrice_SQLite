package calculatricefragmentv2.moi.android.fr.tp4_fragments_calculatricev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditTextFragmentActivity extends AppCompatActivity implements EditTextFragment.OnFragmentInteractionListener {

    EditTextFragment etf;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_fragment);

        edittext = (EditText) findViewById(R.id.editText);

        etf = EditTextFragment.newInstance(); // on crée un nouveau fragment contenant le EditText
        getSupportFragmentManager().beginTransaction().add(R.id.edittext_fragment, etf).commit(); // On l'ajoute dans le FrameLayout


        edittext.addTextChangedListener(new TextWatcher() { // Listener détectant si on effectue une action dans le EditText (pas celui du fragment)
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /// Si on écrit dans le EditText (pas celui du fragment)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /// Si le EditText (pas celui du fragment) a le focus dans l'activité
                /// Déterminer le focus permet d'éviter qu'on modifie indéfiniment les EditText puisque ça fait une boucle sans fin (L'un modifie l'autre qui modifie l'autre qui modifie l'autre ....)
                if(edittext.hasFocus())
                {
                    etf.setTextEditTextFragment(charSequence); // On modifie également le EditText du fragment
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calc) {
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
        Méthode appelé par le fragment contenant l'EditText
        Permet de modifier l'EditText de l'activité
    */
    @Override
    public void setEditTextActivity(CharSequence s) {
        edittext.setText(s);
    }

    @Override
    public void onBackPressed()
    {

    }
}

