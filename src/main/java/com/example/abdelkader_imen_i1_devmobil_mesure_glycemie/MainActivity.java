package com.example.abdelkader_imen_i1_devmobil_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //choisir les attributs qui vont interagir dans l'application
    private SeekBar sbAge ;
    private RadioButton rbIsFasting, rbIsNotFasting;
    private EditText etMesureGlycemie;
    private Button btnConsulter;
    private TextView tvReponse,tvAge;

//activité en état d'execution avec onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// charge interface en plus de la  syncronisation entre logique et interface
        // init() est appelée dans onCreate pour  lier les attributs de classe avec leurs composants graphiques
        init();
        //les listeners fonctionnent avec le  coté hardware de l'appareil (en arriere plan)
        //  définir le listener du seekBar sbAge  en instanciant  l'interface OnSeekBarChangeListener à traver  la classe abstaite SeekBar
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            // Cette méthode est appelée lorsque la progression de la barre de recherche est modifiée.
            //SeekBar seekBar instance (This) qui lance le traitement
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //visualiser le progress  dans LogCat avec un tag personnalisé Information pour le suivi
                Log.i("Information","On Progress Change : "+ progress);
                tvAge.setText("Votre âge : "+ progress);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    btnConsulter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Calculer();
        }
    });

    }
 public void init(){
        tvAge = findViewById(R.id.tvAge); // type de retour par defaut c'est view de findviewbyId donc elle a besoin d'un casting (TextView) mais il s'effectue automatiquement
         tvReponse = findViewById(R.id.tvReponse);
         btnConsulter = findViewById(R.id.btnConsulter);
         etMesureGlycemie = findViewById(R.id.etMesureGlycemie);
         rbIsFasting= findViewById(R.id.rbtIsFasting);
         rbIsNotFasting= findViewById(R.id.rbtIsNotFasting);
         sbAge =findViewById(R.id.sbAge);

 }
    private void Calculer() {
        Log.i("Information","OnClick sur le bouton  btnConsulter");
        int age ;
        float valeur;
        boolean verifAge=false, verifValeur=false;
        if(sbAge.getProgress()!=0){
            verifAge= true;
        }
         else
            Toast.makeText(MainActivity.this,"Veillez saisir Votre âge",Toast.LENGTH_SHORT);
         if (etMesureGlycemie.getText().toString().isEmpty()){
             Toast.makeText(MainActivity.this,"Veillez saisir Votre valeur mesurée",Toast.LENGTH_LONG);

         }else
             verifValeur= true;
        if(verifAge && verifValeur){
            age = sbAge.getProgress();
            valeur = Float.valueOf(etMesureGlycemie.getText().toString());
            if(rbIsFasting.isChecked()){
                if(age>= 13)
                    if(valeur< 5.0)
                        tvReponse.setText("Niveau de glycémie est trop bas");
                    else if(valeur>= 5.0 && valeur <= 7.2)
                        tvReponse.setText("Niveau de glycémie est normal");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
                else if(age>= 6 && age<=12)
                    if(valeur< 5.0)
                        tvReponse.setText("Niveau de glycémie est bas");
                    else if(valeur>= 5.0 && valeur <= 10.0)
                        tvReponse.setText("Niveau de glycémie est normal");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
                else if(age < 6)
                    if(valeur< 5.5)
                        tvReponse.setText("Niveau de glycémie est bas");
                    else if(valeur>= 5.5 && valeur <= 10.0)
                        tvReponse.setText("Niveau de glycémie est normal");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
            } else {
                if (valeur > 10.5)
                    tvReponse.setText("Niveau de glycémie est trop élevé");
                else
                    tvReponse.setText("Niveau de glycémie est normale");
            }
        }


    }
}