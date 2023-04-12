package com.example.responsi_map;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class LanguageFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View languageView = inflater.inflate(R.layout.fragment_language, container, false);

//        loadLocale();

        Button changeLang = languageView.findViewById(R.id.changeLanguage);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showChangeLanguageDialog();

                new FragmentDialogBox().show(getActivity().getSupportFragmentManager(), "fragmentDialog");
            }
        });

        return languageView;
    }

//    private void showChangeLanguageDialog() {
//        final String[] listLang = {"English", "Bahasa Indonesia"};
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
//        mBuilder.setTitle("Choose language...");
//        mBuilder.setSingleChoiceItems(listLang, -1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if (i == 0){
//                    setLocale("en");
//                    getActivity().recreate();
//                }
//                else if (i == 1){
//                    setLocale("id");
//                    getActivity().recreate();
//                }
//
//                dialogInterface.dismiss();
//            }
//        }
//        );
//
//        AlertDialog mDialog = mBuilder.create();
//        mDialog.show();
//
//    }

//    private void setLocale(String lang) {
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
//        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings", MODE_PRIVATE).edit();
//        editor.putString("My_Lang", lang);
//        editor.apply();
//    }
//
//    public void loadLocale() {
//        SharedPreferences pref = getActivity().getSharedPreferences("Settings", Activity.MODE_PRIVATE);
//        String language = pref.getString("My_Lang", "");
//        setLocale(language);
//    }


}