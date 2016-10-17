package net.copaba.configurandomipropioendpointenmiservidor;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import net.copaba.configurandomipropioendpointenmiservidor.pojo.Mail;

import javax.mail.AuthenticationFailedException;

import javax.mail.MessagingException;



public class ActivityContacto extends AppCompatActivity {

    private TextInputEditText txtNombre;
    private TextInputEditText txtEmail;
    private TextInputEditText txtMensaje;
    private final String user = "lpadilla@copaba.net";
    private final String pass = "19091979";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.MiActionBar);
        setSupportActionBar(miActionBar);
        findViewById(R.id.btnFav).setVisibility(View.INVISIBLE);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNombre = (TextInputEditText) findViewById(R.id.txtNombre);
                txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
                txtMensaje = (TextInputEditText) findViewById(R.id.txtMensaje);
                if(txtNombre.length()==0) {
                    txtNombre.setError("Este campo no puese ir vacio");
                    txtNombre.requestFocus();
                    return;
                }else{
                    if(txtEmail.length()==0) {
                        txtEmail.setError("Este campo no puese ir vacio");
                        txtEmail.requestFocus();
                        return;
                    }else{
                        if(txtEmail.length()==0) {
                            txtMensaje.setError("Este campo no puese ir vacio");
                            txtMensaje.requestFocus();
                            return;
                        }
                    }
                }
                enviarComentario();

            }
        });


    }

    private void enviarComentario() {
        String[] recipients = {txtEmail.getText().toString()};
        SendEmailAsyncTask mail = new SendEmailAsyncTask();
        mail.activity = this;
        mail.mail = new Mail(user, pass);
        mail.mail.set_from(user);
        mail.mail.setBody("Mensaje de "+txtNombre.getText() +" / "+txtMensaje.getText().toString());
        mail.mail.set_to(recipients);
        mail.mail.set_subject("Petagram Fragment");
        mail.execute();
    }

    public void displayMessage(String message) {
        Snackbar.make(findViewById(R.id.button), message, Snackbar.LENGTH_LONG)
         .setAction("Action", null).show();
    }
    class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        Mail mail;
        ActivityContacto activity;

        public SendEmailAsyncTask() {}
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (mail.send()) {
                    activity.displayMessage("Comentario Enviado.");
                } else {
                    activity.displayMessage("Fallo el envio del comentario.");
                }

                return true;
            } catch (AuthenticationFailedException e) {
                e.printStackTrace();
                activity.displayMessage("Fallo Autenticación.");
                return false;
            } catch (MessagingException e) {
                e.printStackTrace();
                activity.displayMessage("Fallo el envio del comentario");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                activity.displayMessage("Error inesperado");
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            txtMensaje.setText("");
            txtNombre.setText("");
            txtEmail.setText("");
            txtNombre.requestFocus();
        }
    }
}



