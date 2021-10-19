package es.joseljg.examenhamburguesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Ventana2 extends AppCompatActivity {

    TextView txt_hamburguesa = null;
    TextView txt_complemento = null;
    TextView txt_hamburguesa_precio = null;
    TextView txt_complemento_precio = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana2);
        txt_hamburguesa = (TextView) findViewById(R.id.txt_hamburguesa) ;
        txt_complemento = (TextView) findViewById(R.id.txt_complemento);
        txt_hamburguesa_precio = (TextView) findViewById(R.id.txt_hamburguesa_precio) ;
        txt_complemento_precio = (TextView) findViewById(R.id.txt_complemento_precio);
        //-------------------------------------------------
        Intent intent = getIntent();
        if(intent != null)
        {
            String hamburguesa = intent.getStringExtra(MainActivity.EXTRA_HAMBURGUESA);
            txt_hamburguesa.setText("hamburguesa: \n" + hamburguesa);
            String complemento = intent.getStringExtra(MainActivity.EXTRA_COMPLEMENTO);
            txt_complemento.setText("complemento: \n" + complemento);
            //-------------------------------------------------------------------------------
            double precioHamburguesa = calcularPrecioHamburguesa(hamburguesa);
            txt_hamburguesa_precio.setText(String.valueOf(precioHamburguesa)+ "€");
            double precioComplemento = calcularPrecioComplemento(complemento);
            txt_complemento_precio.setText(String.valueOf(precioComplemento) + "€");
            //-------------------------------------------------------------------------------
        }
    }

    private double calcularPrecioComplemento(String complemento) {
        if(complemento.equalsIgnoreCase("coca cola"))
        {
            return 2.5;
        }
        else if(complemento.equalsIgnoreCase("patatas"))
        {
            return 2.0;
        }
        return 0.0;
    }

    private double calcularPrecioHamburguesa(String hamburguesa) {
        if(hamburguesa.equalsIgnoreCase("mac pollo"))
        {
            return 3.0;
        }
        else if(hamburguesa.equalsIgnoreCase("xxl"))
        {
            return 5.0;
        }
        return 0.0;
    }
}