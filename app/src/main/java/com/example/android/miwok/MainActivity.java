/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mColorsText = null;
    TextView mFamilyText = null;
    TextView mNumbersText = null;
    TextView mPhrasesText = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //get the TextViews ...
        mColorsText = (TextView) findViewById(R.id.colors);
        mFamilyText = (TextView) findViewById(R.id.family);
        mNumbersText = (TextView) findViewById(R.id.numbers);
        mPhrasesText = (TextView) findViewById(R.id.phrases);

        //add the listeners...
        mColorsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActive(ColorsActivity.class);
            }
        });

        mFamilyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActive(FamilyActivity.class);
            }
        });

        mNumbersText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActive(NumbersActivity.class);
            }
        });

        mPhrasesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActive(PhrasesActivity.class);
            }
        });
    }

    public void startActive(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
