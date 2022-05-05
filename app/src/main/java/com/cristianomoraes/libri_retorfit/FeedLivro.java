package com.cristianomoraes.libri_retorfit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cristianomoraes.libri_retorfit.model.Item;
import com.cristianomoraes.libri_retorfit.model.Livro;
import com.cristianomoraes.libri_retorfit.remote.APIUtil;
import com.cristianomoraes.libri_retorfit.remote.RouterInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedLivro extends AppCompatActivity {

    /*
     * Declaração dos atributos
     */

    RouterInterface routerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_livro);

        /*
         * Conecta o aplicativo com a API
         */

        routerInterface = APIUtil.getUsuarioInterface();

        /*
         * Executa a chamada à rota de listagem de livros
         */

        Call<List<Livro>> call = routerInterface.getLivro();

        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {

                if (response.isSuccessful()) {

                    List<Item> itens = new ArrayList<>();

                    /* Recebe os dados da API **/

                    List<Livro> list = new ArrayList<Livro>();

                    list = response.body();


                    for (int i = 0; i < list.size(); i++) {
                        itens.add(new Item(0, list.get(i)));
                    }

                    RecyclerView recyclerView = findViewById(R.id.recyclerview);
                    recyclerView.setAdapter(new LivroAdapter(itens));

                }

            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {

            }

        });


    }   // Fim do método onCreate


    /*
     * Classe de adapter da RecyclerView
     */


    private class LivroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<Item> itens;

        public LivroAdapter(List<Item> itens) {
            this.itens = itens;

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LivroAdapter.LivroViewHolder(
                    LayoutInflater.from(
                            parent.getContext()
                    ).inflate(R.layout.item_container_livro,
                            parent,
                            false)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            /* Dados de livro **/

            if (getItemViewType(position) == 0) {

                Livro livro = (Livro) itens.get(position).getObject();
                ((LivroAdapter.LivroViewHolder) holder).setLivroData(livro);

            }

            /* Dados de HQ **/

            if (getItemViewType(position) == 1) {

            }

            /* Dados de mangá **/

            if (getItemViewType(position) == 2) {

            }

        }

        @Override
        public int getItemCount() {
            return itens.size();
        }

        public int getItemViewType(int position) {

            return itens.get(position).getType();
        }

        /*
         * Classe de viewholder da recyclerview
         */

        class LivroViewHolder extends RecyclerView.ViewHolder {

            /**
             * Atributos da classe LivroViewHolder
             *
             * @param itemView
             */

            private TextView txtTitulo, txtDescricao;
            private int idLivro;


            public LivroViewHolder(@NonNull View itemView) {
                super(itemView);

                txtTitulo = itemView.findViewById(R.id.txtTitulo);
                txtDescricao = itemView.findViewById(R.id.txtDescricao);

                /*
                 * Ação de clique para editar livro e excluir livro
                 */

                itemView.setOnClickListener(view -> {

                    /*
                     *  setMessage -> título da caixa de alerta
                     *
                     *      Parâmetros:
                     *
                     *          1 - Título
                     *
                     *  setPositiveButton -> Define uma opção de ação
                     *
                     *      Parâmetros:
                     *
                     *          1 - Título
                     *          2 - Ação
                     *
                     *  setNegativeButton -> Define uma opção de ação
                     *
                     *     Parâmetros:
                     *
                     *          1 - Título
                     *          2 - Ação
                     */

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(FeedLivro.this)
                            .setMessage("Escolha a ação que deseja!")
                            .setPositiveButton(
                                    "Alterar", (dialog1, witch) -> {


                                    }
                            )
                            .setNegativeButton("Excluir", (dialog1, witch) -> {

                                        routerInterface = APIUtil.getUsuarioInterface();

                                        Call<Livro> call = routerInterface.deleteLivro(idLivro);

                                        call.enqueue(new Callback<Livro>() {

                                            @Override
                                            public void onResponse(Call<Livro> call, Response<Livro> response) {

                                                Toast.makeText(FeedLivro.this, "Livro excluído com sucesso", Toast.LENGTH_SHORT).show();

                                                startActivity(new Intent(FeedLivro.this, FeedLivro.class));

                                            }

                                            @Override
                                            public void onFailure(Call<Livro> call, Throwable t) {

                                            }

                                        });
                                    }

                            );

                    alertDialog.show();

                });


            } // Fim do construtor de LivroViewHolder


            /*
             * Método que carrega os valores nos elementos de textview
             *  - txtTitulo
             *  - txtDescricao
             */

            public void setLivroData(Livro livro) {

                txtTitulo.setText(livro.getTitulo());
                txtDescricao.setText(livro.getDescricao());
                idLivro = livro.getIdLivro();


            }


        } // Fim da classe LivroViewHolder

    } // Fim da classe LivroAdapter


}   // Fim da classe FeedLivro