package com.adonis.labexer4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] companies, countries, industries, ceonames, description;
    int[] logo = {R.drawable.icbc, R.drawable.jpmorgan, R.drawable.chinaconstruction, R.drawable.agriculturalbank, R.drawable.newbankofamerica, R.drawable.apple, R.drawable.pingan, R.drawable.bankofchina, R.drawable.shell, R.drawable.wellsfargo, R.drawable.exxon, R.drawable.att, R.drawable.samsung, R.drawable.citigroup};
    ArrayList<ItemData> companydata = new ArrayList<>();
    ListView listcompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companies = getResources().getStringArray(R.array.topCompanies);
        countries = getResources().getStringArray(R.array.countryNames);
        industries = getResources().getStringArray(R.array.industryNames);
        ceonames = getResources().getStringArray(R.array.ceoNames);
        description = getResources().getStringArray(R.array.companyInfo);

        for (int i = 0; i < companies.length; i++) {
            companydata.add(i, new ItemData(logo[i], companies[i], countries[i], industries[i], ceonames[i]));
        }

        ItemAdapter adapter = new ItemAdapter (this, R.layout.item, companydata);
        listcompanies = findViewById(R.id.lvCompanies);
        listcompanies.setAdapter(adapter);
        listcompanies.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int i, long id) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(logo[i]);
        dialog.setTitle(companies[i]);
        dialog.setMessage(description[i]);
        dialog.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, companies[i] + " | " + countries[i], Toast.LENGTH_LONG).show();
            }
        });

        dialog.create().show();
    }
}
