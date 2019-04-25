Ok, det här är en vag prototyp som existerar för att demonstrera två saker: att vi kan använda oss av vyer, och att vi kan hämta data till appen från servern medelst rest, json och annat tjosan. Det sistnämnda har JD skött och inget jag kan uttala mig om (så den enda kommentaren är "magi"), men vyer och nuvarande prototyp lite kort! Allting jag kommenterat på finns i mapparna PVT15_5_frontend\app\src\main\java\se\su\dsv\pvt\helloworldapp OCH PVT15_5_frontend\app\src\main\res\layout. Resterande kod - och lite mer - står vår pina colada-kamrat för.  

Den enda förkunskap som borde krävas är den som finns här: https://developer.android.com/guide/components/fundamentals

I övrigt bör det inte vara alltför förvirrande, men jag har försökt kommentera lite i koden också.

En vy är en egen skärm: när du trycker på Profile eller Map i föreliggande app så hoppar du till en annan sida. Varje vy består av en Java-fil och en layout-fil: ett exempel här är filen Profile.java som har layoutfilen activity_profile.xml. 

I Java-filerna så kan man se hur detta fungerar på rad 67 och neråt i Profile.java; i motsvarande .xml-fil så deklareras alla fält och whatevernot. 
Ett annat exempel på kopplingen .xml till .java finns i activity_main.xml: här finns attributet "android:onClick="gotoProfile", vilket står under en "<Button ". Detta innebär att vid klick på specifikt denna knapp så aktiveras metoden gotoProfile() i den java-fil som laddat in layouten (vilket som bekant sker i metoden onCreate(), genom setContentView()). 
