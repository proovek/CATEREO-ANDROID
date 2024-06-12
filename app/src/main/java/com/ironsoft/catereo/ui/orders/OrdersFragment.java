package com.ironsoft.catereo.ui.orders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ironsoft.catereo.R;
import com.ironsoft.catereo.SharedViewModel;
import com.ironsoft.catereo.api.ApiClient;
import com.ironsoft.catereo.api.ApiService;
import com.ironsoft.catereo.api.GET.GetCurrentUserDetails;
import com.ironsoft.catereo.api.GET.GetOrdersForCurrentUser;
import com.ironsoft.catereo.api.Helpers.CurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.CustomerCompanyDTO;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;
import com.ironsoft.catereo.api.Helpers.Order;
import com.ironsoft.catereo.api.Helpers.UserManager;
import com.ironsoft.catereo.api.POST.Helpers.OrderShipment;
import com.ironsoft.catereo.api.Helpers.Orderstatus;
import com.ironsoft.catereo.databinding.FragmentOrdersBinding;
import com.ironsoft.catereo.helpers.FoodItemModel;
import com.ironsoft.catereo.helpers.FoodListAdapter;
import com.ironsoft.catereo.helpers.OrderItemModel;
import com.ironsoft.catereo.helpers.OrderListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrdersFragment extends Fragment {

    private FragmentOrdersBinding binding;

    private RecyclerView ordersView;
    private OrderListAdapter orderListAdapter;
    private ImageView avatarImageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        List<OrderItemModel> yourOrdersListData = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getOrderItemsData().observe(getViewLifecycleOwner(), data -> {
            for (Order orderItem : data) {
                List<Orderstatus> statuses = orderItem.getOrderStatus();

                String date = orderItem.getOrderDate().substring(0, orderItem.getOrderDate().indexOf("T")); // "2024-01-18"

                String time = orderItem.getOrderDate().substring(orderItem.getOrderDate().indexOf("T") + 1, orderItem.getOrderDate().indexOf(":") + 6); // "11:47"

                Orderstatus latestStatus = null;

                if (statuses != null && !statuses.isEmpty()) {
                    Date latestDate = null;
                    for (Orderstatus status : statuses) {
                        String statusDateString = status.getStatusDate(); // Pobierz datę jako String
                        try {
                            Date statusDate = isoFormat.parse(statusDateString); // Próba przekonwertowania String na Date
                            if (latestDate == null || (statusDate != null && statusDate.after(latestDate))) {
                                latestDate = statusDate;
                                latestStatus = status;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace(); // Tutaj obsługujesz ParseException
                        }
                    }
                }
                yourOrdersListData.add(new OrderItemModel(orderItem.getOrderId().toString(), date, time, orderItem.getOrderPayments().get(0).getValue().toString() + " ZŁ", latestStatus.getStatus()));
            }
            orderListAdapter.notifyDataSetChanged();
        });


        avatarImageView = view.findViewById(R.id.avatarImageView);
        avatarImageView.setImageBitmap(generateAvatarFromName(UserManager.getUserName(getContext()).toUpperCase()));

        ordersView = view.findViewById(R.id.order_list_view);
        ordersView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        orderListAdapter = new OrderListAdapter(getContext(), yourOrdersListData);
        ordersView.setAdapter(orderListAdapter);

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