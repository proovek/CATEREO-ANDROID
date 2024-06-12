package com.ironsoft.catereo.ui.cart;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.ironsoft.catereo.R;
import com.ironsoft.catereo.api.ApiClient;
import com.ironsoft.catereo.api.ApiService;
import com.ironsoft.catereo.api.GET.GetCurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.CurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.CustomerCompanyDTO;
import com.ironsoft.catereo.api.Helpers.UserManager;
import com.ironsoft.catereo.api.POST.Helpers.NewOrder;
import com.ironsoft.catereo.api.POST.PostNewOrder;
import com.ironsoft.catereo.databinding.FragmentCartBinding;
import com.ironsoft.catereo.helpers.CategoriesListViewAdapter;
import com.ironsoft.catereo.helpers.FoodInCartItemModel;
import com.ironsoft.catereo.helpers.FoodInCartListViewAdapter;
import com.ironsoft.catereo.helpers.FoodItemModel;
import com.ironsoft.catereo.helpers.FoodListAdapter;
import com.ironsoft.catereo.helpers.OrderItemModel;
import com.ironsoft.catereo.realmdb.helpers.FoodInCartItemModelRealm;
import com.ironsoft.catereo.api.POST.Helpers.NewOrder;
import com.ironsoft.catereo.api.POST.Helpers.OrderPayment;
import com.ironsoft.catereo.api.POST.Helpers.OrderShipment;
import com.ironsoft.catereo.api.POST.Helpers.UserDataDTO;
import com.ironsoft.catereo.api.POST.Helpers.MenuItemDTO;
import com.ironsoft.catereo.api.POST.Helpers.Orderstatus;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    private RecyclerView cartView;
    private FoodInCartListViewAdapter foodInCartListViewAdapter;
    private RealmResults<FoodInCartItemModelRealm> yourFoodListData;
    private ImageView avatarImageView;
    private TextView deliveryAddress;
    private Button makeOrderButton;
    private TextView creditsLabel;
    private TextView credits;
    private LinearProgressIndicator progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Retrofit retrofit = ApiClient.getClient(getContext());
        ApiService apiService = retrofit.create(ApiService.class);

        Realm realm = Realm.getDefaultInstance();
        yourFoodListData = realm.where(FoodInCartItemModelRealm.class).findAll();

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        deliveryAddress = view.findViewById(R.id.deliveryAddress);
        makeOrderButton = view.findViewById(R.id.makeOrderButton);
        creditsLabel = view.findViewById(R.id.creditsLabel);
        credits = view.findViewById(R.id.credits);
        progressBar = view.findViewById(R.id.progressBar);


        List<OrderShipment> orderShipmentList = new ArrayList<>();

        GetCurrentUserDetails getCurrentUserDetails = new GetCurrentUserDetails(apiService);
        getCurrentUserDetails.execute(new GetCurrentUserDetails.GetProductCategoriesCallback() {
            @Override
            public void onSuccess(Call<CurrentUserDetails> call, Response<CurrentUserDetails> response) {
                CurrentUserDetails currentUserDetails = response.body();
                String myCreditString = "W sumie możesz wykorzystać" + "<b> " + currentUserDetails.getUser().getDailyCredits() * currentUserDetails.getUser().getWorkDays()  + " zł</b> " + "przez <b> " + currentUserDetails.getUser().getWorkDays() + " </b>dni roboczych.";
                String myCreditsLeft = "Do wykorzystania zostało" + "<b> " + currentUserDetails.getUser().getCredits() + " zł</b>";
                creditsLabel.setText(HtmlCompat.fromHtml(myCreditString, HtmlCompat.FROM_HTML_MODE_COMPACT));
                credits.setText(HtmlCompat.fromHtml(myCreditsLeft, HtmlCompat.FROM_HTML_MODE_COMPACT));

                double dailyCredits = currentUserDetails.getUser().getDailyCredits();
                int workDays = currentUserDetails.getUser().getWorkDays();
                double currentCredits = currentUserDetails.getUser().getCredits();

                double totalCreditsForPeriod = dailyCredits * workDays;

                double usedCredits = totalCreditsForPeriod - currentCredits;

                double progressPercentageDouble = ((totalCreditsForPeriod - usedCredits) / totalCreditsForPeriod) * 100;


                int progressPercentage = (int) Math.round(progressPercentageDouble);
                Log.d("progres", String.valueOf(progressPercentage));
                progressBar.setProgress(progressPercentage);

                List<CustomerCompanyDTO> companyList = currentUserDetails.getUser().getCustomerCompanyDTO();

                for (CustomerCompanyDTO company : companyList) {
                    if(company != null){
                        deliveryAddress.setText(company.getAddress());
                        orderShipmentList.add(new OrderShipment(company.getAddress(), Integer.parseInt(currentUserDetails.getUser().getPhone())));
                    } else {
                        deliveryAddress.setText("Brak adresu dostawy");
                    }
                }

            }

            @Override
            public void onError(Call<CurrentUserDetails> call, Throwable t) {

            }
        });

        avatarImageView = view.findViewById(R.id.avatarImageView);
        avatarImageView.setImageBitmap(generateAvatarFromName(UserManager.getUserName(getContext()).toUpperCase()));

        cartView = view.findViewById(R.id.cart_list_view);
        cartView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        foodInCartListViewAdapter = new FoodInCartListViewAdapter(getContext(), yourFoodListData);
        cartView.setAdapter(foodInCartListViewAdapter);

        yourFoodListData.addChangeListener(new RealmChangeListener<RealmResults<FoodInCartItemModelRealm>>() {
            @Override
            public void onChange(RealmResults<FoodInCartItemModelRealm> foodInCartItemModelRealms) {
                // Ta metoda zostanie wywołana za każdym razem, gdy dane się zmienią
                foodInCartListViewAdapter.notifyDataSetChanged();
                updateTotalPrice(foodInCartItemModelRealms, view);
            }
        });

        // Wywołanie po raz pierwszy, aby zainicjalizować wartość
        updateTotalPrice(yourFoodListData, view);

        List<UserDataDTO> userDataDTOList = new ArrayList<>();
        userDataDTOList.add(new UserDataDTO(UserManager.getUserId(getContext()), UserManager.getUserName(getContext()), UserManager.getUserEmail(getContext()), UserManager.getUserDisplayname(getContext()), UserManager.getUserPosition(getContext()), UserManager.getUserRole(getContext()), new ArrayList<>())); // Dodaj konkretny konstruktor lub ustaw wartości za pomocą setterów

        List<Orderstatus> orderStatusList = new ArrayList<>();
        orderStatusList.add(new Orderstatus("Nowe")); // Dodaj konkretny konstruktor lub ustaw wartości za pomocą setterów

        List<OrderPayment> orderPaymentsList = new ArrayList<>();
        TextView priceInTotalTextView = view.findViewById(R.id.priceInTotal);

        String numericPart = priceInTotalTextView.getText().toString().replaceAll("[^\\d,]", "");
        numericPart = numericPart.replace(',', '.');
        Double price = Double.parseDouble(numericPart);

        orderPaymentsList.add(new OrderPayment(price, "Płatność firmowa - Bestem")); // Dodaj konkretny konstruktor lub ustaw wartości za pomocą setterów

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            makeOrderButton.setOnClickListener(new View.OnClickListener() {

                ZonedDateTime zdt = ZonedDateTime.now();
                String formattedDate = zdt.format(DateTimeFormatter.ISO_INSTANT);

                // Przykładowe dane dla każdego z parametrów
                String orderDate = formattedDate;
                Object isDelivery = null; // Można ustawić na true/false w zależności od wymagań
                Object totalAmount = null; // Ustaw na konkretne wartości, jeśli potrzebne



                @Override
                public void onClick(View view) {
                    PostNewOrder postNewOrder = new PostNewOrder(apiService);

                    List<FoodInCartItemModelRealm> currentItems = realm.copyFromRealm(realm.where(FoodInCartItemModelRealm.class).findAll());

                    // Przygotuj dane do POST na podstawie aktualnych danych
                    List<MenuItemDTO> menuItemDTOList = new ArrayList<>();
                    for (FoodInCartItemModelRealm item : currentItems) {
                        MenuItemDTO menuItem = new MenuItemDTO(
                                item.getMenuItemId(),
                                item.getTitle(),
                                item.getQuantity(), // Używamy aktualnej ilości
                                item.getPrice(),
                                item.getRating()
                        );
                        menuItemDTOList.add(menuItem);
                    }

                    NewOrder newOrder = new NewOrder(
                            orderDate,
                            isDelivery,
                            totalAmount,
                            userDataDTOList,
                            menuItemDTOList,
                            orderStatusList,
                            orderShipmentList,
                            orderPaymentsList
                    );

                    postNewOrder.execute(newOrder, new PostNewOrder.PostNewOrderCallback() {
                        @Override
                        public void onSuccess(Call<Void> call, Response<Void> response) {
                            // Obsługa sukcesu, np. wyświetlenie komunikatu użytkownikowi
                            if (response.isSuccessful()) {
                                realm.beginTransaction();
                                realm.deleteAll();
                                realm.commitTransaction();
                                Toast.makeText(getContext(), "Zamówienie przesłane pomyślnie", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Błąd odpowiedzi serwera: \" + response.errorBody()", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Call<Void> call, Throwable t) {
                            // Obsługa błędu połączenia, np. wyświetlenie komunikatu o błędzie
                            Toast.makeText(getContext(), "Błąd połączenia: \" + t.getMessage()", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }

        return view;
    }
    private void updateTotalPrice(RealmResults<FoodInCartItemModelRealm> items, View view) {
        TextView priceInTotalTextView = view.findViewById(R.id.priceInTotal);
        double totalPrice = 0;
        for (FoodInCartItemModelRealm item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        priceInTotalTextView.setText(String.format("%.2f zł", totalPrice));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(yourFoodListData != null) {
            yourFoodListData.removeAllChangeListeners();
        }
        // Czyszczenie innych zasobów jeśli to konieczne
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