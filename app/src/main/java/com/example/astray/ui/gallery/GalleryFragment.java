package com.example.astray.ui.gallery;


import android.content.Intent;
import android.graphics.Bitmap;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astray.MainActivity;
import com.example.astray.Models.Model;
import com.example.astray.Models.Users;
import com.example.astray.R;
import com.example.astray.databinding.ActivitySignInBinding;
import com.example.astray.home;
import com.example.astray.sign_in;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.HashMap;


import static android.app.Activity.RESULT_OK;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference root = firebaseDatabase.getReference().child("info");
    private StorageReference reference= FirebaseStorage.getInstance().getReference().child("Images");

    Button button,confirm;
    private EditText mName,mage,mGender,mM_place,mAddress,maboutchild,mparentname,mmno;
    private ImageView imageView;
    private Uri filePath;

    public GalleryFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
       // final TextView textView = root.findViewById(R.id.textView5);
       // galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        button = view.findViewById(R.id.sumbit);
        confirm = view.findViewById(R.id.Confirm);
        imageView = view.findViewById(R.id.add);
        mName = view.findViewById(R.id.etfirstname);
        mage = view.findViewById(R.id.etage);
        mGender = view.findViewById(R.id.etgender);
        mM_place = view.findViewById(R.id.etmissing_place);
        mAddress = view.findViewById(R.id.etaddress);
        maboutchild = view.findViewById(R.id.about_child);
        mparentname = view.findViewById(R.id.etparents_name);
        mmno = view.findViewById(R.id.mno);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(galleryIntent,2);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String age = mage.getText().toString();
                String gender = mGender.getText().toString();
                String missingplace = mM_place.getText().toString();
                String address = mAddress.getText().toString();
                String aboutchild = maboutchild.getText().toString();
                String parentname = mparentname.getText().toString();
                String mno = mmno.getText().toString();

                HashMap<String,String> infoMap = new HashMap<>();

                infoMap.put("name",name);
                infoMap.put("age",age);
                infoMap.put("gender",gender);
                infoMap.put("missingplace",missingplace);
                infoMap.put("address",address);
                infoMap.put("aboutchild",aboutchild);
                infoMap.put("parentname",parentname);
                infoMap.put("mno",mno);


                root.push().setValue(infoMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(GalleryFragment.super.getContext(), "Submitted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GalleryFragment.super.getContext(), "Confirm, Your data has been saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GalleryFragment.this.getContext(),home.class));
            }
        });


       return view;



    }


}