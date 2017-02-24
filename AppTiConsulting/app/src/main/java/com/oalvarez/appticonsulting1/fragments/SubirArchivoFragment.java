package com.oalvarez.appticonsulting1.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStorageDirectory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubirArchivoFragment extends Fragment {


    @BindView(R.id.btnFoto)
    Button btnFoto;
    @BindView(R.id.imgFoto)
    ImageView imgFoto;
    @BindView(R.id.tvRutaArchivo)
    TextView tvRutaArchivo;
    @BindView(R.id.btnSubirFoto)
    Button btnSubirFoto;
    @BindView(R.id.pbCargaarchivo)
    ProgressBar pbCargaarchivo;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    String filename;
    int nroTicket;

    public SubirArchivoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subir_archivo, container, false);
        ButterKnife.bind(this, view);

        final Bundle bundle = this.getArguments();

        if (bundle != null) {
            nroTicket = bundle.getInt("nroticket");

            Toast.makeText(getActivity(), String.valueOf(nroTicket), Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @OnClick({R.id.btnFoto, R.id.btnSubirFoto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFoto:
                seleccionarImagen();
                break;
            case R.id.btnSubirFoto:
                subirArchivo(tvRutaArchivo.getText().toString());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    getActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                File file = new File(filename);
                Bitmap bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1000, 700);

                //Toast.makeText(MainActivity.this, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                tvRutaArchivo.setText(file.getAbsolutePath());
                imgFoto.setImageBitmap(bitmap);
            }
            else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getActivity().getApplicationContext().getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                tvRutaArchivo.setText(picturePath);
                imgFoto.setImageBitmap(thumbnail);
            }
        }
    }

    public void seleccionarImagen() {


        final CharSequence[] opciones = {"Tomar Foto", "Escoger de la Galería", "Cancelar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Agregar Foto");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (opciones[which].equals("Tomar Foto")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File carpeta = new File(getExternalStorageDirectory() + File.separator + "Gesti" + File.separator + "default");
                    Boolean bExisteCarpeta = true;
                    if (!carpeta.exists()){
                        bExisteCarpeta = carpeta.mkdirs();
                    }

                    if (bExisteCarpeta){
                        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        filename = getExternalStorageDirectory() + File.separator + "Gesti" + File.separator + "default" + File.separator + "JPEG_" + timestamp + ".jpg";

                        //File file = new File(android.os.Environment.getExternalStorageDirectory() +File.separator + "Foto.jpg");
                        File file = new File(filename);

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                        //startActivityForResult(intent, 1);
                        startActivityForResult(intent, 1);
                    }



                } else if (opciones[which].equals("Escoger de la Galería")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (opciones[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight)
    { // BEST QUALITY MATCH

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight)
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth)
        {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    private void subirArchivo(String sRuta){

        pbCargaarchivo.setIndeterminate(true);
        pbCargaarchivo.setVisibility(View.VISIBLE);

        File file2 = new File(sRuta);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image*//*"), file2);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file2.getName(), reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "uploadtest");

        TicketsApiWs ticketsApiWs= HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<ResponseBody> respuesta = ticketsApiWs.CargarArchivo(body, name, nroTicket);

        respuesta.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getActivity(), "Archivo Subido", Toast.LENGTH_SHORT).show();
                pbCargaarchivo.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pbCargaarchivo.setVisibility(View.INVISIBLE);
                t.printStackTrace();
            }
        });
    }
}
