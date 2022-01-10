package com.mygotoservices.mygotoandroid.Adaptors;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.BookServiceActivity;
import com.mygotoservices.mygotoandroid.CheckOutActivity;
import com.mygotoservices.mygotoandroid.ForgotPasswordActivity;
import com.mygotoservices.mygotoandroid.Fragments.HomeFragment;
import com.mygotoservices.mygotoandroid.Fragments.MarketFragment;
import com.mygotoservices.mygotoandroid.Fragments.ServicesFragment;
import com.mygotoservices.mygotoandroid.LoginActivity;
import com.mygotoservices.mygotoandroid.MainActivity;
import com.mygotoservices.mygotoandroid.Modules.BookingModule;
import com.mygotoservices.mygotoandroid.Modules.OrderDetailModule;
import com.mygotoservices.mygotoandroid.Modules.OrderModule;
import com.mygotoservices.mygotoandroid.Modules.OrderPlacementModule;
import com.mygotoservices.mygotoandroid.Modules.SliderModule;
import com.mygotoservices.mygotoandroid.Modules.UserModule;
import com.mygotoservices.mygotoandroid.OtherPaymentActivity;
import com.mygotoservices.mygotoandroid.R;
import com.mygotoservices.mygotoandroid.RealmTables.CartDetailTable;
import com.mygotoservices.mygotoandroid.RealmTables.CartTable;
import com.mygotoservices.mygotoandroid.RealmTables.ForgotPasswordTable;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.mygotoservices.mygotoandroid.SingleProductActivity;
import com.mygotoservices.mygotoandroid.SingleServiceActivity;
import com.mygotoservices.mygotoandroid.ThankYouActivity;
import com.mygotoservices.mygotoandroid.Views.ViewData.ArtisanData;
import com.mygotoservices.mygotoandroid.Views.ViewData.BookingData;
import com.mygotoservices.mygotoandroid.Views.ViewData.CartItemData;
import com.mygotoservices.mygotoandroid.Views.ViewData.CartTotalData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ImageData;
import com.mygotoservices.mygotoandroid.Views.ViewData.MenuData;
import com.mygotoservices.mygotoandroid.Views.ViewData.NotificationData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ProductData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ReviewData;
import com.mygotoservices.mygotoandroid.Views.ViewData.SliderData;
import com.mygotoservices.mygotoandroid.Views.ViewData.TopArtisanData;
import com.mygotoservices.mygotoandroid.Views.ViewType.ArtisanType;
import com.mygotoservices.mygotoandroid.Views.ViewType.BookingType;
import com.mygotoservices.mygotoandroid.Views.ViewType.CartItemType;
import com.mygotoservices.mygotoandroid.Views.ViewType.CartTotalType;
import com.mygotoservices.mygotoandroid.Views.ViewType.EndedNotificationType;
import com.mygotoservices.mygotoandroid.Views.ViewType.EstimateNotificationType;
import com.mygotoservices.mygotoandroid.Views.ViewType.ImageType;
import com.mygotoservices.mygotoandroid.Views.ViewType.MenuType;
import com.mygotoservices.mygotoandroid.Views.ViewType.NotificationType;
import com.mygotoservices.mygotoandroid.Views.ViewType.PendingNotificationType;
import com.mygotoservices.mygotoandroid.Views.ViewType.ProductType;
import com.mygotoservices.mygotoandroid.Views.ViewType.ReviewType;
import com.mygotoservices.mygotoandroid.Views.ViewType.SliderType;
import com.mygotoservices.mygotoandroid.Views.ViewType.StartedNotificationType;
import com.mygotoservices.mygotoandroid.Views.ViewType.TopArtisanType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import worker8.com.github.radiogroupplus.RadioGroupPlus;

/**
 * Created by isaac on 5/14/18.
 */

