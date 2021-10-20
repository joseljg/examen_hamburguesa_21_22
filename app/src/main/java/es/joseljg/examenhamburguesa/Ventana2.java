package es.joseljg.examenhamburguesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ventana2 extends AppCompatActivity {

    private TextView txt_hamburguesa = null;
    private TextView txt_complemento = null;
    private TextView txt_hamburguesa_precio = null;
    private TextView txt_complemento_precio = null;
    private TextView txt_cupon_precio = null;
    private TextView txt_iva_precio = null;
    private TextView txt_total_precio = null;
    private EditText edt_codigo_cupon = null;

    private double precioHamburguesa = 0.0;
    private double precioComplemento = 0.0;
    private double cupon = 0.0;
    private double precioIva = 0.0;
    private double totalPrecio= 0.0;
    private final double IVA = 0.21;
    //---------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana2);
        txt_hamburguesa = (TextView) findViewById(R.id.txt_hamburguesa) ;
        txt_complemento = (TextView) findViewById(R.id.txt_complemento);
        txt_hamburguesa_precio = (TextView) findViewById(R.id.txt_hamburguesa_precio) ;
        txt_complemento_precio = (TextView) findViewById(R.id.txt_complemento_precio);
        txt_cupon_precio = (TextView) findViewById(R.id.txt_cupon_precio) ;
        txt_iva_precio = (TextView) findViewById(R.id.txt_iva_precio);
        txt_total_precio = (TextView) findViewById(R.id.txt_total_precio);
        edt_codigo_cupon = (EditText) findViewById(R.id.edt_codigo);
        //-------------------------------------------------
        Intent intent = getIntent();
        if(intent != null)
        {
            String hamburguesa = intent.getStringExtra(MainActivity.EXTRA_HAMBURGUESA);
            txt_hamburguesa.setText("hamburguesa: \n" + hamburguesa);
            String complemento = intent.getStringExtra(MainActivity.EXTRA_COMPLEMENTO);
            txt_complemento.setText("complemento: \n" + complemento);
            //-------------------------------------------------------------------------------
            precioHamburguesa = calcularPrecioHamburguesa(hamburguesa);
            txt_hamburguesa_precio.setText(String.valueOf(precioHamburguesa)+ "€");
            precioComplemento = calcularPrecioComplemento(complemento);
            txt_complemento_precio.setText(String.valueOf(precioComplemento) + "€");
            //-------------------------------------------------------------------------------
            txt_cupon_precio.setText(String.valueOf(cupon)+ "€");
            precioIva = (precioHamburguesa+precioComplemento-cupon)*IVA;
            txt_iva_precio.setText(String.valueOf(precioIva)+ "€");
            totalPrecio = (precioHamburguesa + precioComplemento - cupon) + precioIva;
            txt_total_precio.setText(String.valueOf(totalPrecio) + "€");
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


    private double validarCupon(String elcupon) {
        double premio = 0.0;
        if(elcupon.equals("MAC123"))
        {
            premio = 1.0;
        }
        return premio;
    }

    public void aplicar(View view) {
        String textocupon = String.valueOf(edt_codigo_cupon.getText());
        cupon = validarCupon(textocupon);
        if(cupon == 0)
        {
            Toast.makeText(this,"CUPON NO PREMIADO", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"HAS OBTENIDO UN DESCUENTO", Toast.LENGTH_SHORT).show();
        }
        txt_cupon_precio.setText(String.valueOf(cupon)+ "€");
        precioIva = (precioHamburguesa+precioComplemento-cupon)*IVA;
        txt_iva_precio.setText(String.valueOf(precioIva)+ "€");
        totalPrecio = (precioHamburguesa + precioComplemento - cupon) + precioIva;
        txt_total_precio.setText(String.valueOf(totalPrecio) + "€");
    }
}