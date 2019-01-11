package mrzeinaliarya3624_mycar.mycarapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.os.Build.VERSION.SDK_INT;

public class MainActivity_Introduction extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText edtyp ;
    EditText edtchas ;
    EditText edtbrnd ;
    EditText edtcolor ;
    EditText edtplk ;
    EditText edtyer ;
    EditText edtful ;
    EditText edtclvo ;
    Button addibtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__introduction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("معرفی خودرو");

        if(SDK_INT>17){
            getWindow().peekDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        edtyp = findViewById(R.id.txt_intro_cartyp1);
        edtchas = findViewById(R.id.txt_intro_chassi);
        edtbrnd = findViewById(R.id.txt_intro_brand);
        edtcolor = findViewById(R.id.txt_intro_color);
        edtplk = findViewById(R.id.txt_intro_moto_plak);
        edtyer = findViewById(R.id.txt_intro_moto_yer);
        edtful = findViewById(R.id.txt_intro_moto_cyl_fultyp);
        edtclvo = findViewById(R.id.txt_intro_moto_moto_cap);
        addibtn =findViewById(R.id.btm_fintro_add) ;
        addibtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                /*
                if (edtyp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }
                if (edtchas.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }
                if (edtbrnd.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }
                if (edtcolor.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }
                if (edtplk.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }
                if (edtyer.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }
                if (edtful.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }
                if (edtclvo.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }
                */
                if (!edtyp.getText().toString().equals("")&& !edtchas.getText().toString().equals("")
                    && !edtbrnd.getText().toString().equals("") && !edtcolor.getText().toString().equals("")
                    && !edtplk.getText().toString().equals("") && !edtyer.getText().toString().equals("")
                    && !edtful.getText().toString().equals("")&& !edtclvo.getText().toString().equals("")){
                    //Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),edtyp.getText().toString(),Toast.LENGTH_LONG).show();
                    dbHandler dbh= new dbHandler(MainActivity_Introduction.this);
                    dbh.open();
                    dbh.insertCar(edtyp.getText().toString(),edtchas.getText().toString()
                            ,edtbrnd.getText().toString(),edtcolor.getText().toString()
                            ,edtplk.getText().toString(),edtyer.getText().toString()
                            ,edtful.getText().toString(),edtclvo.getText().toString());
                    Toast.makeText(getApplicationContext(),R.string.Inserted,Toast.LENGTH_LONG).show();
                    edtyp.getText().clear();
                    edtchas.getText().clear();
                    edtbrnd.getText().clear();
                    edtcolor.getText().clear();
                    edtplk.getText().clear();
                    edtyer.getText().clear();
                    edtful.getText().clear();
                    edtclvo.getText().clear();


                    //Toast.makeText(getApplicationContext(),dbh.displayCar(),Toast.LENGTH_LONG).show();
                    dbh.close();
                }
                else {
                    Toast.makeText(getApplicationContext(),R.string.ValidInput,Toast.LENGTH_LONG).show();
                }


            }
        });
/*
        dbHandler dbh= new dbHandler(this);
        dbh.open();
        Toast.makeText(getApplicationContext(),dbh.displayCar(),Toast.LENGTH_LONG).show();
        dbh.close();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

      //  NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity__introduction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
