package com.example.fisba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import java.io.OutputStreamWriter;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;

public class FingerprintDelete extends Activity {
	public final static int ID_IMAGE_GALLERY = 1;
	public final static int ID_IMAGE_CROP  = 2;
	private static final int REQUEST_GALLERY = 0;
	
	private static TextView mScannerInfo2;
	private static ListView listView;
	
    /** Called when the activity is first created. */
	private static Button mButtonCancel;
	private static Button mButtonDelete;
	private static Button mButtonDeleteStart;
	
	ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerprint_delete);
        
        mButtonDelete = (Button) findViewById(R.id.btnDelete);
        mButtonDeleteStart = (Button) findViewById(R.id.btnDeleteStart);
    	mButtonCancel = (Button) findViewById(R.id.btnCancel);

    	mScannerInfo2 = (TextView) findViewById(R.id.tvScannerInfo2);
    	
    	mButtonDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	//�f�o�b�O�p
                BufferedReader in = null;
                try {
                  FileInputStream fileRead = openFileInput("test.txt");
                  in = new BufferedReader(new InputStreamReader(fileRead));
                  String str = in.readLine();
                  if(str!=null){
                  String[] str_Name = str.split(",", 0);
                  Toast.makeText(FingerprintDelete.this, String.format("%d", str_Name.length),Toast.LENGTH_SHORT).show();
                  if( ((str_Name.length % 3) == 0 )&&(str_Name.length!=0)){
                	  mScannerInfo2.setText(str_Name[0] + "," + str_Name[1] + "," + str_Name[2]);
                	  //String[] set_Name = str_Name.substring(2);
                	  listView = (ListView)findViewById(R.id.ListView);  
                	  
                	  ArrayList<String> test_data = new ArrayList<String>();
                	  for(int i = 0; i < str_Name.length/3; i++){
                		  //if("" != str_Name[i*3+1])
                			  test_data.add(str_Name[i*3+1]);
                	  }
                	  
                      // �A�_�v�^�̍쐬  
                      listView.setAdapter(adapter = new ArrayAdapter<String>(  
                          FingerprintDelete.this,  
                          android.R.layout.simple_list_item_multiple_choice,  
                          test_data)
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
                  }
                      // ���݃`�F�b�N����Ă���A�C�e�����擾  
                      // �`�F�b�N����ĂȂ��A�C�e���͊܂܂�Ȃ��͗l  
                      SparseBooleanArray checked = listView.getCheckedItemPositions();  
                      for (int i = 0; i < checked.size(); i++) {  
                        // �`�F�b�N����Ă���A�C�e���� key �̎擾  
                        int key = checked.keyAt(i);  
                        //Log.v(getClass().getSimpleName(), "values: " + DAYS[key]);  
                      }
                  } else {
                	  mScannerInfo2.setText("�t�@�C�������݂��[�ւ�B");
                  }
                  in.close();
                } catch (IOException e) {
                	mScannerInfo2.setText("�t�@�C�������݂��܂���B");
                  e.printStackTrace();
                }
            }
        });
    	
    	mButtonDeleteStart.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	//�f�o�b�O�p
                BufferedReader in = null;
                try {
                  FileInputStream fileRead = openFileInput("test.txt");
                  in = new BufferedReader(new InputStreamReader(fileRead));
                  String str = in.readLine();
                  String[] str_Name = str.split(",", 0);
                  //setData data = new setData(�iInteger)str_Name[0],str_Name[1],str_Name[2]);
                  
                  in.close();
                  
                  String msg = "i:";
                  for(int i =0;i<listView.getChildCount();i++){
                	  CheckedTextView check = (CheckedTextView)listView.getChildAt(i);
                	  if(check.isChecked()){
                		  try {
                	            // �X�g���[�����J��
                	        	FileOutputStream outStream = openFileOutput("test.txt", MODE_PRIVATE);
                	            OutputStreamWriter writer = new OutputStreamWriter(outStream);
                	            
                	            //�t�@�C���폜
                	            File file = new File(str_Name[i*3+2]);
                	            file.delete();
                    	  
                	            /*
                	            str = str.replaceAll(str_Name[i*3+0],"");
                	            str = str.replaceAll(str_Name[i*3+1],"");
                	            str = str.replaceAll(str_Name[i*3+2],"");
                	            str = str.replaceAll(",,","");
                	            */
                	            
                	            str = str.replaceAll(str_Name[i*3+0]+","+str_Name[i*3+2]+","+str_Name[i*3+2]+",","");
                	            
                	            //���X�g�r���[����폜
                	            adapter.remove(str_Name[i*3+1]);
                    	  
                	            //�f�o�b�O�p
                	            msg += check.getText() + "," +str_Name[i*3+0] + "," +str_Name[i*3+1] + "," +str_Name[i*3+2];
                	            
                	            //�t�@�C���X�V
                	            writer.write(str);
                	            writer.flush();
                	            //deleteFile("test.txt");
                	            writer.close();
                	        } catch (IOException e) {
                	            e.printStackTrace();
                	        }
                	  }
                  }
                  //msg += msg.substring(0, msg.length()-1);
                  Toast.makeText(FingerprintDelete.this, msg, Toast.LENGTH_LONG).show();
                  
                  //in.close();
                } catch (IOException e) {
                	mScannerInfo2.setText("�t�@�C�������݂��܂���B");
                  e.printStackTrace();
                }
            }
        });
    	
        mButtonCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
        });
    }
}