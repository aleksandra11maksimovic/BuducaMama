package elab3.com.buducamama2.Majka;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import elab3.com.buducamama2.R;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener{

    TextView pitanje1;
    TextView odgovor1;
    int otvoren1=0;
    TextView pitanje2;
    TextView odgovor2;
    int otvoren2=0;
    TextView pitanje3;
    TextView odgovor3;
    int otvoren3=0;
    TextView pitanje4;
    TextView odgovor4;
    int otvoren4=0;
    TextView pitanje5;
    TextView odgovor5;
    int otvoren5=0;
    TextView pitanje6;
    TextView odgovor6;
    int otvoren6=0;
    TextView pitanje7;
    TextView odgovor7;
    int otvoren7=0;
    TextView pitanje8;
    TextView odgovor8;
    int otvoren8=0;
    TextView pitanje9;
    TextView odgovor9;
    int otvoren9=0;
    TextView pitanje10;
    TextView odgovor10;
    int otvoren10=0;
    TextView pitanje11;
    TextView odgovor11;
    int otvoren11=0;
    TextView pitanje12;
    TextView odgovor12;
    int otvoren12=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        pitanje1=(TextView)findViewById(R.id.pitanje1);
        odgovor1=(TextView)findViewById(R.id.odgovor1);
        pitanje1.setOnClickListener(this);
        pitanje2=(TextView)findViewById(R.id.pitanje2);
        odgovor2=(TextView)findViewById(R.id.odgovor2);
        pitanje2.setOnClickListener(this);
        pitanje3=(TextView)findViewById(R.id.pitanje3);
        odgovor3=(TextView)findViewById(R.id.odgovor3);
        pitanje3.setOnClickListener(this);
        pitanje4=(TextView)findViewById(R.id.pitanje4);
        odgovor4=(TextView)findViewById(R.id.odgovor4);
        pitanje4.setOnClickListener(this);
        pitanje5=(TextView)findViewById(R.id.pitanje5);
        odgovor5=(TextView)findViewById(R.id.odgovor5);
        pitanje5.setOnClickListener(this);
        pitanje6=(TextView)findViewById(R.id.pitanje6);
        odgovor6=(TextView)findViewById(R.id.odgovor6);
        pitanje6.setOnClickListener(this);
        pitanje7=(TextView)findViewById(R.id.pitanje7);
        odgovor7=(TextView)findViewById(R.id.odgovor7);
        pitanje7.setOnClickListener(this);
        pitanje8=(TextView)findViewById(R.id.pitanje8);
        odgovor8=(TextView)findViewById(R.id.odgovor8);
        pitanje8.setOnClickListener(this);
        pitanje9=(TextView)findViewById(R.id.pitanje9);
        odgovor9=(TextView)findViewById(R.id.odgovor9);
        pitanje9.setOnClickListener(this);
        pitanje10=(TextView)findViewById(R.id.pitanje10);
        odgovor10=(TextView)findViewById(R.id.odgovor10);
        pitanje10.setOnClickListener(this);
        pitanje11=(TextView)findViewById(R.id.pitanje11);
        odgovor11=(TextView)findViewById(R.id.odgovor11);
        pitanje11.setOnClickListener(this);
        pitanje12=(TextView)findViewById(R.id.pitanje12);
        odgovor12=(TextView)findViewById(R.id.odgovor12);
        pitanje12.setOnClickListener(this);


    }
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.pitanje1:
                if(otvoren1==0){
                    promeniStilPitanjeOtvoreno(pitanje1);
                    promeniStilOdgovorOtvoren(odgovor1);
                    odgovor1.setText("Kratak odgovor bi bio sme, ali kod poodmakle trudnoće prosto je nemoguće spavati na stomaku. Trudnica treba da se dobro naspava u položaju koji njoj odgovara. Beba je zaštićena plodovom vodom i nema razloga za brigu.\n" +
                            "Ipak, preporučeni položaj za spavanje u poslednjem trimestru trudnoće je na levoj strani sa levom nogom skupljenom ili ispruženom, a desnom prebačenom preko leve sa jastukom između nogu. Ovaj položaj omogućava dobar protok krvi do posteljice i podstiče izlučivanje nepotrebnih materija preko bubrega iz organizma trudnice.\n" +
                            "Treba imati na umu da svako u toku spavanja promeni nekoliko puta položaj, a pogotovo trudnica, tako da glavna preporuka za spavanje je dobar odmor ma koji to položaj bio.\n");
                    otvoren1=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje1);
                    promeniStilOdgovorZatvoren(odgovor1);
                    odgovor1.setText("");
                    otvoren1=0;
                }

                break;
            case R.id.pitanje2:
                if(otvoren2==0){
                    promeniStilPitanjeOtvoreno(pitanje2);
                    promeniStilOdgovorOtvoren(odgovor2);
                    odgovor2.setText("Namirnice koje treba izbegavati, naročito u toku trudnoće jer mogu štetno delovati na plod i na trudnicu su:\n" +
                            "\t• nepasterizovani mlečni proizvodi – mekani sirevi, bri, plavi sir, kamember, koziji sir. Ovi proizvodi mogu sadržati bakteriju listeriju a ona može izazvati infekciju.\n" +
                            "\t• sirovo meso i mesne prerađevine – takođe mogu izazvati infekciju listerijom. Dobro termički obrađeno meso ne treba izbegavati ali mesne prerađevine (viršle, kobasice, paštete, šunku) treba.\n" +
                            "\t• suši – može sadržati bakterije koje mogu izazvati trovanje.\n" +
                            "\t• živa jaja, rovita – mogu biti zaražena bakterijom salmonelom, dobro termički obrađena su itekako korisna.\n" +
                            "\t• biljni čajevi – oprez! Nisu sve biljke korisne a neke nisu ni bezazlene!\n" +
                            "\t• kofein – kafa nije štetna u razumnim količinama (1 šolja dnevno).\n" +
                            "\t• alkoholna pića prolaze kroz placentu, štetno utiču na zdravlje trudnice i ploda.\n" +
                            "\t• gazirana pića – sadrže previše šećera, veštačkih boja i aroma.\n");
                    otvoren2=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje2);
                    promeniStilOdgovorZatvoren(odgovor2);
                    odgovor2.setText("");
                    otvoren2=0;
                }
                break;
            case R.id.pitanje3:
                if(otvoren3==0){
                    promeniStilPitanjeOtvoreno(pitanje3);
                    promeniStilOdgovorOtvoren(odgovor3);
                    odgovor3.setText("Ne možete imati menstruaciju dok ste u drugom stanju." +
                            "Neke žene imaju vaginalno krvarenje u toku trudnoće koje po nekim karakteristikama podseća na menstrualno, ali sa njim nema nikakve veze. Ovakav vid vaginalnog krvarenja  potiče od drugog uzroka." +
                            "Da pojednostavimo, menstruaciju imate samo kada niste u drugom stanju. \n");
                    otvoren3=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje3);
                    promeniStilOdgovorZatvoren(odgovor3);
                    odgovor3.setText("");
                    otvoren3=0;
                }
                break;
            case R.id.pitanje4:
                if(otvoren4==0){
                    promeniStilPitanjeOtvoreno(pitanje4);
                    promeniStilOdgovorOtvoren(odgovor4);
                    odgovor4.setText("Krvarenje u toku trudnoće se dešava iz više različitih razloga, neki od njih su ozbiljni, a neki nisu. Neke žene u ranoj fazi trudnoće imaju bledo ili tačkasto krvarenje i zato često pomisle da je u pitanju mesečni ciklus. Niko nije potvrdio tačan uzrok ovakvih krvarenja, ali se pretpostavlja da se krvarenje javlja kao znak implantacije oplođene jajne ćelije unutar materice i ovo krvarenje je veoma svetlije od onog menstrualnog i traje dan ili dva.\n" +
                            "\t•Takođe, možete krvariti u tačkicama nakon Papa testa, vaginalnog pregleda ili nakon seksualnog odnosa. Generalno, krvarenje u toku trudnoće je moguće objasniti i većom količinom krvi koja tada prolazi kroz matericu.\n" +
                            "\t•Krvarenje, međutim, može biti i znak nečega lošeg - infekcije, problema sa placentom, pobačaja ili ektopične trudnoće, i ovo su ozbiljna zdravstvena stanja koja su opasna po život.\n" +
                            "\t•Ukoliko ste primetili krvarenje odmah se konsultujte sa lekarom, čak i ako je krvarenje prestalo. Većina žena koje pomalo krvare tokom trudnoće nemaju komplikacije prilikom porođaja, ali je potreban razgovor sa lekarom kako biste eliminisali sve loše opcije.\n" +
                            "\t•Ako pored krvarenja osećate bilo kakav bol, u bilo kom delu tela, istog trenutka pozovite lekara.\n");
                    otvoren4=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje4);
                    promeniStilOdgovorZatvoren(odgovor4);
                    odgovor4.setText("");
                    otvoren4=0;
                }
                break;
            case R.id.pitanje5:
                if(otvoren5==0){
                    promeniStilPitanjeOtvoreno(pitanje5);
                    promeniStilOdgovorOtvoren(odgovor5);
                    odgovor5.setText("Gorušica se može javiti u početku trudnoće, ali je najizraženija u poslednjem trimestru trudnoće. Ne preporučuje se upotreba lekova (antacida) jer oni mogu da spreče iskorištavanje gvožđa iz hrane, osim kada lekar proceni da je neophodno.\n" +
                            "Preporuka je da se uzimaju manji, a češći obroci, jogurt, grickati bademe, dvopek. Jesti polako, odmarati se na uzdignutom uzglavlju.\n");
                    otvoren5=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje5);
                    promeniStilOdgovorZatvoren(odgovor5);
                    odgovor5.setText("");
                    otvoren5=0;
                }
                break;
            case R.id.pitanje6:
                if(otvoren6==0){
                    promeniStilPitanjeOtvoreno(pitanje6);
                    promeniStilOdgovorOtvoren(odgovor6);
                    odgovor6.setText("Naravno, vaša beba je potpuno bezbedna! To majušno stvorenje je dobro ušuškano i zaštićeno, jer je okruženo amnionskom tečnošću, unutar čvrstog mišićnog zida materice. Od spoljašnjeg sveta ga odvaja i štiti sluzav čep, koji se nalazi u grliću materice, pozicioniran na bezbednoj distanci od vagine.");
                    otvoren6=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje6);
                    promeniStilOdgovorZatvoren(odgovor6);
                    odgovor6.setText("");
                    otvoren6=0;
                }
                break;
            case R.id.pitanje7:
                if(otvoren7==0){
                    promeniStilPitanjeOtvoreno(pitanje7);
                    promeniStilOdgovorOtvoren(odgovor7);
                    odgovor7.setText("Bilo kakvo krvarenje - naravno, ne govorimo o rozikastom iscetku, jeste znak da morate da odete na pregled kod vašeg ginekologa. I to iz jednostavnog razloga - da bi se ustanovilo da li je krvarenje bezazleno ili ne. Ukoliko se radi o posteljici koja delimično ili u celosti prekriva unutrašnje ušće materice (placenta previa), onda seks nije dozvoljen.");
                    otvoren7=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje7);
                    promeniStilOdgovorZatvoren(odgovor7);
                    odgovor7.setText("");
                    otvoren7=0;
                }
                break;
            case R.id.pitanje8:
                if(otvoren8==0){
                    promeniStilPitanjeOtvoreno(pitanje8);
                    promeniStilOdgovorOtvoren(odgovor8);
                    odgovor8.setText("Kontrakcije su \"prave\" - porođajne:\n" +
                            "- ukoliko su vremenom jače i češće (na pet do deset minuta)\n" +
                            "- ako su duže, traju i do jednog minuta\n" +
                            "- u slučaju da osećate isti intenzitet kontrakcija iako ste promenili položaj ili se odmarate\n" +
                            "- dok su prisutne, ne možete normalno da razgovarate\n" +
                            "- ako započinju u gornjem delu stomaka ili krstima, a taj osećaj se prenosi prema napred\n");
                    otvoren8=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje8);
                    promeniStilOdgovorZatvoren(odgovor8);
                    odgovor8.setText("");
                    otvoren8=0;
                }
                break;
            case R.id.pitanje9:
                if(otvoren9==0){
                    promeniStilPitanjeOtvoreno(pitanje9);
                    promeniStilOdgovorOtvoren(odgovor9);
                    odgovor9.setText("Zdravstvena dokumentacija sadrži\n\n" +
                            "1. lična karta \n" +
                            "2. zdravstvena knjižica \n" +
                            "3. potvrda o krvnoj grupi i Rh faktoru \n" +
                            "4. nalaz krvi i urina (ako se ide na carski rez)\n" +
                            "5. potvrda o vaginalnom i cervikalnom brisu\n" +
                            "6. uput za porodilište iz doma zdravlja\n\n" +
                            "Preporučuje se da se u torbu za porodilište spakuju sledeće stvari:\n\n" +
                            "- sobne papuče i plastične papuče ili japanke (za tuširanje)\n" +
                            "- jednokratne, mrežaste, gaćice (10 komada)\n" +
                            "- mrežasti ulošci (5 paketa)\n" +
                            "- pamučni brusthalter za dojenje \n" +
                            "- ulošci za dojenje (10 komada)\n" +
                            "- kućni ogrtač ili frotir sa džepovima\n" +
                            "- dve spavaćice koje se otvaraju na prednjoj strani zbog dojenja (proveriti sa  porodilištem da li možete da ih zadržite za stalno korišćenje) \n" +
                            "- 1 veliki i 2 manja peškira\n" +
                            "- toalet papir, vlažne maramice\n" +
                            "- pribor za ličnu higijenu \n" +
                            "- čarape ili soknice (3 para)\n" +
                            "- dugačka pamučna majica (1-2 komada)\n\n" +
                            "Ostale stvari nisu neophodne, ali su veoma praktične:\n\n" +
                            "- mobilni telefon, punjač, telefonska kartica, MP3 player, foto aparat\n" +
                            "- blok, olovka\n" +
                            "- plastične čaše  \n" +
                            "- flaširana voda za piće \n" +
                            "- plastične kese (nekoliko komada za prljav veš koji se šalje kući)\n");
                    otvoren9=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje9);
                    promeniStilOdgovorZatvoren(odgovor9);
                    odgovor9.setText("");
                    otvoren9=0;
                }
                break;
            case R.id.pitanje10:
                if(otvoren10==0){
                    promeniStilPitanjeOtvoreno(pitanje10);
                    promeniStilOdgovorOtvoren(odgovor10);
                    odgovor10.setText("Trudnice mogu da se izlažu suncu najduže 1 sat u vremenu do 10 pre podne i od 17 posle podne, u zavisnosti od tipa kože. Na kožu obavezno naneti deblji sloj kreme sa visokim zaštitnim faktorom, nositi šešir sa širokim obodom i naočare za zaštitu od UV zračenja, piti dosta vode (oko 2 l dnevno). Poneti bocu sa raspršivačem (kao za orošavanje cveća) za rashlađivanje otvorenih delova tela. Plivati bez naprezanja u vodi koja je čista i umereno topla. Obavezno se istuširati po izlasku iz vode i presvući kupaći kostim.");
                    otvoren10=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje10);
                    promeniStilOdgovorZatvoren(odgovor10);
                    odgovor10.setText("");
                    otvoren10=0;
                }
                break;
            case R.id.pitanje11:
                if(otvoren11==0){
                    promeniStilPitanjeOtvoreno(pitanje11);
                    promeniStilOdgovorOtvoren(odgovor11);
                    odgovor11.setText("Jedan od tretmana koji nisu preporučljivi u trudnoći je tetoviranje, pre svega zbog moguće infekcije koja bi mogla naškoditi bebinom zdravlju. Tetovaže se tokom trudnoće zbog istezanja kože obično deformišu.\n" +
                            "Ako trudnica želi da se tetovira preporuka je sačekati dok se ne završi dojenje, a i tada treba voditi računa o sterilnosti pribora u salonima za tetovažu. Ne zaboravite da se pod tetoviranjem podrazumeva i tetoviranje obrva.\n");
                    otvoren11=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje11);
                    promeniStilOdgovorZatvoren(odgovor11);
                    odgovor11.setText("");
                    otvoren11=0;
                }
                break;
            case R.id.pitanje12:
                if(otvoren12==0){
                    promeniStilPitanjeOtvoreno(pitanje12);
                    promeniStilOdgovorOtvoren(odgovor12);
                    odgovor12.setText("Nije opasno. Vakcinacija kućnih ljubimaca, kontrola kod veterinara i pridržavanje osnovnih higijenskih pravila su neophodni preduslovi prevencije bolesti koje bi mogle preneti kućni ljubimci.\n" +
                            "Ako imate mačku i planirate trudnoću, onda bi bilo neophodno da uradite imunološke testove na toksoplazmozu. Testovi se rade iz uzorka krvi. Oko 10-15 % žena je steklo imunitet na toksoplazmozu (posebno ako su duže vreme držale mačku u kući), što znači da su prethodno bile zaražene i da su se stvorila antitela protiv ovog parazita, pa se može reći da je trudnica imuna na toksoplazmozu i nema opasnosti za bebu. Imunitet je, praktično, doživotan.\n" +
                            "U protivnom, dobar savet je da u toku trudnoće ili neposredno pre nje ne dovodite novu mačku u kuću.\n" +
                            "Ukoliko sređujete vrt ili baštu, obavezno koristite zaštitne rukavice. Promenu prostirke i sređivanje mačjeg legla prepustite nekom od ukućana.\n" +
                            "Pažnja!!! Kada se porodite, ne ostavljajte kućnog ljubimca samog sa bebom bez nadzora odrasle osobe!\n");
                    otvoren12=1;
                } else{
                    promeniStilPitanjeZatvoreno(pitanje12);
                    promeniStilOdgovorZatvoren(odgovor12);
                    odgovor12.setText("");
                    otvoren12=0;
                }
                break;
            default:
                break;
        }

    }
    public void promeniStilPitanjeZatvoreno(TextView textView){
        textView.setTypeface(null,Typeface.NORMAL);

        textView.setBackgroundColor(Color.TRANSPARENT);


    }
    public void promeniStilPitanjeOtvoreno(TextView textView){
        textView.setBackgroundColor(Color.parseColor("#C7C7C3"));
        textView.setTypeface(null,Typeface.BOLD);

    }
    public void promeniStilOdgovorZatvoren(TextView textView){
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,6);
        textView.setPadding(10,0,10,0);
        textView.setBackgroundColor(Color.parseColor("#3A3E60"));

    }
    public void promeniStilOdgovorOtvoren(TextView textView){

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        textView.setPadding(12,0,12,0);
        textView.setBackgroundColor(Color.parseColor("#C7C7C3"));
    }


}
