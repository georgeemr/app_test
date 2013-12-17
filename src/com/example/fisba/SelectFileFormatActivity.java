package com.example.fisba;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SelectFileFormatActivity extends Activity {

	private Button mButtonOK;
	private RadioGroup mRadioGroup;
	private RadioButton mRadioBitmap;
	private RadioButton mRadioWSQ;
	private EditText mEditFileName;
	private TextView mMessage;

    private static File mDir;
	private String mFileFormat = "BITMAP";
	private String mFileName;
	
	//�f�[�^�Ǘ��p
	private String mScanName;
	private int mScan_num = 0; 
    // Return Intent extra
    public static String EXTRA_FILE_FORMAT = "file_format";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file_format);
        mButtonOK = (Button) findViewById(R.id.buttonOK);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        mRadioBitmap = (RadioButton) findViewById(R.id.radioBitmap);
        mRadioWSQ = (RadioButton) findViewById(R.id.radioWSQ);
        mEditFileName = (EditText) findViewById(R.id.editFileName);
        mMessage = (TextView) findViewById(R.id.textMessage);
        
        setResult(Activity.RESULT_CANCELED);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {         	
        	public void onCheckedChanged(RadioGroup group, int checkedId) {          	 
        		if(checkedId==mRadioBitmap.getId())
        			mFileFormat = "BITMAP";
        		else if(checkedId==mRadioWSQ.getId())
        			mFileFormat = "WSQ";
        	}
        });
        
        mButtonOK.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	mFileName = mEditFileName.getText().toString();
        		if( mFileName.trim().isEmpty() )
        		{
            		ShowAlertDialog();
            		return;
            	}
            	if( !isImageFolder() )
            		return;
            	
            	if(mFileFormat.compareTo("BITMAP") == 0 )
            		mFileName = mFileName + ".bmp";
            	else 
            		mFileName = mFileName + ".wsq";
            	
            	CheckFileName();
            }
        });
    }
    
    private void ShowAlertDialog()
    {
        new AlertDialog.Builder(this) 
        .setTitle("File name") 
        .setMessage("File name can not be empty!") 
        .setPositiveButton("OK", new DialogInterface.OnClickListener() { 
             public void onClick(DialogInterface dialog, int whichButton) { 
             } 
        })
		.setCancelable(false)
        .show();
    }
    
    private void SetFileName()
    {    	
    	String[] extraString = new String[2];
    	extraString[0] = mFileFormat;
    	extraString[1] = mDir.getAbsolutePath() + "/"+ mFileName;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_FILE_FORMAT, extraString);
        
        try {
            // �X�g���[�����J��
        	FileOutputStream outStream = openFileOutput("test.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(outStream);
            /*
            //�����Ńt�@�C��������g���q��
            if(mFileFormat.compareTo("BITMAP") == 0 )
        		mFileName = mFileName.replaceAll(".bmp","");
        	else 
        		mFileName = mFileName.replaceAll(".wsq","");
             */
            writer.write(mScan_num + "," + mFileName + ",");
            writer.flush();
            writer.close();
            mScan_num ++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Set result and finish this Activity
        setResult(Activity.RESULT_OK, intent);
        finish();		
    }
    
    private void CheckFileName()
    {    	
    	File f = new File(mDir, mFileName);
    	if( f.exists() )
    	{
            new AlertDialog.Builder(this) 
            .setTitle("File name") 
            .setMessage("File already exists. Do you want replace it?") 
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
                 public void onClick(DialogInterface dialog, int whichButton) { 
                	 SetFileName();              	
                 } 
            }) 
            .setNegativeButton("No", new DialogInterface.OnClickListener() { 
                 public void onClick(DialogInterface dialog, int whichButton) {
                	 mMessage.setText("Cancel");
                 } 
            })
			.setCancelable(false)
            .show();
        }
    	else
    		SetFileName();
    }
    
    public boolean isImageFolder()
    {
    	/*������
        //File extStorageDirectory = Environment.getExternalStorageDirectory();
    	File extStorageDirectory = getFilesDir();
    	//File data = getFilesDir();
        mDir = new File(extStorageDirectory, "Android//FtrScanDemo"); 
        */
        
    	//�Ƃ肠����
        File extStorageDirectory = Environment.getExternalStorageDirectory();
        //mDir = new File(extStorageDirectory, "Android//FtrScanDemo");
        mDir = new File(extStorageDirectory, "/mnt/sdcard/Android/FtrScanDemo");
        
        
        if( mDir.exists() )
        {
            if( !mDir.isDirectory() )
            {
            	mMessage.setText( "Can not create image folder " + mDir.getAbsolutePath() + 
                        ". File with the same name already exist." );
            	return false;
            }
        } else {
            try
            {
            	mDir.mkdirs();
            }
            catch( SecurityException e )
            {
            	mMessage.setText( "Can not create image folder " + mDir.getAbsolutePath() +
                        ". Access denied.");
            	return false;
            }
        }
        return true;
    }

}
