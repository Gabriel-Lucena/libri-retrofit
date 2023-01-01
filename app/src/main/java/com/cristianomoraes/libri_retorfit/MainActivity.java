package com.cristianomoraes.libri_retorfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cristianomoraes.libri_retorfit.model.Usuario;
import com.cristianomoraes.libri_retorfit.remote.APIUtil;
import com.cristianomoraes.libri_retorfit.remote.RouterInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RouterInterface routerInterface;
    EditText txtLogin;
    EditText txtSenha;
    Button btnLogin;
    Button btnCadastrarUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);


        routerInterface = APIUtil.getUsuarioInterface();

        btnLogin.setOnClickListener(view -> {


            Usuario usuario = new Usuario();

            usuario.setLogin(txtLogin.getText().toString());
            usuario.setSenha(txtSenha.getText().toString());


            realizarLogin(usuario);



        });

    }


    public void realizarLogin(Usuario usuario) {

        Call<Usuario> call = routerInterface.realizarLogin(usuario.getLogin(),usuario.getSenha());

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Toast.makeText(MainActivity.this, "Login realizado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("Erro-API", t.getMessage());
            }
        });
    }

}

