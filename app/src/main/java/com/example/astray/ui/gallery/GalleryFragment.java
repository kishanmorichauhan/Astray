package com.example.astray.ui.gallery;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.astray.R;
import com.example.astray.home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;


import static android.app.Activity.RESULT_OK;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference root = firebaseDatabase.getReference().child("info");

    Button button,confirm;
    private EditText mName,mage,mGender,mM_place,mAddress,maboutchild,mparentname,mmno;
    private ImageView childImage;
    private StorageReference storageReference;
    private FirebaseStorage storage;
    Uri mImageUri;


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
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        button = view.findViewById(R.id.sumbit);
        confirm = view.findViewById(R.id.Confirm);
        childImage = view.findViewById(R.id.add);
        mName = view.findViewById(R.id.etfirstname);
        mage = view.findViewById(R.id.etage);
        mGender = view.findViewById(R.id.etgender);
        mM_place = view.findViewById(R.id.etmissing_place);
        mAddress = view.findViewById(R.id.etaddress);
        maboutchild = view.findViewById(R.id.about_child);
        mparentname = view.findViewById(R.id.etparents_name);
        mmno = view.findViewById(R.id.mno);

        childImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000 && resultCode == RESULT_OK){
                Uri imageUri=data.getData();
            childImage.setImageURI(imageUri);
                uploadImagetoFirebase(imageUri);

        }
    }

    private void uploadImagetoFirebase(Uri imageUri) {

        //upload image to firebase storage
        StorageReference fileRef = FirebaseStorage.getInstance().getReference().child(UUID.randomUUID().toString());
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
             
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(GalleryFragment.super.getContext(), "Image is not Uploaded", Toast.LENGTH_SHORT).show();
            }
        });

    }
}