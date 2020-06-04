package org.newtonproject.android.component;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author weixuefeng@lubangame.com
 * @version $
 * @time: 2020/6/4--11:01 PM
 * @description
 * @copyright (c) 2020 Newton Foundation. All rights reserved.
 */
class PlanetActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planet);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
	}
}
