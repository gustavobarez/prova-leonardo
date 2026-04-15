package com.example.provaleonardo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.provaleonardo.data.Product;
import com.example.provaleonardo.data.ProductDatabase;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern CODE_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");
    private static final Pattern PRICE_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");

    private EditText etName;
    private EditText etCode;
    private EditText etPrice;
    private EditText etQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etCode = findViewById(R.id.etCode);
        etPrice = findViewById(R.id.etPrice);
        etQuantity = findViewById(R.id.etQuantity);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnViewProducts = findViewById(R.id.btnViewProducts);

        btnSave.setOnClickListener(v -> saveProduct());
        btnViewProducts.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
    }

    private void saveProduct() {
        String name = etName.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String priceText = etPrice.getText().toString().trim().replace(',', '.');
        String quantityText = etQuantity.getText().toString().trim();

        if (name.isEmpty() || code.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!CODE_PATTERN.matcher(code).matches()) {
            etCode.setError(getString(R.string.error_code_invalid));
            etCode.requestFocus();
            return;
        }

        if (!PRICE_PATTERN.matcher(priceText).matches()) {
            etPrice.setError(getString(R.string.error_price_invalid));
            etPrice.requestFocus();
            return;
        }

        double price = Double.parseDouble(priceText);
        if (price <= 0) {
            etPrice.setError(getString(R.string.error_price_invalid));
            etPrice.requestFocus();
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            etQuantity.setError(getString(R.string.error_quantity_invalid));
            etQuantity.requestFocus();
            return;
        }

        if (quantity <= 0) {
            etQuantity.setError(getString(R.string.error_quantity_invalid));
            etQuantity.requestFocus();
            return;
        }

        Product product = new Product(name, code, price, quantity);
        ProductDatabase.getInstance(this).productDao().insert(product);

        clearFields();
        Toast.makeText(this, R.string.product_saved, Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        etName.setText("");
        etCode.setText("");
        etPrice.setText("");
        etQuantity.setText("");
        etName.requestFocus();
    }
}
