package com.mygotoservices.mygotoandroid.Api;

import com.mygotoservices.mygotoandroid.Modules.ArtisanModule;
import com.mygotoservices.mygotoandroid.Modules.BookingModule;
import com.mygotoservices.mygotoandroid.Modules.CategoryModule;
import com.mygotoservices.mygotoandroid.Modules.DashboardModule;
import com.mygotoservices.mygotoandroid.Modules.ListingModule;
import com.mygotoservices.mygotoandroid.Modules.MyBooking;
import com.mygotoservices.mygotoandroid.Modules.NotificationModule;
import com.mygotoservices.mygotoandroid.Modules.OrderPlacementModule;
import com.mygotoservices.mygotoandroid.Modules.PlaceModule;
import com.mygotoservices.mygotoandroid.Modules.RetailerModule;
import com.mygotoservices.mygotoandroid.Modules.SliderModule;
import com.mygotoservices.mygotoandroid.Modules.UserModule;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit2.http.Multipart;

/**
 * Created by admin on 15/05/2018.
 */

public interface ApiEndpoint {


    @GET("mobile/places")
    Call<List<PlaceModule>> listPlaces();

    @GET("mobile/categories")
    Call<List<CategoryModule>> listCategories();

    @GET("mobile/dashboard")
    Call<DashboardModule> dashboard();


    @FormUrlEncoded
    @POST("mobile/confirm-estimate")
    Call<UserModule> confirmEstimate(@Field("booking_id") String string,@Field("notification_id") String notification_id);

    @FormUrlEncoded
    @POST("mobile/decline-estimate")
    Call<UserModule> declineEstimate(@Field("booking_id") String string,@Field("notification_id") String notification_id);

    @FormUrlEncoded
    @POST("mobile/confirm-completion")
    Call<UserModule> confirmCompletion(@Field("booking_id") String string,@Field("notification_id") String notification_id);



    @GET("mobile/notifications/{id}")
    Call<NotificationModule> getMyNotification(@Path(value = "id", encoded = true) int id);

    @FormUrlEncoded
    @POST("mobile/update-signal")
    Call<UserModule> updateSignal(@Field("user_id") int String, @Field("token") String start);

    @GET("mobile/provider-bookings/{id}/{date}")
    Call<List<BookingModule>> getBookings(@Path(value = "id", encoded = true) int id,@Path(value = "date", encoded = true) String date);

    @GET("listings/{id}")
    Call<ListingModule> getListing(@Path(value = "id", encoded = true) int id);

    @GET("mobile/my-bookings/{id}")
    Call<List<MyBooking>> myBookings(@Path(value = "id", encoded = true) int id);

    @GET("mobile/my-fav/{id}")
    Call<List<ArtisanModule>> getMyFav(@Path(value = "id", encoded = true) int id);



    @FormUrlEncoded
    @POST("mobile/send_request")
    Call<UserModule> sendArtisanRequest(@Field("location") String location, @Field("user_id") int user_id, @Field("landmark") String landmark,@Field("visible_name") String visible_name,@Field("description") String description,@Field("lon") double lon,@Field("lat") double lat);

    @FormUrlEncoded
    @POST("mobile/booking")
    Call<BookingModule> bookService(@Field("amount") double amount, @Field("user_id") int user_id, @Field("paymentChannel") String paymentChannel,@Field("network") String network,@Field("listing_id") int listing_id,@Field("start_time") String start_time,@Field("number_hours") int number_hours,@Field("description") String description,@Field("lon") String lon,@Field("lat") String lat,@Field("location") String location,@Field("landmark") String landmark,@Field("provider_id") int provider_id,@Field("name") String name,@Field("unit_price") double unit_price,@Field("total_price") double total_price);

    @FormUrlEncoded
    @POST("mobile/add-fav")
    Call<BookingModule> addToFav(@Field("user_id") int user_id, @Field("service_id") int service_id, @Field("bookings_id") int bookings_id);

    @FormUrlEncoded
    @POST("mobile/rate-booking")
    Call<BookingModule> rateBooking(@Field("user_id") int user_id, @Field("listing_id") int listing_id, @Field("rating") float rating, @Field("bookings_id") int bookings_id, @Field("description") String comment);

    @FormUrlEncoded
    @POST("mobile/booking")
    Call<BookingModule> bookmMService(@Field("amount") double amount, @Field("user_id") int user_id, @Field("paymentChannel") String paymentChannel,@Field("network") String network,@Field("listing_id") int listing_id,@Field("start_time") String start_time,@Field("number_hours") int number_hours,@Field("description") String description,@Field("lon") String lon,@Field("lat") String lat,@Field("location") String location,@Field("landmark") String landmark,@Field("provider_id") int provider_id,@Field("name") String name,@Field("unit_price") double unit_price,@Field("total_price") double total_price,@Field("phone") String phone,@Field("token") String token);

    @FormUrlEncoded
    @POST("mobile/search-artisan")
    Call<List<ArtisanModule>> searchArtisan(@Field("search") String search, @Field("lon") double lon, @Field("lat") double lat, @Field("categories") int category);



    @FormUrlEncoded
    @POST("mobile/login")
    Call<UserModule> login(@Field("email") String email, @Field("password") String password);



    @FormUrlEncoded
    @POST("mobile/register")
    Call<UserModule> register(@Field("email") String email, @Field("name") String name, @Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("mobile/search-retailer")
    Call<List<RetailerModule>> searchRetailer(@Field("search") String search, @Field("lon") double lon, @Field("lat") double lat, @Field("categories") int category);


    @FormUrlEncoded
    @POST("mobile/forgot")
    Call<UserModule> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("mobile/resend-forgot")
    Call<UserModule> resendForgotCode(@Field("email") String email);

    @FormUrlEncoded
    @POST("mobile/place-order")
    Call<OrderPlacementModule> placeOrder(@Field("order") JSONObject order, @Field("order_details") JSONArray orderDetail);


    @FormUrlEncoded
    @POST("mobile/reset-password")
    Call<UserModule> resetForgotPassword(@Field("forgot_code") String forgot_code, @Field("confirmPassword") String confirmPassword, @Field("password") String password);



    @FormUrlEncoded
    @POST("mobile/customer_service")
    Call<UserModule> customerService(@Field("user_id") int user_id,@Field("type") String type , @Field("subject") String subject ,@Field("body") String body);

    //update user image
    @Multipart
    @POST("user/user-pic")
    Call<SliderModule> changeDP(@Part("image") MultipartBody.Part file, @Part("user_id") RequestBody name);
}
