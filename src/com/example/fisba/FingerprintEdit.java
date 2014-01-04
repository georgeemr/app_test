package com.example.fisba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
import android.widget.CheckedTextView;

public class FingerprintEdit extends Activity {
	public final static int ID_IMAGE_GALLERY = 1;
	public final static int ID_IMAGE_CROP  = 2;
	private static final int REQUEST_GALLERY = 0;
	
	//private static String[] str_Name;
	
	private static TextView mScannerInfo2;
	
	private static ListView listView;
	
    /** Called when the activity is first created. */
	private static Button mButtonCancel;
	private static Button mButtonEdit;
	private static Button mButtonEditStart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerprint_edit);
        
        mButtonEdit = (Button) findViewById(R.id.btnEdit);
        mButtonEditStart = (Button) findViewById(R.id.btnEditStart);
    	mButtonCancel = (Button) findViewById(R.id.btnCancel);
    	
    	mScannerInfo2 = (TextView) findViewById(R.id.tvScannerInfo2);
        
        mButtonEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	//デバッグ用
                BufferedReader in = null;
                try {
                  FileInputStream fileRead = openFileInput("test.txt");
                  in = new BufferedReader(new InputStreamReader(fileRead));
                  String str = in.readLine();
                  String[] str_Name = str.split(",", 0);
                  //Toast.makeText(FingerprintEdit.this, String.format("%d", str_Name.length),Toast.LENGTH_SHORT).show();
                  if( (str_Name.length % 3) == 0 ){
                	  mScannerInfo2.setText(str_Name[0] + "," + str_Name[1] + "," + str_Name[2]);
                	  //String[] set_Name = str_Name.substring(2);
                	  listView = (ListView)findViewById(R.id.ListView);  
                	  
                	  ArrayList<String> test_data = new ArrayList<String>();
                	  for(int i = 0; i < str_Name.length/3; i++){
                		  test_data.add(str_Name[i*3+1]);
                	  }
                	  
                	  //test_data.add(str_Name[1]);
                	  //test_data.add(str_Name[1]);
                	  //test_data.add(str_Name[2]);
                      // アダプタの作成  
                      listView.setAdapter(new ArrayAdapter<String>(  
                          FingerprintEdit.this,  
                          android.R.layout.simple_list_item_1,  
                          test_data)
                      );
                    
                      // フォーカスが当たらないよう設定  
                      listView.setItemsCanFocus(false);
                      
                        
                      // アイテムがクリックされたときに呼び出されるコールバックを登録  
                      listView.setOnItemClickListener(new OnItemClickListener() {  
                        @Override  
                        public void onItemClick(AdapterView<?> parent,  
                                View view, int position, long id) {  
                            // クリックされた時の処理  
                        }  
                      });  
                  } else {
                	  mScannerInfo2.setText("ファイルが存在せーへん。");
                  }
                  in.close();
                } catch (IOException e) {
                	mScannerInfo2.setText("ファイルが存在しません。");
                  e.printStackTrace();
                }
            }
        });
    	
        /*
    	mButtonEditStart.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	//デバッグ用
                BufferedReader in = null;
                try {
                  FileInputStream fileRead = openFileInput("test.txt");
                  in = new BufferedReader(new InputStreamReader(fileRead));
                  String str = in.readLine();
                  String[] str_Name = str.split(",", 0);
                  
                  String msg = "i:";
                  for(int i =0;i<listView.getChildCount();i++){
                	  CheckedTextView check = (CheckedTextView)listView.getChildAt(i);
                	  if(check.isChecked()){
                		  msg += check.getText() + "," +str_Name[i*3+0] + "," +str_Name[i*3+1] + "," +str_Name[i*3+2];
                	  }
                  }
                  //msg += msg.substring(0, msg.length()-1);
                  Toast.makeText(FingerprintEdit.this, msg, Toast.LENGTH_LONG).show();
                  in.close();
                } catch (IOException e) {
                	mScannerInfo2.setText("ファイルが存在しません。");
                  e.printStackTrace();
                }
            }
        });
    	*/
    	
    	
        mButtonCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
        });
      }  
        
    
      class data{
    	  Integer num;
    	  String name;
    	  String path;
    	  
    	  void setData(Integer num, String name, String path){
    		  this.num = num;
    		  this.name = name;
    		  this.path = path;
    	  }
      }
      
      // ListView に表示させる文字列  
      private static final String[] DAYS = new String[] {
    	  "Sunday", "Monday", "Tuesday", "Wednesday"
      };
}