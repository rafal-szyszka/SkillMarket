package it.szyszka.skillmarket;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        setContentView(R.layout.module_b_user_profile);

        View view = findViewById(R.id.user_profile_email);
        ImageView img = view.findViewById(R.id.user_info_img);
        TextView txt = view.findViewById(R.id.user_info_text);

        txt.setText("rafal@szyszka.it");

        view = findViewById(R.id.user_profile_location);
        img = view.findViewById(R.id.user_info_img);
        img.setImageResource(R.drawable.ic_location);
        txt = view.findViewById(R.id.user_info_text);
        txt.setText("Wrocław");

        ArrayList<Offer> offers = new ArrayList<>();
        populateArray(offers);

        ListView list = (ListView) findViewById(R.id.user_profile_offers);

        list.setAdapter(new ArrayAdapter<Offer>(SignInActivity.this, 0, offers){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = LayoutInflater.from(SignInActivity.this).inflate(R.layout.module_c_offer_item, parent, false);
                }

                ((TextView)convertView.findViewById(R.id.offer_item_owner)).setText(getItem(position).showOfferOwner());
                ((TextView)convertView.findViewById(R.id.offer_item_owner_email)).setText(getItem(position).showOfferOwnerEmail());
                ((TextView)convertView.findViewById(R.id.offer_item_title)).setText(getItem(position).showTitle());
                ((TextView)convertView.findViewById(R.id.offer_item_category)).setText(getItem(position).showCategory());
                ((TextView)convertView.findViewById(R.id.offer_item_short_description)).setText(getItem(position).showDescription(false));
                ((TextView)convertView.findViewById(R.id.offer_item_payment)).setText(getItem(position).showPaymentDetails());

                convertView.setPadding(20,20,20,20);

                return convertView;
            }
        });


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
}
