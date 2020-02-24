package com.example.schiotchiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText oldHotWater;
    EditText oldColdWater;
    EditText oldElectricity;
    EditText newHotWater;
    EditText newColdWater;
    EditText newElectricity;
    //  Эти нужно сделать константами.
    float costHotWater;
    float costColdWater;
    float costElectricity;
    float costWater;

    float OldHotWater;
    float OldColdWater;
    float OldElectricity;
    float NewHotWater;
    float NewColdWater;
    float NewElectricity;
    float HotWater;
    float ColdWater;
    float Electricity;
    float Water;
    float Summ;
    private SharedPreferences mSettings;
    private static final String APP_PREFERENCES = "mysettings";
    private static final String OLD_HOT_WATER = "OldHotWater";
    private static final String OLD_COLD_WATER = "OldColdWater";
    private static final String OLD_ELECTRICITY = "oldElectricyty";
    private static final String NEW_HOT_WATER = "NewHotWater";
    private static final String NEW_COLD_WATER = "NewColdWater";
    private static final String NEW_ELECTRICITY = "NewElectricity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        oldHotWater = findViewById(R.id.editTextOldHotWater);
        oldColdWater = findViewById(R.id.editTextOldColdWater);
        oldElectricity = findViewById(R.id.editTextOldElectricity);
        newHotWater = findViewById(R.id.editTextNewHotWater);
        newColdWater = findViewById(R.id.editTextNewColdWater);
        newElectricity = findViewById(R.id.editTextNewElectricity);

        costHotWater = 129.3f;
        costColdWater = 31.21f;
        costElectricity = 2.02f;
        costWater = 19.75f;

        GetTextFromSettings();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SetTextInSettings();
    }

    // Создание меню.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Обработка кнопок меню.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Обновить.
        if (id == R.id.action_refresh) {
            Refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Меню - Обновить.
    // Перемещает текст из правых EditText в левые.
    private void Refresh() {
        oldHotWater.setText(newHotWater.getText().toString());
        oldColdWater.setText(newColdWater.getText().toString());
        oldElectricity.setText(newElectricity.getText().toString());
        newHotWater.setText("");
        newColdWater.setText("");
        newElectricity.setText("");
    }

    // Получаем числа из настроек.
    @SuppressLint("SetTextI18n")
    void GetTextFromSettings() {

        if (mSettings.contains(OLD_HOT_WATER)) {
            OldHotWater = mSettings.getFloat(OLD_HOT_WATER, 0);
        }
        if (mSettings.contains(OLD_COLD_WATER)) {
            OldColdWater = mSettings.getFloat(OLD_COLD_WATER, 0);
        }
        if (mSettings.contains(OLD_ELECTRICITY)) {
            OldElectricity = mSettings.getFloat(OLD_ELECTRICITY, 0);
        }
        if (mSettings.contains(NEW_HOT_WATER)) {
            NewHotWater = mSettings.getFloat(NEW_HOT_WATER, 0);
        }
        if (mSettings.contains(NEW_COLD_WATER)) {
            NewColdWater = mSettings.getFloat(NEW_COLD_WATER, 0);
        }
        if (mSettings.contains(NEW_ELECTRICITY)) {
            NewElectricity = mSettings.getFloat(NEW_ELECTRICITY, 0);
        }

        // Выводим на экран данные из настроек
        oldHotWater.setText(""+Math.round(OldHotWater));
        oldColdWater.setText(""+Math.round(OldColdWater));
        oldElectricity.setText(""+Math.round(OldElectricity));
        newHotWater.setText(""+Math.round(NewHotWater));
        newColdWater.setText(""+Math.round(NewColdWater));
        newElectricity.setText(""+Math.round(NewElectricity));
    }

    // Записываем числа в настройки.
    void SetTextInSettings() {
        OldHotWater = (float) Double.parseDouble(oldHotWater.getText().toString());
        OldColdWater = (float) Double.parseDouble(oldColdWater.getText().toString());
        OldElectricity = (float) Double.parseDouble(oldElectricity.getText().toString());
        NewHotWater = (float) Double.parseDouble(newHotWater.getText().toString());
        NewColdWater = (float) Double.parseDouble(newColdWater.getText().toString());
        NewElectricity = (float) Double.parseDouble(newElectricity.getText().toString());

        // Запоминаем данные
        SharedPreferences.Editor editor = mSettings.edit();

        editor.putFloat(OLD_HOT_WATER, OldHotWater);
        editor.putFloat(OLD_COLD_WATER, OldColdWater);
        editor.putFloat(OLD_ELECTRICITY, OldElectricity);
        editor.putFloat(NEW_HOT_WATER, NewHotWater);
        editor.putFloat(NEW_COLD_WATER, NewColdWater);
        editor.putFloat(NEW_ELECTRICITY, NewElectricity);
        editor.apply();
    }

    // При нажатии на кнопку "Расчитать".
    @SuppressLint("SetTextI18n")
    public void onClick(View view) {
        TextView text = findViewById(R.id.textView);

        // Считывание данных из EditText.
        try {
            OldHotWater = (float) Double.parseDouble(oldHotWater.getText().toString());
            OldColdWater = (float) Double.parseDouble(oldColdWater.getText().toString());
            OldElectricity = (float) Double.parseDouble(oldElectricity.getText().toString());
            NewHotWater = (float) Double.parseDouble(newHotWater.getText().toString());
            NewColdWater = (float) Double.parseDouble(newColdWater.getText().toString());
            NewElectricity = (float) Double.parseDouble(newElectricity.getText().toString());

            // Считаем показатели.
            HotWater = ((NewHotWater - OldHotWater) * costHotWater);
            ColdWater = ((NewColdWater - OldColdWater) * costColdWater);
            Electricity = ((NewElectricity - OldElectricity) * costElectricity);
            Water = ((NewHotWater + NewColdWater - OldHotWater - OldColdWater) * costWater);
            Summ = Water + HotWater + ColdWater + Electricity;

            // Вывод данных.
            text.setText("" +
                    "\n " + getString(R.string.hot_water) +" "+ Math.round(HotWater) +" "+ getString(R.string.rub) +
                    "\n " + getString(R.string.cold_water) +" "+ Math.round(ColdWater) +" "+ getString(R.string.rub) +
                    "\n " + getString(R.string.water) +" "+ Math.round(Water) +" "+ getString(R.string.rub) +
                    "\n " + getString(R.string.electricity) +" "+ Math.round(Electricity) +" "+ getString(R.string.rub) +
                    "\n " + getString(R.string.summ) +" "+ Math.round(Summ) +" "+ getString(R.string.rub));
        } catch (Exception e)
        {
            text.setText(R.string.error);
        }
    }
}