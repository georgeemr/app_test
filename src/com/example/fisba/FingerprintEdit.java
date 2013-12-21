package com.example.fisba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class FingerprintEdit extends Activity {
	public final static int ID_IMAGE_GALLERY = 1;
	public final static int ID_IMAGE_CROP  = 2;
	private static final int REQUEST_GALLERY = 0;
	
	private static TextView mScannerInfo2;
	
    /** Called when the activity is first created. */
	private static Button mButtonCancel;
	private static Button mButtonDelete;
	private static Button mButtonDeleteStart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerprint_edit);
        
        mButtonDelete = (Button) findViewById(R.id.btnDelete);
        mButtonDeleteStart = (Button) findViewById(R.id.btnDeleteStart);
    	mButtonCancel = (Button) findViewById(R.id.btnCancel);
    	

    	mScannerInfo2 = (TextView) findViewById(R.id.tvScannerInfo2);

    	
    	ListView listView = (ListView)findViewById(R.id.ListView);  
    	  
        // �A�_�v�^�̍쐬  
        listView.setAdapter(new ArrayAdapter<String>(  
            this,  
            android.R.layout.simple_list_item_multiple_choice,  
            DAYS)  
        );  
      
        // �t�H�[�J�X��������Ȃ��悤�ݒ�  
        listView.setItemsCanFocus(false);  
      
        // �I���̕����̐ݒ�  
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);  
          
        for (int i = 1; i < 6; i++) {  
          // �w�肵���A�C�e�����`�F�b�N����Ă��邩��ݒ�  
          listView.setItemChecked(i, true);  
        }  
          
        // �A�C�e�����N���b�N���ꂽ�Ƃ��ɌĂяo�����R�[���o�b�N��o�^  
        listView.setOnItemClickListener(new OnItemClickListener() {  
          @Override  
          public void onItemClick(AdapterView<?> parent,  
                  View view, int position, long id) {  
              // �N���b�N���ꂽ���̏���  
          }  
        });  
          
        // ���݃`�F�b�N����Ă���A�C�e�����擾  
        // �`�F�b�N����ĂȂ��A�C�e���͊܂܂�Ȃ��͗l  
        SparseBooleanArray checked = listView.getCheckedItemPositions();  
        for (int i = 0; i < checked.size(); i++) {  
          // �`�F�b�N����Ă���A�C�e���� key �̎擾  
          int key = checked.keyAt(i);  
          //Log.v(getClass().getSimpleName(), "values: " + DAYS[key]);  
        }  
      }  
        
      // ListView �ɕ\�������镶����  
      private static final String[] DAYS = new String[] {  
        "Sunday", "Monday", "Tuesday", "Wednesday",  
        "Thursday", "Friday", "Saturday"  
      };
}