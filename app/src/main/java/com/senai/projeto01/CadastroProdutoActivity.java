package com.senai.projeto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.senai.projeto01.modelo.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int RESULT_CODE_PRODUTO_EDITADO = 11;
    private final int RESULT_CODE_EXCLUIR_PRODUTO = 12;

    private boolean edicao = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        setTitle("Cadastro de Produto");

        carregarProduto();
    }

    private void carregarProduto() {
        Intent intentEdicaoProduto = getIntent();
        if (intentEdicaoProduto != null && intentEdicaoProduto.getExtras() != null && intentEdicaoProduto.getExtras().get("produtoEdicao") != null) {
            Produto produto = (Produto) intentEdicaoProduto.getExtras().get("produtoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextValor = findViewById(R.id.editText_valor);
            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));
            edicao = true;
            id = produto.getId();
        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextValor = findViewById(R.id.editText_valor);

        String nome = editTextNome.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());

        Produto produto = new Produto(id, nome, valor);
        Intent intent = new Intent();

        if (edicao) {
            intent.putExtra("produtoEditado", produto);
            setResult(RESULT_CODE_PRODUTO_EDITADO, intent);
        } else {
            intent.putExtra("novoProduto", produto);
            setResult(RESULT_CODE_NOVO_PRODUTO, intent);
        }
        finish();
    }

    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextValor = findViewById(R.id.editText_valor);

        String nome = editTextNome.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());

        Produto produto = new Produto(id, nome, valor);
        Intent intentExcluir = new Intent();

        if (edicao) {
            intentExcluir.putExtra("produtoExcluir", produto);
            setResult(RESULT_CODE_EXCLUIR_PRODUTO, intentExcluir);

            finish();
        }
    }
}