package com.cristianomoraes.libri_retorfit.remote;

import com.cristianomoraes.libri_retorfit.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RouterInterface {

    /*
     * Rota de inserção de usuário
     */

    @POST("/usuario/inserir/")
    Call<Usuario> addUsuario(@Body Usuario usuario);
}
