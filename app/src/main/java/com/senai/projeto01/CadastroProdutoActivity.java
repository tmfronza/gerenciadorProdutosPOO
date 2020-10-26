package com.senai.projeto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.senai.projeto01.database.ProdutoDAO;
import com.senai.projeto01.modelo.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

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
        ProdutoDAO produtoDao = new ProdutoDAO(getBaseContext());

        boolean salvou = produtoDao.salvar(produto);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastroProdutoActivity.this, "Erro ao Salvar", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextValor = findViewById(R.id.editText_valor);

        String nome = editTextNome.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());

        Produto produto = new Produto(id, nome, valor);


        ProdutoDAO produtoDao = new ProdutoDAO(getBaseContext());
        boolean excluiu = produtoDao.excluir(produto);
        if (excluiu) {
            finish();
        }
    }
}

