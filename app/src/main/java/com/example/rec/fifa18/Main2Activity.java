package com.example.rec.fifa18;




import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.Color;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.TableLayout;
        import android.widget.TableRow;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.ArrayList;

public  class Main2Activity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{


    private String item1, item2, item;
    Button btn2;
    ArrayList<String> teamnames;
    public int count=0;
    public int flag;
    Button btn;
    TableLayout table;
    String[] country = {"select team","ARG","BRA","BEL","AUS","DEN","GER","ESP","MEX","USA","KSA","RUS","FRA","CRO","NGA","COL","CRC","EGY","ENG","ISL","PER","POR","IRN","JPN","KOR","MAR","PAN","POL","SEN","SRB","SUN","TUI","URU"};

    String[] venue = {"select venue","MOSCOW","SAINT PETERSBURG","NIZHNY NOVGOROD","KAZAN","EKATERINMURG","SARANSK","SAMARA","VOLGOGRAD","ROSTOV-ON-DON","SOCHI"};
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public  String userChoosenTask;

    public ImageButton ibm1;

    public TableRow tr;
    public ImageButton ibm2 ;
    private  int i ;

    private int a=1;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try {

            setContentView(R.layout.activity_main2);

            setUITEXT();

        } catch (Exception e) {

            e.printStackTrace();

        }


        btn2 = findViewById(R.id.buttonadd);
        table = findViewById(R.id.tablelayout);






        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonadd:a++;

                    btn2.setEnabled(false);
                        newfixture();

                        break;


                }
            }
        });


    }


    @SuppressLint({"SetTextI18n", "ResourceType"})
    public void newfixture() {

        table=findViewById(R.id.tablelayout);
        tr = new TableRow(this);

        tr.setClickable(true);
        ibm1 =new ImageButton(this);


        ibm2=new ImageButton(this);





        EditText date = new EditText(this);
date.setText("dd/mm/yyyy");
        TextView text = new TextView(this);
        text.setText("delete");
        Spinner team1 = new Spinner(this);

        Spinner team2 = new Spinner(this);
        Spinner teamvenue = new Spinner(this);

        tr.addView(team1);
        tr.addView(ibm1);

        tr.addView(date);
        tr.addView(teamvenue);
        tr.addView(ibm2);
        tr.addView(team2);
        tr.addView(text);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        ArrayAdapter ab = new ArrayAdapter(this, android.R.layout.simple_spinner_item, venue);
        team1.setAdapter(aa);
        team2.setAdapter(aa);
        teamvenue.setAdapter(ab);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        table.addView(tr);

        ibm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {i=1;
                selectImage();i=2;
            }
        });
        ibm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {i=2;
                selectImage();i=1;
            }
        });
        ibm1.setMaxHeight(5);ibm1.setMaxWidth(5);
        ibm2.setMaxHeight(5);ibm2.setMaxWidth(5);
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.DKGRAY);
                deleterow(v);
                count++;
            }
        });
        team1.setOnItemSelectedListener(this);
        team2.setOnItemSelectedListener(this);
        teamvenue.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                item1 = parent.getItemAtPosition(position).toString();

                break;
            case 2:
                item2 = parent.getItemAtPosition(position).toString();
                break;
            case 3:
                item = parent.getItemAtPosition(position).toString();
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setUITEXT() {


    }

    @Override

    protected void onResume() {

        super.onResume();

    }





    @Override

    protected void onPause() {

        super.onPause();

    }





    @Override

    protected void onDestroy() {

        super.onDestroy();

    }







    private void selectImage() {



        final CharSequence[] items = { "Take Photo", "Choose from Library",

                "Cancel" };



        AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);

        builder.setTitle("Add Photo!");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                boolean result=Utility.checkPermission(Main2Activity.this);



                if (items[item].equals("Take Photo")) {

                    userChoosenTask ="Take Photo";

                    if(result)

                        cameraIntent();



                } else if (items[item].equals("Choose from Library")) {

                    userChoosenTask ="Choose from Library";

                    if(result)

                        galleryIntent();



                } else if (items[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();





    }



    private void galleryIntent() {



        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);//

        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);



    }



    private void cameraIntent() {



        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, REQUEST_CAMERA);



    }


    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if(userChoosenTask.equals("Take Photo"))

                        cameraIntent();

                    else if(userChoosenTask.equals("Choose from Library"))

                        galleryIntent();

                } else {

                    //code for deny

                }

                break;

        }

    }



    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == SELECT_FILE)

                onSelectFromGalleryResult(data);

            else if (requestCode == REQUEST_CAMERA)

                onCaptureImageResult(data);

        }

    }



    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);



        File destination = new File(Environment.getExternalStorageDirectory(),

                System.currentTimeMillis() + ".jpg");



        FileOutputStream fo;

        try {

            destination.createNewFile();

            fo = new FileOutputStream(destination);

            fo.write(bytes.toByteArray());

            fo.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }





        if(i==1) {flag++;
            ibm2.setImageBitmap(thumbnail);
        }
        if(i==2){flag++;
            ibm1.setImageBitmap(thumbnail);
        }
        if(flag==2){
            btn2.setEnabled(true);
            flag=0;}
    }



    @SuppressWarnings("deprecation")

    private void onSelectFromGalleryResult(Intent data) {



        Bitmap bm=null;

        if (data != null) {

            try {

                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());

            } catch (IOException e) {

                e.printStackTrace();

            }

        }



        if(i==2){flag++;
            ibm1.setImageBitmap(bm);}
        if(i==1){flag++;
            ibm2.setImageBitmap(bm);}
        if(flag==2){
            btn2.setEnabled(true);
            flag=0;}

    }


    public void updatetrow(){

    }
    public void deleterow(final View v){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("DELETE THE FIXTURE");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                table.removeView(v);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                v.setBackgroundColor(Color.BLUE);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();



    }

}