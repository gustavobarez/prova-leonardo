package com.example.provaleonardo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.provaleonardo.data.Product;
import com.example.provaleonardo.data.ProductDatabase;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerProducts);
        emptyText = findViewById(R.id.tvEmpty);

        adapter = new ProductAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Product> products = ProductDatabase.getInstance(this).productDao().getAllProducts();
        adapter.setItems(products);
        emptyText.setVisibility(products.isEmpty() ? TextView.VISIBLE : TextView.GONE);
    }
}