public class MultiCustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Object> albumList;
    private final int Artisans = 1, Product = 2, Slider = 3,TopArtisan = 4, Menu = 5, Image = 6, Review = 7, CartItem = 8, CartTotal = 9, MyBooking = 10, MyOrders = 11, MyFav = 12,NormalNotification = 13,EstimateNotification = 14, PendingNotification = 15, StartedNotification = 16, EndedNotification = 17;
    public static TextView title, count;
    public static ImageView overflow;
    private static DecimalFormat df2 = new DecimalFormat(".##");
    Activity mActivity;
    static int defaultShipment = 0, paymentMethod = 0;
    TextInputLayout tokenTIL;
    static String network = "Mtn";
    static double deliveryOption = 0.00;

    ProgressDialog progressDialog;

    public MultiCustomAdapter(Context mContext, List<Object> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }


    public MultiCustomAdapter(Context mContext, List<Object> albumList, Activity activity) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater itemView = LayoutInflater.from(parent.getContext());
        View view = null;
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //        Realm realm = Realm.getDefaultInstance();

        switch (viewType) {
            case Product:
                view = itemView.inflate(R.layout.row_product, parent, false);
                viewHolder = new ProductType(view);
                break;
            case Artisans:
                view = itemView.inflate(R.layout.row_artisan, parent, false);
                viewHolder = new ArtisanType(view);
                break;

            case Slider:
                view = itemView.inflate(R.layout.row_slider, parent, false);
                viewHolder = new SliderType(view);
                break;

            case Menu:
                view = itemView.inflate(R.layout.row_top_nav, parent, false);
                viewHolder = new MenuType(view);
                break;

            case TopArtisan:
                view = itemView.inflate(R.layout.row_top_artisans, parent, false);
                viewHolder = new TopArtisanType(view);
                break;

            case Review:
                view = itemView.inflate(R.layout.row_listing_rating, parent, false);
                viewHolder = new ReviewType(view);
                break;

            case Image:
                view = itemView.inflate(R.layout.row_listing_image, parent, false);
                viewHolder = new ImageType(view);
                break;

            case CartItem:
                view = itemView.inflate(R.layout.cart_row_item, parent, false);
                viewHolder = new CartItemType(view);
                break;

            case CartTotal:
                view = itemView.inflate(R.layout.cart_row_total, parent, false);
                viewHolder = new CartTotalType(view);
                break;

            case MyBooking:
                view = itemView.inflate(R.layout.booking_session_item, parent, false);
                viewHolder = new BookingType(view);
                break;

            case MyOrders:
                view = itemView.inflate(R.layout.order_item, parent, false);
                viewHolder = new CartTotalType(view);
                break;

            case NormalNotification:
                view = itemView.inflate(R.layout.notification_default_layout, parent, false);
                viewHolder = new NotificationType(view);
                break;

            case EstimateNotification:
                view = itemView.inflate(R.layout.notification_estimate_layout, parent, false);
                viewHolder = new EstimateNotificationType(view);
                break;

            case PendingNotification:
                view = itemView.inflate(R.layout.notification_pending_layout, parent, false);
                viewHolder = new PendingNotificationType(view);
                break;

            case StartedNotification:
                view = itemView.inflate(R.layout.notification_task_started_layout, parent, false);
                viewHolder = new StartedNotificationType(view);
                break;

            case EndedNotification:
                view = itemView.inflate(R.layout.notification_task_ended_layout, parent, false);
                viewHolder = new EndedNotificationType(view);
                break;

            default:
                view = itemView.inflate(R.layout.row_top_artisan_text, parent, false);
                viewHolder = new EndedNotificationType(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case Product:
                ProductType vh1 = (ProductType) holder;
                configureProduct(vh1, (ProductData) albumList.get(position), position);
                break;
            case Artisans:
                ArtisanType vh2 = (ArtisanType) holder;
                configureArtisan(vh2, (ArtisanData) albumList.get(position), position);
                break;
            case Slider:
                SliderType vh6 = (SliderType) holder;
                configureSlider(vh6, (SliderData) albumList.get(position), position);
                break;
            case Menu:
                MenuType vh3 = (MenuType) holder;
                configureMenu(vh3, (MenuData) albumList.get(position), position);
                break;
            case TopArtisan:
                TopArtisanType vh4 = (TopArtisanType) holder;
                configureTopArtisan(vh4, (TopArtisanData) albumList.get(position), position);
                break;

            case Review:
                ReviewType vh5 = (ReviewType) holder;
                configureRating(vh5, (ReviewData) albumList.get(position), position);
                break;

            case Image:
                ImageType vh7 = (ImageType) holder;
                configureImage(vh7, (ImageData) albumList.get(position), position);
                break;

            case CartItem:
                CartItemType vh8 = (CartItemType) holder;
                configureCartItem(vh8, (CartItemData) albumList.get(position), position);
                break;

            case CartTotal:
                CartTotalType vh9 = (CartTotalType) holder;
                configureCartType(vh9, (CartTotalData) albumList.get(position), position);
                break;

            case MyBooking:
                BookingType vh11 = (BookingType) holder;
                configureBooking(vh11, (BookingData) albumList.get(position), position);
                break;

            case NormalNotification:
                NotificationType vh12 = (NotificationType) holder;
                configureNotification(vh12, (NotificationData) albumList.get(position), position);
                break;
            case EstimateNotification:
                EstimateNotificationType vh13 = (EstimateNotificationType) holder;
                configureEstimateNotification(vh13, (NotificationData) albumList.get(position), position);
                break;
            case PendingNotification:
                PendingNotificationType vh14 = (PendingNotificationType) holder;
                configurePendingNotification(vh14, (NotificationData) albumList.get(position), position);
                break;
            case StartedNotification:
                StartedNotificationType vh15 = (StartedNotificationType) holder;
                configureStartedNotification(vh15, (NotificationData) albumList.get(position), position);
                break;
            case EndedNotification:
                EndedNotificationType vh16 = (EndedNotificationType) holder;
                configureEndedNotification(vh16, (NotificationData) albumList.get(position), position);
                break;

            default:
                //ProductType vh10 = (ProductType) holder;
                //configureProduct(vh10, (ProductData) albumList.get(position), position);

        }


    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (albumList.get(position) instanceof ProductData) {
            return Product;
        } else if (albumList.get(position) instanceof ArtisanData) {

            return Artisans;
        }

        if (albumList.get(position) instanceof SliderData) {
            return Slider;
        }

        if (albumList.get(position) instanceof TopArtisanData) {
            return TopArtisan;
        }

        if (albumList.get(position) instanceof MenuData) {
            return Menu;
        }


        if (albumList.get(position) instanceof ReviewData) {
            return Review;
        }

        if (albumList.get(position) instanceof ImageData) {
            return Image;
        }

        if (albumList.get(position) instanceof CartTotalData) {
            return CartTotal;
        }

        if (albumList.get(position) instanceof CartItemData) {
            return CartItem;
        }

        if (albumList.get(position) instanceof BookingData) {
            return MyBooking;
        }

        if (albumList.get(position) instanceof NotificationData) {
            NotificationData notificationData = (NotificationData) albumList.get(position);
            switch (notificationData.getType())
            {
                //'Normal','Pending','Estimate','Approve Estimate','Started','Ended','Confirm Completion'
                case "Normal":
                    return NormalNotification;
                case "Pending":
                    return PendingNotification;
                case "Estimate":
                    return EstimateNotification;
                case "Approve Estimate":
                    return PendingNotification;
                case "Started":
                    return StartedNotification;
                case "Ended":
                    return EndedNotification;
                case "Confirm Completion":
                    return NormalNotification;
                default:
                    return NormalNotification;
            }
        }
        return -1;
    }



    /**
     * Making notification bar transparent
     */

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = mActivity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    @Override
    public long getItemId(int position) {
        return position;
        //return imageModelArrayList.get(position).getUser_id();
    }

    private void configureNotification(NotificationType notificationType, final NotificationData notificationData, int position)
    {
        notificationType.getDateTime().setText(notificationData.getCreated());
        notificationType.getInformation().setText(notificationData.getNotification());
    }

    private void configureEstimateNotification(EstimateNotificationType notificationType, final NotificationData notificationData, int position)
    {
        notificationType.getDateTime().setText(notificationData.getCreated());
        notificationType.getInformation().setText(notificationData.getNotification());
        notificationType.getConfirmButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptEstimate(notificationData);
            }
        });
        notificationType.getDeclineButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declineEstimate(notificationData);
            }
        });

    }

    private void configurePendingNotification(PendingNotificationType notificationType, final NotificationData notificationData, int position)
    {
        notificationType.getDateTime().setText(notificationData.getCreated());
        notificationType.getInformation().setText(notificationData.getNotification());
    }

    private void configureStartedNotification(StartedNotificationType notificationType, final NotificationData notificationData, int position)
    {
        notificationType.getDateTime().setText(notificationData.getCreated());
        notificationType.getInformation().setText(notificationData.getNotification());

    }

    private void configureEndedNotification(EndedNotificationType notificationType, final NotificationData notificationData, int position)
    {
        notificationType.getDateTime().setText(notificationData.getCreated());
        notificationType.getInformation().setText(notificationData.getNotification());
        notificationType.getConfirmButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCompleted(notificationData);
            }
        });

    }


    private void configureBooking(BookingType bookingType, final BookingData bookingData, int position)
    {
        bookingType.getProductName().setText(bookingData.getListing().getMainName());
        bookingType.getProductQuantity().setText(bookingData.getNumber_hours()+" Session(s)");
        bookingType.getProductUnitPrice().setText(bookingData.getCategory().getPrice()+" GhC /session");
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.goicon)
                .error(R.drawable.goicon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(mContext).load(ApiLocation.getImage()+""+bookingData.getListing().getThumbnail())
                .thumbnail(0.5f)
                .apply(options)
                .into(bookingType.getThumbnail());
        if(bookingData.getCategory().getCharge_type().equals("Hourly"))
        {
            bookingType.getProductQuantity().setText(bookingData.getNumber_hours()+" Hour(s)");
            bookingType.getProductUnitPrice().setText(bookingData.getCategory().getPrice()+" /hour");
        }
        bookingType.getProductSubTotal().setText("Estimated : "+bookingData.getTotal_charge()+" GhC");

        bookingType.getBookingStatus().setText("Status : "+bookingData.getStatus());
        bookingType.getPaymentStatus().setText("Payment Status : "+bookingData.getPayment_status());
        bookingType.getEstimatedTime().setText("Time :"+bookingData.getStart_time());
        bookingType.getLikeBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeArtisan(bookingData);
            }
        });
        if(bookingData.getStatus().equals("Completed") && bookingData.getRated() == null)
        {
            //Todo rate this booking
            bookingType.getRateBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rateBooking(bookingData);
                }
            });
        }
        else
        {
            bookingType.getRateBtn().setVisibility(View.GONE);
            //Toast.makeText(mContext,"This booking is not completed, sorry you cant rate it",Toast.LENGTH_LONG).show();
        }
    }

    static int quantity ;
    private void configureCartItem(CartItemType cartItemType, final CartItemData cartItemData, final int myPosition)
    {
        final Realm realm = Realm.getDefaultInstance();
        final CartDetailTable cartDetailTable = realm.where(CartDetailTable.class).equalTo("listing_id",cartItemData.getListing_id()).findFirst();

        quantity = cartItemData.getQuantity();

        cartItemType.getMinusBtn().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                quantity = cartItemData.getQuantity();

                if(quantity > 1)
                {
                    quantity--;
                }
                realm.beginTransaction();
                cartDetailTable.setQuantity(quantity);
                cartItemData.setQuantity(quantity);
                cartDetailTable.setSub_total(quantity * cartDetailTable.getUnit_price());
                cartItemData.setSub_total(quantity * cartItemData.getUnit_price());
                realm.copyToRealmOrUpdate(cartDetailTable);
                realm.commitTransaction();

                CheckOutActivity.loadData();
                notifyDataSetChanged();
            }
        });

        cartItemType.getPlusBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = cartItemData.getQuantity();

                quantity++;
                realm.beginTransaction();
                cartDetailTable.setQuantity(quantity);
                cartItemData.setQuantity(quantity);
                cartDetailTable.setSub_total(quantity * cartDetailTable.getUnit_price());
                cartItemData.setSub_total(quantity * cartItemData.getUnit_price());
                realm.copyToRealmOrUpdate(cartDetailTable);
                realm.commitTransaction();
                CheckOutActivity.loadData();
                notifyDataSetChanged();
            }
        });
        cartItemType.getRemoveItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                cartDetailTable.deleteFromRealm();
                realm.copyToRealmOrUpdate(cartDetailTable);
                realm.commitTransaction();
                CheckOutActivity.loadData();
                notifyDataSetChanged();
            }
        });

        cartItemType.getProductName().setText(cartItemData.getName());
        cartItemType.getProductQuantity().setText(cartItemData.getQuantity()+"");
        cartItemType.getProductSubTotal().setText(cartItemData.getSub_total()+"");
        cartItemType.getProductUnitPrice().setText(cartItemData.getUnit_price()+"");
    }

    private void configureCartType(CartTotalType cartTotalType,CartTotalData cartTotalData, int myPosition)
    {
        cartTotalType.getSubTotal().setText(getSubTotal()+"");
        cartTotalType.getDeliveryCost().setText("0.00");
        final double cost = (Double.parseDouble(cartTotalType.getSubTotal().getText().toString().trim()) + Double.parseDouble(cartTotalType.getDeliveryCost().getText().toString().trim()));
        cartTotalType.getTotalCost().setText((Double.parseDouble(cartTotalType.getSubTotal().getText().toString().trim()) + Double.parseDouble(cartTotalType.getDeliveryCost().getText().toString().trim())) +" GhC");
        cartTotalType.getSubTotal().setText(getSubTotal()+" GhC");
        if(cost > 0.00)
        cartTotalType.getCheckoutNow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();
                UserTable userTable = realm.where(UserTable.class).findFirst();
                if(userTable != null)
                {
                    JSONArray jsonArray = new JSONArray();
                    List<OrderDetailModule> orderDetailModules = new ArrayList<>();
                    RealmResults<CartDetailTable> cartDetailTableRealmResults = realm.where(CartDetailTable.class).greaterThan("quantity",0).findAll();
                    double total = 0.00;
                    for (int i =0; i<cartDetailTableRealmResults.size();i++)
                    {
                        OrderDetailModule orderDetailModule = new OrderDetailModule();
                        CartDetailTable cartDetailTable = cartDetailTableRealmResults.get(i);
                        orderDetailModule.setListing_id(cartDetailTable.getListing_id());
                        orderDetailModule.setQuantity(cartDetailTable.getQuantity());
                        orderDetailModule.setSub_total(cartDetailTable.getSub_total());
                        orderDetailModule.setUnit_price(cartDetailTable.getUnit_price());
                        orderDetailModule.setUser_id(userTable.getUser_id());
                        total += cartDetailTable.getSub_total();
                        try {
                            jsonArray.put(i,orderDetailModule.toJSon());
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    OrderModule orderModule = new OrderModule();
                    orderModule.setAmount(total);
                    orderModule.setUser_id(userTable.getUser_id());
                    showDeliveryOptions(cost);
                    //submitOrder();
                }
                else
                {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }

            }
        });

        cartTotalType.getEmptyCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.where(CartDetailTable.class).findAll().deleteAllFromRealm();
                realm.commitTransaction();
            }
        });

    }

    private boolean validateUsing_libphonenumber(String countryCode, String phNumber)
    {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        String isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCode));
        Phonenumber.PhoneNumber mPhoneNumber = null;
        try {
            //phoneNumber = phoneNumberUtil.parse(phNumber, "IN");  //if you want to pass region code
            mPhoneNumber = phoneNumberUtil.parse(phNumber, isoCode);
        } catch (NumberParseException e) {
            System.err.println(e);
        }
        boolean isValid = phoneNumberUtil.isValidNumber(mPhoneNumber);
        if (isValid) {
            phoneNumber = phoneNumberUtil.format(mPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            return true;
        } else {
            //Toast.makeText(this, "Phone Number is Invalid " + phoneNumber, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void configureRating(ReviewType reviewType,ReviewData reviewData, int myPosition)
    {
        reviewType.getRatingBar().setNumStars(5);
        reviewType.getRatingBar().setMax(5);
        reviewType.getRatingBar().setRating(Float.valueOf(reviewData.getRating()+""));
        reviewType.getRatingValue().setText(reviewData.getRating()+" Out Of 5");
        reviewType.getReviewDescription().setText(reviewData.getDescription());
        reviewType.getUserName().setText("");
    }

    private void configureImage(ImageType imageType, ImageData imageData, int myPosition)
    {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.goicon)
                .error(R.drawable.goicon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(mContext).load(ApiLocation.getImage()+""+imageData.getImage())
                .thumbnail(0.5f)
                .apply(options)
                .into(imageType.getImageView());
    }

    private void configureProduct(final ProductType productType, final ProductData productData, final int position) {
        System.out.println("Product Service "+productData.getCurrency());
        productType.getAvg_ratings().setText(productData.getRatings()+"");
        String currency = "GhC";
        if(productData.getCurrency() != "")
        {
            currency = productData.getCurrency();

        }
        productType.getLocation().setText(productData.getUnit_price()+" "+currency);
        productType.getMainName().setText(productData.getMainName());
        productType.getType().setText(productData.getType());
        productType.getNumber_orders().setText(productData.getNumber_orders());
        productType.getRatingBar().setNumStars(5);
        productType.getRatingBar().setMax(5);
        productType.getRatingBar().setRating(Float.valueOf(productData.getRatings()+""));
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.goicon)
                .error(R.drawable.goicon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        //topArtisanData.getRating()
        Glide.with(mContext).load(ApiLocation.getImage()+""+productData.getThumbnail())
                .thumbnail(0.5f)
                .apply(options)
                .into(productType.getThumbnail());

        productType.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleProductActivity.class);
                intent.putExtra("listing_id",productData.getId());
                intent.putExtra("lon",MarketFragment.mLongitude);
                intent.putExtra("lat",MarketFragment.mLatitude);
                mContext.startActivity(intent);
            }
        });
    }


    private void configureArtisan(final ArtisanType artisanType, final ArtisanData artisanData, final int position) {
        artisanType.getAvg_ratings().setText(artisanData.getRatings()+"");
        artisanType.getLocation().setText(artisanData.getLocation());
        artisanType.getMainName().setText(artisanData.getType());
        artisanType.getType().setText(artisanData.getType());
        artisanType.getNumber_orders().setText(artisanData.getNumber_orders());
        artisanType.getRatingBar().setNumStars(5);
        artisanType.getRatingBar().setMax(5);
        artisanType.getArtisanCategory().setText(artisanData.getCategory());
        artisanType.getRatingBar().setRating(Float.valueOf(artisanData.getRatings()+""));
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.goicon)
                .error(R.drawable.account)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        //topArtisanData.getRating()
        Glide.with(mContext).load(ApiLocation.getImage()+""+artisanData.getImage())
                .thumbnail(0.5f)
                .apply(options)
                .into(artisanType.getThumbnail());
        //Toast.makeText(mContext,artisanData.getImage(),Toast.LENGTH_LONG).show();
        //topArtisanType.getThumbnail()


        artisanType.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleServiceActivity.class);
                intent.putExtra("listing_id",artisanData.getId());
                intent.putExtra("lon",ServicesFragment.mLongitude);
                intent.putExtra("lat",ServicesFragment.mLatitude);
                intent.putExtra("currentLocation",ServicesFragment.currentLocation);

                mContext.startActivity(intent);
            }
        });


    }

    private static SliderType hh;
    private static int[] layouts = new int[]{
            R.layout.banner_layout
    };
    static List <SliderData> headerDetails ;
    static ViewPager viewPager;

    private void configureSlider(final SliderType h, final SliderData sliderData, final int position) {
        // adding bottom dots
        hh = h;
        //Toast.makeText(mContext,""+)

        // making notification bar transparent
        //changeStatusBarColor();
        int myWidth = 512;
        int myHeight = 384;
        List<SliderModule> sliderModules = HomeFragment.sliderModules;
        headerDetails = new ArrayList<>();
        headerDetails.clear();
        for(int q = 0; q < sliderModules.size(); q++)
        {
            SliderData sliderData1 = new SliderData();
            SliderModule sliderModule = sliderModules.get(q);
            sliderData1.setAdvert_id(sliderModule.getAdvert_id());
            sliderData1.setFile_name(sliderModule.getFile_name());
            sliderData1.setUuid(sliderModule.getUuid());
            headerDetails.add(sliderData1);
        }
        //headerDetails = header.getHeaderDetails();
        if(headerDetails == null || headerDetails.size() == 0)
        {
            for (int i = 0; i< 5; i ++)
            {
                SliderData headerDetails1 = new SliderData();
                //headerDetails1.setThumbnail("phpNiF9Ob.png");
                //headerDetails1.setName("");
                headerDetails.add(headerDetails1);

            }
        }
        layouts = new int[headerDetails.size()];
        //            for (int i=0; i<headerDetails.size(); i++)

        for (int i=0; i<headerDetails.size(); i++)
        {
            layouts[i] = R.layout.banner_layout;
        }
        addBottomDots(0,layouts,h);

        viewPager = h.getViewPager();
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(mContext, layouts);
        viewPager.setAdapter(myViewPagerAdapter);
        myViewPagerAdapter.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        myViewPagerAdapter.notifyDataSetChanged();


    }

    private void configureMenu(final MenuType orderTotalType, final MenuData orderTotalData, final int position) {

        orderTotalType.getBestPerson().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        orderTotalType.getDailyOffer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        orderTotalType.getFreeService().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        orderTotalType.getPlayWin().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void configureTopArtisan(final TopArtisanType topArtisanType, final TopArtisanData topArtisanData, final int position) {
        topArtisanType.getArtisan_name().setText(topArtisanData.getUser());
        topArtisanType.getTitle().setText(topArtisanData.getName());
        topArtisanType.getCurrent_rating_number().setText(topArtisanData.getRating()+"");
        topArtisanType.getOrder_numbers().setText(topArtisanData.getBookings()+" Orders");
        topArtisanType.getRatingBar().setNumStars(5);
        topArtisanType.getRatingBar().setMax(5);
        topArtisanType.getRatingBar().setRating(Float.valueOf(topArtisanData.getRating()+""));
        //topArtisanData.getRating()
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.goicon)
                .error(R.drawable.account)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(mContext).load(ApiLocation.getImage()+""+topArtisanData.getImage())
                .thumbnail(0.5f)
                .apply(options)
                .into(topArtisanType.getThumbnail());
        //topArtisanType.getThumbnail()
        topArtisanType.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleServiceActivity.class);
                intent.putExtra("listing_id",topArtisanData.getListing_id());
                intent.putExtra("lon",HomeFragment.mLongitude);
                intent.putExtra("lat",HomeFragment.mLatitude);
                intent.putExtra("currentLocation","");
                mContext.startActivity(intent);
            }
        });
    }




    private void addBottomDots(int currentPage, int[] layouts,SliderType header) {

         TextView[] dots;

        dots = new TextView[layouts.length];

        int[] colorsActive = mContext.getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = mContext.getResources().getIntArray(R.array.array_dot_inactive);

        header.getLayoutDots().removeAllViews();
        for (int i = 0; i < layouts.length; i++) {
            dots[i] = new TextView(mContext);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[0]);
            header.getLayoutDots().addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[0]);

    }

    /**
     * View pager adapter
     */

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private Context mContext;
        private int[] layouts;
        public MyViewPagerAdapter(Context context, int [] layouts) {
            this.mContext = context;
            this.layouts = layouts;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            System.out.println("Position "+position);
            //Toast.makeText(mContext,headerDetails.get(position).getThumbnail(),Toast.LENGTH_LONG).show();

            layoutInflater = (LayoutInflater) mContext.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            final View view = layoutInflater.inflate(layouts[position], container, false);

            int myWidth = 512;
            int myHeight = 384;

                        Glide.with(mContext).asBitmap().load(ApiLocation.getImage()+headerDetails.get(position).getFile_name()).into(new SimpleTarget<Bitmap>(myWidth, myHeight) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                Drawable drawable = new BitmapDrawable(resource);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    view.findViewById(R.id.relative).setBackground(drawable);
                                }

                            }

                        });

                        //TextView header = view.findViewById(R.id.header);
                        /*
                        *
                        *
                                    Glide.with(mContext).load(ApiLocation.getImage()+headerDetails.get(position).getFile_name()).asBitmap().into(new SimpleTarget<Bitmap>(myWidth, myHeight) {
                                        @Override
                                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                            Drawable drawable = new BitmapDrawable(resource);
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                                view.findViewById(R.id.relative).setBackground(drawable);
                                            }
                                        }
                                    });

                        * */

                        //TextView second_header = view.findViewById(R.id.secondHeader);
                        //header.setText(position+"");

            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }



    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position,layouts,hh);

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private double getSubTotal()
    {
        double total = 0.00;
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CartDetailTable> cartDetailTables = realm.where(CartDetailTable.class).findAll();

        for(int i=0; i< cartDetailTables.size(); i++)
        {
            total += cartDetailTables.get(i).getSub_total();
        }
        return  total;
    }

    void submitOrder(final Dialog dialog)
    {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Submitting your order ...");
        progressDialog.setTitle("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JSONArray jsonArray = new JSONArray();
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        List<OrderDetailModule> orderDetailModules = new ArrayList<>();
        RealmResults<CartDetailTable> cartDetailTableRealmResults = realm.where(CartDetailTable.class).greaterThan("quantity",0).findAll();
        double total = 0.00;
            OrderDetailModule orderDetailModule = new OrderDetailModule();
        for (int i =0; i<cartDetailTableRealmResults.size();i++)
        {
            CartDetailTable cartDetailTable = cartDetailTableRealmResults.get(i);
            orderDetailModule.setListing_id(cartDetailTable.getListing_id());
            orderDetailModule.setQuantity(cartDetailTable.getQuantity());
            orderDetailModule.setSub_total(cartDetailTable.getSub_total());
            orderDetailModule.setUnit_price(cartDetailTable.getUnit_price());
            orderDetailModule.setUser_id(userTable.getUser_id());
            total += cartDetailTable.getSub_total();
            try
            {
                jsonArray.put(i,orderDetailModule.toJSon());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        OrderModule orderModule = new OrderModule();
        orderModule.setAmount(total);
        orderModule.setUser_id(userTable.getUser_id());
        orderModule.setPayment_means(paymentChannel);
        orderModule.setNetwork(network);
        orderModule.setToken(token);
        orderModule.setPhone(phoneNumber);
        orderModule.setDelivery_charge(deliveryOption);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = orderModule.toJSon();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Call<OrderPlacementModule> login = endpoints.placeOrder(jsonObject,jsonArray);
        login.enqueue(new Callback<OrderPlacementModule>() {
            @Override
            public void onResponse(Response<OrderPlacementModule> response, Retrofit retrofit) {
                OrderPlacementModule user = response.body();
                Intent intent;
                switch (paymentMethod)
                {
                    case 1:
                        paymentChannel = "cash";
                            Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();
                            intent = new Intent(mContext,ThankYouActivity.class);
                            mContext.startActivity(intent);
                            dialog.dismiss();
                            dialog.hide();
                            mActivity.finish();
                        break;
                    case 2:
                        paymentChannel = "mobileMoney";
                            Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();
                            intent = new Intent(mContext,ThankYouActivity.class);
                            mContext.startActivity(intent);
                            dialog.dismiss();
                            dialog.hide();
                            mActivity.finish();
                        break;

                    default:
                        paymentChannel = "cash";
                            Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();
                            intent = new Intent(mContext,ThankYouActivity.class);
                            mContext.startActivity(intent);
                            dialog.dismiss();
                            dialog.hide();
                            mActivity.finish();
                        break;
                }
                clearOrder();
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(mContext,"Sorry booking failed",Toast.LENGTH_LONG).show();

            }
        });

    }

    public void showDialog(final double cost)
    {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.complete_order_booking);

        TextView subTotal = dialog.findViewById(R.id.subTotal);
        TextView deliveryCost = dialog.findViewById(R.id.deliveryCost);
        TextView grandTotal = dialog.findViewById(R.id.grandTotal);
        subTotal.setText(cost+" GhC");

        if(defaultShipment == 1)
        {
            deliveryOption = 14.00;
            deliveryCost.setText(deliveryOption+" GhC");
        }
        else
        {
            deliveryOption = 0.00;
            deliveryCost.setText(deliveryOption+" GhC");
        }
        grandTotal.setText((deliveryOption +  cost)+" GhC");
        final RadioGroupPlus radio_group_plus = dialog.findViewById(R.id.radio_group_plus);
        Button scheduledBtn = dialog.findViewById(R.id.scheduledBtn);
        final RadioButton cashPayment = dialog.findViewById(R.id.cashPayment);
        TextView cashPaymentText = dialog.findViewById(R.id.cashPaymentText);
        cashPaymentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashPayment.setChecked(true);

            }
        });

        final RadioButton mobileMoneyPayment = dialog.findViewById(R.id.mobileMoneyPayment);
        TextView mobileMoneyPaymentText = dialog.findViewById(R.id.mobileMoneyPaymentText);
        mobileMoneyPaymentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileMoneyPayment.setChecked(true);
            }
        });

        mobileMoneyPayment.setSelected(true);
        mobileMoneyPayment.setChecked(true);

        final RadioButton cardPayment = dialog.findViewById(R.id.cardPayment);
        TextView cardPaymentText = dialog.findViewById(R.id.cardPaymentText);
        cardPaymentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPayment.setChecked(true);
            }
        });

        final RadioButton slydePay = dialog.findViewById(R.id.slydePay);

        TextView slydePayText = dialog.findViewById(R.id.slydePayText);

        slydePayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slydePay.setChecked(true);
            }
        });

        scheduledBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radio_group_plus.getCheckedRadioButtonId())
                {
                    case R.id.cashPayment:
                        paymentChannel = "cash";
                        paymentMethod = 1;
                        dialog.dismiss();
                        submitOrder(dialog);
                        break;
                    case R.id.mobileMoneyPayment:
                        paymentChannel = "mobileMoney";
                        paymentMethod = 2;
                        showMMPaymentMethodOptions(cost);
                        break;

                    default:
                        paymentChannel = "cash";
                        paymentMethod = 1;
                        dialog.dismiss();
                        submitOrder(dialog);
                        break;
                }
                dialog.hide();

            }
        });
        dialog.show();
    }

    private void showDeliveryOptions(final double cost)
    {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.delivery_options_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        final RadioGroupPlus radio_group_plus = dialog.findViewById(R.id.radio_group_plus);
        Button deliveryMethodBtn = dialog.findViewById(R.id.deliveryMethodBtn);
        final RadioButton locationDeliveryRadioBtn = dialog.findViewById(R.id.locationDeliveryRadioBtn);
        TextView locationDeliveryTV = dialog.findViewById(R.id.locationDeliveryTV);
        locationDeliveryTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationDeliveryRadioBtn.setChecked(true);
            }
        });

        final RadioButton personalPickBtn = dialog.findViewById(R.id.personalPickBtn);
        TextView personalPickTV = dialog.findViewById(R.id.personalPickTV);
        personalPickTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalPickBtn.setChecked(true);
            }
        });

        locationDeliveryRadioBtn.setSelected(true);
        locationDeliveryRadioBtn.setChecked(true);

        deliveryMethodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                switch (radio_group_plus.getCheckedRadioButtonId())
                {
                    case R.id.locationDeliveryRadioBtn:
                        defaultShipment = 1;
                        dialog.dismiss();
                        showDialog(cost);
                        break;
                    case R.id.personalPickBtn:
                        defaultShipment = 2;
                        showDialog(cost);
                        break;

                    default:
                        defaultShipment = 2;
                        dialog.dismiss();
                        showDialog(cost);
                        break;
                }
                dialog.hide();
            }
        });
        dialog.show();
    }

    static String paymentChannel = "";
    static String phoneNumber = "";
    static String token = "";

    private void showMMPaymentMethodOptions(double cost)
    {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.complete_mobile_money_order_placement);
        TextView estimatedCost = dialog.findViewById(R.id.grandTotal);
        RadioGroupPlus radioGroupPlus = dialog.findViewById(R.id.radio_group_plus);
        final RadioButton mtnMobileMoney = dialog.findViewById(R.id.mtnMobileMoney);
        TextView mtnMobileMoneyText = dialog.findViewById(R.id.mtnMobileMoneyText);
        final RadioButton airtelMoney = dialog.findViewById(R.id.airtelMoney);
        TextView airtelMoneyText = dialog.findViewById(R.id.airtelMoneyText);
        final RadioButton tigoCash = dialog.findViewById(R.id.tigoCash);
        TextView tigoCashText = dialog.findViewById(R.id.tigoCashText);
        final RadioButton vodafoneCash = dialog.findViewById(R.id.vodafoneCash);
        TextView vodafoneCashText = dialog.findViewById(R.id.vodafoneCash);
        final EditText vodafoneToken = dialog.findViewById(R.id.vodafoneToken);
        final EditText mPhoneNumber = dialog.findViewById(R.id.phoneNumber);
        tokenTIL = dialog.findViewById(R.id.tokenTIL);
        Button scheduledBtn = dialog.findViewById(R.id.scheduledBtn);

        mtnMobileMoney.setChecked(true);
        estimatedCost.setText(cost+" GhC");
        mtnMobileMoney.setSelected(true);
        vodafoneToken.setEnabled(false);

        mtnMobileMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Mtn";
                vodafoneToken.setEnabled(false);

            }
        });

        mtnMobileMoneyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Mtn";
                mtnMobileMoney.setChecked(true);
                vodafoneToken.setEnabled(false);

            }
        });

        airtelMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Airtel";

            }
        });

        airtelMoneyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Airtel";
                airtelMoney.setChecked(true);
                vodafoneToken.setEnabled(false);

            }
        });


        tigoCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Tigo";
                vodafoneToken.setEnabled(false);

            }
        });

        tigoCashText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Tigo";
                tigoCash.setChecked(true);
                vodafoneToken.setEnabled(false);

            }
        });


        vodafoneCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Vodafone";
                vodafoneToken.setEnabled(true);

            }
        });

        vodafoneCashText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Vodafone";
                vodafoneCash.setChecked(true);
                vodafoneToken.setEnabled(true);

            }
        });


        scheduledBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = false;
                if(network.equals("Vodafone"))
                {
                    token = vodafoneToken.getText().toString().trim();
                    if(token.length() > 4)
                    {
                        isValid = true;
                    }
                }
                else
                {
                    isValid = true;
                }
                if(mPhoneNumber.getText().toString().trim().length() > 8 && isValid)
                {

                    phoneNumber = mPhoneNumber.getText().toString().trim();
                    if(validateUsing_libphonenumber("233",phoneNumber))
                    {
                        submitOrder(dialog);
                    }
                }
                else
                {
                    Toast.makeText(mContext,"Kindly provide a valid phone number",Toast.LENGTH_LONG).show();
                }
                if(!isValid)
                {
                    Toast.makeText(mContext,"Kindly provide a valid token",Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    void clearOrder()
    {
        Realm realm= Realm.getDefaultInstance();
        RealmResults<CartDetailTable> cartDetailTableRealmResults= realm.where(CartDetailTable.class).findAll();
        CartTable cartTable = realm.where(CartTable.class).findFirst();
        realm.beginTransaction();
        if(!cartDetailTableRealmResults.isEmpty())
            cartDetailTableRealmResults.deleteAllFromRealm();
        if(cartTable != null)
            cartTable.deleteFromRealm();

        realm.commitTransaction();
        realm.close();
    }

    private void rateBooking(final BookingData bookingData)
    {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.rate_booking_dialog);
        ImageView imageView = dialog.findViewById(R.id.artisanImage);
        TextView textView = dialog.findViewById(R.id.ratingText);
        final TextInputEditText commentText = dialog.findViewById(R.id.commentText);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.goicon)
                .error(R.drawable.goicon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        textView.setText("How will you rate the service that was provided by "+bookingData.getListing().getMainName()+" ?");

        Glide.with(mContext).load(ApiLocation.getImage()+""+bookingData.getListing().getThumbnail())
                .thumbnail(0.5f)
                .apply(options)
                .into(imageView);
        final RatingBar ratingBar = dialog.findViewById(R.id.currentRating);
        Button button = dialog.findViewById(R.id.rateService);
        ratingBar.setStepSize(1);
        ratingBar.setNumStars(5);
        ratingBar.setRating(0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                dialog.dismiss();

                final ProgressDialog progressDialog = new ProgressDialog(mContext);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Completing request ...");
                progressDialog.setTitle("Please wait ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                JSONArray jsonArray = new JSONArray();
                Realm realm = Realm.getDefaultInstance();
                UserTable userTable = realm.where(UserTable.class).findFirst();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiLocation.getLocation())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
                String input = "";
                if(commentText.getText().toString().trim() != null)
                 input =  commentText.getText().toString().trim();
                Call<BookingModule> login = endpoints.rateBooking(userTable.getUser_id(),bookingData.getListing_id(),ratingBar.getRating(),bookingData.getId(),input);
                login.enqueue(new Callback<BookingModule>() {
                    @Override
                    public void onResponse(Response<BookingModule> response, Retrofit retrofit) {
                        BookingModule user = response.body();
                        if(response.code() == 200 && user.getResponseCode().equals("200"))
                        {
                            Toast.makeText(mContext,"Thank you for rating this artisan",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(mContext,"Sorry, Try again later",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }
                    @Override
                    public void onFailure(Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(mContext,"Sorry rating failed",Toast.LENGTH_LONG).show();

                    }
                });



            }
        });



        dialog.show();
    }

    private void likeArtisan(BookingData bookingData)
    {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Completing request ...");
        progressDialog.setTitle("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JSONArray jsonArray = new JSONArray();
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);


        Call<BookingModule> login = endpoints.addToFav(userTable.getUser_id(),bookingData.getListing_id(),bookingData.getId());
        login.enqueue(new Callback<BookingModule>() {
            @Override
            public void onResponse(Response<BookingModule> response, Retrofit retrofit) {
                BookingModule user = response.body();
                    if(response.code() == 200 && user.getResponseCode().equals("200"))
                    {
                        Toast.makeText(mContext,"This listing has been added to your favourites",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(mContext,"Sorry, Try again later",Toast.LENGTH_LONG).show();
                    }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(mContext,"Sorry add to favourites failed",Toast.LENGTH_LONG).show();

            }
        });

    }

    void acceptEstimate(final NotificationData notificationData)
    {

        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.confirm_estimate_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView cancelBtn = dialog.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                dialog.dismiss();
            }
        });

        Button acceptBtn = dialog.findViewById(R.id.acceptBtn);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    dialog.hide();
                    dialog.dismiss();
                confirmEstimate(notificationData.getBooking_id()+"",notificationData.getId()+"");

            }
        });

        dialog.show();

    }

    void confirmCompleted(final NotificationData notificationData)
    {

        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.confirm_completion_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView cancelBtn = dialog.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                dialog.dismiss();
            }
        });

        Button acceptBtn = dialog.findViewById(R.id.acceptBtn);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.hide();
                dialog.dismiss();
                confirmCompletion(notificationData.getBooking_id()+"",notificationData.getId()+"");

            }
        });

        dialog.show();

    }

    void declineEstimate(final NotificationData notificationData)
    {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.decline_estimate);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView cancelBtn = dialog.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                dialog.dismiss();

            }
        });

        Button acceptBtn = dialog.findViewById(R.id.acceptBtn);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                dialog.dismiss();
                declineEstimate(notificationData.getBooking_id()+"",notificationData.getId()+"");

            }
        });

        dialog.show();


    }


    void confirmEstimate(final String string, String id)
    {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(mContext);

        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.confirmEstimate(string,id);
        login.enqueue(new Callback<UserModule>() {
            @Override
            public void onResponse(Response<UserModule> response, Retrofit retrofit) {
                UserModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {
                    if(user.getResponseCode() == 200)
                    {

                        Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(mContext,MainActivity.class);
                        mContext.startActivity(intent);
                        //mContext.finish();
                    }
                    else
                    {

                        Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();

                    }
                }
                else
                {

                    Toast.makeText(mContext,"Sorry an error was encountered. Error code "+response.code(),Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(mContext,"Sorry an error was encountered",Toast.LENGTH_LONG).show();

            }
        });


    }



    void declineEstimate(final String string,final String id)
    {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(mContext);

        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.declineEstimate(string, id);
        login.enqueue(new Callback<UserModule>() {
            @Override
            public void onResponse(Response<UserModule> response, Retrofit retrofit) {
                UserModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {
                    if(user.getResponseCode() == 200)
                    {

                        Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(mContext,MainActivity.class);
                        mContext.startActivity(intent);
                    }
                    else
                    {

                        Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(mContext,"Sorry an error was encountered. Error code "+response.code(),Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(mContext,"Sorry an error was encountered",Toast.LENGTH_LONG).show();

            }
        });


    }



    void confirmCompletion(final String string, String id)
    {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(mContext);

        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.confirmCompletion(string,id);
        login.enqueue(new Callback<UserModule>() {
            @Override
            public void onResponse(Response<UserModule> response, Retrofit retrofit) {
                UserModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {
                    if(user.getResponseCode() == 200)
                    {

                        Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(mContext,MainActivity.class);
                        mContext.startActivity(intent);
                        //mContext.finish();
                    }
                    else
                    {

                        Toast.makeText(mContext,user.getResponseMessage(),Toast.LENGTH_LONG).show();

                    }
                }
                else
                {

                    Toast.makeText(mContext,"Sorry an error was encountered. Error code "+response.code(),Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(mContext,"Sorry an error was encountered",Toast.LENGTH_LONG).show();

            }
        });


    }



}
