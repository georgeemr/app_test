package com.example.dialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	public void onClick(View view){
		final String[] items = {"A�^","B�^","AB�^","O�^"};
		
		final int[] selected = {0};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("���t�^")
		.setIcon(R.drawable.ic_launcher)
		.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int which){
				selected[0] = which;
			}
		})
		
		.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int which){
				Toast.makeText(MainActivity.this, String.format("�u%s�v���I������܂����B", items[selected[0]]),Toast.LENGTH_SHORT).show();
			}
		})
		.show();
	}
		
		
		/*
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("�_�C�A���O�̊�{")
		.setMessage("Android��Java�ŊJ���ł��܂����H")
		.setIcon(R.drawable.ic_launcher)
		
		.setPositiveButton("�͂�",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				Toast.makeText(MainActivity.this, "�����ł��I", Toast.LENGTH_SHORT).show();
			}
		})
	
		.setNegativeButton("������",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				Toast.makeText(MainActivity.this, "�~�X�I", Toast.LENGTH_SHORT).show();
			}
		})
	
		.setNeutralButton("�L�����Z��",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){}
		})
		.show();
	}*/
}
