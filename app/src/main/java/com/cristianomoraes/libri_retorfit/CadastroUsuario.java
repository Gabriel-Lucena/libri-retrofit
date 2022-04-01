package com.cristianomoraes.libri_retorfit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.cristianomoraes.libri_retorfit.model.Usuario;
import com.cristianomoraes.libri_retorfit.remote.RouterInterface;

import retrofit2.Call;

public class CadastroUsuario extends AppCompatActivity {

    /*
     * Atributo de interface gráfica
     */

    EditText txtNome;
    EditText txtSobrenome;
    EditText txtEmail;
    EditText txtLogin;
    EditText txtSenha;
    Button btnCadastrar;

    /*
     * Atributo de representação das rotas
     */

    RouterInterface routerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        /*
         * Recebendo os objetos de interface
         */

        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario);

        /*
         * Tratamento do envento de click no botão inserir
         */

        btnCadastrar.setOnClickListener(view -> {

            /*
             *  Cria um objeto da model de usuário
             */

            Usuario usuario = new Usuario();

            /*
             *  Carrega os dados do formulário no objeto de model usuário
             */

            usuario.setNome(txtNome.getText().toString());
            usuario.setSobrenome(txtSobrenome.getText().toString());
            usuario.setEmail(txtEmail.getText().toString());
            usuario.setLogin(txtLogin.getText().toString());
            usuario.setSenha(txtSenha.getText().toString());

            /*
             *  Passar os dados para a APIREST
             */

             routerInterface = APIUtil.getUsuarioInterface();


        });

        /*
         *  Fim do método onCreate
         */
    }

    /**
     * Implementação do método de Call addUsuario
     */

    public void addUsuario(Usuario usuario) {

        Call<Usuario> call = routerInterface.addUsuario();

        call.enqueue( new Callback<Usuario> () {

            @override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Toast.makeText(CadastroUsuario.this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            }

            @override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("Erro-API", t.getMessage());
            }
        }
    }

}