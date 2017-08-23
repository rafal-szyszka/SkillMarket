package it.szyszka.skillmarket;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {

    private String[] categories = {"IT", "Catering", "Zdrowie", "IT"};
    private String[] shortDescriptions = {"Strona dla prowadzonej przezemnie działalności - usługi konserwacji. Zajmujemy się myciem okiem w wysokich budynkach, stacji benzynowych, etc.",
                                            "Organizacja imprez, chrzcin , urodzin itp. z nastawieniem na potrawy z grilla, (ale nie tylko, możliwość przygotowania dowolnego menu) w Twoim ogrodzie lub domu.sałatki i przekąski wraz z obsługą i zapewnieniem niezbędnego sprzętu.Warszawa i okolice.Konkurencyjne ceny.Wiadomość przez gumtree.",
                                            "Gabinet masażu, oferuję zabiegi relaksacyjne na całe ciało, oraz peelingi, masaż; klasyczny, sportowy, izometryczny, punktowy, jak również masaż bańkami chińskimi, oraz gorącymi kamieniami...kontakt telefoniczny..... Jeśli chcesz poprawic swoja sylwetkę jest propozycja serii masaży wyszczuplająco/ujędrniających w dobrej cenie:)",
                                            "Wdrażam sklepy internetowe, które zaprojektowane są przez najlepszych programistów i pasjonatów ecommerce. Jeśli chcesz dodatkowo sprzedawać swoje produkty na allegro otrzymasz szablon aukcji,  który będzie zintegrowany z Twoim sklepem.  Wystawiasz tyle produktów ile chcesz,  na bieżąco monitorując sprzedaż. Dzięki prezentacji wykresów dowiesz się jakie produkty sprzedają się najlepiej, jak wygląda ogólna sprzedaż,  jakim produktom klienci poświęcają najwięcej czasu."};

    private String[] titles = {"Zlecę wykonanie strony www", "Catering dla firm i osób prywatnych", "Potrzebujesz masażu? Nie szukaj dalej", "Sklepy internetowe, szybko i solidnie!"};
    private String[] payments = {"500PLN - 1500PLN", "W zależności od wielkości imprezy", "Szukam instruktora gry na gitarze", "Szukam usług remontowych"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_b_main_user_profile);

        /*View row = findViewById(R.id.info_form_first_row);

        TextView txt = (TextView) row.findViewById(R.id.utils_form_row_title);
        txt.setText("Pseudonim");
        txt = (TextView) row.findViewById(R.id.utils_form_row_value);
        txt.setText("SQUIER");

        row = findViewById(R.id.info_form_second_row);
        txt = row.findViewById(R.id.utils_form_row_title);
        txt.setText("Adres");
        txt = row.findViewById(R.id.utils_form_row_value);
        txt.setText("Wrocław, Sudecka 147/7");

        row = findViewById(R.id.info_form_third_row);
        txt = row.findViewById(R.id.utils_form_row_title);
        txt.setText("Numer telefonu");
        txt = row.findViewById(R.id.utils_form_row_value);
        txt.setText("535-981-325");

        row = findViewById(R.id.info_form_fourth_row);
        txt = row.findViewById(R.id.utils_form_row_title);
        txt.setText("Adres e-mail");
        txt = row.findViewById(R.id.utils_form_row_value);
        txt.setText("rafal@szyszka.it");*/

//        ImageView img = view.findViewById(R.id.user_info_img);
//        TextView txt = view.findViewById(R.id.user_info_text);
//
//        txt.setText("rafal@szyszka.it");
//
//        view = findViewById(R.id.user_profile_location);
//        img = view.findViewById(R.id.user_info_img);
//        img.setImageResource(R.drawable.ic_location);
//        txt = view.findViewById(R.id.user_info_text);
//        txt.setText("Wrocław");
//
//        view = findViewById(R.id.user_profile_phone);
//        img = view.findViewById(R.id.user_info_img);
//        img.setImageResource(R.drawable.ic_phone);
//        txt = view.findViewById(R.id.user_info_text);
//        txt.setText("535-981-325");
//
//        ArrayList<Offer> offers = new ArrayList<>();
//        populateArray(offers);
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.user_profile_offers);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setNestedScrollingEnabled(false);
//
//        recyclerView.setAdapter(new MyAdapter(offers, recyclerView));

    }

    private void populateArray(ArrayList<Offer> offers) {
        Offer offer;
        for(int i = 0; i < 4; i++) {
            offer = new Offer();
            offer.attachOwner("Rafał Szyszka");
            offer.setOwnerEmail("rafal@szyszka.it");
            offer.writeTitle(titles[i]);
            offer.writeShortDescription(shortDescriptions[i]);
            offer.setCategory(categories[i]);
            offer.attachPaymentDetails(payments[i]);
            offers.add(offer);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter {

        private ArrayList<Offer> offers;
        private RecyclerView recyclerView;

        private class OfferViewHolder extends RecyclerView.ViewHolder {
            public TextView owner, ownerEmail, title, description, paymentDetails;

            public OfferViewHolder(View parent) {
                super(parent);
                owner = parent.findViewById(R.id.offer_item_owner);
                ownerEmail  =parent.findViewById(R.id.offer_item_owner_email);
                title = parent.findViewById(R.id.offer_item_title);
                description = parent.findViewById(R.id.offer_item_short_description);
                paymentDetails = parent.findViewById(R.id.offer_item_payment);
            }

        }

        public MyAdapter(ArrayList<Offer> offers, RecyclerView recyclerView) {
            this.offers = offers;
            this.recyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.module_c_offer_item, parent, false);

            return new OfferViewHolder(view);

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            OfferViewHolder myHolder = (OfferViewHolder) holder;
            Offer offer = offers.get(position);

            myHolder.owner.setText("Rafał Szyszka");
            myHolder.ownerEmail.setText("rafal@szyszka.it");
            myHolder.title.setText(offer.showTitle());
            myHolder.description.setText(offer.showDescription(false));
            myHolder.paymentDetails.setText(offer.showPaymentDetails());

        }

        @Override
        public int getItemCount() {
            return offers.size();
        }
    }
}
