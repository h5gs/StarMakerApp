package com.archirayan.starmakerapp.activity;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;
import com.archirayan.starmakerapp.adapter.FindyourfreindsAdapter;
import com.archirayan.starmakerapp.utils.database;

public class FindYourFreindsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    RecyclerView item_findyourfreinds_list;
    FindyourfreindsAdapter findyourfreindsAdapter;
    TextView txt_next;
    Boolean permission = false;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 99;
    int exist;
    database myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_freinds);

        txt_next = findViewById(R.id.txt_next);
        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindYourFreindsActivity.this, PeopleLikeActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
            }
        });

        item_findyourfreinds_list = findViewById(R.id.item_findyourfreinds_list);
        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.M) {permission = checkContactsPermission();
            if (permission) {
                if (exist == 0) {
                    getLoaderManager().initLoader(1, null, this);
                    displayAllContacts();
                }
            }
        } else {
            if (exist == 0) {
                getLoaderManager().initLoader(1, null, this);
                displayAllContacts();
            }
        }
        findyourfreindsAdapter = new FindyourfreindsAdapter(FindYourFreindsActivity.this);
        item_findyourfreinds_list.setLayoutManager(new LinearLayoutManager(FindYourFreindsActivity.this, LinearLayoutManager.VERTICAL, false));
        item_findyourfreinds_list.setAdapter(findyourfreindsAdapter);
    }

    private void displayAllContacts() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FindYourFreindsActivity.this, EditprofileActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }
    public boolean checkContactsPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_CONTACTS)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == 1) {
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                    null,
                    null,
                    null,
                    "upper(" +
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME +
                            ") ASC");
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int hasPhoneNumber = Integer.parseInt(cursor.getString
                        (cursor.getColumnIndex
                                (ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex
                            (ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex
                            (ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                    ContentResolver contentResolver = getContentResolver();
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +
                                    " = ?",
                            new String[]{id},
                            null);
                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString
                                (phoneCursor.getColumnIndex
                                        (ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneCursor.close();
                        myDB.addContact(name, phoneNumber);
                    }
                }
            }
            displayAllContacts();
        }

    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}