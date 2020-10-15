package com.senai.projeto01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.senai.projeto01.modelo.Produto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_PRODUTO = 1;
    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int REQUEST_CODE_EDITAR_PRODUTO = 2;
    private final int RESULT_CODE_PRODUTO_EDITADO = 11;
    private final int RESULT_CODE_EXCLUIR_PRODUTO = 12;

    private ListView listViewProdutos;
    private ArrayAdapter<Produto> adapterProdutos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Produtos");

        listViewProdutos = findViewById(R.id.listView_produtos);
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        adapterProdutos = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, produtos);
        listViewProdutos.setAdapter(adapterProdutos);

        definirOnClickListenerListView();
    }

    private void definirOnClickListenerListView() {
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoClicado = adapterProdutos.getItem(position);
                Intent intentEdicaoProduto = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                intentEdicaoProduto.putExtra("produtoEdicao", produtoClicado);
                startActivityForResult(intentEdicaoProduto, REQUEST_CODE_EDITAR_PRODUTO);
            }
        });
    }

    public void onClickNovoProduto(View v) {
        Intent intentCadastro = new Intent(MainActivity.this, CadastroProdutoActivity.class);
        startActivityForResult(intentCadastro, REQUEST_CODE_NOVO_PRODUTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_NOVO_PRODUTO && resultCode == RESULT_CODE_NOVO_PRODUTO) {
            Toast.makeText(MainActivity.this, "Produto cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            Produto produto = (Produto) data.getExtras().getSerializable("novoProduto");
            produto.setId(++id);
            this.adapterProdutos.add(produto);
        } else if (requestCode == REQUEST_CODE_EDITAR_PRODUTO && resultCode == RESULT_CODE_PRODUTO_EDITADO) {
            Toast.makeText(MainActivity.this, "Produto editado com sucesso!", Toast.LENGTH_LONG).show();
            Produto produtoEditado = (Produto) data.getExtras().getSerializable("produtoEditado");
            for (int i = 0; i < adapterProdutos.getCount(); i++) {
                Produto produto = adapterProdutos.getItem(i);
                if (produto.getId() == produtoEditado.getId()) {
                    adapterProdutos.remove(produto);
                    adapterProdutos.insert(produtoEditado, i);
                    break;
                }
            }
        } else if (requestCode == REQUEST_CODE_EDITAR_PRODUTO && resultCode == RESULT_CODE_EXCLUIR_PRODUTO) {
            Toast.makeText(MainActivity.this, "Produto excluído com sucesso!", Toast.LENGTH_LONG).show();
            Produto produtoExcluido = (Produto) data.getExtras().getSerializable("produtoExcluir");
            for (int i = 0; i < adapterProdutos.getCount(); i++) {
                Produto produto = adapterProdutos.getItem(i);
                if (produto.getId() == produtoExcluido.getId()) {
                    adapterProdutos.remove(produto);
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}