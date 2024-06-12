package com.ironsoft.catereo.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ironsoft.catereo.LoginScreen;
import com.ironsoft.catereo.R;
import com.ironsoft.catereo.SharedViewModel;
import com.ironsoft.catereo.api.ApiClient;
import com.ironsoft.catereo.api.ApiService;
import com.ironsoft.catereo.api.GET.GetCurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.Category;
import com.ironsoft.catereo.api.Helpers.CurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.CustomerCompanyDTO;
import com.ironsoft.catereo.api.Helpers.Ingredient;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;
import com.ironsoft.catereo.databinding.FragmentProfileBinding;
import com.ironsoft.catereo.helpers.FoodItemModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private static final String PREFS_NAME = "TokenPrefs";

    private TextView userNameTop;
    private TextView userName;
    private TextView userEmail;
    private TextView userPhone;
    private TextView userDisplayName;
    private TextView userCompanyName;
    private TextView userCompanyAddress;
    private CircleImageView userImage;

    private TextView credits;
    private Button logoutButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Retrofit retrofit = ApiClient.getClient(getContext());
        ApiService apiService = retrofit.create(ApiService.class);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userNameTop = view.findViewById(R.id.userNameTop);
        userName = view.findViewById(R.id.userName);
        userEmail = view.findViewById(R.id.userEmail);
        userPhone = view.findViewById(R.id.userPhone);
        userDisplayName = view.findViewById(R.id.userDisplayName);
        userCompanyName = view.findViewById(R.id.userCompanyName);
        userCompanyAddress = view.findViewById(R.id.userCompanyAddress);
        userImage = view.findViewById(R.id.userImage);
        credits = view.findViewById(R.id.credits);
        logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Usuwanie danych z SharedPreferences
                SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                // Przejście do ekranu logowania
                Intent intent = new Intent(getActivity(), LoginScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish(); // Zamyka obecną aktywność
            }
        });



        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getCurrentUserItemsData().observe(getViewLifecycleOwner(), data -> {
            List<CustomerCompanyDTO> companyList = data.getUser().getCustomerCompanyDTO();

            userImage.setImageBitmap(generateAvatarFromName(data.getUser().getUserName().toUpperCase()));

            userNameTop.setText(data.getUser().getUserName() != null ? data.getUser().getUserName() : "");
            userName.setText(data.getUser().getUserName() != null ? data.getUser().getUserName() : "");
            userEmail.setText(data.getUser().getUserEmail() != null ? data.getUser().getUserEmail() : "");
            userPhone.setText(data.getUser().getPhone() != null ? data.getUser().getPhone() : "");
            userDisplayName.setText(data.getUser().getDisplayName() != null ? data.getUser().getDisplayName() : "");
            credits.setText(data.getUser().getCredits() != null ? data.getUser().getCredits().toString() + " PLN" : "0.00");

            if (companyList != null && !companyList.isEmpty()) {
                for (CustomerCompanyDTO company : companyList) {
                    if(company != null ) {
                        userCompanyName.setText(company.getName() != null ? company.getName() : "");
                        userCompanyAddress.setText(company.getAddress() != null ? company.getAddress() : "");
                    } else {
                        userCompanyName.setText("Brak przypisania do firmy");
                        userCompanyAddress.setText("Brak przypisanego adresu");
                    }
                }
            } else {
                userCompanyName.setText("Brak przypisania do firmy");
                userCompanyAddress.setText("Brak przypisanego adresu");
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Bitmap generateAvatarFromName(String name) {
        // Tworzenie bitmapy z tłem jasnoniebieskim
        Bitmap backgroundBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        backgroundBitmap.eraseColor(0xFF006C67); // Kolor tła (jasnoniebieski)

        // Tworzenie canvas do rysowania
        Canvas canvas = new Canvas(backgroundBitmap);

        // Ustawienie koloru tekstu na biały
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);

        // Ustawienie rozmiaru tekstu
        textPaint.setTextSize(40);

        // Ustalenie pozycji tekstu na środku
        Rect textBounds = new Rect();
        textPaint.getTextBounds(name.substring(0, 1), 0, 1, textBounds);
        int x = (backgroundBitmap.getWidth() - textBounds.width()) / 2;
        int y = (backgroundBitmap.getHeight() + textBounds.height()) / 2;

        // Rysowanie pierwszej litery "name" na tle
        canvas.drawText(name.substring(0, 1), x, y, textPaint);

        return backgroundBitmap;
    }
}