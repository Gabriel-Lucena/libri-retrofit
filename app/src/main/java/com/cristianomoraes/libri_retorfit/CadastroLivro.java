package com.cristianomoraes.libri_retorfit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cristianomoraes.libri_retorfit.model.Livro;
import com.cristianomoraes.libri_retorfit.remote.APIUtil;
import com.cristianomoraes.libri_retorfit.remote.RouterInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroLivro extends AppCompatActivity {

    /**
     * Atributos
     *
     * @param savedInstanceState
     */

    RouterInterface routerInterface;
    EditText txtTitulo;
    EditText txtDescricao;
    EditText txtFoto;
    Button btnInserirLivro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        /*
         * Associar os componentes gráficos aos atributos
         */

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtFoto = findViewById(R.id.txtFoto);
        btnInserirLivro = findViewById(R.id.btnCadastrarLivro);

        /*
         * Configuração do RouterInterface
         */

        routerInterface = APIUtil.getUsuarioInterface();

        /*
         * Tratamento da ação de click ou toque de tela
         * do botão inserir livro
         */

        btnInserirLivro.setOnClickListener(view -> {

            /*
             * Cria o objeto de livro e recebe os dados
             */

            Livro livro = new Livro();

            livro.setTitulo(txtTitulo.getText().toString());
            livro.setDescricao(txtDescricao.getText().toString());
            livro.setIdUsuario(1);

            /*
             * Chamada do método da rota de inserção
             */

            addLivro(livro);
        });


    }

    /*
     * Implementação do método addLivro da interface routerInterface
     */

    public void addLivro(Livro livro) {

        /*
         * Liga o método addLivro da classe CadastroLivro com sua representação
         */

        Call<Livro> call = routerInterface.addLivro(livro);

        /*
         * Execução da chamada da rota
         */

        call.enqueue(new Callback<Livro>() {
            @Override
            public void onResponse(Call<Livro> call, Response<Livro> response) {
                Toast.makeText(CadastroLivro.this, "Livro cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Livro> call, Throwable t) {
                Log.d("Erro-API", t.getMessage());
            }
        });


    }
}